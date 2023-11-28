package me.masterkaiser.framework.item.meta;

import lombok.Getter;
import me.masterkaiser.framework.item.MasterMeta;
import org.bukkit.Color;
import org.bukkit.inventory.meta.PotionMeta;
import org.bukkit.potion.PotionData;
import org.bukkit.potion.PotionEffect;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.ArrayList;
import java.util.List;

@Getter
public class MasterPotionMeta implements MasterMeta<PotionMeta> {
    private @NotNull PotionData potionData;
    private @NotNull List<PotionEffect> customEffects;
    private @Nullable Color color;

    public MasterPotionMeta(@NotNull PotionData potionData, @NotNull List<PotionEffect> customEffects, @Nullable Color color) {
        this.potionData = potionData;
        this.customEffects = customEffects;
        this.color = color;
    }
    public MasterPotionMeta(@NotNull PotionData potionData, @NotNull Color color) {
        this(potionData, new ArrayList<>(), color);
    }

    public MasterPotionMeta setColor(@Nullable Color color) {
        this.color = color;
        return this;
    }

    public MasterPotionMeta setCustomEffects(@NotNull List<PotionEffect> customEffects) {
        this.customEffects = customEffects;
        return this;
    }

    public MasterPotionMeta setPotionData(@NotNull PotionData potionData) {
        this.potionData = potionData;
        return this;
    }

    @Override
    public void applyFor(PotionMeta potionMeta) {
        potionMeta.setBasePotionData(this.potionData);
        this.customEffects.forEach(potionEffect -> potionMeta.addCustomEffect(potionEffect, true));
        potionMeta.setColor(this.color);
    }
}
