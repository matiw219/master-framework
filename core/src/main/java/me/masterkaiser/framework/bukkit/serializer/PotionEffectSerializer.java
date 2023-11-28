package me.masterkaiser.framework.bukkit.serializer;

import eu.okaeri.configs.schema.GenericsDeclaration;
import eu.okaeri.configs.serdes.DeserializationData;
import eu.okaeri.configs.serdes.ObjectSerializer;
import eu.okaeri.configs.serdes.SerializationData;
import lombok.NonNull;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

public class PotionEffectSerializer implements ObjectSerializer<PotionEffect> {
    @Override
    public boolean supports(@NonNull Class<? super PotionEffect> type) {
        return PotionEffect.class.isAssignableFrom(type);
    }

    @Override
    public void serialize(@NonNull PotionEffect object, @NonNull SerializationData data, @NonNull GenericsDeclaration generics) {
        data.add("type", object.getType(), PotionEffectType.class);
        data.add("amplifier", object.getAmplifier());
        data.add("duration", object.getDuration());
    }

    @Override
    public PotionEffect deserialize(@NonNull DeserializationData data, @NonNull GenericsDeclaration generics) {
        final PotionEffectType potionEffectType = data.get("type", PotionEffectType.class);
        final int amplifier = data.get("amplifier", Integer.class);
        final int duration = data.get("duration", Integer.class);

        return new PotionEffect(
                potionEffectType,
                amplifier,
                duration
        );
    }
}
