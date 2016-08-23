package crawler.dao;


import com.ibatis.sqlmap.client.SqlMapClient;

public class CrawlerDao {
    private static SqlMapClient sqlMap = new SqlMapConfig().getSqlMapInstance();
}
