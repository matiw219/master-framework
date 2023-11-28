package me.masterkaiser.framework.bukkit.serializer;

import eu.okaeri.configs.schema.GenericsDeclaration;
import eu.okaeri.configs.serdes.DeserializationData;
import eu.okaeri.configs.serdes.ObjectSerializer;
import eu.okaeri.configs.serdes.SerializationData;
import lombok.NonNull;
import org.bukkit.Color;
import org.bukkit.FireworkEffect;

import java.util.List;

public class FireworkEffectSerializer implements ObjectSerializer<FireworkEffect> {
    @Override
    public boolean supports(@NonNull Class<? super FireworkEffect> type) {
        return FireworkEffect.class.isAssignableFrom(type);
    }

    @Override
    public void serialize(@NonNull FireworkEffect object, @NonNull SerializationData data, @NonNull GenericsDeclaration generics) {
        data.add("flicker", object.hasFlicker());
        data.add("trail", object.hasTrail());
        data.add("colors", object.getColors());
        data.add("fadeColors", object.getFadeColors());
        data.add("type", object.getType());
    }

    @Override
    public FireworkEffect deserialize(@NonNull DeserializationData data, @NonNull GenericsDeclaration generics) {
        final boolean flicker = data.get("flicker", Boolean.class);
        final boolean trail = data.get("trail", Boolean.class);
        final List<Color> colors = data.getAsList("colors", Color.class);
        final List<Color> fadeColors = data.getAsList("fadeColors", Color.class);
        final FireworkEffect.Type type = data.get("type", FireworkEffect.Type.class);

        return FireworkEffect.builder()
                .flicker(flicker)
                .trail(trail)
                .withColor(colors)
                .withFade(fadeColors)
                .with(type)
                .build();
    }
}
