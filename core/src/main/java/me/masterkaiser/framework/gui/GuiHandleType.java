package me.masterkaiser.framework.gui;

import org.bukkit.event.Event;

@FunctionalInterface
public interface GuiHandleType<T extends Event> {
    void handle(T t);
}
