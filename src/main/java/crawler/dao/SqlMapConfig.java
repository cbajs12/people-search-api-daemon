package crawler.dao;

import com.ibatis.common.resources.Resources;
import com.ibatis.sqlmap.client.SqlMapClient;
import com.ibatis.sqlmap.client.SqlMapClientBuilder;

import java.io.Reader;

public class SqlMapConfig {
    private static SqlMapClient sqlMap;
    private String resource = "SqlMapConfig.xml";
    public SqlMapClient getSqlMapInstance(){

        return this.getSqlMapInstance(resource);
    }

    public SqlMapClient getSqlMapInstance(String resource) {
        Reader reader;
        try{
            reader = Resources.getResourceAsReader(resource);
            sqlMap = SqlMapClientBuilder.buildSqlMapClient(reader);
        } catch(Exception e) {
            e.printStackTrace();
        }
        return sqlMap;
    }
}
