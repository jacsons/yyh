package yyh.munia.com.lagoTask;

import org.springframework.scheduling.concurrent.ConcurrentTaskScheduler;
import yyh.munia.com.util.LoggerManager;
import yyh.munia.com.util.LoggerType;

import java.util.Iterator;
import java.util.concurrent.ConcurrentHashMap;

/**
 * 记录正在运行的任务线程,记录正在运行的线程(便于计算有多酸)
 * Created by oak on 2017/8/1.
 */
public class RunningManagerMgr
{

    /**
     * 固定时间
     */
    private static final int FIX_TIME_TICK = 10000;

    /**
     *
     */
    private static ConcurrentTaskScheduler concurrentTaskScheduler = new ConcurrentTaskScheduler();


    /**
     * 记录正在运行的的任务
     */
    private static ConcurrentHashMap<String,ILegoTask<?>> runningTask = new ConcurrentHashMap<>();



    static
    {
        concurrentTaskScheduler.scheduleAtFixedRate(new Runnable(){
            @Override
            public void run()
            {
                clearRunningTask();
            }
        },FIX_TIME_TICK);


    }

    /**
     * 清除正在执行的任务
     */
    public static void clearRunningTask()
    {
        Iterator<String>  iterator = runningTask.keySet().iterator();


        while(iterator.hasNext())
        {
            String key = iterator.next();
            ILegoTask<?> task = runningTask.get(key);

            if(isTaskEnd(task))
            {
                runningTask.remove(key);

                LoggerManager.record(LoggerType.INFO,"Clear task" + key + ":success");
            }

        }
    }




    public static void addTaskToRunning(ILegoTask<?> task)
    {
        runningTask.put(task.getTaskKey(),task);

    }

    /**
     * 判断任务是否结束
     * @param task
     * @return
     */
    public static boolean isTaskEnd(ILegoTask<?> task)
    {
        return task.getFutureTask().isDone();
    }

}
