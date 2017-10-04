package yyh.munia.com.util;

import org.apache.commons.lang3.StringUtils;
import yyh.munia.com.lagoTask.ILegoTask;
import yyh.munia.com.fileParser.ITravel;
import yyh.munia.com.fileParser.model.EFileType;
import yyh.munia.com.prgress.model.EnumTaskStatus;

import java.io.File;

/**
 * 文件解析函数处理函数
 * Created by oak on 2017/8/22.
 */
public class FileTaskMagr extends ILegoTask<EnumTaskStatus>
{

    //文件解析处理完后的处理函数
    private ITravel iTravel;

    /**
     * 文件类型
     */
    private EFileType eFileType;

    private String filePath;

    private File file;

    public FileTaskMagr(ITravel iTravel,EFileType eFileType,String filePath)
    {
        this.iTravel = iTravel;
        this.eFileType = eFileType;
        this.filePath = filePath;
    }

    public FileTaskMagr(ITravel iTravel,EFileType eFileType, File file)
    {
        this.iTravel = iTravel;
        this.eFileType = eFileType;
        this.file = file;
    }


    @Override
    public EnumTaskStatus main()
    {
        LoggerManager.record(LoggerType.INFO,"start parse fileParser " + this.filePath);

        if(checkHasNessaryInfo(this.file,this.filePath))
        {
            LoggerManager.record(LoggerType.WARN,"Pleases set neccessary fileParser path");
            return EnumTaskStatus.FAILED;

        }

        FileParseUtil fileParseUtil = FileParseUtil.getInstance();
        fileParseUtil.parserFile(this.eFileType, this.filePath, this.iTravel);

        LoggerManager.record(LoggerType.INFO,"end parser fileParser " + this.filePath);
        return EnumTaskStatus.SUCCESS;
    }

    @Override
    public EnumTaskStatus handleException(RuntimeException e)
    {
        return EnumTaskStatus.FAILED;
    }

    /**
     * 检测是否含有必要信息
     * @return
     */
    private boolean checkHasNessaryInfo(File file, String filePath)
    {
        return file == null && StringUtils.isEmpty(filePath);
    }

}
