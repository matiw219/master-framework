package me.masterkaiser.framework.redis;

import lombok.Getter;
import me.masterkaiser.framework.redis.impl.RedisFactory;
import org.redisson.api.RMap;

import java.time.Instant;

abstract public class RedisMap<K, V> {
    private final RedisFactory redisFactory;
    private @Getter RMap<K, V> map;

    public RedisMap(RedisFactory redisFactory) {
        this.redisFactory = redisFactory;
        start();
    }

    protected abstract String mapName();

    public void start() {
        this.map = this.redisFactory.getRedissonClient().getMap(mapName());

        long ttl31days = System.currentTimeMillis() + (1000L * 60L * 60L * 24L * 31L);
        this.map.expire(Instant.ofEpochMilli(ttl31days));
    }
}
