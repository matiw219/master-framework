package me.masterkaiser.framework.item.meta;

import lombok.AllArgsConstructor;
import lombok.Getter;
import me.masterkaiser.framework.item.MasterMeta;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.inventory.meta.EnchantmentStorageMeta;
import org.jetbrains.annotations.NotNull;

import java.util.Map;

@AllArgsConstructor
@Getter
public class MasterEnchantmentStorageMeta implements MasterMeta<EnchantmentStorageMeta> {
    private @NotNull Map<Enchantment, Integer> enchantments;

    public MasterEnchantmentStorageMeta setEnchantments(@NotNull Map<Enchantment, Integer> enchantments) {
        this.enchantments = enchantments;
        return this;
    }

    @Override
    public void applyFor(EnchantmentStorageMeta enchantmentStorageMeta) {
        this.enchantments.forEach((enchantment, integer) ->
                enchantmentStorageMeta.addStoredEnchant(enchantment, integer, true));
    }
}
