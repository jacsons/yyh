package yyh.munia.parser.parser.model;

import java.lang.reflect.Field;

/**
 * Created by oak on 2017/10/16.
 */
public class Wrap
{
    /**
     * class名字
     */
    private String className;

    /**
     *
     */
    private Field[][] fls;

    /**
     *
     */
    private String[][] filed;


    public String getClassName()
    {
        return className;
    }

    public void setClassName(String className)
    {
        this.className = className;
    }

    public Field[][] getFls()
    {
        return fls;
    }

    public void setFls(Field[][] fls)
    {
        this.fls = fls;
    }

    public String[][] getFiled()
    {
        return filed;
    }

    public void setFiled(String[][] filed)
    {
        if (filed == null)
        {
            this.filed = null;
            return;
        }

        this.filed = new String[filed.length][];
        System.arraycopy(filed, 0, this.filed,0, this.filed.length);
    }
}
