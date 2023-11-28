package me.masterkaiser.framework.item;

import eu.okaeri.configs.serdes.OkaeriSerdesPack;
import eu.okaeri.configs.serdes.SerdesRegistry;
import lombok.NonNull;
import me.masterkaiser.framework.item.serializer.MasterBannerMetaSerializer;
import me.masterkaiser.framework.item.serializer.MasterCompassMetaSerializer;
import me.masterkaiser.framework.item.serializer.MasterCrossbowMetaSerializer;
import me.masterkaiser.framework.item.serializer.MasterEnchantmentStorageMetaSerializer;
import me.masterkaiser.framework.item.serializer.MasterFireworkEffectMetaSerializer;
import me.masterkaiser.framework.item.serializer.MasterFireworkMetaSerializer;
import me.masterkaiser.framework.item.serializer.MasterKnowledgeBookMetaSerializer;
import me.masterkaiser.framework.item.serializer.MasterLeatherArmorMetaSerializer;
import me.masterkaiser.framework.item.serializer.MasterPotionMetaSerializer;
import me.masterkaiser.framework.item.serializer.MasterRepairableSerializer;
import me.masterkaiser.framework.item.serializer.MasterSkullMetaSerializer;
import me.masterkaiser.framework.item.serializer.MasterSuspiciousStewMetaSerializer;
import me.masterkaiser.framework.item.serializer.MasterTropicalFishBucketMetaSerializer;
import me.masterkaiser.framework.item.serializer.map.MapCursorSerializer;
import me.masterkaiser.framework.item.serializer.map.MapMetaSerializer;
import me.masterkaiser.framework.item.serializer.map.MapPixelSerializer;
import me.masterkaiser.framework.item.serializer.map.MapViewSerializer;

public class ItemBuilder16Serializer implements OkaeriSerdesPack {
    @Override
    public void register(@NonNull SerdesRegistry registry) {
        registry.register(new ItemBuilderSerializer());

        registry.register(new MasterBannerMetaSerializer());
        registry.register(new MasterCompassMetaSerializer());
        registry.register(new MasterCrossbowMetaSerializer());
        registry.register(new MasterEnchantmentStorageMetaSerializer());
        registry.register(new MasterFireworkEffectMetaSerializer());
        registry.register(new MasterFireworkMetaSerializer());
        registry.register(new MasterKnowledgeBookMetaSerializer());
        registry.register(new MasterLeatherArmorMetaSerializer());
        registry.register(new MasterPotionMetaSerializer());
        registry.register(new MasterRepairableSerializer());
        registry.register(new MasterSkullMetaSerializer());
        registry.register(new MasterSuspiciousStewMetaSerializer());
        registry.register(new MasterTropicalFishBucketMetaSerializer());

        registry.register(new MapMetaSerializer());
        registry.register(new MapViewSerializer());
        registry.register(new MapPixelSerializer());
        registry.register(new MapCursorSerializer());
    }
}
