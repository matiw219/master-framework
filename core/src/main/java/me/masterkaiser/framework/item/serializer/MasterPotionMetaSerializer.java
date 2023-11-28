package me.masterkaiser.framework.item.serializer;

import eu.okaeri.configs.schema.GenericsDeclaration;
import eu.okaeri.configs.serdes.DeserializationData;
import eu.okaeri.configs.serdes.ObjectSerializer;
import eu.okaeri.configs.serdes.SerializationData;
import lombok.NonNull;
import me.masterkaiser.framework.item.meta.MasterPotionMeta;
import org.bukkit.Color;
import org.bukkit.potion.PotionData;
import org.bukkit.potion.PotionEffect;

import java.util.List;

public class MasterPotionMetaSerializer implements ObjectSerializer<MasterPotionMeta> {
    @Override
    public boolean supports(@NonNull Class<? super MasterPotionMeta> type) {
        return MasterPotionMeta.class.isAssignableFrom(type);
    }

    @Override
    public void serialize(@NonNull MasterPotionMeta object, @NonNull SerializationData data, @NonNull GenericsDeclaration generics) {
        data.add("potionData", object.getPotionData());
        data.add("customEffects", object.getCustomEffects());
        data.add("color", object.getColor());
    }

    @Override
    public MasterPotionMeta deserialize(@NonNull DeserializationData data, @NonNull GenericsDeclaration generics) {
        final PotionData potionData = data.get("potionData", PotionData.class);
        final List<PotionEffect> potionEffects = data.getAsList("customEffects", PotionEffect.class);
        final Color color = data.get("color", Color.class);

        return new MasterPotionMeta(potionData, potionEffects, color);
    }
}
