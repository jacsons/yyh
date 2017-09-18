package yyh.munia.com.prgress;

import yyh.munia.com.prgress.impl.DefaultTool;
import yyh.munia.com.prgress.inf.IProgressReader;
import yyh.munia.com.prgress.inf.IProgressWriter;
import yyh.munia.com.util.LoggerManager;
import yyh.munia.com.util.LoggerType;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Parameter;

/**
 * 对外接口，获取读与写的操作器，进行后续的读写操作
 * Created by oak on 2017/9/14.
 */
public class ProgressFactory
{
    /**
     * 默认的读操作器
     */
    private Class<? extends IProgressReader> iProgressReader = null;

    /**
     * 默认的写操作器
     */
    private Class<? extends IProgressWriter> iProgressWriter = null;


    /**
     * 单例
     */
    private static final ProgressFactory INSTANCE = new ProgressFactory();


    /**
     * 写数据构造的参数
     */
    private  Object[] writeArges;


    /**
     * 读构造函数的参数
     */
    private  Object[] readArges;


    /**
     * 单例模式
     */
    private ProgressFactory()
    {

    }

    /**
     * 构建写进度器
     * @param key
     * @return
     */

    public  IProgressWriter buildWriter(String key)
    {
        IProgressWriter iProgressWriter = writerInstance();
        iProgressWriter.setKey(key);

        return iProgressWriter;
    }

    /**
     * 获取度期器件
     * @param key
     * @return
     */
    public  IProgressReader builderReader(String key)
    {
        IProgressReader iProgressReader = readerInstance();
        iProgressReader.setKey(key);

        return iProgressReader;
    }

    /**
     * 返回该数据类型
     * @return
     */
    public static ProgressFactory getInstanc()
    {
        return INSTANCE;
    }

    /**
     *
     * @return
     */
    private  IProgressWriter writerInstance()
    {
        Class progressClass = iProgressWriter == null ? DefaultTool.class : iProgressReader;
        try
        {
            return writeArges.length == 0 ? (IProgressWriter)progressClass.newInstance() : (IProgressWriter)newInstance(progressClass, writeArges);
        }
        catch (InstantiationException | IllegalAccessException e)
        {
            return  (IProgressWriter)defalutProgress();
        }
    }

    /**
     *
     * @return
     */
    private IProgressReader readerInstance()
    {
        Class progressClass = iProgressReader == null ? DefaultTool.class : iProgressReader;
        try
        {
            return readArges.length == 0 ? (IProgressReader)progressClass.newInstance() : (IProgressReader)newInstance(progressClass, readArges);
        }
        catch (InstantiationException | IllegalAccessException e)
        {
            return  (IProgressReader)defalutProgress();
        }
    }


    /**
     * 生成对象
     * @param args
     * @return
     */
    private  Object newInstance(Class cls, Object[] args)
    {

        Constructor<?>[] constructors = cls.getConstructors();
        for (Constructor constructor : constructors)
        {
            Parameter[] parameters = constructor.getParameters();
            if(parameters.length == args.length)
            {
                try
                {
                    return constructor.newInstance(args);
                }
                catch (InstantiationException | IllegalAccessException | InvocationTargetException e)
                {
                    LoggerManager.record(LoggerType.ERROR,"newInstance failed", e);
                }
            }
        }
        return new RuntimeException("not support this length args");
    }

    /**
     * 默认的读写器
     * @return
     */
    private  Object defalutProgress()
    {
        try
        {
            return  DefaultTool.class.newInstance();
        }
        catch (InstantiationException | IllegalAccessException e)
        {
            LoggerManager.record(LoggerType.ERROR,"defalutProgress init failed");
        }
        return new DefaultTool();
    }


    public Class<? extends IProgressReader> getiProgressReader()
    {
        return iProgressReader;
    }

    public void setiProgressReader(Class<? extends IProgressReader> iProgressReader)
    {
        this.iProgressReader = iProgressReader;
    }

    public Class<? extends IProgressWriter> getiProgressWriter()
    {
        return iProgressWriter;
    }

    public void setiProgressWriter(Class<? extends IProgressWriter> iProgressWriter)
    {
        this.iProgressWriter = iProgressWriter;
    }

    public Object[] getWriteArges()
    {
        return writeArges;
    }

    public void setWriteArges(Object[] writeArges)
    {
        this.writeArges = writeArges;
    }

    public  Object[] getReadArges()
    {
        return readArges;
    }

    public void setReadArges(Object[] readArges)
    {
        this.readArges = readArges;
    }
}
