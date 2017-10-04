package yyh.munia.com.fileParser;

import yyh.munia.com.fileParser.model.LineTravel;

/**
 * Created by oak on 2017/8/23.
 */
public class CsvParse extends LineTravel
{


    @Override
    public boolean travelStart()
    {
        return false;
    }

    @Override
    public boolean travel(Object... objects)
    {
        return false;
    }

    @Override
    public void travelEnd()
    {

    }
}
