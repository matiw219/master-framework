package me.masterkaiser.framework.gui;

import me.masterkaiser.framework.item.ItemBuilder;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public class GuiFillerItemBuilder implements GuiFiller<ItemBuilder> {
    private final Gui<?> gui;

    public GuiFillerItemBuilder(Gui<?> gui) {
        this.gui = gui;
    }

    @Override
    public void setItem(int slot, @Nullable ItemBuilder itemBuilder) {
        setItem(slot, itemBuilder, null);
    }

    @Override
    public void setItem(int slot, @Nullable ItemBuilder itemBuilder, @Nullable GuiHandleType<InventoryClickEvent> onClick) {
        gui.getInventory().setItem((slot - 1), itemBuilder.build());
        gui.getGuiEvents().handleClickEvent((slot), onClick);
    }

    @Override
    public void addItem(@NotNull ItemBuilder itemBuilder) {
        gui.getInventory().addItem(itemBuilder.build());
    }
}
