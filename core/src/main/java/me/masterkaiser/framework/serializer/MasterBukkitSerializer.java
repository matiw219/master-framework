package me.masterkaiser.framework.serializer;

import eu.okaeri.configs.serdes.OkaeriSerdesPack;
import eu.okaeri.configs.serdes.SerdesRegistry;
import lombok.NonNull;
import me.masterkaiser.command.ArgumentSerializer;
import me.masterkaiser.command.CommandSerializer;
import me.masterkaiser.framework.bossbar.MasterBossBarSerializer;
import me.masterkaiser.framework.bukkit.BukkitSerializers;
import me.masterkaiser.framework.gui.GuiBuilderSerializer;
import me.masterkaiser.framework.item.ItemBuilder16Serializer;
import me.masterkaiser.framework.item.base.BaseItemsSerializer;

public class MasterBukkitSerializer implements OkaeriSerdesPack {
    @Override
    public void register(@NonNull SerdesRegistry registry) {
        registry.register(new RawMessageSerializer());
        registry.register(new RawTitleSerializer());
        registry.register(new CommandSerializer());
        registry.register(new ArgumentSerializer());
        registry.register(new BukkitSerializers());
        registry.register(new ItemBuilder16Serializer());
        registry.register(new GuiBuilderSerializer());
        registry.register(new BaseItemsSerializer());
        registry.register(new MasterBossBarSerializer());
        registry.register(new SchedulerMetaSerializer());
    }
}
