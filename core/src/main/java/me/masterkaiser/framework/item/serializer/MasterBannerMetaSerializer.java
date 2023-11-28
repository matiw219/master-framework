package me.masterkaiser.framework.item.serializer;

import eu.okaeri.configs.schema.GenericsDeclaration;
import eu.okaeri.configs.serdes.DeserializationData;
import eu.okaeri.configs.serdes.ObjectSerializer;
import eu.okaeri.configs.serdes.SerializationData;
import lombok.NonNull;
import me.masterkaiser.framework.item.meta.MasterBannerMeta;
import org.bukkit.block.banner.Pattern;

public class MasterBannerMetaSerializer implements ObjectSerializer<MasterBannerMeta> {
    @Override
    public boolean supports(@NonNull Class<? super MasterBannerMeta> type) {
        return MasterBannerMeta.class.isAssignableFrom(type);
    }

    @Override
    public void serialize(@NonNull MasterBannerMeta object, @NonNull SerializationData data, @NonNull GenericsDeclaration generics) {
        data.add("patterns", object.getPatterns());
    }

    @Override
    public MasterBannerMeta deserialize(@NonNull DeserializationData data, @NonNull GenericsDeclaration generics) {
        return new MasterBannerMeta(
                data.getAsList("patterns", Pattern.class)
        );

    }
}
