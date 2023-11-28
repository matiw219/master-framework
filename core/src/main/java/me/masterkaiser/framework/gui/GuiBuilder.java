package me.masterkaiser.framework.gui;

import lombok.Getter;
import me.masterkaiser.framework.item.ItemBuilder;
import org.bukkit.event.inventory.InventoryType;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.LinkedHashMap;
import java.util.Map;

@Getter
public class GuiBuilder {
    private final @NotNull String name;
    private @Nullable InventoryType inventoryType;
    private int rows;
    private @Nullable Map<String, Integer> actions = null;
    private Map<Integer, ItemBuilder> items = new LinkedHashMap<>();
    private @Nullable ItemBuilder background;

    public GuiBuilder(@NotNull String name, int rows) {
        this.name = name;
        this.rows = rows;
    }

    public GuiBuilder(@NotNull String name, @Nullable InventoryType inventoryType) {
        this.name = name;
        this.inventoryType = inventoryType;
    }

    public GuiBuilder(@NotNull String name, @Nullable InventoryType inventoryType, int rows) {
        this.name = name;
        this.inventoryType = inventoryType;
        this.rows = rows;
    }

    public GuiBuilder putItem(int slot, ItemBuilder itemBuilder) {
        this.items.put(slot, itemBuilder);

        return this;
    }

    public GuiBuilder setActions(Map<String, Integer> actions) {
        this.actions = actions;
        return this;
    }

    public GuiBuilder setItems(Map<Integer, ItemBuilder> items) {
        this.items = items;
        return this;
    }

    public GuiBuilder setBackground(@Nullable ItemBuilder background) {
        this.background = background;
        return this;
    }

    public GuiBuilder addAction(String action, int slot) {
        if (this.actions == null) {
            this.actions = new LinkedHashMap<>();
        }
        this.actions.put(action, slot);

        return this;
    }
}
