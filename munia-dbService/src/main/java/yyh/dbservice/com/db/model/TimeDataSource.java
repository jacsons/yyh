package yyh.dbservice.com.db.model;

import com.mchange.v2.c3p0.ComboPooledDataSource;


/**
 * Created by oak on 2017/8/5.
 */
public class TimeDataSource
{

    private long time;

    private ComboPooledDataSource combopooleddatasource;

    public TimeDataSource(ComboPooledDataSource combopooleddatasource)
    {
        time = System.currentTimeMillis();
        this.combopooleddatasource = combopooleddatasource;
    }

    public long getTime()
    {
        return time;
    }

    public void updataTime(long time)
    {
        this.time = time;
    }

    public ComboPooledDataSource getCombopooleddatasource()
    {
        return combopooleddatasource;
    }

    public void setCombopooleddatasource(ComboPooledDataSource combopooleddatasource)
    {
        this.combopooleddatasource = combopooleddatasource;
    }

    /**
     * 是否未超期，反面不好判断。
     * @param maxAliveTime
     * @return
     */
    public boolean isNotTimeOut(long maxAliveTime)
    {
        //两个时间之差是否小于最大时间，且当前时间要比上一次记录的时间大。
        return System.currentTimeMillis() - time < maxAliveTime  && System.currentTimeMillis() > time;
    }
}
