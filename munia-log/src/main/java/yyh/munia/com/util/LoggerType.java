package yyh.munia.com.util;

/**
 * Created by oak on 2017/7/26.
 */
public enum LoggerType
{

    /**
     * 通知级别
     */
    INFO(1),

    /**
     * Debug级别
     */
    DEBUG(2),

    /**
     * 警告级别
     */
    WARN(3),

    /**
     * 错误级别
     */
    ERROR(4),

    /**
     * 严重级别
     */
    FATAL(5);

    /**
     * 对应的id号
     */
    private Integer id;


    LoggerType(int id)
    {
        this.id = id;
    }

}
