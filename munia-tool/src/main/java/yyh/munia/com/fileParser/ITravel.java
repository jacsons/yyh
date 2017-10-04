package yyh.munia.com.fileParser;

/**
 * Created by oak on 2017/8/18.
 */
public interface ITravel
{
    /**
     *
     *
     * @return
     */

    default boolean initParam(String path, ITravel iTravel)
    {
        return true;
    }


    boolean travelStart();

    boolean travel(Object...objects);

    void travelEnd();

}
