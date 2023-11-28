package me.masterkaiser.framework.item;

import lombok.experimental.UtilityClass;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.List;
import java.util.Map;
import java.util.function.Consumer;
import java.util.stream.Collectors;

@UtilityClass
public class ItemUtil {
    public static void addItems(@NotNull Inventory inventory, @NotNull List<ItemStack> items, @Nullable Consumer<List<ItemStack>> remaining) {
        final Map<Integer, ItemStack> remainingItems = inventory.addItem(items.toArray(new ItemStack[0]));

        if (!remainingItems.isEmpty() && remaining != null) {
            remaining.accept(remainingItems.values().stream().toList());
        }
    }

    public static void addItems(@NotNull Inventory inventory, @NotNull ItemStack itemStack, @Nullable Consumer<List<ItemStack>> remaining) {
        addItems(inventory, List.of(itemStack), remaining);
    }

    public static void addOrDrop(@NotNull Inventory inventory, @NotNull List<ItemStack> items, @NotNull Location location) {
        addItems(inventory, items, remaining ->
                remaining.forEach(itemStack ->
                        location.getWorld().dropItemNaturally(location, itemStack)));
    }

    public static void addOrDrop(@NotNull Inventory inventory, @NotNull ItemStack itemStack, @NotNull Location location) {
        addOrDrop(inventory, List.of(itemStack), location);
    }

    public static void addOrDrop(@NotNull Player player, @NotNull List<ItemStack> items) {
        addOrDrop(player.getInventory(), items, player.getLocation());
    }

    public static void addOrDrop(@NotNull Player player, @NotNull ItemStack itemStack) {
        addOrDrop(player, List.of(itemStack));
    }

    public static void addToFirstIfNotThenToSecond(@NotNull Inventory inventory, @NotNull Inventory other, @NotNull List<ItemStack> items) {
        addItems(inventory, items, remaining ->
                other.addItem(items.toArray(new ItemStack[0])));
    }

    public static void addToFirstIfNotThenToSecond(@NotNull Inventory inventory, @NotNull Inventory other, @NotNull ItemStack itemStack) {
        addToFirstIfNotThenToSecond(inventory, other, List.of(itemStack));
    }


    public static void addToFirstIfNotThenToSecondElse(@NotNull Inventory inventory, @NotNull Inventory other, @NotNull List<ItemStack> items,
                                  @Nullable Consumer<List<ItemStack>> remaining) {
        addItems(inventory, items, remainingItems ->
                addItems(other, remainingItems, remainingItems2 -> {
                    if (remaining != null) {
                        remaining.accept(remainingItems2);
                    }
                })
        );
    }

    public static void addToFirstIfNotThenToSecondElse(@NotNull Inventory inventory, @NotNull Inventory other, @NotNull ItemStack itemStack,
                                                       @Nullable Consumer<List<ItemStack>> remaining) {
        addToFirstIfNotThenToSecondElse(inventory, other, List.of(itemStack), remaining);
    }

    public static void hasItems(@NotNull Inventory inventory, @NotNull List<ItemStack> items, @NotNull Consumer<List<ItemStack>> notOwned) {
        notOwned.accept(
                items.stream()
                        .filter(itemStack -> !inventory.containsAtLeast(itemStack, itemStack.getAmount()))
                        .collect(Collectors.toList())
        );
    }

    public static String getNameOrType(@NotNull ItemStack itemStack) {
        if (itemStack.hasItemMeta() && itemStack.getItemMeta().hasDisplayName()) {
            return itemStack.getItemMeta().getDisplayName();
        }

        final String type = itemStack.getType().getKey().getKey();

        return type.substring(0, 1).toUpperCase() + type.substring(1).toLowerCase();
    }

    public static int getAmount(@NotNull Inventory inventory, @NotNull ItemStack itemStack) {
        int amount = 0;

        for (int i = 0; i < inventory.getSize(); i++) {
            final ItemStack item = inventory.getItem(i);

            if (item == null || item.getType().equals(Material.AIR)) {
                continue;
            }

            if (itemStack.isSimilar(item)) {
                amount += item.getAmount();
            }
        }

        return amount;
    }
}
