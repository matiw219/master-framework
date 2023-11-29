package me.masterkaiser.framework.gui;

import lombok.Getter;
import lombok.Setter;
import me.masterkaiser.framework.bukkit.util.ColorUtil;
import me.masterkaiser.framework.condition.BiResult;
import me.masterkaiser.framework.item.ItemBuilder;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.Event;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryCloseEvent;
import org.bukkit.event.inventory.InventoryDragEvent;
import org.bukkit.event.inventory.InventoryOpenEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.InventoryHolder;
import org.bukkit.inventory.ItemStack;
import org.bukkit.plugin.java.JavaPlugin;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.Optional;

public class Gui<Type> implements InventoryHolder, Listener {
    private JavaPlugin javaPlugin;
    private GuiBuilder guiBuilder;
    private Inventory inventory;
    private @Getter GuiFiller<ItemStack> fillerByItemStack;
    private @Getter GuiFiller<ItemBuilder> fillerByItemBuilder;
    private @Getter GuiEvents guiEvents;
    private @Getter GuiEvents otherGuiEvents;
    private @Getter @Setter boolean cancelClick = true;
    private @Getter @Setter boolean dispose = true;
    private @Getter @Setter CancelType cancelType = CancelType.TOP;
    private @Nullable BiResult<Type, String, String> result;

    public Gui(@NotNull JavaPlugin javaPlugin, @NotNull GuiBuilder guiBuilder, @NotNull Type type, boolean addItems) {
        this(javaPlugin, guiBuilder, type, addItems, null);
    }

    public Gui(@NotNull JavaPlugin javaPlugin, @NotNull GuiBuilder guiBuilder, @NotNull Type type, boolean addItems,
               @Nullable BiResult<Type, String, String> result) {
        this.javaPlugin = javaPlugin;
        this.guiBuilder = guiBuilder;
        this.fillerByItemStack = new GuiFillerItemStack(this);
        this.fillerByItemBuilder = new GuiFillerItemBuilder(this);
        this.guiEvents = new GuiEvents();
        this.otherGuiEvents = new GuiEvents();
        this.result = result;

        build(type, addItems);
    }

    public void build(@NotNull Type type, boolean addItems) {
        if (this.guiBuilder.getInventoryType() != null) {
            this.inventory = Bukkit.createInventory(
                    this,
                    this.guiBuilder.getInventoryType(),
                    ColorUtil.color((result == null ? this.guiBuilder.getName() : result.result(type, this.guiBuilder.getName())))
            );
        } else {
            this.inventory = Bukkit.createInventory(
                    this,
                    this.guiBuilder.getRows() * 9,
                    ColorUtil.color((result == null ? this.guiBuilder.getName() : result.result(type, this.guiBuilder.getName())))
            );
        }

        if (this.guiBuilder.getBackground() != null) {
            final ItemStack background = this.guiBuilder.getBackground().build();
            for (int i = 1; i <= this.inventory.getSize(); i++) {
                this.fillerByItemStack.setItem(i, background);
            }
        }

        if (addItems) {
            if (result != null) {
                setItems(type, result);
            } else {
                setItems();
            }
        }

        Bukkit.getPluginManager().registerEvents(this, javaPlugin);
    }

    public void setItems(@Nullable Type type) {
        if (type != null) {
            this.guiBuilder.getItems().forEach((integer, itemBuilder) ->
                    this.fillerByItemStack.setItem(integer, itemBuilder.build((Object) type, (BiResult<Object, String, String>) this.result)));
        } else {
            this.guiBuilder.getItems().forEach((integer, itemBuilder) -> this.fillerByItemBuilder.setItem(integer, itemBuilder));
        }
    }

    public void setItems(@Nullable Type type, @Nullable BiResult<Type, String, String> result) {
        if (type != null && result != null) {
            this.guiBuilder.getItems().forEach((integer, itemBuilder) ->{
                        getInventory().setItem(integer - 1, itemBuilder.build((Object) type, (BiResult<Object, String, String>) this.result));
                    });
        } else {
            this.guiBuilder.getItems().forEach((integer, itemBuilder) -> this.fillerByItemBuilder.setItem(integer, itemBuilder));
        }
    }

    public <T> void setItemsByObject(@Nullable T type, @Nullable BiResult<T, String, String> result) {
        if (type != null && result != null) {
            this.guiBuilder.getItems().forEach((integer, itemBuilder) ->
                    this.fillerByItemStack.setItem(integer, itemBuilder.build(type, (BiResult<Object, String, String>) result)));
        } else {
            this.guiBuilder.getItems().forEach((integer, itemBuilder) -> this.fillerByItemBuilder.setItem(integer, itemBuilder));
        }
    }

    public void setItems() {
        setItems(null, null);
    }

    @NotNull
    @Override
    public Inventory getInventory() {
        return this.inventory;
    }

    public void open(@NotNull Player player) {
        player.openInventory(getInventory());
    }

