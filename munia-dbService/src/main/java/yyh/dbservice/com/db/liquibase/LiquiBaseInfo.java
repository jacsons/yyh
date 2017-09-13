package yyh.dbservice.com.db.liquibase;

import org.springframework.beans.factory.InitializingBean;
import yyh.dbservice.com.db.DBServerImpl;
import yyh.dbservice.com.db.model.DBProperties;

import java.util.List;

/**
 * 存放liquibase需要的基础信息
 * Created by oak on 2017/9/13.
 */
public class LiquiBaseInfo implements InitializingBean
{

    /**
     * 用户名
     */
    private String userName;

    /**
     * 密码
     */
    private String password;

    /**
     * 驱动class
     */
    private String driveClass;

    /**
     * 该实例
     */
    private static LiquiBaseInfo instance;


    /**
     * redis中的所有端口资源信息
     */
    private List<DBProperties> dbPropertiesList;


    public LiquiBaseInfo()
    {
        this.instance = this;
    }

    /**
     * 获取单例对象
     * @return
     */
    public LiquiBaseInfo getInstance()
    {
        return instance;
    }

    /**
     *
     * @throws Exception
     */
    @Override
    public void afterPropertiesSet() throws Exception
    {
        DBServerImpl dbServer = new DBServerImpl();

        /**
         * 获取所有的ip 端口 信息
         */
        this.dbPropertiesList = dbServer.getDBProperties();
    }

    /**
     * 获取所有的ip端口信息
     * @return
     */
    public List<DBProperties> feachDBProperties()
    {
        return dbPropertiesList;
    }


    public String getUserName()
    {
        return userName;
    }

    public void setUserName(String userName)
    {
        this.userName = userName;
    }

    public String getPassword()
    {
        return password;
    }

    public void setPassword(String password)
    {
        this.password = password;
    }

    public String getDriveClass()
    {
        return driveClass;
    }

    public void setDriveClass(String driveClass)
    {
        this.driveClass = driveClass;
    }
}
