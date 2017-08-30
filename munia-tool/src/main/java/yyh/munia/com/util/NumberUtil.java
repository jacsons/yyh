package yyh.munia.com.util;

import org.apache.commons.lang3.StringUtils;

/**
 * Created by oak on 2017/8/29.
 */
public class NumberUtil
{

    /**
     * 解析int类型
     * @param s
     * @param defaultValue
     * @return
     */
    public static int parserInteger(String s,int defaultValue)
    {
        if(StringUtils.isEmpty(s))
        {
            return defaultValue;
        }

        try
        {
            Integer result = Integer.parseInt(s);

            return result;
        }
        catch (NumberFormatException e)
        {
            LoggerManager.record(LoggerType.ERROR,"NumberFormatException failed");
        }
        return defaultValue;
    }




}
