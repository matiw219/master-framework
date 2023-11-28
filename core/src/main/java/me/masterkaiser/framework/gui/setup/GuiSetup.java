package me.masterkaiser.framework.gui.setup;

import lombok.Getter;
import me.masterkaiser.framework.MasterPlugin;
import me.masterkaiser.framework.gui.Gui;
import org.bukkit.entity.Player;

abstract public class GuiSetup<Type> {
    private final @Getter MasterPlugin masterPlugin;

    public GuiSetup(MasterPlugin masterPlugin) {
        this.masterPlugin = masterPlugin;
    }

    abstract public Gui<Type> build(Type type);

    public Gui<Type> andOpen(Type type, Player player) {
        Gui<Type> gui = build(type);
        gui.open(player);
        return gui;
    }
}
