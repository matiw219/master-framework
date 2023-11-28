package me.masterkaiser.framework.item.serializer;

import eu.okaeri.configs.schema.GenericsDeclaration;
import eu.okaeri.configs.serdes.DeserializationData;
import eu.okaeri.configs.serdes.ObjectSerializer;
import eu.okaeri.configs.serdes.SerializationData;
import lombok.NonNull;
import me.masterkaiser.framework.item.meta.MasterTropicalFishBucketMeta;
import org.bukkit.DyeColor;
import org.bukkit.entity.TropicalFish;

public class MasterTropicalFishBucketMetaSerializer implements ObjectSerializer<MasterTropicalFishBucketMeta> {
    @Override
    public boolean supports(@NonNull Class<? super MasterTropicalFishBucketMeta> type) {
        return MasterTropicalFishBucketMeta.class.isAssignableFrom(type);
    }

    @Override
    public void serialize(@NonNull MasterTropicalFishBucketMeta object, @NonNull SerializationData data, @NonNull GenericsDeclaration generics) {
        data.add("patternColor", object.getPatternColor());
        data.add("bodyColor", object.getBodyColor());
        data.add("pattern", object.getPattern());
    }

    @Override
    public MasterTropicalFishBucketMeta deserialize(@NonNull DeserializationData data, @NonNull GenericsDeclaration generics) {
        final DyeColor patternColor = data.get("patternColor", DyeColor.class);
        final DyeColor bodyColor = data.get("bodyColor", DyeColor.class);
        final TropicalFish.Pattern pattern = data.get("pattern", TropicalFish.Pattern.class);

        return new MasterTropicalFishBucketMeta(patternColor, bodyColor, pattern);
    }
}
