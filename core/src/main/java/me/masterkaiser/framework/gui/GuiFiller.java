package me.masterkaiser.framework.gui;

import org.bukkit.event.inventory.InventoryClickEvent;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public interface GuiFiller<T> {
    void setItem(int slot, @Nullable T t);
    void setItem(int slot, @Nullable T t, @Nullable GuiHandleType<InventoryClickEvent> onClick);
    void addItem(@NotNull T t);
}
