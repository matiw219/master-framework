package me.masterkaiser.framework.item.serializer;

import eu.okaeri.configs.schema.GenericsDeclaration;
import eu.okaeri.configs.serdes.DeserializationData;
import eu.okaeri.configs.serdes.ObjectSerializer;
import eu.okaeri.configs.serdes.SerializationData;
import lombok.NonNull;
import me.masterkaiser.framework.item.meta.MasterSuspiciousStewMeta;
import org.bukkit.potion.PotionEffect;

public class MasterSuspiciousStewMetaSerializer implements ObjectSerializer<MasterSuspiciousStewMeta> {
    @Override
    public boolean supports(@NonNull Class<? super MasterSuspiciousStewMeta> type) {
        return MasterSuspiciousStewMeta.class.isAssignableFrom(type);
    }

    @Override
    public void serialize(@NonNull MasterSuspiciousStewMeta object, @NonNull SerializationData data, @NonNull GenericsDeclaration generics) {
        data.add("effects", object.getEffects());
    }

    @Override
    public MasterSuspiciousStewMeta deserialize(@NonNull DeserializationData data, @NonNull GenericsDeclaration generics) {
        return new MasterSuspiciousStewMeta(data.getAsList("effects", PotionEffect.class));
    }
}
