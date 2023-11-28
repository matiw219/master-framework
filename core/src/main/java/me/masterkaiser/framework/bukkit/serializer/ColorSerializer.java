package me.masterkaiser.framework.bukkit.serializer;

import eu.okaeri.configs.schema.GenericsDeclaration;
import eu.okaeri.configs.serdes.DeserializationData;
import eu.okaeri.configs.serdes.ObjectSerializer;
import eu.okaeri.configs.serdes.SerializationData;
import lombok.NonNull;
import org.bukkit.Color;

public class ColorSerializer implements ObjectSerializer<Color> {
    @Override
    public boolean supports(@NonNull Class<? super Color> type) {
        return Color.class.isAssignableFrom(type);
    }

    @Override
    public void serialize(@NonNull Color object, @NonNull SerializationData data, @NonNull GenericsDeclaration generics) {
        data.add("red", object.getRed());
        data.add("green", object.getGreen());
        data.add("blue", object.getBlue());
    }

    @Override
    public Color deserialize(@NonNull DeserializationData data, @NonNull GenericsDeclaration generics) {
        final int red = data.get("red", Integer.class);
        final int green = data.get("green", Integer.class);
        final int blue = data.get("blue", Integer.class);

        return Color.fromRGB(red, green, blue);
    }
}
