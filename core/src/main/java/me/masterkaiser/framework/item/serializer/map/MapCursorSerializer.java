package me.masterkaiser.framework.item.serializer.map;

import eu.okaeri.configs.schema.GenericsDeclaration;
import eu.okaeri.configs.serdes.DeserializationData;
import eu.okaeri.configs.serdes.ObjectSerializer;
import eu.okaeri.configs.serdes.SerializationData;
import lombok.NonNull;
import org.bukkit.map.MapCursor;

public class MapCursorSerializer implements ObjectSerializer<MapCursor> {
    @Override
    public boolean supports(@NonNull Class<? super MapCursor> type) {
        return MapCursor.class.isAssignableFrom(type);
    }

    @Override
    public void serialize(@NonNull MapCursor object, @NonNull SerializationData data, @NonNull GenericsDeclaration generics) {
        data.add("x", object.getX());
        data.add("y", object.getY());
        data.add("direction", object.getDirection());
        data.add("type", object.getType());
        data.add("visible", object.isVisible());
        data.add("caption", object.getCaption());
    }

    @Override
    public MapCursor deserialize(@NonNull DeserializationData data, @NonNull GenericsDeclaration generics) {
        final byte x = data.get("x", Byte.class);
        final byte y = data.get("y", Byte.class);
        final byte direction = data.get("direction", Byte.class);
        final MapCursor.Type type = data.get("type", MapCursor.Type.class);
        final boolean visible = data.get("visible", Boolean.class);
        final String caption = data.get("caption", String.class);

        return new MapCursor(x, y, direction, type, visible, caption);
    }
}
