package yyh.munia.com.lagoTask;

import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
import yyh.munia.com.util.LoggerManager;
import yyh.munia.com.util.LoggerType;

import java.util.UUID;
import java.util.concurrent.ConcurrentLinkedQueue;

/**
 *
 * 积木式编程
 *
 *
 *
 * Created by oak on 2017/7/29.
 */
public class LegoTaskMgr
{


    private ThreadPoolTaskExecutor threadPoolTaskExecutor = new ThreadPoolTaskExecutor();

    /**
     * 并行队列，记录任务链
     */
    private ConcurrentLinkedQueue<ILegoTask> concurrentLinkedQueue = new ConcurrentLinkedQueue<>();

    /**
     * 线程池最大数量
     */
    private static final int Max_POOL_SIZE = 300;

    /**
     * 队列数量
     */
    private static final int Max_QUEUE_CAPACITY_SIZE = 300;


    /**
     * 积木话实力
     */
    private static final LegoTaskMgr legoTaskMgrt = new LegoTaskMgr();


    public LegoTaskMgr()
    {
        init();
    }

    /**
     * 获取实例
     * @return
     */
    public static LegoTaskMgr getInstance()
    {
        return legoTaskMgrt;
    }
    /**
     *
     * 提交任务
     *
     */
    public void submit(ILegoTask legoTask)
    {
        //可以判断下其他条件，再添加至并行队列中
        concurrentLinkedQueue.add(legoTask);
    }

    public void init()
    {
        threadPoolTaskExecutor.setMaxPoolSize(Max_POOL_SIZE);
        threadPoolTaskExecutor.setQueueCapacity(Max_QUEUE_CAPACITY_SIZE);
        threadPoolTaskExecutor.initialize();
    }


    /**
     *
     * 执行任务
     *
     */
    public boolean fetchAndRunTask()
    {

        ILegoTask task = LegoTaskMgr.getInstance().peekLegaTask();
        if(task != null)
        {
            LoggerManager.record(LoggerType.INFO,"LegoTaskMgr fetch a Task");
            return runTask(task);
        }
        return  false;
    }


    /**
     * 获取任务，并执行任务
     * @return
     */
    private ILegoTask peekLegaTask()
    {

        ILegoTask task = concurrentLinkedQueue.peek();

        if(task != null)
        {
            return concurrentLinkedQueue.poll();
        }
        return null;
    }


    /**
     * 执行任务
     * @param task
     */
    private boolean runTask(ILegoTask task)
    {

        task.setTaskKey(UUID.randomUUID().toString());
        threadPoolTaskExecutor.submit(task.getFutureTask());
        RunningManagerMgr.addTaskToRunning(task);

        return true;
    }

}


