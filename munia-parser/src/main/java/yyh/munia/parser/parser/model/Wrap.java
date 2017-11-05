package yyh.munia.parser.parser.model;

import yyh.munia.com.util.LoggerManager;
import yyh.munia.com.util.LoggerType;

import java.lang.reflect.Field;
import java.util.List;

/**
 * 对象实例
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


    private Class cls;


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


    /**
     * 返回实例对象
     * @return
     */
    public Wrap getInstance(List<String> strings, String project)
    {
        initFileds();
        Object object = newInstance();
        if (object == null)
        {
            LoggerManager.record(LoggerType.WARN, "set filed Failed, class name is; " + className);
        }





        return null;
    }


    /**
     * 获取实例
     * @return
     */
    private Object newInstance()
    {
        try
        {
            cls = Class.forName(className);

            return cls.newInstance();
        }
        catch (ClassNotFoundException e)
        {
            LoggerManager.record(LoggerType.WARN, "can not load class : " + className);
        }
        catch (InstantiationException e)
        {
            LoggerManager.record(LoggerType.WARN, "can not InstantiationException class : " + className);
        }
        catch (IllegalAccessException e)
        {
            LoggerManager.record(LoggerType.WARN, "can not IllegalAccessException class : " + className);
        }
        return null;
    }

    /**
     * 初始化Fileds
     */
    private void initFileds()
    {
        if (filed != null)
        {

            try
            {
                cls = Class.forName(className);
                Field[][] fieldArrays = new Field[filed.length][];
                int index = 0;
                for (int i = 0 ; i < filed.length; ++i)
                {
                    String[] fieldStr = filed[i];
                    if (fieldStr == null)
                    {
                        continue;
                    }

                    fieldArrays[index++] = new Field[fieldStr.length];
                    int col = 0;
                    for (String filed : fieldStr)
                    {
                        try
                        {
                            fieldArrays[index][col] = cls.getDeclaredField(filed);
                        }
                        catch (NoSuchFieldException e)
                        {
                            LoggerManager.record(LoggerType.WARN, "can not find filed :" + filed,e);
                        }
                    }
                }
                this.fls = fieldArrays;
                this.filed = null;
            }
            catch (ClassNotFoundException e)
            {
                LoggerManager.record(LoggerType.WARN, "can not load class : " + className);

            }
        }
    }

}
