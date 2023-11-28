package me.masterkaiser.framework.redis.packet;

import lombok.Getter;
import me.masterkaiser.framework.redis.RedisSettings;
import me.masterkaiser.framework.util.gson.GsonHelper;

public class Packet {
    private final @Getter String packetClass = getClass().getTypeName();
    private final @Getter String r_serverId = RedisSettings.serverId;

    public String toJson() {
        return GsonHelper.getGson().toJson(this, this.getClass());
    }
}
