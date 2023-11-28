package me.masterkaiser.framework.item.meta;

import lombok.AllArgsConstructor;
import lombok.Getter;
import me.masterkaiser.framework.item.MasterMeta;
import org.bukkit.FireworkEffect;
import org.bukkit.inventory.meta.FireworkMeta;
import org.jetbrains.annotations.NotNull;

import java.util.List;

@AllArgsConstructor
@Getter
public class MasterFireworkMeta implements MasterMeta<FireworkMeta> {
    private @NotNull List<FireworkEffect> effects;
    private int power;

    public MasterFireworkMeta setEffects(@NotNull List<FireworkEffect> effects) {
        this.effects = effects;
        return this;
    }

    public MasterFireworkMeta setPower(int power) {
        this.power = power;
        return this;
    }

    @Override
    public void applyFor(FireworkMeta fireworkMeta) {
        fireworkMeta.addEffects(this.effects);
        fireworkMeta.setPower(this.power);
    }
}
