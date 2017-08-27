import org.junit.Test;
import yyh.munia.com.util.LoggerManager;
import yyh.munia.com.util.LoggerType;

/**
 * Created by oak on 2017/7/28.
 */
public class TestLoggerManager
{
    @Test
    public void test1()
    {

        LoggerManager.record(LoggerType.INFO,"hello");
    }

}