    @EventHandler
    public void onClick(InventoryClickEvent event) {
        if (event.isCancelled()) {
            return;
        }
        if (event.getInventory().getHolder() == null) {
            return;
        }
        if (!event.getInventory().getHolder().equals(this)) {
            return;
        }
        if (isCancelClick()) {
            if (this.cancelType == CancelType.BOTH || (event.getRawSlot() < event.getInventory().getSize())) {
                event.setCancelled(true);
                event.setResult(Event.Result.DENY);
            }
            if (this.cancelType == CancelType.TOP && event.getClick().toString().toLowerCase().contains("shift")
                    && event.getRawSlot() >= event.getInventory().getSize()) {
                event.setCancelled(true);
                event.setResult(Event.Result.DENY);
            }
        }

        Optional.ofNullable(getGuiEvents().getOnClick()).ifPresent(inventoryClickEventGuiHandleType ->
                inventoryClickEventGuiHandleType.handle(event));

        Optional.ofNullable(getOtherGuiEvents().getOnClick()).ifPresent(inventoryClickEventGuiHandleType ->
                inventoryClickEventGuiHandleType.handle(event));

        final int slot = event.getRawSlot();

        Optional.ofNullable(getGuiEvents().getClicks().get(slot)).ifPresent(inventoryClickEventGuiHandleType ->
                inventoryClickEventGuiHandleType.handle(event));

        Optional.ofNullable(getOtherGuiEvents().getClicks().get(slot)).ifPresent(inventoryClickEventGuiHandleType ->
                inventoryClickEventGuiHandleType.handle(event));
    }

    @EventHandler
    public void onOpen(InventoryOpenEvent event) {
        if (event.isCancelled()) {
            return;
        }
        if (event.getInventory().getHolder() == null) {
            return;
        }
        if (!event.getInventory().getHolder().equals(this)) {
            return;
        }
        Optional.ofNullable(getGuiEvents().getOnOpen()).ifPresent(inventoryOpenEventGuiHandleType ->
                inventoryOpenEventGuiHandleType.handle(event));
        Optional.ofNullable(getOtherGuiEvents().getOnOpen()).ifPresent(inventoryOpenEventGuiHandleType ->
                inventoryOpenEventGuiHandleType.handle(event));
    }

    @EventHandler
    public void onClose(InventoryCloseEvent event) {
        if (event.getInventory().getHolder() == null) {
            return;
        }
        if (!event.getInventory().getHolder().equals(this)) {
            return;
        }
        Optional.ofNullable(getGuiEvents().getOnClose()).ifPresent(inventoryCloseEventGuiHandleType -> 
                inventoryCloseEventGuiHandleType.handle(event));

        Optional.ofNullable(getOtherGuiEvents().getOnClose()).ifPresent(inventoryCloseEventGuiHandleType ->
                inventoryCloseEventGuiHandleType.handle(event));

        if (event.getViewers().size() == 1 && isDispose()) {
            dispose();
        }
    }
    
    @EventHandler
    public void onDrag(InventoryDragEvent event) {
        if (event.getInventory().getHolder() == null) {
            return;
        }
        if (!event.getInventory().getHolder().equals(this)) {
            return;
        }
        if (this.isCancelClick() && this.cancelType == CancelType.TOP) {
            event.getNewItems().keySet().stream()
                    .filter(integer -> integer < event.getInventory().getSize())
                    .findAny()
                    .ifPresent(integer -> {
                        event.setCancelled(true);
                        event.setResult(Event.Result.DENY);
                    });
        }
        Optional.ofNullable(getGuiEvents().getOnDrag()).ifPresent(inventoryDragEventGuiHandleType -> 
                inventoryDragEventGuiHandleType.handle(event));
        Optional.ofNullable(getOtherGuiEvents().getOnDrag()).ifPresent(inventoryDragEventGuiHandleType ->
                inventoryDragEventGuiHandleType.handle(event));
    }

    public void dispose() {
        InventoryClickEvent.getHandlerList().unregister(this);
        InventoryOpenEvent.getHandlerList().unregister(this);
        InventoryDragEvent.getHandlerList().unregister(this);
        InventoryCloseEvent.getHandlerList().unregister(this);
        this.javaPlugin = null;
        this.guiBuilder = null;
        this.inventory = null;
        this.fillerByItemStack = null;
        this.fillerByItemBuilder = null;
        this.guiEvents = null;
        this.otherGuiEvents = null;
    }


    public static <Type> Gui<Type> create(@NotNull JavaPlugin javaPlugin, @NotNull GuiBuilder guiBuilder, @NotNull Type type, boolean addItems) {
        return new Gui<>(javaPlugin, guiBuilder, type, addItems);
    }

    public static <Type> Gui<Type> create(@NotNull JavaPlugin javaPlugin, @NotNull GuiBuilder guiBuilder, @NotNull Type type, boolean addItems,
                                          @Nullable BiResult<Type, String, String> result) {
        return new Gui<>(javaPlugin, guiBuilder, type, addItems, result);
    }
}
