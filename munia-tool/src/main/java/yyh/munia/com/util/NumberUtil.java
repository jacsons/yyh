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
    public static Integer parserInteger(String s,int defaultValue)
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
            LoggerManager.record(LoggerType.ERROR,"parserInteger NumberFormatException failed");
        }
        return defaultValue;
    }


    /**
     * 转
     * @param s
     * @param defaultValue
     * @return
     */
    public static Double parserDouble(String s, Double defaultValue)
    {
        if(StringUtils.isEmpty(s))
        {
            return defaultValue;
        }

        try
        {
            Double result = Double.parseDouble(s);

            return result;
        }
        catch (NumberFormatException e)
        {
            LoggerManager.record(LoggerType.ERROR,"parserDouble NumberFormatException failed");
        }
        return defaultValue;
    }


    /**
     * 解析float
     * @param s
     * @param defaultValue
     * @return
     */
    public static Float parserFloat(String s, Float defaultValue)
    {
        if(StringUtils.isEmpty(s))
        {
            return defaultValue;
        }

        try
        {
            Float result = Float.parseFloat(s);

            return result;
        }
        catch (NumberFormatException e)
        {
            LoggerManager.record(LoggerType.ERROR,"parserFloat NumberFormatException failed");
        }
        return defaultValue;
    }
}
