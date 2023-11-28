package me.masterkaiser.framework.scheduler;

import org.bukkit.scheduler.BukkitRunnable;
import org.jetbrains.annotations.NotNull;

abstract public class MasterRunnable extends BukkitRunnable {
    abstract public @NotNull SchedulerMeta schedulerMeta();
}
