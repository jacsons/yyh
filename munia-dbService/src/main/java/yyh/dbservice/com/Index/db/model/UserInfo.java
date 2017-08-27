package yyh.dbservice.com.Index.db.model;

/**
 * Created by oak on 2017/8/2.
 */
public class UserInfo
{
    /**
     *
     */
    private Integer id;

    /**
     *
     */
    private String sName;

    /**
     *
     */
    private String sex;


    /**
     * 增长id
     * @return
     */
    public Integer getId()
    {
        return id;
    }

    public void setId(Integer id)
    {
        this.id = id;
    }

    public String getsName()
    {
        return sName;
    }

    public void setsName(String sName)
    {
        this.sName = sName;
    }

    public String getSex()
    {
        return sex;
    }

    public void setSex(String sex)
    {
        this.sex = sex;
    }
}
