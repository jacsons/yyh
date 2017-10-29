package yyh.munia.com.classrelative;

import yyh.munia.com.util.LoggerManager;
import yyh.munia.com.util.LoggerType;
import yyh.munia.com.util.NullCheckUtil;

/**
 * 与Class对象处理相关的类
 * Created by oak on 2017/10/15.
 */
public class ClassUtil
{
    /**
     * 判断是不是同样的构造函数
     * @return
     */
    public static boolean typeCheck(Class[] parameters, Object[] args)
    {
        if (NullCheckUtil.isNull(parameters,args))
        {
            return false;
        }

        if (parameters.length != args.length)
        {
            return false;
        }

        for (int i = 0; i < parameters.length; ++i)
        {
            if (!isAssign(parameters[i], args[i].getClass()))
            {
                return false;
            }
        }
        return true;
    }


    /**
     * 判断是否能够赋值
     * @param src
     * @param snk
     * @return
     */
    private static boolean isAssign(Class src, Class snk)
    {
        if (src.isAssignableFrom(snk))
        {
            return true;
        }

        try
        {
            if (src.isPrimitive() && src.getField("TYPE").get(null) == snk)
            {
                return true;
            }

            if (snk.isPrimitive() && snk.getField("TYPE").get(null) == src)
            {
                return true;
            }
        }
        catch (IllegalAccessException | NoSuchFieldException e)
        {
            LoggerManager.record(LoggerType.WARN, "Type is error");
        }
        return false;
    }



}
