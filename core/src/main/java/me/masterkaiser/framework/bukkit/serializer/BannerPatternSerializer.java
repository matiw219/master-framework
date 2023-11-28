package me.masterkaiser.framework.bukkit.serializer;

import eu.okaeri.configs.schema.GenericsDeclaration;
import eu.okaeri.configs.serdes.DeserializationData;
import eu.okaeri.configs.serdes.ObjectSerializer;
import eu.okaeri.configs.serdes.SerializationData;
import lombok.NonNull;
import org.bukkit.DyeColor;
import org.bukkit.block.banner.Pattern;
import org.bukkit.block.banner.PatternType;

public class BannerPatternSerializer implements ObjectSerializer<Pattern> {
    @Override
    public boolean supports(@NonNull Class<? super Pattern> type) {
        return Pattern.class.isAssignableFrom(type);
    }

    @Override
    public void serialize(@NonNull Pattern object, @NonNull SerializationData data, @NonNull GenericsDeclaration generics) {
        data.add("color", object.getColor());
        data.add("pattern", object.getPattern());
    }

    @Override
    public Pattern deserialize(@NonNull DeserializationData data, @NonNull GenericsDeclaration generics) {
        final DyeColor color = data.get("color", DyeColor.class);
        final PatternType pattern = data.get("pattern", PatternType.class);

        return new Pattern(color, pattern);
    }
}
