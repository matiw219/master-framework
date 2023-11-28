package me.masterkaiser.framework.gui;

import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.ItemStack;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public class GuiFillerItemStack implements GuiFiller<ItemStack> {
    private final Gui<?> gui;

    public GuiFillerItemStack(Gui<?> gui) {
        this.gui = gui;
    }

    @Override
    public void setItem(int slot, @Nullable ItemStack itemStack) {
        setItem(slot, itemStack, null);
    }

    @Override
    public void setItem(int slot, @Nullable ItemStack itemStack, @Nullable GuiHandleType<InventoryClickEvent> onClick) {
        gui.getInventory().setItem((slot - 1), itemStack);
        gui.getGuiEvents().handleClickEvent((slot), onClick);
    }

    @Override
    public void addItem(@NotNull ItemStack itemStack) {
        gui.getInventory().addItem(itemStack);
    }
}
