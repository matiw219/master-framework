package me.masterkaiser.framework.item.meta;

import lombok.AllArgsConstructor;
import lombok.Getter;
import me.masterkaiser.framework.item.MasterMeta;
import org.bukkit.Color;
import org.bukkit.inventory.meta.LeatherArmorMeta;
import org.jetbrains.annotations.NotNull;

@AllArgsConstructor
@Getter
public class MasterLeatherArmorMeta implements MasterMeta<LeatherArmorMeta> {
    private @NotNull Color color;

    public MasterLeatherArmorMeta setColor(Color color) {
        this.color = color;
        return this;
    }

    @Override
    public void applyFor(LeatherArmorMeta leatherArmorMeta) {
        leatherArmorMeta.setColor(this.color);
    }
}
