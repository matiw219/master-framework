package me.masterkaiser.framework.item;

import lombok.AllArgsConstructor;
import lombok.Getter;
import me.masterkaiser.framework.bukkit.util.ColorUtil;
import me.masterkaiser.framework.condition.BiResult;
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
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.BannerMeta;
import org.bukkit.inventory.meta.CompassMeta;
import org.bukkit.inventory.meta.CrossbowMeta;
import org.bukkit.inventory.meta.Damageable;
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
import org.jetbrains.annotations.Nullable;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@AllArgsConstructor
@Getter
public class ItemBuilder {
    private Material material;
    private int amount;
    private int damage;
    private int customModelData;
    private String name;
    private String localizedName;
    private List<String> lore;
    private Map<Enchantment, Integer> enchantments;
    private List<ItemFlag> flags;
    private MasterBannerMeta bannerMeta;
    private MasterCompassMeta compassMeta;
    private MasterCrossbowMeta crossbowMeta;
    private MasterEnchantmentStorageMeta enchantmentStorageMeta;
    private MasterFireworkEffectMeta fireworkEffectMeta;
    private MasterFireworkMeta fireworkMeta;
    private MasterKnowledgeBookMeta knowledgeBookMeta;
    private MasterLeatherArmorMeta leatherArmorMeta;
    private MasterMapMeta mapMeta;
    private MasterPotionMeta potionMeta;
    private MasterRepairable repairable;
    private MasterSkullMeta skullMeta;
    private MasterSuspiciousStewMeta suspiciousStewMeta;
    private MasterTropicalFishBucketMeta tropicalFishBucketMeta;

    public <T> ItemStack build(@Nullable T object, @Nullable BiResult<T, String, String> result) {
        final ItemStack itemStack = new ItemStack(this.material, amount); {
            ItemMeta itemMeta = itemStack.getItemMeta();

            if (this.name != null ) {
                if (object != null && result != null) {
                    itemMeta.setDisplayName(ColorUtil.color(result.result(object, this.name)));
                } else {
                    itemMeta.setDisplayName(ColorUtil.color(this.name));
                }
            }

            itemMeta.setLocalizedName(this.localizedName);

            if (this.lore != null){
                if (object != null && result != null) {
                    List<String> lore = new ArrayList<>();
                    this.lore.forEach(line -> lore.add(result.result(object, line)));
                    itemMeta.setLore(ColorUtil.color(lore));
                } else {
                    itemMeta.setLore(ColorUtil.color(this.lore));
                }
            }

            if (itemMeta instanceof Damageable damageable) {
                damageable.setDamage(damage);
            }

            if (this.bannerMeta != null && itemMeta instanceof BannerMeta bannerMeta) {
                this.bannerMeta.applyFor(bannerMeta);
            }

            if (this.compassMeta != null && itemMeta instanceof CompassMeta compassMeta) {
                this.compassMeta.applyFor(compassMeta);
            }

            if (this.crossbowMeta != null && itemMeta instanceof CrossbowMeta crossbowMeta) {
                this.crossbowMeta.applyFor(crossbowMeta);
            }

            if (this.enchantmentStorageMeta != null && itemMeta instanceof EnchantmentStorageMeta enchantmentStorageMeta) {
                this.enchantmentStorageMeta.applyFor(enchantmentStorageMeta);
            }

            if (this.fireworkEffectMeta != null && itemMeta instanceof FireworkEffectMeta fireworkEffectMeta) {
                this.fireworkEffectMeta.applyFor(fireworkEffectMeta);
            }

            if (this.fireworkMeta != null && itemMeta instanceof FireworkMeta fireworkMeta) {
                this.fireworkMeta.applyFor(fireworkMeta);
            }

            if (this.knowledgeBookMeta != null && itemMeta instanceof KnowledgeBookMeta knowledgeBookMeta) {
                this.knowledgeBookMeta.applyFor(knowledgeBookMeta);
            }

            if (this.leatherArmorMeta != null && itemMeta instanceof LeatherArmorMeta leatherArmorMeta) {
                this.leatherArmorMeta.applyFor(leatherArmorMeta);
            }

            if (this.potionMeta != null && itemMeta instanceof PotionMeta potionMeta) {
                this.potionMeta.applyFor(potionMeta);
            }

            if (this.repairable != null && itemMeta instanceof Repairable repairable) {
                this.repairable.applyFor(repairable);
            }

            if (this.skullMeta != null && itemMeta instanceof SkullMeta skullMeta) {
                this.skullMeta.applyFor(skullMeta);
            }

            if (this.suspiciousStewMeta != null && itemMeta instanceof SuspiciousStewMeta suspiciousStewMeta) {
                this.suspiciousStewMeta.applyFor(suspiciousStewMeta);
            }

            if (this.tropicalFishBucketMeta != null && itemMeta instanceof TropicalFishBucketMeta tropicalFishBucketMeta) {
                this.tropicalFishBucketMeta.applyFor(tropicalFishBucketMeta);
            }

            if (this.mapMeta != null && itemMeta instanceof MapMeta mapMeta) {
                this.mapMeta.applyFor(mapMeta);
            }

            if (this.flags != null) {
                itemMeta.addItemFlags(this.flags.toArray(new ItemFlag[0]));
            }

            itemStack.setItemMeta(itemMeta);
        }

        if (this.enchantments != null) {
            this.enchantments.forEach(itemStack::addUnsafeEnchantment);
        }

        return itemStack;
    }

    public ItemStack build() {
        return build(null, null);
    }

    public ItemBuilder(@NotNull Material material) {
        this(material, 1);
    }

