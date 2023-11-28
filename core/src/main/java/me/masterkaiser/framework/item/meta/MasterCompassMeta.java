package me.masterkaiser.framework.item.meta;

import lombok.AllArgsConstructor;
import lombok.Getter;
import me.masterkaiser.framework.item.MasterMeta;
import org.bukkit.Location;
import org.bukkit.inventory.meta.CompassMeta;
import org.jetbrains.annotations.NotNull;

@AllArgsConstructor
@Getter
public class MasterCompassMeta implements MasterMeta<CompassMeta> {
    private @NotNull Location lodestone;
    private boolean lodestoneTracked;

    public MasterCompassMeta setLodestone(@NotNull Location lodestone) {
        this.lodestone = lodestone;
        return this;
    }

    public MasterCompassMeta setLodestoneTracked(boolean lodestoneTracked) {
        this.lodestoneTracked = lodestoneTracked;
        return this;
    }

    @Override
    public void applyFor(CompassMeta compassMeta) {
        compassMeta.setLodestone(this.lodestone);
        compassMeta.setLodestoneTracked(this.lodestoneTracked);
    }
}
