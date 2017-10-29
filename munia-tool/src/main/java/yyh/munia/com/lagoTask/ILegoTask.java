package yyh.munia.com.lagoTask;


import yyh.munia.com.util.LoggerManager;
import yyh.munia.com.util.LoggerType;

import java.util.concurrent.Callable;
import java.util.concurrent.FutureTask;

/**
 * legaTask接口,所有需要实现积木化编程的都得实现该接口
 * Created by oak on 2017/7/29.
 */
public abstract class ILegoTask<T>
{

    private FutureTask futureTask = null;

    /**
     * taskKey
     */
    private String taskKey;

    /**
     * 内存使用量
     */
    private int useMemory = 200;


    /**
     *
     * 工作类
     *
     */
    public ILegoTask()
    {
        futureTask = new FutureTask<T>(new Callable<T>()
        {
            @Override
            public T call() throws Exception
            {
                LoggerManager.record(LoggerType.INFO,"taskKey :" + taskKey);
                try
                {
                    return main();

                }
                catch (RuntimeException e)
                {
                    handleException(e);
                }
                return null;
            }
        });
    }

    /**
     * 主执行函数
     * @return
     */

    public abstract T main();

    /**
     *
     * 异常处理函数
     * @param e
     * @return
     *
     */
    public abstract T handleException(RuntimeException e);


    /**
     *
     * 获取
     * @return
     *
     */
    public FutureTask getFutureTask()
    {
        return futureTask;
    }

    /**
     *
     * 
     *
     * @return
     */
    public String getTaskKey()
    {
        return taskKey;
    }

    /**
     *
     *
     * @param taskKey
     */
    public void setTaskKey(String taskKey)
    {
        this.taskKey = taskKey;
    }

    /**
     * 运行是否结束
     * @return
     */
    public boolean isDone()
    {
        return futureTask.isDone();
    }

    /**
     *
     * @return
     */
    public int getUseMemory()
    {
        return useMemory;
    }

    public void setUseMemory(int useMemory)
    {
        this.useMemory = useMemory;
    }
}
