package yyh.munia.com.lagoTask;

/**
 * 系统运行情况类
 * Created by oak on 2017/8/27.
 */
public class SystemRunInfo
{
    /**
     * 获取剩余内存
     * @return
     */
    public static long getFreeMemory()
    {
        Runtime runtime = Runtime.getRuntime();
        return runtime.freeMemory();

    }


}
