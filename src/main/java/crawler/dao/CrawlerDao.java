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

    /*
        Get baseVO list with code is not CR002 from DB
     */
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

    /*
        Add baseVO into DB
     */
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

    /*
        Add nameVO into DB
     */
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

    /*
        Get nameVO list with code is not CR002 from DB
     */
    public List getNameList() throws InterruptedException {
        NameVO nameVO = new NameVO();
        List nameVOList = new LinkedList<NameVO>();
        try{
            nameVOList = sqlMap.queryForList("daemon.getNameList", nameVO);
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return nameVOList;
    }
}
