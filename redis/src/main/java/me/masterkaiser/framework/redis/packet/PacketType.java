package me.masterkaiser.framework.redis.packet;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.lang.reflect.Method;

@AllArgsConstructor
@Data
public class PacketType {
    private Method method;
    private Object instance;
    private boolean ignore;
}
