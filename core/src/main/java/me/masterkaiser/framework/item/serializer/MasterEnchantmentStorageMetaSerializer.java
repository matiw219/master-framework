package me.masterkaiser.framework.item.serializer;

import eu.okaeri.configs.schema.GenericsDeclaration;
import eu.okaeri.configs.serdes.DeserializationData;
import eu.okaeri.configs.serdes.ObjectSerializer;
import eu.okaeri.configs.serdes.SerializationData;
import lombok.NonNull;
import me.masterkaiser.framework.item.meta.MasterEnchantmentStorageMeta;
import org.bukkit.enchantments.Enchantment;

public class MasterEnchantmentStorageMetaSerializer implements ObjectSerializer<MasterEnchantmentStorageMeta> {
    @Override
    public boolean supports(@NonNull Class<? super MasterEnchantmentStorageMeta> type) {
        return MasterEnchantmentStorageMeta.class.isAssignableFrom(type);
    }

    @Override
    public void serialize(@NonNull MasterEnchantmentStorageMeta object, @NonNull SerializationData data, @NonNull GenericsDeclaration generics) {
        data.addAsMap("enchantments", object.getEnchantments(), Enchantment.class, Integer.class);
    }

    @Override
    public MasterEnchantmentStorageMeta deserialize(@NonNull DeserializationData data, @NonNull GenericsDeclaration generics) {
        return new MasterEnchantmentStorageMeta(data.getAsMap("enchantments", Enchantment.class, Integer.class));
    }
}
