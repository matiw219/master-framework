package me.masterkaiser.framework.item.serializer.map;

import eu.okaeri.configs.schema.GenericsDeclaration;
import eu.okaeri.configs.serdes.DeserializationData;
import eu.okaeri.configs.serdes.ObjectSerializer;
import eu.okaeri.configs.serdes.SerializationData;
import lombok.NonNull;
import me.masterkaiser.framework.item.meta.map.MasterMapPixel;
import org.bukkit.Color;

public class MapPixelSerializer implements ObjectSerializer<MasterMapPixel> {
    @Override
    public boolean supports(@NonNull Class<? super MasterMapPixel> type) {
        return MasterMapPixel.class.isAssignableFrom(type);
    }

    @Override
    public void serialize(@NonNull MasterMapPixel object, @NonNull SerializationData data, @NonNull GenericsDeclaration generics) {
        data.add("x", object.getX());
        data.add("y", object.getY());
        data.add("color", object.getColor());
    }

    @Override
    public MasterMapPixel deserialize(@NonNull DeserializationData data, @NonNull GenericsDeclaration generics) {
        final int x = data.get("x", Integer.class);
        final int y = data.get("y", Integer.class);
        final Color color = data.get("color", Color.class);

        return new MasterMapPixel(x, y, color);
    }
}
