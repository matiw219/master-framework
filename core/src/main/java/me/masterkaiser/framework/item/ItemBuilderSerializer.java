package me.masterkaiser.framework.item;

import eu.okaeri.configs.schema.GenericsDeclaration;
import eu.okaeri.configs.serdes.DeserializationData;
import eu.okaeri.configs.serdes.ObjectSerializer;
import eu.okaeri.configs.serdes.SerializationData;
import lombok.NonNull;
import me.masterkaiser.framework.item.meta.MasterBannerMeta;
import me.masterkaiser.framework.item.meta.MasterCompassMeta;
import me.masterkaiser.framework.item.meta.MasterCrossbowMeta;
import me.masterkaiser.framework.item.meta.MasterEnchantmentStorageMeta;
import me.masterkaiser.framework.item.meta.MasterFireworkEffectMeta;
import me.masterkaiser.framework.item.meta.MasterFireworkMeta;
import me.masterkaiser.framework.item.meta.MasterKnowledgeBookMeta;
import me.masterkaiser.framework.item.meta.MasterLeatherArmorMeta;
import me.masterkaiser.framework.item.meta.MasterPotionMeta;
import me.masterkaiser.framework.item.meta.MasterRepairable;
import me.masterkaiser.framework.item.meta.MasterSkullMeta;
import me.masterkaiser.framework.item.meta.MasterSuspiciousStewMeta;
import me.masterkaiser.framework.item.meta.MasterTropicalFishBucketMeta;
import me.masterkaiser.framework.item.meta.map.MasterMapMeta;
import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.inventory.ItemFlag;

import java.util.List;
import java.util.Map;

public class ItemBuilderSerializer implements ObjectSerializer<ItemBuilder> {
    @Override
    public boolean supports(@NonNull Class<? super ItemBuilder> type) {
        return ItemBuilder.class.isAssignableFrom(type);
    }

    @Override
    public void serialize(@NonNull ItemBuilder object, @NonNull SerializationData data, @NonNull GenericsDeclaration generics) {
        data.add("material", object.getMaterial());

        if (object.getAmount() != 1) {
            data.add("amount", object.getAmount());
        }

        if (object.getDamage() != 0) {
            data.add("damage", object.getDamage());
        }

        if (object.getCustomModelData() != 0) {
            data.add("customModelData", object.getCustomModelData());
        }

        if (object.getName() != null) {
            data.add("name", object.getName());
        }

        if (object.getLocalizedName() != null) {
            data.add("localizedName", object.getLocalizedName());
        }

        if (object.getLore() != null) {
            data.add("lore", object.getLore());
        }

        if (object.getEnchantments() != null) {
            data.addAsMap("enchantments", object.getEnchantments(), Enchantment.class, Integer.class);
        }

        if (object.getFlags() != null) {
            data.add("flags", object.getFlags());
        }

        if (object.getBannerMeta() != null) {
            data.add("bannerMeta", object.getBannerMeta());
        }

        if (object.getCompassMeta() != null) {
            data.add("compassMeta", object.getCompassMeta());
        }

        if (object.getCrossbowMeta() != null) {
            data.add("crossbowMeta", object.getCrossbowMeta());
        }

        if (object.getEnchantmentStorageMeta() != null) {
            data.add("enchantmentStorageMeta", object.getEnchantmentStorageMeta());
        }

        if (object.getFireworkEffectMeta() != null) {
            data.add("fireworkEffectMeta", object.getFireworkEffectMeta());
        }

        if (object.getFireworkMeta() != null) {
            data.add("fireworkMeta", object.getFireworkMeta());
        }

        if (object.getKnowledgeBookMeta() != null) {
            data.add("knowledgeBookMeta", object.getKnowledgeBookMeta());
        }

        if (object.getLeatherArmorMeta() != null) {
            data.add("leatherArmorMeta", object.getLeatherArmorMeta());
        }

        if (object.getPotionMeta() != null) {
            data.add("potionMeta", object.getPotionMeta());
        }

        if (object.getRepairable() != null) {
            data.add("repairable", object.getRepairable());
        }

        if (object.getSkullMeta() != null) {
            data.add("skullMeta", object.getSkullMeta());
        }

        if (object.getSuspiciousStewMeta() != null) {
            data.add("suspiciousStewMeta", object.getSuspiciousStewMeta());
        }

        if (object.getTropicalFishBucketMeta() != null) {
            data.add("tropicalFishBucketMeta", object.getTropicalFishBucketMeta());
        }

        if (object.getMapMeta() != null) {
            data.add("mapMeta", object.getMapMeta());
        }
    }

