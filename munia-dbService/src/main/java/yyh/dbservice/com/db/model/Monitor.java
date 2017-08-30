package yyh.dbservice.com.db.model;

/**
 * Created by oak on 2017/7/31.
 */
public class Monitor
{
    /**
     * 项目ID
     */
    private String projectId;

    private boolean newDB = false;

    public String getProjectId()
    {
        return projectId;
    }

    public void setProjectId(String projectId)
    {
        this.projectId = projectId;
    }

    public boolean isNewDB()
    {
        return newDB;
    }

    public void setNewDB(boolean newDB)
    {
        this.newDB = newDB;
    }
}
