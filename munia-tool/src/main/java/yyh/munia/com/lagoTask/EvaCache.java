package yyh.munia.com.lagoTask;

/**
 *
 * 存储副本单元，保存副本数据，用于线程中的读
 * Created by oak on 2017/7/29.
 */
public class EvaCache
{
    private ThreadLocal<Monitor> threadLocal = new ThreadLocal<Monitor>();


    /**
     * 设置uuid
     * @param t
     */
    public void setUUID(String t)
    {
        if(threadLocal.get() == null)
        {
            Monitor monitor = new Monitor();
            threadLocal.set(monitor);
        }
        threadLocal.get().setUuid(t);
    }

    /**
     * 获取uuid
     * @return
     */
    public String  getUUID()
    {
        return threadLocal.get().getUuid();
    }
}

