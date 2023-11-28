package me.masterkaiser.framework.redis.impl;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import me.masterkaiser.framework.redis.packet.Packet;
import me.masterkaiser.framework.util.gson.GsonHelper;
import org.redisson.api.listener.MessageListener;
import org.springframework.stereotype.Component;

@Component
public class RedisPacketHandler implements MessageListener<String> {
    private final PacketListenerManager packetListenerManager;

    public RedisPacketHandler(PacketListenerManager packetListenerManager) {
        this.packetListenerManager = packetListenerManager;
    }

    @Override
    public void onMessage(CharSequence channel, String msg) {
        final JsonElement jsonElement = JsonParser.parseString(msg);
        final JsonObject jsonObject = jsonElement.getAsJsonObject();

        final String packetClass = jsonObject.get("packetClass").getAsString();

        try {
            final Class<?> clazz = Class.forName(packetClass);
            final Packet packet = (Packet) GsonHelper.getGson().fromJson(msg, clazz);

            this.packetListenerManager.handlePacket(packet);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }
}
