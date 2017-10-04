package yyh.munia.com.fileParser;

import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;
import yyh.munia.com.util.LoggerManager;
import yyh.munia.com.util.LoggerType;

import java.io.File;

/**
 * xml解析类，解析xml文件
 * Created by oak on 2017/10/3.
 */
public class XmlParser implements ITravel
{

    /**
     * 路径
     */
    private String path;

    /**
     * 回掉器
     */
    private ITravel iTravel;


    @Override
    public boolean initParam(String path, ITravel iTravel)
    {
        this.path = path;
        this.iTravel = iTravel;
        return true;
    }

    @Override
    public boolean travelStart()
    {
        return this.path != null && this.iTravel != null;
    }

    @Override
    public boolean travel(Object... objects)
    {
        File file = new File(XmlParser.class.getClassLoader().getResource(this.path).getFile());
        SAXReader saxReader = new SAXReader();
        try
        {
            Document document = saxReader.read(file);
            Element element = document.getRootElement();
            this.iTravel.travel(element);
            return true;
        }
        catch (DocumentException e)
        {
            LoggerManager.record(LoggerType.ERROR,"parser Xml failed, this xml is :" + this.path);
        }
        return false;
    }

    @Override
    public void travelEnd()
    {

    }
}
