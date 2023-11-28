package me.masterkaiser.framework.redis;

import lombok.Getter;
import me.masterkaiser.framework.redis.impl.RedisFactory;
import me.masterkaiser.framework.redis.impl.RedisPacketHandler;
import me.masterkaiser.framework.redis.packet.Packet;
import org.jetbrains.annotations.NotNull;
import org.redisson.api.RTopic;

abstract public class RedisTopic {
    private final RedisFactory redisFactory;
    private final RedisPacketHandler redisPacketHandler;
    private @Getter RTopic topic;

    protected RedisTopic(RedisFactory redisFactory, RedisPacketHandler redisPacketHandler) {
        this.redisFactory = redisFactory;
        this.redisPacketHandler = redisPacketHandler;
        start();
    }

    protected abstract String topicName();

    public void start() {
        this.topic = this.redisFactory.getRedissonClient().getTopic(topicName());

        this.topic.addListener(String.class, this.redisPacketHandler);
    }

    public void sendPacket(@NotNull Packet packet, boolean async) {
        if (async) {
            getTopic().publishAsync(packet.toJson());
        } else {
            getTopic().publish(packet.toJson());
        }
    }

    public void sendPacket(@NotNull Packet packet) {
        sendPacket(packet, true);
    }
}
