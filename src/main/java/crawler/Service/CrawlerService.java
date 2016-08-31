package crawler.service;


import crawler.dao.CrawlerDao;
import crawler.vo.BaseVO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class CrawlerService {
    private static Logger logger = LoggerFactory.getLogger(CrawlerService.class);
    public static boolean 	threadFlag 	=  true;
    private static Pattern patternDomainName;
    private Matcher matcher;
    private static final String DOMAIN_NAME_PATTERN = "([a-zA-Z0-9]([a-zA-Z0-9\\-]{0,61}[a-zA-Z0-9])?\\.)+[a-zA-Z]{2,6}";
    private CrawlerDao crawlerDao = new CrawlerDao();
    static {
        patternDomainName = Pattern.compile(DOMAIN_NAME_PATTERN);
    }

    public void startService(){
        startCrawlerBase();
    }

    public void startCrawlerBase(){
        try {
            List baseVOs = crawlerDao.getBaseList();
            logger.debug("baseVOs {}", baseVOs);
        } catch (InterruptedException e) {
            logger.debug("startCrawlerBase SQL ERROR");
            e.printStackTrace();
        }
    }

    public void startCrawlerDetail(){

    }

    public void initCrawlerData(){
        try {
            BufferedReader reader = new BufferedReader(new InputStreamReader(getClass().getResourceAsStream("/initdata/people_keyword.txt")));

            String line;
            BaseVO baseVO = new BaseVO();
            while ((line = reader.readLine()) != null)
            {
//                logger.debug("name {}, size {}", line, line.length());
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

    public static void main(String args[]){
        CrawlerService crawlerService = new CrawlerService();
        crawlerService.initCrawlerData();
        logger.debug("finished");
    }



}
