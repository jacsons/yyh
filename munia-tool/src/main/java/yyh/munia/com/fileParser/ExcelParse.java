package yyh.munia.com.fileParser;

import java.io.InputStream;

/**
 * excel解析
 * Created by oak on 2017/8/18.
 */
public class ExcelParse implements ITravel
{

    private boolean isFile = false;

    private String filePath;

    private InputStream inputStream;

    /**
     *
     * @param filePath
     * @param isFile
     */
    public ExcelParse(String filePath,boolean isFile)
    {

        this.filePath = filePath;
        this.isFile = isFile;

    }

    /**
     * 分析开始
     * @param inputStream
     * @param isFile
     */
    public ExcelParse(InputStream inputStream, boolean isFile)
    {
        this.inputStream = inputStream;
        this.isFile = isFile;
    }

    /**
     * 解析开始
     * @return
     */
    public boolean travelStart()
    {

        return true;
    }

    @Override
    public boolean travel(Object...objects)
    {
        if(isFile)
        {


        }
        else
        {

        }

        return true;
    }


    /**
     * 解析结束
     * @return
     */
    public void travelEnd()
    {

    }
}
