package me.masterkaiser.framework.item.serializer;

import eu.okaeri.configs.schema.GenericsDeclaration;
import eu.okaeri.configs.serdes.DeserializationData;
import eu.okaeri.configs.serdes.ObjectSerializer;
import eu.okaeri.configs.serdes.SerializationData;
import lombok.NonNull;
import me.masterkaiser.framework.item.meta.MasterLeatherArmorMeta;
import org.bukkit.Color;

public class MasterLeatherArmorMetaSerializer implements ObjectSerializer<MasterLeatherArmorMeta> {
    @Override
    public boolean supports(@NonNull Class<? super MasterLeatherArmorMeta> type) {
        return MasterLeatherArmorMeta.class.isAssignableFrom(type);
    }

    @Override
    public void serialize(@NonNull MasterLeatherArmorMeta object, @NonNull SerializationData data, @NonNull GenericsDeclaration generics) {
        data.add("color", object.getColor());
    }

    @Override
    public MasterLeatherArmorMeta deserialize(@NonNull DeserializationData data, @NonNull GenericsDeclaration generics) {
        return new MasterLeatherArmorMeta(data.get("color", Color.class));
    }
}
