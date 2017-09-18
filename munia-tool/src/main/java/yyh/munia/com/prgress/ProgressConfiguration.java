package yyh.munia.com.prgress;

import org.springframework.beans.factory.InitializingBean;
import yyh.munia.com.prgress.impl.DefaultTool;
import yyh.munia.com.prgress.inf.IProgressReader;
import yyh.munia.com.prgress.inf.IProgressWriter;

/**
 *
 *
 * Created by oak on 2017/9/18.
 */
public class ProgressConfiguration implements InitializingBean
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
     * 写数据构造的参数
     */
    private  Object[] writeArges;


    /**
     * 读构造函数的参数
     */
    private  Object[] readArges;


    @Override
    public void afterPropertiesSet() throws Exception
    {
        ProgressFactory.getInstanc().setiProgressReader(getReaderClass());
        ProgressFactory.getInstanc().setiProgressWriter(getWriterClass());

        ProgressFactory.getInstanc().setReadArges(readArges);
        ProgressFactory.getInstanc().setWriteArges(writeArges);
    }

    /**
     * 获取
     * @return
     */
    private Class<? extends IProgressReader> getReaderClass()
    {
        if(iProgressReader == null)
        {
            return DefaultTool.class;
        }

        return this.iProgressReader;
    }

    /**
     * 获取写句柄
     * @return
     */
    private Class<? extends IProgressWriter> getWriterClass()
    {
        if(this.iProgressWriter == null)
        {
            return DefaultTool.class;
        }
        return this.iProgressWriter;
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

    public Object[] getReadArges()
    {
        return readArges;
    }

    public void setReadArges(Object[] readArges)
    {
        this.readArges = readArges;
    }
}
