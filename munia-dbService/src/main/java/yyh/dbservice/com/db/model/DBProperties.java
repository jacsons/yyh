package yyh.dbservice.com.db.model;

/**
 * 项目id的信息
 * Created by oak on 2017/8/4.
 */
public class DBProperties
{

    private int id;
    /**
     * 数据库的ip地址
     */
    private String ip;

    /**
     * 数据库的端口名称
     */
    private String port;

    /**
     * 数据库的名称
     */
    private String dbName;

    /**
     * 项目ID
     */
    private String projectID;


    public DBProperties()
    {

    }

    public DBProperties(String projectID)
    {
        this.projectID = projectID;
    }

    public String getProjectID()
    {
        return projectID;
    }

    public void setProjectID(String projectID)
    {
        this.projectID = projectID;
    }

    public String getIp()
    {
        return ip;
    }

    public void setIp(String ip)
    {
        this.ip = ip;
    }

    public String getPort()
    {
        return port;
    }

    public void setPort(String port)
    {
        this.port = port;
    }

    public String getDbName()
    {
        return dbName;
    }

    public void setDbName(String dbName)
    {
        this.dbName = dbName;
    }

    public int getId()
    {
        return id;
    }

    public void setId(int id)
    {
        this.id = id;
    }
}
