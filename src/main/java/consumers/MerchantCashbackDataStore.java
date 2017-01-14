package consumers;

import com.lambdaworks.redis.RedisClient;
import com.lambdaworks.redis.api.StatefulRedisConnection;

/**
 * Created by apple on 14/01/17.
 */
public class MerchantCashbackDataStore {
    private final StatefulRedisConnection<String, String> connection;

    public MerchantCashbackDataStore(){
        RedisClient client = RedisClient.create("redis://localhost");
        connection = client.connect();
    }

    public void update(int merchantId, int cashbackPercentage) {
        connection.sync().set("merchant_cashback_" + merchantId, String.valueOf(cashbackPercentage));
    }
}
