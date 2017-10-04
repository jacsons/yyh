package yyh.munia.com.util;

import yyh.munia.com.fileParser.ITravel;
import yyh.munia.com.fileParser.TxtParse;
import yyh.munia.com.fileParser.XmlParser;
import yyh.munia.com.fileParser.model.EFileType;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by oak on 2017/8/18.
 */
public class FileParseUtil
{

    private static Map<EFileType,ITravel> travelMap = new HashMap<>();


    static
    {
        travelMap.put(EFileType.TXT_TYPE, new TxtParse());
        travelMap.put(EFileType.XML_TYPE, new XmlParser());
    }



    public void parserFile(EFileType eFileType, String path, ITravel travel)
    {
        ITravel iTravel = travelMap.get(eFileType);
        if (iTravel == null)
        {
            LoggerManager.record(LoggerType.ERROR, "don't has that parser" + eFileType);
            return;
        }

        iTravel.initParam(path, travel);

        if (!iTravel.travelStart())
        {
            return;
        }
        //开始解析
        iTravel.travel();
        iTravel.travelEnd();

    }

    /**
     * 获取解析对象
     * @return
     */
    public static FileParseUtil getInstance()
    {
        return LazyInstall.getIntance();
    }



   public static class LazyInstall
   {
       public static final FileParseUtil INSTANCE = new FileParseUtil();

       public static FileParseUtil getIntance()
       {
           return INSTANCE;

       }
   }
}
