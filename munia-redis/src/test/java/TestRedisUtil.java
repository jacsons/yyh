import org.junit.Test;
import yyh.munia.com.util.redis.util.RedisUtil;

/**
 *
 * 测试redis类的使用
 * Created by oak on 2017/7/28.
 */
public class TestRedisUtil
{

    @Test
    public void TestHset()
    {
        RedisUtil.hset(8,"Test1","key2","hello3");
        System.out.println(RedisUtil.hget(8,"Test1","key2"));
    }
}
