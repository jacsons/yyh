package yyh.munia.com.lagoTask;

import org.springframework.scheduling.concurrent.ConcurrentTaskScheduler;
import yyh.munia.com.util.LoggerManager;
import yyh.munia.com.util.LoggerType;

/**
 * 积木化编程的入口程序启动时调用
 * Created by oak on 2017/8/1.
 */
public class LegoTaskSchedule
{

    /**
     * 固定时间
     */
    private static final int FIX_TIME_TICK = 10000;

    /**
     *
     */
    private static ConcurrentTaskScheduler concurrentTaskScheduler = new ConcurrentTaskScheduler();



    public static void start()
    {
        LoggerManager.record(LoggerType.INFO,"LegoTaskSchedule start!");
        concurrentTaskScheduler.scheduleAtFixedRate(new Runnable(){
            @Override
            public void run()
            {
                deQuenTask();
            }
        },FIX_TIME_TICK);

    }


    /**
     *
     * 执行任务
     *
     */
    private static void deQuenTask()
    {
        boolean isContinue = true;
        while (isContinue)
        {
            isContinue = LegoTaskMgr.getInstance().fetchAndRunTask();
        }
    }
}
