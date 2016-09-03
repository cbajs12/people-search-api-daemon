package crawler.service;


import crawler.dao.CrawlerDao;
import crawler.vo.BaseVO;
import crawler.vo.NameVO;
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
            System.setProperty("webdriver.chrome.driver", "/Users/jeonjiseong/Git/people-search-api-daemon/excute/chromedriver"); // changeLocal
        }
//        else if(System.getProperty("os.name").toLowerCase().contains("linux")){
//            System.setProperty("webdriver.chrome.driver", "/Users/jeonjiseong/Git/people-search-api-daemon/excute/chromedriver");
//        }
        driver = new ChromeDriver();
        driver.manage().timeouts().pageLoadTimeout(10, TimeUnit.SECONDS);
        driver.manage().timeouts().setScriptTimeout(20, TimeUnit.SECONDS);
    }

    public void startService(){
        baseCrawler();
    }

    public void baseCrawler(){
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
            logger.debug("startCrawlerBase SQL ERROR");
            e.printStackTrace();
        }
        driver.close();
    }

    public void detailCrawler(){

    }

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
                    nameVO.setName_os(data.getAttribute("href"));           // 중복 체크
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

    public void waitForLoad(final WebDriver driver){
        ExpectedCondition<Boolean> pageLoadCondition = new ExpectedCondition<Boolean>() {
            public Boolean apply(WebDriver webDriver) {
                return ((JavascriptExecutor)driver).executeScript("return document.readyState").equals("complete");
            }
        };
        WebDriverWait wait = new WebDriverWait(driver, 30);
        wait.until(pageLoadCondition);
    }

    public static void main(String args[]){
        CrawlerService crawlerService = new CrawlerService();
        crawlerService.baseCrawler();
//        String url = "http://people.search.naver.com/search.naver?where=nexearch&query="+name+"&ie=utf8&key=PeopleService&os="+os;
    }



}
