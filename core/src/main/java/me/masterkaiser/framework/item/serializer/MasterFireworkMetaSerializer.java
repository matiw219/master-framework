package me.masterkaiser.framework.item.serializer;

import eu.okaeri.configs.schema.GenericsDeclaration;
import eu.okaeri.configs.serdes.DeserializationData;
import eu.okaeri.configs.serdes.ObjectSerializer;
import eu.okaeri.configs.serdes.SerializationData;
import lombok.NonNull;
import me.masterkaiser.framework.item.meta.MasterFireworkMeta;
import org.bukkit.FireworkEffect;

import java.util.List;

public class MasterFireworkMetaSerializer implements ObjectSerializer<MasterFireworkMeta> {
    @Override
    public boolean supports(@NonNull Class<? super MasterFireworkMeta> type) {
        return MasterFireworkMeta.class.isAssignableFrom(type);
    }

    @Override
    public void serialize(@NonNull MasterFireworkMeta object, @NonNull SerializationData data, @NonNull GenericsDeclaration generics) {
        data.add("effects", object.getEffects());
        data.add("power", object.getPower());
    }

    @Override
    public MasterFireworkMeta deserialize(@NonNull DeserializationData data, @NonNull GenericsDeclaration generics) {
        final List<FireworkEffect> effects = data.getAsList("effects", FireworkEffect.class);
        final int power = data.get("power", Integer.class);

        return new MasterFireworkMeta(effects, power);
    }
}
