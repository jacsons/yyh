package yyh.munia.com.util;

import org.apache.log4j.Logger;

/**
 * 日志处理类
 *
 * 主要需要实现以下功能
 *
 * 自动获取错误的类，错误在哪一行的信息（未完成）
 *
 *  根据配置初始化，记录部分信息
 *
 * Created by oak on 2017/7/26.
 */
public class LoggerManager
{

    /**
     * 这个将记录的是以
     */
    private static final Logger logger = Logger.getLogger(LoggerManager.class);


    /**
     * 日志记录类
     * @param type
     * @param info
     */
    public static void record(LoggerType type,String info)
    {
        if(info == null || info.isEmpty() || type == null)
        {
            return;
        }

        String msg = packageMessage(info);

        switch (type)
        {
            case WARN:
                writeWarnInfo(msg);
                break;
            case DEBUG:
                writeDebugInfo(msg);
                break;
            case ERROR:
                wirteErrorInfo(msg);
                break;
            case FATAL:
                writeFatalInfo(msg);
                break;
            case INFO:
                writeInfo(msg);
                break;
            default:
        }
    }


    /**
     * 记录日志信息,直接是异常信息
     * @param Type
     * @param e
     */
    public synchronized static void record(LoggerType Type,Exception e)
    {


    }

    /**
     * 打包message信息
     * @param msg
     * @return
     */
    private synchronized static String packageMessage(String msg)
    {
        StackTraceElement stackTraceElement = new Throwable().getStackTrace()[2];

        String className = stackTraceElement.getClassName();
        String methodName = stackTraceElement.getMethodName();
        int lineNumber = stackTraceElement.getLineNumber();

        return String.format("[ClassName : %s | methodName : %s | LineUnmber : %d] : %s",new Object[]{className,methodName,lineNumber,msg});
    }



    /**
     * 打印info信息
     * @param info
     */
    private synchronized static void writeInfo(String info)
    {
        logger.info(info);
    }

    /**
     * 写debugger信息
     * @param debug
     */
    private synchronized static void writeDebugInfo(String debug)
    {
        if(logger.isDebugEnabled())
        {
            logger.debug(debug);
        }
    }

    /**
     * 写错误信息
     * @param error
     */
    private synchronized static void wirteErrorInfo(String error)
    {

        logger.error(error);

    }

    /**
     * 写严重错误信息
     * @param fatal
     */
    private synchronized static void writeFatalInfo(String fatal)
    {
        logger.fatal(fatal);
    }

    /**
     * 写警告信息
     * @param warn
     */
    private synchronized static void writeWarnInfo(String warn)
    {
        if(logger.isInfoEnabled())
        {
            logger.warn(warn);
        }
    }
}
