package FileTest;

import yyh.munia.com.fileParser.ITravel;

/**
 * Created by oak on 2017/9/29.
 */
public class TxtTravel implements ITravel
{
    @Override
    public boolean travelStart()
    {
        return true;
    }

    @Override
    public boolean travel(Object... objects)
    {
        String lineStr = objects[0].toString();
        System.out.println(lineStr);
        return true;
    }

    @Override
    public void travelEnd()
    {

    }
}
