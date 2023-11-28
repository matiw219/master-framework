package me.masterkaiser.framework.item.meta;

import lombok.AllArgsConstructor;
import lombok.Getter;
import me.masterkaiser.framework.item.MasterMeta;
import org.bukkit.inventory.meta.SuspiciousStewMeta;
import org.bukkit.potion.PotionEffect;
import org.jetbrains.annotations.NotNull;

import java.util.List;

@AllArgsConstructor
@Getter
public class MasterSuspiciousStewMeta implements MasterMeta<SuspiciousStewMeta> {
    private @NotNull List<PotionEffect> effects;

    public MasterSuspiciousStewMeta setEffects(@NotNull List<PotionEffect> effects) {
        this.effects = effects;
        return this;
    }

    @Override
    public void applyFor(SuspiciousStewMeta suspiciousStewMeta) {
        this.effects.forEach(potionEffect ->
                suspiciousStewMeta.addCustomEffect(potionEffect, true));
    }
}
