package me.masterkaiser.framework.bukkit.serializer;

import eu.okaeri.configs.schema.GenericsDeclaration;
import eu.okaeri.configs.serdes.DeserializationData;
import eu.okaeri.configs.serdes.ObjectSerializer;
import eu.okaeri.configs.serdes.SerializationData;
import lombok.NonNull;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.World;

public class LocationSerializer implements ObjectSerializer<Location> {
    @Override
    public boolean supports(@NonNull Class<? super Location> type) {
        return Location.class.isAssignableFrom(type);
    }

    @Override
    public void serialize(@NonNull Location object, @NonNull SerializationData data, @NonNull GenericsDeclaration generics) {
        if (object.getWorld() == null) {
            data.add("correct", false);
            return;
        }

        data.add("world", object.getWorld().getName());
        data.add("x", object.getX());
        data.add("y", object.getY());
        data.add("z", object.getZ());

        if (object.getYaw() == 0F && object.getPitch() == 0F) {
            return;
        }

        data.add("yaw", object.getYaw());
        data.add("pitch", object.getPitch());
    }

    @Override
    public Location deserialize(@NonNull DeserializationData data, @NonNull GenericsDeclaration generics) {
        if (data.containsKey("correct") && !data.get("correct", Boolean.class)) {
            return null;
        }

        final String worldName = data.get("world", String.class);
        try {
            final World world = Bukkit.getWorld(worldName);
            final double x = data.get("x", Double.class);
            final double y = data.get("y", Double.class);
            final double z = data.get("z", Double.class);
            final float yaw = data.containsKey("yaw") ? data.get("yaw", Float.class) : 0F;
            final float pitch = data.containsKey("pitch") ? data.get("pitch", Float.class) : 0F;

            return new Location(world, x, y, z, yaw, pitch);
        } catch (NullPointerException e) {
            return null;
        }
    }
}
