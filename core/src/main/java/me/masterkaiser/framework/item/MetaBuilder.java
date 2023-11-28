package me.masterkaiser.framework.item;

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
import me.masterkaiser.framework.item.meta.map.MasterMapView;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.BannerMeta;
import org.bukkit.inventory.meta.CompassMeta;
import org.bukkit.inventory.meta.CrossbowMeta;
import org.bukkit.inventory.meta.EnchantmentStorageMeta;
import org.bukkit.inventory.meta.FireworkEffectMeta;
import org.bukkit.inventory.meta.FireworkMeta;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.inventory.meta.KnowledgeBookMeta;
import org.bukkit.inventory.meta.LeatherArmorMeta;
import org.bukkit.inventory.meta.MapMeta;
import org.bukkit.inventory.meta.PotionMeta;
import org.bukkit.inventory.meta.Repairable;
import org.bukkit.inventory.meta.SkullMeta;
import org.bukkit.inventory.meta.SuspiciousStewMeta;
import org.bukkit.inventory.meta.TropicalFishBucketMeta;
import org.jetbrains.annotations.NotNull;

public class MetaBuilder {
    public static void applyMetas(@NotNull ItemStack itemStack, @NotNull ItemBuilder itemBuilder) {
        if (itemStack.getItemMeta() == null) {
            return;
        }

        applyMetas(itemStack.getItemMeta(), itemBuilder);
    }

    public static void applyMetas(@NotNull ItemMeta itemMeta, @NotNull ItemBuilder itemBuilder) {
        if (itemMeta instanceof BannerMeta bannerMeta) {
            itemBuilder.setBannerMeta(
                    new MasterBannerMeta(
                            bannerMeta.getPatterns()
                    )
            );
        }

        if (itemMeta instanceof CompassMeta compassMeta && compassMeta.getLodestone() != null) {
            itemBuilder.setCompassMeta(
                    new MasterCompassMeta(
                            compassMeta.getLodestone(),
                            compassMeta.isLodestoneTracked()
                    )
            );
        }

        if (itemMeta instanceof CrossbowMeta crossbowMeta) {
            itemBuilder.setCrossbowMeta(
                    new MasterCrossbowMeta(
                            ItemBuilder.toItemBuilder(crossbowMeta.getChargedProjectiles())
                    )
            );
        }

        if (itemMeta instanceof EnchantmentStorageMeta enchantmentStorageMeta) {
            itemBuilder.setEnchantmentStorageMeta(
                    new MasterEnchantmentStorageMeta(
                            enchantmentStorageMeta.getStoredEnchants()
                    )
            );
        }

        if (itemMeta instanceof FireworkEffectMeta fireworkEffectMeta) {
            itemBuilder.setFireworkEffectMeta(
                    new MasterFireworkEffectMeta(
                            fireworkEffectMeta.getEffect()
                    )
            );
        }

        if (itemMeta instanceof FireworkMeta fireworkMeta) {
            itemBuilder.setFireworkMeta(
                    new MasterFireworkMeta(
                            fireworkMeta.getEffects(),
                            fireworkMeta.getPower()
                    )
            );
        }

        if (itemMeta instanceof KnowledgeBookMeta knowledgeBookMeta) {
            itemBuilder.setKnowledgeBookMeta(
                    new MasterKnowledgeBookMeta(
                            knowledgeBookMeta.getRecipes()
                    )
            );
        }

        if (itemMeta instanceof LeatherArmorMeta leatherArmorMeta) {
            itemBuilder.setLeatherArmorMeta(
                    new MasterLeatherArmorMeta(
                            leatherArmorMeta.getColor()
                    )
            );
        }

        if (itemMeta instanceof MapMeta mapMeta) {
            itemBuilder.setMapMeta(
                    new MasterMapMeta(
                            mapMeta.getMapView() == null
                                    ? null
                                    : new MasterMapView(mapMeta.getMapView()),
                            mapMeta.isScaling(),
                            mapMeta.getColor(),
                            mapMeta.getLocationName(),
                            mapMeta.getMapView() == null
                                    ? null
                                    : mapMeta.getMapView().getWorld() == null
                                    ? null
                                    : mapMeta.getMapView().getWorld().getName()
                    )
            );
        }

        if (itemMeta instanceof PotionMeta potionMeta) {
            itemBuilder.setPotionMeta(
                    new MasterPotionMeta(
                            potionMeta.getBasePotionData(),
                            potionMeta.getCustomEffects(),
                            potionMeta.getColor()
                    )
            );
        }

        if (itemMeta instanceof Repairable repairable) {
            itemBuilder.setRepairable(
                    new MasterRepairable(
                            repairable.getRepairCost()
                    )
            );
        }

        if (itemMeta instanceof SkullMeta skullMeta) {
            itemBuilder.setSkullMeta(
                    new MasterSkullMeta(
                            skullMeta.getOwner(),
                            (skullMeta.getOwningPlayer() != null ? skullMeta.getOwningPlayer().getUniqueId() : null)
                    )
            );
        }

        if (itemMeta instanceof SuspiciousStewMeta suspiciousStewMeta) {
            itemBuilder.setSuspiciousStewMeta(
                    new MasterSuspiciousStewMeta(
                            suspiciousStewMeta.getCustomEffects()
                    )
            );
        }

        if (itemMeta instanceof TropicalFishBucketMeta tropicalFishBucketMeta) {
            itemBuilder.setTropicalFishBucketMeta(
                    new MasterTropicalFishBucketMeta(
                            tropicalFishBucketMeta.getPatternColor(),
                            tropicalFishBucketMeta.getBodyColor(),
                            tropicalFishBucketMeta.getPattern()
                    )
            );
        }
    }
}
