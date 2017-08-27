package yyh.dbservice.com.db;

import com.mchange.v2.c3p0.ComboPooledDataSource;
import yyh.dbservice.com.db.model.ComboPooledConfig;
import yyh.dbservice.com.db.model.DBProperties;
import yyh.dbservice.com.db.model.TimeDataSource;
import yyh.munia.com.RSA.RSA2048Util;

import java.util.*;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

/**
 *
 * sqlSeession类，给spring使用的
 * Created by oak on 2017/6/10.
 */
public class DataSourceFactory
{

    /**
     * 存储sqlSsession的map
     */
    private static Map<String,TimeDataSource> timeDataSourceMap = new HashMap<>();



    /**
     * 定时清理周期
     */
    private static final long INTERVAL = 60 * 1000L;


    /**
     * 最长的存活周期
     */
    private static final long MAX_ALIVE_TIME = 60 * 60 * 1000L;


    /**
     * 对象锁
     */
    private static ReadWriteLock reentrantReadWriteLock  = new  ReentrantReadWriteLock();


    private static ComboPooledConfig comboPooledConfig;


    public void timeSchedule()
    {
        /**
         * 配置定时清理任务
         */
        Date date = new Date();
        TimerTask timerTask = new TimerTask() {
            @Override
            public void run() {
                clearComboPooledDataSource();
            }
        };
        Timer timer = new Timer();
        timer.scheduleAtFixedRate(timerTask,date,INTERVAL);
    }

    /**
     * 获取getSqlSession实例
     * @param dbProperties
     *          项目ID
     * @return
     */
    public static ComboPooledDataSource getComboPooledDataSource(DBProperties dbProperties)
    {
        String dbName = getdbName(dbProperties);

        //知识点：reentrantReadWriteLock的使用方法！
        reentrantReadWriteLock.readLock().lock();

        TimeDataSource ssq = timeDataSourceMap.get(dbName);
        if (ssq == null)
        {
            reentrantReadWriteLock.readLock().unlock();
            reentrantReadWriteLock.writeLock().lock();

            try
            {
                ssq = timeDataSourceMap.get(dbName);
                if(ssq == null)
                {
                    //初始化SqlSession 保存并  ！！此处应该特殊处理以支持能够使用多个数据库
                    ssq = initConnetion(dbProperties);
                    timeDataSourceMap.put(dbName,ssq);
                }
                reentrantReadWriteLock.readLock().lock();
            }
            finally {
                reentrantReadWriteLock.writeLock().unlock();
            }
        }
        reentrantReadWriteLock.readLock().unlock();

        ssq.updataTime(System.currentTimeMillis());
        return ssq.getCombopooleddatasource();
    }


    /**
     * 清除超时的连接
     */
    public static void clearComboPooledDataSource()
    {
        Iterator<Map.Entry<String,TimeDataSource>> iterator = timeDataSourceMap.entrySet().iterator();

        List<String> reapOut = new ArrayList<>();
        while (iterator.hasNext())
        {
            Map.Entry<String,TimeDataSource> entry = iterator.next();
            if (!entry.getValue().isNotTimeOut(MAX_ALIVE_TIME))
            {
                reapOut.add(entry.getKey());
            }
        }

        //删除已经超时的连接
        reapOut.forEach(item ->
        {
            timeDataSourceMap.remove(item);
        });

    }


    /**
     *
     * @param comboPooledConfig
     */
    public void setComboPooledConfig(ComboPooledConfig comboPooledConfig)
    {
        this.comboPooledConfig = comboPooledConfig;
    }


    private static TimeDataSource initConnetion(DBProperties dbProperties)
    {
        return new TimeDataSource(initComboPooledDataSource());
    }

    /**
     *
     * @return
     */
    private static ComboPooledDataSource initComboPooledDataSource()
    {
        ComboPooledDataSource comboPooledDataSource = new ComboPooledDataSource();

        comboPooledDataSource.setUser(comboPooledConfig.getUser());
        comboPooledDataSource.setPassword(RSA2048Util.deEncrypt(comboPooledConfig.getPassword()));
        comboPooledDataSource.setJdbcUrl(comboPooledConfig.getJdbcUrl());
        comboPooledDataSource.setMaxPoolSize(Integer.valueOf(comboPooledConfig.getMaxPoolSize()));
        comboPooledDataSource.setMinPoolSize(Integer.valueOf(comboPooledConfig.getMinPoolSize()));
        comboPooledDataSource.setInitialPoolSize(Integer.valueOf(comboPooledConfig.getInitialPoolSize()));
        comboPooledDataSource.setMaxIdleTime(Integer.valueOf(comboPooledConfig.getMaxIdleTime()));

        return comboPooledDataSource;
    }

    /**
     * 获取dbname
     * @param dbProperties
     * @return
     */
    private static String getdbName(DBProperties dbProperties)
    {
        return String.format("%s_%s",dbProperties.getIp(),dbProperties.getPort());

    }
}
