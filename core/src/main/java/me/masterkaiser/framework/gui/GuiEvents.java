package me.masterkaiser.framework.gui;

import lombok.Getter;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryCloseEvent;
import org.bukkit.event.inventory.InventoryDragEvent;
import org.bukkit.event.inventory.InventoryOpenEvent;
import org.jetbrains.annotations.Nullable;

import java.util.HashMap;
import java.util.Map;

@Getter
public class GuiEvents implements IGuiEvents {
    private final Map<Integer, GuiHandleType<InventoryClickEvent>> clicks = new HashMap<>();
    private GuiHandleType<InventoryOpenEvent> onOpen;
    private GuiHandleType<InventoryClickEvent> onClick;
    private GuiHandleType<InventoryDragEvent> onDrag;
    private GuiHandleType<InventoryCloseEvent> onClose;

    @Override
    public void handleClickEvent(int slot, @Nullable GuiHandleType<InventoryClickEvent> onClick) {
        if (onClick == null) {
            this.clicks.remove((slot - 1));
            return;
        }
        this.clicks.put((slot - 1), onClick);
    }

    @Override
    public void handleClickEvent(@Nullable GuiHandleType<InventoryClickEvent> onClick) {
        this.onClick = onClick;
    }

    @Override
    public void handleOpenEvent(@Nullable GuiHandleType<InventoryOpenEvent> onOpen) {
        this.onOpen = onOpen;
    }

    @Override
    public void handleCloseEvent(@Nullable GuiHandleType<InventoryCloseEvent> onClose) {
        this.onClose = onClose;
    }

    @Override
    public void handleDragEvent(@Nullable GuiHandleType<InventoryDragEvent> onDrag) {
        this.onDrag = onDrag;
    }
}
