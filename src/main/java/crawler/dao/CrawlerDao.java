package crawler.dao;


import com.ibatis.sqlmap.client.SqlMapClient;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class CrawlerDao {
    private static Logger logger = LoggerFactory.getLogger(CrawlerDao.class);
    private static SqlMapClient sqlMap = new SqlMapConfig().getSqlMapInstance();

}
