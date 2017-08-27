package yyh.munia.com.file;

import yyh.munia.com.util.LoggerManager;
import yyh.munia.com.util.LoggerType;

/**
 * Created by oak on 2017/8/23.
 */
public class NullParse implements ITravel
{
    @Override
    public boolean travelStart()
    {
        LoggerManager.record(LoggerType.INFO,"NullParse parse");
        return false;
    }

    @Override
    public boolean travel(Object... objects)
    {
        LoggerManager.record(LoggerType.INFO,"NullParse parse");
        return false;
    }

    @Override
    public void travelEnd()
    {
        LoggerManager.record(LoggerType.INFO,"NullParse parse");
    }
}
