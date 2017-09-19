package yyh.dbservice.com.db;

import yyh.munia.com.util.LoggerManager;
import yyh.munia.com.util.LoggerType;

/**
 * 关闭sql流的Util，还有其他Util这个再修改
 * Created by oak on 2017/9/19.
 */
public class IOCloseUtil
{


    /**
     * 关闭mysql的IO
     * @param sqlIo
     */
    public static void close(AutoCloseable sqlIo)
    {

        if (sqlIo == null)
        {
            return;
        }

        try
        {
            sqlIo.close();
        }
        catch (Exception e)
        {
            LoggerManager.record(LoggerType.ERROR, "BaseDBinit updateDB getConnection MYSQL failed", e);
        }
    }


}
