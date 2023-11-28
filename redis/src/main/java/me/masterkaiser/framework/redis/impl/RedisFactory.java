package me.masterkaiser.framework.redis.impl;

import lombok.Getter;
import me.masterkaiser.framework.Initializable;
import org.redisson.Redisson;
import org.redisson.api.RedissonClient;
import org.redisson.config.Config;
import org.springframework.stereotype.Component;

@Component
public class RedisFactory implements Initializable {
    private final Redis redis;
    private @Getter RedissonClient redissonClient;

    public RedisFactory(Redis redis) {
        this.redis = redis;
    }

    @Override
    public void initialize() {
        final Config config = new Config();
        config.useSingleServer().setAddress(this.redis.address).setDatabase(this.redis.database).setPassword(this.redis.password);

        this.redissonClient = Redisson.create(config);
    }
}
