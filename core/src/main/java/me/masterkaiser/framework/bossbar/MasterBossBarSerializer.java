package me.masterkaiser.framework.bossbar;

import eu.okaeri.configs.schema.GenericsDeclaration;
import eu.okaeri.configs.serdes.DeserializationData;
import eu.okaeri.configs.serdes.ObjectSerializer;
import eu.okaeri.configs.serdes.SerializationData;
import lombok.NonNull;
import org.bukkit.boss.BarColor;
import org.bukkit.boss.BarFlag;
import org.bukkit.boss.BarStyle;

import java.util.List;

public class MasterBossBarSerializer implements ObjectSerializer<MasterBossBar> {
    @Override
    public boolean supports(@NonNull Class<? super MasterBossBar> type) {
        return MasterBossBar.class.isAssignableFrom(type);
    }

    @Override
    public void serialize(@NonNull MasterBossBar object, @NonNull SerializationData data, @NonNull GenericsDeclaration generics) {
        data.add("title", object.getTitle());
        data.add("style", object.getStyle());
        data.add("color", object.getColor());
        if (object.getFlags() != null && !object.getFlags().isEmpty()) {
            data.add("flags", object.getFlags());
        }
        if (object.getProgress() != null) {
            data.add("progress", object.getProgress());
        }
    }

    @Override
    public MasterBossBar deserialize(@NonNull DeserializationData data, @NonNull GenericsDeclaration generics) {
        final String title = data.get("title", String.class);
        final BarStyle barStyle = data.get("style", BarStyle.class);
        final BarColor barColor = data.get("color", BarColor.class);

        final List<BarFlag> barFlags = data.containsKey("flags")
                ? data.getAsList("flags", BarFlag.class)
                : null;

        final Double progress = data.containsKey("progress")
                ? data.get("progress", Double.class)
                : null;

        return new MasterBossBar(title, barStyle, barColor, barFlags, progress);
    }
}
