package FileTest;

import org.dom4j.Element;
import yyh.munia.com.fileParser.ITravel;

/**
 * Created by oak on 2017/10/3.
 */
public class xmlTravel implements ITravel
{
    @Override
    public boolean travelStart()
    {
        return true;
    }

    /**
     * 解析Element
     * @param objects
     * @return
     */
    @Override
    public boolean travel(Object... objects)
    {
        Element element = (Element)objects[0];





        return true;
    }

    @Override
    public void travelEnd()
    {

    }
}
