package crawler.dao;


import com.ibatis.sqlmap.client.SqlMapClient;
import crawler.vo.*;
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

    /*
        Add familyVO into DB and return id
     */
    public int setFamilyData(FamilyVO familyVO) throws InterruptedException{
        int id = 0;
        try{
            sqlMap.insert("daemon.setFamilyData", familyVO);
            FamilyVO familyVO1 = getFamilyVO();
            if(familyVO1 != null || familyVO1.getFm_json() != null || familyVO1.getFm_json().length() != 0){
                id = familyVO1.getFamily_seq();
            }

        } catch (SQLException e) {
            logger.debug("SET Family DATA ERROR", e);
        }
        return id;
    }

    /*
        Get familyVO from DB
     */
    public FamilyVO getFamilyVO() throws InterruptedException{
        FamilyVO familyVO = new FamilyVO();
        try{
            familyVO = (FamilyVO)sqlMap.queryForObject("daemon.getFamilyId", familyVO);
        } catch (SQLException e) {
            logger.debug("SET BASE DATA ERROR", e);
        }
        return familyVO;
    }

    /*
        Add detailVO into DB
     */
    public boolean setDetailData(DetailVO detailVO) throws InterruptedException{
        boolean check = false;
        try{
            sqlMap.insert("daemon.setDetailData", detailVO);
            check = true;
        } catch (SQLException e) {
            logger.debug("SET Detail DATA ERROR", e);
        }
        return check;
    }

    /*
        Add careerVO into DB
     */
    public boolean setCareerData(CareerVO careerVO) throws InterruptedException{
        boolean check = false;
        try{
            sqlMap.insert("daemon.setCareerData", careerVO);
            check = true;
        } catch (SQLException e) {
            logger.debug("SET Career DATA ERROR", e);
        }
        return check;
    }

}
