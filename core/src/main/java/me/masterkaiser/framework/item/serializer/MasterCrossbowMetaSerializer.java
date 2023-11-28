package me.masterkaiser.framework.item.serializer;

import eu.okaeri.configs.schema.GenericsDeclaration;
import eu.okaeri.configs.serdes.DeserializationData;
import eu.okaeri.configs.serdes.ObjectSerializer;
import eu.okaeri.configs.serdes.SerializationData;
import lombok.NonNull;
import me.masterkaiser.framework.item.ItemBuilder;
import me.masterkaiser.framework.item.meta.MasterCrossbowMeta;

public class MasterCrossbowMetaSerializer implements ObjectSerializer<MasterCrossbowMeta> {
    @Override
    public boolean supports(@NonNull Class<? super MasterCrossbowMeta> type) {
        return MasterCrossbowMeta.class.isAssignableFrom(type);
    }

    @Override
    public void serialize(@NonNull MasterCrossbowMeta object, @NonNull SerializationData data, @NonNull GenericsDeclaration generics) {
        data.add("chargedProjectiles", object.getChargedProjectiles());
    }

    @Override
    public MasterCrossbowMeta deserialize(@NonNull DeserializationData data, @NonNull GenericsDeclaration generics) {
        return new MasterCrossbowMeta(data.getAsList("chargedProjectiles", ItemBuilder.class));
    }
}