    public ItemBuilder(@NotNull ItemStack itemStack) {
        this.material = itemStack.getType();
        this.amount = itemStack.getAmount();
        if (itemStack.getItemMeta() != null && itemStack.hasItemMeta()) {
            final ItemMeta itemMeta = itemStack.getItemMeta();

            if (itemMeta instanceof Damageable damageable) {
                this.damage = damageable.getDamage();
            }

            if (itemMeta.hasDisplayName()) {
                this.name = itemMeta.getDisplayName();
            }

            if (itemMeta.hasLocalizedName()) {
                this.localizedName = itemMeta.getLocalizedName();
            }

            if (itemMeta.hasLore()) {
                this.lore = itemMeta.getLore();
            }

            if (itemMeta.hasEnchants()) {
                this.enchantments = itemMeta.getEnchants();
            }

            if (!itemMeta.getItemFlags().isEmpty()) {
                this.flags = new ArrayList<>();
                this.flags.addAll(itemMeta.getItemFlags());
            }

            MetaBuilder.applyMetas(itemMeta, this);
        }
    }

    public ItemBuilder(@NotNull Material material, int amount) {
        this.material = material;
        this.amount = amount;
    }

    public ItemBuilder setMaterial(@NotNull Material material) {
        this.material = material;
        return this;
    }

    public ItemBuilder setAmount(int amount) {
        this.amount = amount;
        return this;
    }

    public ItemBuilder setDamage(int damage) {
        this.damage = damage;
        return this;
    }

    public ItemBuilder setCustomModelData(int customModelData) {
        this.customModelData = customModelData;
        return this;
    }

    public ItemBuilder setName(String name) {
        this.name = name;
        return this;
    }

    public ItemBuilder setLocalizedName(String localizedName) {
        this.localizedName = localizedName;
        return this;
    }

    public ItemBuilder setLore(List<String> lore) {
        this.lore = lore;
        return this;
    }

    public ItemBuilder addLoreLine(String line) {
        if (this.lore == null) {
            this.lore = new ArrayList<>();
        }
        this.lore.add(line);
        return this;
    }

    public ItemBuilder setEnchantments(Map<Enchantment, Integer> enchantments) {
        this.enchantments = enchantments;
        return this;
    }

    public ItemBuilder addEnchantment(Enchantment enchantment, int level) {
        if (this.enchantments == null) {
            this.enchantments = new HashMap<>();
        }

        this.enchantments.put(enchantment, level);

        return this;
    }

    public ItemBuilder setFlags(List<ItemFlag> flags) {
        this.flags = flags;
        return this;
    }

    public ItemBuilder addFlag(ItemFlag itemFlag) {
        if (this.flags == null) {
            this.flags = new ArrayList<>();
        }

        this.flags.add(itemFlag);

        return this;
    }

    public ItemBuilder setBannerMeta(MasterBannerMeta bannerMeta) {
        this.bannerMeta = bannerMeta;
        return this;
    }

    public ItemBuilder setCompassMeta(MasterCompassMeta compassMeta) {
        this.compassMeta = compassMeta;
        return this;
    }

    public ItemBuilder setCrossbowMeta(MasterCrossbowMeta crossbowMeta) {
        this.crossbowMeta = crossbowMeta;
        return this;
    }

    public ItemBuilder setEnchantmentStorageMeta(MasterEnchantmentStorageMeta enchantmentStorageMeta) {
        this.enchantmentStorageMeta = enchantmentStorageMeta;
        return this;
    }

    public ItemBuilder setFireworkEffectMeta(MasterFireworkEffectMeta fireworkEffectMeta) {
        this.fireworkEffectMeta = fireworkEffectMeta;
        return this;
    }

    public ItemBuilder setFireworkMeta(MasterFireworkMeta fireworkMeta) {
        this.fireworkMeta = fireworkMeta;
        return this;
    }

    public ItemBuilder setKnowledgeBookMeta(MasterKnowledgeBookMeta knowledgeBookMeta) {
        this.knowledgeBookMeta = knowledgeBookMeta;
        return this;
    }

    public ItemBuilder setLeatherArmorMeta(MasterLeatherArmorMeta leatherArmorMeta) {
        this.leatherArmorMeta = leatherArmorMeta;
        return this;
    }

    public ItemBuilder setMapMeta(MasterMapMeta mapMeta) {
        this.mapMeta = mapMeta;
        return this;
    }

    public ItemBuilder setPotionMeta(MasterPotionMeta potionMeta) {
        this.potionMeta = potionMeta;
        return this;
    }

    public ItemBuilder setRepairable(MasterRepairable repairable) {
        this.repairable = repairable;
        return this;
    }

    public ItemBuilder setSkullMeta(MasterSkullMeta skullMeta) {
        this.skullMeta = skullMeta;
        return this;
    }

    public ItemBuilder setSuspiciousStewMeta(MasterSuspiciousStewMeta suspiciousStewMeta) {
        this.suspiciousStewMeta = suspiciousStewMeta;
        return this;
    }

    public ItemBuilder setTropicalFishBucketMeta(MasterTropicalFishBucketMeta tropicalFishBucketMeta) {
        this.tropicalFishBucketMeta = tropicalFishBucketMeta;
        return this;
    }

    public static ItemBuilder toItemBuilder(ItemStack itemStack) {
        return new ItemBuilder(itemStack);
    }

    public static ItemStack toItemStack(ItemBuilder itemBuilder) {
        return itemBuilder.build();
    }

    public static List<ItemStack> toItemStack(List<ItemBuilder> itemBuilders) {
        return itemBuilders
                .stream()
                .map(ItemBuilder::build)
                .collect(Collectors.toList());
    }

    public static List<ItemBuilder> toItemBuilder(List<ItemStack> itemStacks) {
        return itemStacks
                .stream()
                .map(ItemBuilder::new)
                .collect(Collectors.toList());
    }
}
