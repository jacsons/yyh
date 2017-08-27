import org.junit.Test;
import yyh.munia.com.util.PropertiesUtil;

import java.util.Properties;

/**
 * Created by oak on 2017/7/27.
 */
public class TestPropertiesUtil
{

    @Test
    public void  TestRead()
    {
        Properties properties = PropertiesUtil.getProperties();
        System.out.println(properties.get("redis.ip"));
    }


}
