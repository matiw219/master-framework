package me.masterkaiser.framework.item.serializer;

import eu.okaeri.configs.schema.GenericsDeclaration;
import eu.okaeri.configs.serdes.DeserializationData;
import eu.okaeri.configs.serdes.ObjectSerializer;
import eu.okaeri.configs.serdes.SerializationData;
import lombok.NonNull;
import me.masterkaiser.framework.item.meta.MasterFireworkEffectMeta;
import org.bukkit.FireworkEffect;

public class MasterFireworkEffectMetaSerializer implements ObjectSerializer<MasterFireworkEffectMeta> {
    @Override
    public boolean supports(@NonNull Class<? super MasterFireworkEffectMeta> type) {
        return MasterFireworkEffectMeta.class.isAssignableFrom(type);
    }

    @Override
    public void serialize(@NonNull MasterFireworkEffectMeta object, @NonNull SerializationData data, @NonNull GenericsDeclaration generics) {
        data.add("fireworkEffect", object.getFireworkEffect());
    }

    @Override
    public MasterFireworkEffectMeta deserialize(@NonNull DeserializationData data, @NonNull GenericsDeclaration generics) {
        return new MasterFireworkEffectMeta(data.get("fireworkEffect", FireworkEffect.class));
    }
}
