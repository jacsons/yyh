package yyh.munia.com.file;

import org.apache.commons.io.LineIterator;
import yyh.munia.com.util.LoggerManager;
import yyh.munia.com.util.LoggerType;

import java.io.*;

/**
 * txt解析
 * Created by oak on 2017/8/18.
 */
public class TxtParse implements ITravel
{
    private File file;

    private String filePath;

    /**
     * 提供处理完一行数据后的处理方式
     */
    private ITravel iTravel;

    public TxtParse()
    {

    }
    /**
     * txt解析
     */
    public TxtParse(String filePath, ITravel iTravel)
    {
        this.filePath = filePath;
        this.iTravel = iTravel;
    }

    /**
     * 解析
     * @param file
     */
    public TxtParse(File file, ITravel iTravel)
    {
        this.file = file;
        this.iTravel = iTravel;
    }

    /**
     * 开始启动，这个需要先调用
     * @return
     */
    @Override
    public boolean travelStart()
    {
        if(this.filePath == null && this.file == null )
        {
            LoggerManager.record(LoggerType.ERROR,"TxtParse: please set file path");
            return false;
        }
        return true;
    }

    /**
     * 进行文件搜索
     * @param objects
     * @return
     */
    @Override
    public boolean travel(Object...objects)
    {
        if(iTravel == null)
        {
            LoggerManager.record(LoggerType.ERROR,"TxtParse: iTravel is null,please set iTravel");
            return false;
        }


        if(this.file != null)
        {
            travelLine(this.file);
        }
        else
        {
            travelLine(this.filePath);
        }
        return true;
    }


    @Override
    public void travelEnd()
    {
    }

    public File getFile()
    {
        return file;
    }

    public void setFile(File file)
    {
        this.file = file;
    }

    public String getFilePath()
    {
        return filePath;
    }

    public void setFilePath(String filePath)
    {
        this.filePath = filePath;
    }

    public ITravel getiTravel()
    {
        return iTravel;
    }

    public void setiTravel(ITravel iTravel)
    {
        this.iTravel = iTravel;
    }

    /**
     * 读取而谨慎
     * @param file
     */
    private void travelLine(File file)
    {
        try
        {
            Reader reader = new FileReader(file);
            LineIterator lineIterator = new LineIterator(reader);

            String lineStr;
            while (lineIterator.hasNext())
            {
                lineStr = lineIterator.nextLine();
                this.iTravel.travel(lineStr);
            }
        }
        catch (FileNotFoundException e)
        {
            LoggerManager.record(LoggerType.ERROR,"TxtParse open Error");
        }
    }

    /**
     * 搜索文件
     * @param filePath
     */
    private void travelLine(String filePath)
    {
        //文件
        File file = new File(filePath);
        if(!file.exists())
        {
            LoggerManager.record(LoggerType.ERROR,"TxtParse pase Error");
            return;
        }
        travelLine(filePath);
    }

}
