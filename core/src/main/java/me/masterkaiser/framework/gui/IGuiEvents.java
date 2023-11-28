package me.masterkaiser.framework.gui;

import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryCloseEvent;
import org.bukkit.event.inventory.InventoryDragEvent;
import org.bukkit.event.inventory.InventoryOpenEvent;
import org.jetbrains.annotations.Nullable;

public interface IGuiEvents {
    void handleClickEvent(int slot, @Nullable GuiHandleType<InventoryClickEvent> onClick);
    void handleClickEvent(@Nullable GuiHandleType<InventoryClickEvent> onClick);
    void handleOpenEvent(@Nullable GuiHandleType<InventoryOpenEvent> onOpen);
    void handleCloseEvent(@Nullable GuiHandleType<InventoryCloseEvent> onClose);
    void handleDragEvent(@Nullable GuiHandleType<InventoryDragEvent> onDrag);
}
