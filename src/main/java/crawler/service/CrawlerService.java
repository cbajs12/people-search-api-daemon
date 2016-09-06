package crawler.service;


import crawler.dao.CrawlerDao;
import crawler.vo.*;
import org.json.JSONObject;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.TimeUnit;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class CrawlerService {
    private static Logger logger = LoggerFactory.getLogger(CrawlerService.class);
    public static boolean 	threadFlag 	=  true;
    private static Pattern patternDomainName;
    private Matcher matcher;
    private static final String DOMAIN_NAME_PATTERN = "([a-zA-Z0-9]([a-zA-Z0-9\\-]{0,61}[a-zA-Z0-9])?\\.)+[a-zA-Z]{2,6}";
    private CrawlerDao crawlerDao = new CrawlerDao();
    private ChromeDriver driver;

    static {
        patternDomainName = Pattern.compile(DOMAIN_NAME_PATTERN);
    }

    private CrawlerService(){
        if(System.getProperty("os.name").toLowerCase().contains("mac")){
            System.setProperty("webdriver.chrome.driver", "/Users/jeonjiseong/Git/people-search-api-daemon/excute/chromedriver"); // change path
        }
//        else if(System.getProperty("os.name").toLowerCase().contains("linux")){
//            System.setProperty("webdriver.chrome.driver", "/Users/jeonjiseong/Git/people-search-api-daemon/excute/chromedriver");
//        }
    }

    public void startService(){
        baseCrawler();
    }

    /*
        Crawling naver-people-search based on Base Table data
     */
    public void baseCrawler(){
        driver = new ChromeDriver();    // start driver
        driver.manage().timeouts().pageLoadTimeout(10, TimeUnit.SECONDS);
        driver.manage().timeouts().setScriptTimeout(20, TimeUnit.SECONDS);

        try {
            List baseVOs = crawlerDao.getBaseList();
            logger.debug("baseVOs {}", baseVOs);
            BaseVO baseVO;
            String url = "http://people.search.naver.com/search.naver?where=nexearch&ie=utf8&query=";
            driver.get(url);
            for(int i=0; i<baseVOs.size(); ++i){
                baseVO = (BaseVO)baseVOs.get(i);
                baseLoadSequence(baseVO.getBase_name());
            }
        } catch (Exception e) {
            logger.debug("baseCrawler SQL ERROR");
            e.printStackTrace();
        }
        driver.close();
    }

    /*
        Crawling naver-people-search based on Name Table data
     */
    public void detailCrawler(){
        try {
            List nameVOs = crawlerDao.getNameList();
            logger.debug("nameVOs {}", nameVOs);
            NameVO nameVO;

            for(int i=0; i<nameVOs.size(); ++i){
                nameVO = (NameVO)nameVOs.get(i);

            }

        } catch (Exception e) {
            logger.debug("detailCrawler SQL ERROR");
            e.printStackTrace();
        }
    }

    public void activityCrawler(){

    }

    /*
        Get names from text and add it into DB
     */
    public void initCrawlerData(){
        try {
            BufferedReader reader = new BufferedReader(new InputStreamReader(getClass().getResourceAsStream("/initdata/people_keyword.txt")));
            String line;
            BaseVO baseVO = new BaseVO();

            while ((line = reader.readLine()) != null)
            {
                baseVO.setBase_name(line);
                baseVO.setBase_code("CR001");
                logger.debug(line);
                if(!crawlerDao.setBaseData(baseVO)){
                    continue;
                }
            }
        }
        catch (IOException e)
        {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    /*
        Crawling naver-people-search using selenium browser and add the data into DB
     */
    public void baseLoadSequence(String name){
        NameVO nameVO = new NameVO(name, "CR001");
        WebElement query = driver.findElement(By.id("nx_query"));
        query.sendKeys(name);
        query.submit();

        List<WebElement> results;
        WebElement data;
        String paging = " ";

        while(paging != null) {
            try {
                results = driver.findElements(By.cssSelector("div.who"));
                for (int i = 0; i < results.size(); ++i) {
                    data = results.get(i).findElement(By.xpath(".//a"));
                    nameVO.setName_os(data.getAttribute("href"));           // 중복 데이터에 대한 처리 필요
                    crawlerDao.setNameData(nameVO);
//                logger.debug("data {}", data.getAttribute("href"));
                }
            }catch (InterruptedException e){
                logger.debug("BaseLoad SQL Error", e);
            }

            try {
                paging = driver.findElement(By.cssSelector("a.next")).getText();
                driver.findElement(By.cssSelector("a.next")).click();
//                waitForLoad(driver);
                Thread.sleep(1000);
            }catch (Exception e){
                paging = null;
            }
        }
    }

    /*
        Wait until document state is complete using javascript injection
     */
    public void waitForLoad(final WebDriver driver){
        ExpectedCondition<Boolean> pageLoadCondition = new ExpectedCondition<Boolean>() {
            public Boolean apply(WebDriver webDriver) {
                return ((JavascriptExecutor)driver).executeScript("return document.readyState").equals("complete");
            }
        };
        WebDriverWait wait = new WebDriverWait(driver, 30);
        wait.until(pageLoadCondition);
    }

    /*
        Crawling naver-people-search using JSoup and add the data into DB
     */
    public void detailLoadSequence(String os){//
        String osNumber = getOsFromUrl("os");
        DetailVO detailVO = new DetailVO();
//        String url = "http://people.search.naver.com/search.naver?where=nexearch&sm=tab_ppn&query=안성기&os=94590&ie=utf8&key=PeopleService";

        try{
            Document doc = Jsoup
                    .connect(os)
                    .userAgent("Mozilla/5.0 (Windows NT 6.1; WOW64) AppleWebKit/535.2 (KHTML, like Gecko) Chrome/15.0.874.120 Safari/535.2")
                    .timeout(30000).get();

            Elements profileWrap = doc.getElementsByClass("profile_wrap");

            String name = profileWrap.select("dt.name").text();
            String job = profileWrap.select("dt.sub").text();
            String age = profileWrap.select("dd.dft").text();
            Elements dts = profileWrap.select("dl.dsc").select("dt");
            Elements dds = profileWrap.select("dl.dsc").select("dd");
            JSONObject obj = new JSONObject();

            if((dds != null || dds.size() != 0) && (dts !=null || dts.size() != 0)){
                Iterator<Element> dtsIterator = dts.iterator();
                Iterator<Element> ddsIterator = dds.iterator();
                while (ddsIterator.hasNext() && dtsIterator.hasNext()){
                    Element dt = dtsIterator.next();
                    Element dd = ddsIterator.next();

                    if(dt.text().equals("가족")){
                        if(dd.select("a").attr("href") != null || dd.select("a").attr("href").length() !=0){
                            Elements links = dd.select("a");
                            JSONObject familyObj = new JSONObject();
                            for(Element e : links) {
                                familyObj.append(e.attr("href"), e.text());
//                                logger.debug("href {}", e.attr("href"));
//                                logger.debug("name {}", e.text());
                            }
                            detailVO.setFm_urls(familyObj.toString());
                        }
                    }
                    obj.append(dt.text(), dd.text());
                }
            }

//            logger.debug("json {}", obj.toString());
//            JSONObject object = new JSONObject(jsonString);

            detailVO.setDetail_os(osNumber);
            detailVO.setDetail_name(name);
            detailVO.setDetail_job(job);
            detailVO.setAge(age);
            detailVO.setProfile(obj.toString());

            crawlerDao.setDetailData(detailVO);

            Elements recordWrap = doc.getElementsByClass("record_wrap");

            if(recordWrap != null || recordWrap.size() != 0){
                Elements blind = recordWrap.select("div.record");

                dts = blind.select("dt");
                dds = blind.select("dd");

                Iterator<Element> dtsIterator = dts.iterator();
                Iterator<Element> ddsIterator = dds.iterator();

                CareerVO careerVO = new CareerVO(osNumber);
                while (ddsIterator.hasNext() && dtsIterator.hasNext()) {
                    Element dt = dtsIterator.next();
                    Element dd = ddsIterator.next();

                    careerVO.setCareer_dt(dt.text());
                    careerVO.setDescription(dd.text());

                    crawlerDao.setCareerData(careerVO);
                }
            }

        }catch (Exception e){
            e.printStackTrace();
        }
    }

    /*
        Get os parameter from url
     */
    public String getOsFromUrl(String url){//String os
        String[] params = url.split("&");
        String name;
        for(String param : params){
            name = param.split("=")[0];
            if(name.equals("os")){
//                logger.debug("data {}", param.split("=")[1]);
                return param.split("=")[1];
            }
        }
        return null;
    }

    public static void main(String args[]){
        CrawlerService crawlerService = new CrawlerService();
//        crawlerService.detailLoadSequence();
    }



}
