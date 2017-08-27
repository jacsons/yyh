package yyh.munia.com.file;

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
    boolean travelStart();

    boolean travel(Object...objects);

    void travelEnd();

}
