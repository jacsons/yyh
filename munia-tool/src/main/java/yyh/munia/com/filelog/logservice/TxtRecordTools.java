package yyh.munia.com.filelog.logservice;

import org.apache.commons.io.IOUtils;
import yyh.munia.com.filelog.inf.IRecordTools;
import yyh.munia.com.util.LoggerManager;
import yyh.munia.com.util.LoggerType;

import java.io.*;
import java.util.List;

/**
 *
 * txt日志输出器
 * Created by oak on 2017/10/7.
 */
public class TxtRecordTools implements IRecordTools
{


    /**
     * 写日志信息
     * @param taskKey
     * @param modelName
     * @param messages
     */
    @Override
    public void writeMessage(String taskKey, String modelName, List<String> messages)
    {
        String filePath = filePath(taskKey, modelName);

        File file = new File(filePath);
        OutputStream outputStream = null;
        try
        {
            outputStream = new FileOutputStream(file);
            //输出信息至文件中
            for (String message : messages)
            {
                outputStream.write(message.getBytes());
            }
            outputStream.flush();
        }
        catch (FileNotFoundException e)
        {
            LoggerManager.record(LoggerType.ERROR,"FileNotFoundException error");
        }
        catch (IOException e)
        {
            LoggerManager.record(LoggerType.ERROR,"IOException error");
        }
        finally
        {
            IOUtils.closeQuietly(outputStream);
        }

    }

    /**
     * 文件压缩
     */
    @Override
    public String compressFiles(String taskkey)
    {

        return "";
    }



    /**
     * 文件路径
     * @param fileName
     * @return
     */
    private String filePath(String taskKey, String fileName)
    {

        return "";
    }

}





