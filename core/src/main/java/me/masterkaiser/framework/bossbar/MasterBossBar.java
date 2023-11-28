package me.masterkaiser.framework.bossbar;

import lombok.AllArgsConstructor;
import lombok.Getter;
import me.masterkaiser.framework.bukkit.util.ColorUtil;
import me.masterkaiser.framework.condition.BiResult;
import org.bukkit.Bukkit;
import org.bukkit.boss.BarColor;
import org.bukkit.boss.BarFlag;
import org.bukkit.boss.BarStyle;
import org.bukkit.boss.BossBar;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

import java.util.List;

@AllArgsConstructor
@Getter
public class MasterBossBar {
    private @NotNull String title;
    private @NotNull BarStyle style;
    private @NotNull BarColor color;
    private List<BarFlag> flags;
    private Double progress;

    public MasterBossBar(@NotNull String title, @NotNull BarStyle style, @NotNull BarColor color) {
        this.title = title;
        this.style = style;
        this.color = color;
    }

    public BossBar build() {
        final BossBar bossBar = Bukkit.createBossBar(ColorUtil.color(this.title), color, style);

        if (this.progress != null) {
            bossBar.setProgress(this.progress);
        }

        if (this.flags != null) {
            this.flags.forEach(bossBar::addFlag);
        }

        return bossBar;
    }

    public BossBar build(@NotNull Player player, @NotNull BiResult<Player, String, String> replaces) {
        final BossBar bossBar = Bukkit.createBossBar(ColorUtil.color(replaces.result(player, this.title)), color, style);

        if (this.progress != null) {
            bossBar.setProgress(this.progress);
        }

        if (this.flags != null) {
            this.flags.forEach(bossBar::addFlag);
        }

        return bossBar;
    }

    public void applyFor(@NotNull BossBar bossBar) {
        bossBar.setTitle(ColorUtil.color(this.title));
        bossBar.setStyle(this.style);
        bossBar.setColor(this.color);

        if (this.progress != null) {
            bossBar.setProgress(this.progress);
        }

        if (this.flags != null) {
            this.flags.forEach(bossBar::addFlag);
        }
    }

    public void applyFor(@NotNull BossBar bossBar, @NotNull Player player, @NotNull BiResult<Player, String, String> replaces) {
        bossBar.setTitle(ColorUtil.color(replaces.result(player, this.title)));
        bossBar.setStyle(this.style);
        bossBar.setColor(this.color);

        if (this.progress != null) {
            bossBar.setProgress(this.progress);
        }

        if (this.flags != null) {
            this.flags.forEach(bossBar::addFlag);
        }
    }

    public MasterBossBar setColor(BarColor color) {
        this.color = color;
        return this;
    }

    public MasterBossBar setFlags(List<BarFlag> flags) {
        this.flags = flags;
        return this;
    }

    public MasterBossBar setProgress(Double progress) {
        this.progress = progress;
        return this;
    }

    public MasterBossBar setStyle(BarStyle style) {
        this.style = style;
        return this;
    }

    public MasterBossBar setTitle(String title) {
        this.title = title;
        return this;
    }
}
