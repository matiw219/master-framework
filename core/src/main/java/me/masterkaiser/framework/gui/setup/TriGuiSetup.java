package me.masterkaiser.framework.gui.setup;

import lombok.Getter;
import me.masterkaiser.framework.MasterPlugin;
import me.masterkaiser.framework.gui.Gui;
import org.bukkit.entity.Player;

abstract public class TriGuiSetup<Type, Link, Value> {
    private final @Getter MasterPlugin masterPlugin;

    public TriGuiSetup(MasterPlugin masterPlugin) {
        this.masterPlugin = masterPlugin;
    }

    abstract public Gui<Type> build(Type type, Link link, Value value);

    public Gui<Type> andOpen(Type type, Link link, Value value, Player player) {
        Gui<Type> gui = build(type, link, value);
        gui.open(player);
        return gui;
    }
}
