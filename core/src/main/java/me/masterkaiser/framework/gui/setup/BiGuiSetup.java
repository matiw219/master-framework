package me.masterkaiser.framework.gui.setup;

import lombok.Getter;
import me.masterkaiser.framework.MasterPlugin;
import me.masterkaiser.framework.gui.Gui;
import org.bukkit.entity.Player;

abstract public class BiGuiSetup<Type, Link> {
    private final @Getter MasterPlugin masterPlugin;

    public BiGuiSetup(MasterPlugin masterPlugin) {
        this.masterPlugin = masterPlugin;
    }

    abstract public Gui<Type> build(Type type, Link link);

    public Gui<Type> andOpen(Type type, Link link, Player player) {
        Gui<Type> gui = build(type, link);
        gui.open(player);
        return gui;
    }
}
