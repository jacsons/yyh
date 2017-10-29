package yyh.munia.com.util;

import org.apache.commons.lang3.StringUtils;

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
            if (object instanceof String)
            {
                if (StringUtils.isEmpty((String)object))
                {
                    return true;
                }

            }
        }
        return false;
    }

}
