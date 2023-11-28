package me.masterkaiser.framework.item.meta;

import lombok.AllArgsConstructor;
import lombok.Getter;
import me.masterkaiser.framework.item.MasterMeta;
import org.bukkit.FireworkEffect;
import org.bukkit.inventory.meta.FireworkEffectMeta;
import org.jetbrains.annotations.Nullable;

@AllArgsConstructor
@Getter
public class MasterFireworkEffectMeta implements MasterMeta<FireworkEffectMeta> {
    private @Nullable FireworkEffect fireworkEffect;

    public MasterFireworkEffectMeta setFireworkEffect(@Nullable FireworkEffect fireworkEffect) {
        this.fireworkEffect = fireworkEffect;
        return this;
    }

    @Override
    public void applyFor(FireworkEffectMeta fireworkEffectMeta) {
        fireworkEffectMeta.setEffect(this.fireworkEffect);
    }
}
