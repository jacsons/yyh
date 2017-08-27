package yyh.dbservice.com.db.error;

/**
 * 异常
 * Created by oak on 2017/8/16.
 */
public class DBException extends Exception
{
    public static final long serialVersionUID = -3387516993124229948L;

    public String message;

    public DBException(String message)
    {
        super();
        this.message = message;
    }

    public DBException(Exception e)
    {
        this.message = e.getMessage();
    }

    @Override
    public String toString()
    {
        return this.message;
    }

    @Override
    public void printStackTrace()
    {
        printStackTrace(System.err);

    }
}
