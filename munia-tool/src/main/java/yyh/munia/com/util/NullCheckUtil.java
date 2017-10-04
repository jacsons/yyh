package yyh.munia.com.util;

/**
 * 检测是否为null的工具类
 * Created by oak on 2017/10/3.
 */
public class NullCheckUtil
{

    public static boolean isNull(Object...objects)
    {
        if (objects == null || objects.length == 0)
        {
            return true;
        }

        for (Object object : objects)
        {
            if (object == null)
            {
                return true;
            }
        }
        return false;
    }

}
