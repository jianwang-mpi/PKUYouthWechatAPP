package pkuyouth;

import org.junit.Test;
import redis.clients.jedis.Jedis;

/**
 * Created by wangjian on 17-11-6.
 */
public class RedisTest extends BaseTest{
    @Test
    public void test(){
        Jedis jedis = new Jedis("127.0.0.1", 6379);
        String result = jedis.get("q2232");
        System.out.println(result);
    }
}
