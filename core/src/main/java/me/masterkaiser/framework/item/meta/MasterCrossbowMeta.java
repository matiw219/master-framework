package me.masterkaiser.framework.item.meta;

import lombok.AllArgsConstructor;
import lombok.Getter;
import me.masterkaiser.framework.item.ItemBuilder;
import me.masterkaiser.framework.item.MasterMeta;
import org.bukkit.inventory.meta.CrossbowMeta;
import org.jetbrains.annotations.NotNull;

import java.util.List;

@AllArgsConstructor
@Getter
public class MasterCrossbowMeta implements MasterMeta<CrossbowMeta> {
    private @NotNull List<ItemBuilder> chargedProjectiles;

    public MasterCrossbowMeta setChargedProjectiles(@NotNull List<ItemBuilder> chargedProjectiles) {
        this.chargedProjectiles = chargedProjectiles;
        return this;
    }

    @Override
    public void applyFor(CrossbowMeta crossbowMeta) {
        crossbowMeta.setChargedProjectiles(ItemBuilder.toItemStack(this.chargedProjectiles));
    }
}
