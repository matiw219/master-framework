package me.masterkaiser.framework.item.meta;

import lombok.AllArgsConstructor;
import lombok.Getter;
import me.masterkaiser.framework.item.MasterMeta;
import org.bukkit.inventory.meta.Repairable;

@AllArgsConstructor
@Getter
public class MasterRepairable implements MasterMeta<Repairable> {
    private int repairCost;

    public MasterRepairable setRepairCost(int repairCost) {
        this.repairCost = repairCost;
        return this;
    }

    @Override
    public void applyFor(Repairable repairable) {
        repairable.setRepairCost(this.repairCost);
    }
}
