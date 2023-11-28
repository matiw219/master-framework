package me.masterkaiser.framework.bukkit;

import eu.okaeri.configs.serdes.OkaeriSerdesPack;
import eu.okaeri.configs.serdes.SerdesRegistry;
import lombok.NonNull;
import me.masterkaiser.framework.bukkit.serializer.BannerPatternSerializer;
import me.masterkaiser.framework.bukkit.serializer.ColorSerializer;
import me.masterkaiser.framework.bukkit.serializer.FireworkEffectSerializer;
import me.masterkaiser.framework.bukkit.serializer.LocationSerializer;
import me.masterkaiser.framework.bukkit.serializer.PotionDataSerializer;
import me.masterkaiser.framework.bukkit.serializer.PotionEffectSerializer;
import me.masterkaiser.framework.bukkit.transformer.StringEnchantmentTransformer;
import me.masterkaiser.framework.bukkit.transformer.StringMaterialTransformer;
import me.masterkaiser.framework.bukkit.transformer.StringNamespacedKeyTransformer;
import me.masterkaiser.framework.bukkit.transformer.StringPotionEffectTypeTransformer;

public class BukkitSerializers implements OkaeriSerdesPack {
    @Override
    public void register(@NonNull SerdesRegistry registry) {
        registry.register(new StringEnchantmentTransformer());
        registry.register(new StringNamespacedKeyTransformer());
        registry.register(new StringMaterialTransformer());
        registry.register(new StringPotionEffectTypeTransformer());

        registry.register(new BannerPatternSerializer());
        registry.register(new ColorSerializer());
        registry.register(new FireworkEffectSerializer());
        registry.register(new LocationSerializer());
        registry.register(new PotionDataSerializer());
        registry.register(new PotionEffectSerializer());
    }
}
