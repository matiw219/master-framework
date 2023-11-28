package me.masterkaiser.framework.redis.impl;

import me.masterkaiser.framework.redis.RedisSettings;
import me.masterkaiser.framework.redis.packet.Packet;
import me.masterkaiser.framework.redis.packet.PacketType;
import org.jetbrains.annotations.NotNull;
import org.springframework.stereotype.Component;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;

@Component
public class PacketListenerManager {
    private final Map<Class<? extends Packet>, PacketType> packetTypes = new HashMap<>();

    public void addPacketType(@NotNull Class<? extends Packet> clazz, @NotNull Method method, @NotNull Object instance, boolean ignore) {
        this.packetTypes.put(clazz, new PacketType(method, instance, ignore));
    }

    public PacketType getPacket(@NotNull Class<? extends Packet> clazz) {
        return this.packetTypes.getOrDefault(clazz, null);
    }

    public void handlePacket(@NotNull Packet packet) {
        final PacketType packetType = getPacket(packet.getClass());

        if (packetType == null) {
            return;
        }

        if (packet.getR_serverId() != null && packet.getR_serverId().equals(RedisSettings.serverId) && packetType.isIgnore()) {
            return;
        }

        try {
            packetType.getMethod().invoke(packetType.getInstance(), packet);
        } catch (IllegalAccessException | InvocationTargetException e) {
            throw new RuntimeException(e);
        }
    }
}
