package yyh.munia.com.util;

import yyh.munia.com.fileParser.TxtParse;
import yyh.munia.com.fileParser.ITravel;
import yyh.munia.com.fileParser.NullParse;
import yyh.munia.com.fileParser.model.EFileType;

import java.io.File;

/**
 * 文件解析工厂类生成
 * Created by oak on 2017/8/23.
 */
public class FileParseFactory
{

    /**
     * 获取解析器
     * @param eFileType
     * @param filePath
     * @param iTravel
     * @return
     */
    public static ITravel getParseImpl(EFileType eFileType,String filePath,ITravel iTravel)
    {
        switch (eFileType)
        {
            case TXT_TYPE:
                return new TxtParse(filePath,iTravel);
            default:
                LoggerManager.record(LoggerType.WARN,"can't found type:" + eFileType.getTypeID() + "parseImpl");
                return new NullParse();
        }
    }

    /**
     * 获取解析器
     * @param eFileType
     * @param file
     * @param iTravel
     * @return
     */
    public static ITravel getParseImpl(EFileType eFileType,File file, ITravel iTravel)
    {
        switch (eFileType)
        {
            case TXT_TYPE:
                return new TxtParse(file,iTravel);
            default:
                LoggerManager.record(LoggerType.WARN,"can't found type:" + eFileType.getTypeID() + "parseImpl");
                return new NullParse();
        }
    }

}
