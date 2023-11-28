package me.masterkaiser.framework.item.base;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.bukkit.inventory.ItemStack;

import java.util.List;

@Data
@AllArgsConstructor
public class BaseItems {
    private List<ItemStack> items;
}
