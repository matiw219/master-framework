package me.masterkaiser.framework.redis.maker;

import me.masterkaiser.framework.maker.BeanMaker;
import me.masterkaiser.framework.maker.BeanProcess;
import me.masterkaiser.framework.maker.BeanProcessType;
import me.masterkaiser.framework.redis.impl.PacketListenerManager;
import me.masterkaiser.framework.redis.packet.Packet;
import me.masterkaiser.framework.redis.packet.PacketHandler;
import me.masterkaiser.framework.redis.packet.PacketListener;

import java.util.Arrays;

@BeanProcess(
        initializationLevel = BeanProcessType.AFTER
)
public class PacketMaker implements BeanMaker {
    private final PacketListenerManager packetListenerManager;

    public PacketMaker(PacketListenerManager packetListenerManager) {
        this.packetListenerManager = packetListenerManager;
    }

    @Override
    public boolean isSupport(Class<?> aClass) {
        return PacketListener.class.isAssignableFrom(aClass);
    }

    @Override
    public Object override(Object o, String s) {
        Arrays.stream(o.getClass().getDeclaredMethods())
                .filter(method -> method.isAnnotationPresent(PacketHandler.class))
                .filter(method -> method.getParameterCount() == 1)
                .filter(method -> Packet.class.isAssignableFrom(method.getParameters()[0].getType()))
                .forEach(method -> {
                    final PacketHandler packetHandler = method.getAnnotation(PacketHandler.class);
                    final Class<?> packetClass = method.getParameters()[0].getType();

                    this.packetListenerManager.addPacketType((Class<? extends Packet>) packetClass, method, o, packetHandler.ignoreYourServer());
                });

        return o;
    }
}