    @Override
    public ItemBuilder deserialize(@NonNull DeserializationData data, @NonNull GenericsDeclaration generics) {
        final Material material = data.get("material", Material.class);
        final ItemBuilder itemBuilder = new ItemBuilder(material);

        final int amount = data.containsKey("amount")
                ? data.get("amount", Integer.class)
                : 1;
        final int damage = data.containsKey("damage")
                ? data.get("damage", Integer.class)
                : 0;
        final int customModelData = data.containsKey("customModelData")
                ? data.get("customModelData", Integer.class)
                : 0;

        itemBuilder.setAmount(amount);
        itemBuilder.setDamage(damage);
        itemBuilder.setCustomModelData(customModelData);

        final String name = data.containsKey("name")
                ? data.get("name", String.class)
                : null;
        final String localizedName = data.containsKey("localizedName")
                ? data.get("localizedName", String.class)
                : null;
        final List<String> lore = data.containsKey("lore")
                ? data.getAsList("lore", String.class)
                : null;
        final Map<Enchantment, Integer> enchantments = data.containsKey("enchantments")
                ? data.getAsMap("enchantments", Enchantment.class, Integer.class)
                : null;
        final List<ItemFlag> flags = data.containsKey("flags")
                ? data.getAsList("flags", ItemFlag.class)
                : null;

        itemBuilder.setName(name);
        itemBuilder.setLocalizedName(localizedName);
        itemBuilder.setLore(lore);
        itemBuilder.setEnchantments(enchantments);
        itemBuilder.setFlags(flags);

        final MasterBannerMeta bannerMeta = data.containsKey("bannerMeta")
                ? data.get("bannerMeta", MasterBannerMeta.class)
                : null;
        final MasterCompassMeta compassMeta = data.containsKey("compassMeta")
                ? data.get("compassMeta", MasterCompassMeta.class)
                : null;
        final MasterCrossbowMeta crossbowMeta = data.containsKey("crossbowMeta")
                ? data.get("crossbowMeta", MasterCrossbowMeta.class)
                : null;
        final MasterEnchantmentStorageMeta enchantmentStorageMeta = data.containsKey("enchantmentStorageMeta")
                ? data.get("enchantmentStorageMeta", MasterEnchantmentStorageMeta.class)
                : null;
        final MasterFireworkEffectMeta fireworkEffectMeta = data.containsKey("fireworkEffectMeta")
                ? data.get("fireworkEffectMeta", MasterFireworkEffectMeta.class)
                : null;
        final MasterFireworkMeta fireworkMeta = data.containsKey("fireworkMeta")
                ? data.get("fireworkMeta", MasterFireworkMeta.class)
                : null;
        final MasterKnowledgeBookMeta knowledgeBookMeta = data.containsKey("knowledgeBookMeta")
                ? data.get("knowledgeBookMeta", MasterKnowledgeBookMeta.class)
                : null;
        final MasterLeatherArmorMeta leatherArmorMeta = data.containsKey("leatherArmorMeta")
                ? data.get("leatherArmorMeta", MasterLeatherArmorMeta.class)
                : null;
        final MasterPotionMeta potionMeta = data.containsKey("potionMeta")
                ? data.get("potionMeta", MasterPotionMeta.class)
                : null;
        final MasterRepairable repairable = data.containsKey("repairable")
                ? data.get("repairable", MasterRepairable.class)
                : null;
        final MasterSkullMeta skullMeta = data.containsKey("skullMeta")
                ? data.get("skullMeta", MasterSkullMeta.class)
                : null;
        final MasterSuspiciousStewMeta suspiciousStewMeta = data.containsKey("suspiciousStewMeta")
                ? data.get("suspiciousStewMeta", MasterSuspiciousStewMeta.class)
                : null;
        final MasterTropicalFishBucketMeta tropicalFishBucketMeta = data.containsKey("tropicalFishBucketMeta")
                ? data.get("tropicalFishBucketMeta", MasterTropicalFishBucketMeta.class)
                : null;
        final MasterMapMeta mapMeta = data.containsKey("mapMeta")
                ? data.get("mapMeta", MasterMapMeta.class)
                : null;

        itemBuilder.setBannerMeta(bannerMeta);
        itemBuilder.setCompassMeta(compassMeta);
        itemBuilder.setCrossbowMeta(crossbowMeta);
        itemBuilder.setEnchantmentStorageMeta(enchantmentStorageMeta);
        itemBuilder.setFireworkEffectMeta(fireworkEffectMeta);
        itemBuilder.setFireworkMeta(fireworkMeta);
        itemBuilder.setKnowledgeBookMeta(knowledgeBookMeta);
        itemBuilder.setLeatherArmorMeta(leatherArmorMeta);
        itemBuilder.setPotionMeta(potionMeta);
        itemBuilder.setRepairable(repairable);
        itemBuilder.setSkullMeta(skullMeta);
        itemBuilder.setSuspiciousStewMeta(suspiciousStewMeta);
        itemBuilder.setTropicalFishBucketMeta(tropicalFishBucketMeta);
        itemBuilder.setMapMeta(mapMeta);

        return itemBuilder;
    }
}
