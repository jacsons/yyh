package yyh.dbservice.com.db;


import yyh.dbservice.com.db.error.DBException;
import yyh.dbservice.com.db.model.DBProperties;

import java.util.HashMap;
import java.util.Map;

/**
 *
 * 项目管理类，存储项目与数据库中的项目对应关系
 * Created by oak on 2017/8/4.
 */
public class ProjectSourceDBMgr
{

    /**
     * 数据库的前缀
     */
    private static final String PROJECT_PREFEX = "YYH_";

    /**
     * 静态数据库名称
     */
    private static final String SYSTEM_CONFIG_DB = "0";

    /**
     *
     * 记录项目ID与数据库中项目id的对应关系
     *
     */
    private Map<String,DBProperties> projectMapping = new HashMap<>();

    /**
     * 获取数据库中的项目id
     * @param projectID
     * @return
     */
    public DBProperties getDBProperties(String projectID) throws DBException
    {
        String projectKey = getProjectId(projectID);
        DBProperties db = projectMapping.get(projectID);

        //项目id未找到
        if(db == null)
        {

            return SYSTEM_CONFIG_DB.equals(projectKey) ? initSystemDB(projectKey) : getProjectProperite(projectID);
        }
        return db;
    }

    /**
     * 获取项目
     * @param projectID
     * @return
     */
    private String getProjectId(String projectID)
    {
        return String.format("%s%s",PROJECT_PREFEX,projectID);
    }


    /**
     * 初始化静态数据库
     * @param projectKey
     * @return
     */
    private DBProperties initSystemDB(String projectKey)
    {
        DBProperties db = null;






        return db;
    }

    /**
     * 获取项目信息
     * @param projectID
     * @return
     */
    private DBProperties getProjectProperite(String projectID) throws DBException
    {
        DBProperties dbProperties = DBFactory.getInstance().getProjectPropertie(projectID);
        if(!DBFactory.getInstance().isExsitProject(projectID))
        {
            DBFactory.getInstance().creatNeProject(projectID);
            dbProperties =  DBFactory.getInstance().getProjectPropertie(projectID);
            projectMapping.put(projectID,dbProperties);
        }
        return dbProperties;
    }
}
