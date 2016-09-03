package crawler.dao;


import com.ibatis.sqlmap.client.SqlMapClient;
import crawler.vo.BaseVO;
import crawler.vo.NameVO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;

public class CrawlerDao {
    private static Logger logger = LoggerFactory.getLogger(CrawlerDao.class);
    private static SqlMapClient sqlMap = new SqlMapConfig().getSqlMapInstance();

    public List getBaseList() throws InterruptedException {
        BaseVO baseVO = new BaseVO();
        List baseVOList = new LinkedList<BaseVO>();
        try{
            baseVOList = sqlMap.queryForList("daemon.getBaseList", baseVO);
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return baseVOList;
    }

    public boolean setBaseData(BaseVO baseVO) throws InterruptedException{
        boolean check = false;
        try{
            sqlMap.insert("daemon.setBaseData", baseVO);
            check = true;
        } catch (SQLException e) {
            logger.debug("SET BASE DATA ERROR", e);
        }
        return check;
    }

    public boolean setNameData(NameVO nameVO) throws InterruptedException{
        boolean check = false;
        try{
            sqlMap.insert("daemon.setNameData", nameVO);
            check = true;
        } catch (SQLException e) {
            logger.debug("SET Name DATA ERROR", e);
        }
        return check;
    }
}
