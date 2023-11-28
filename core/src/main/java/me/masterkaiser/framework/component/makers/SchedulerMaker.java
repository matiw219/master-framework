package me.masterkaiser.framework.component.makers;

import me.masterkaiser.framework.MasterPlugin;
import me.masterkaiser.framework.maker.BeanMaker;
import me.masterkaiser.framework.maker.BeanProcess;
import me.masterkaiser.framework.maker.BeanProcessType;
import me.masterkaiser.framework.scheduler.MasterRunnable;
import me.masterkaiser.framework.scheduler.Scheduler;
import me.masterkaiser.framework.scheduler.SchedulerMeta;
import org.bukkit.scheduler.BukkitRunnable;

@BeanProcess(initializationLevel = BeanProcessType.AFTER)
public class SchedulerMaker implements BeanMaker {
    private final MasterPlugin masterPlugin;

    public SchedulerMaker(MasterPlugin masterPlugin) {
        this.masterPlugin = masterPlugin;
    }

    @Override
    public boolean isSupport(Class<?> clazz) {
        return MasterRunnable.class.isAssignableFrom(clazz) ||
                BukkitRunnable.class.isAssignableFrom(clazz) && clazz.isAnnotationPresent(Scheduler.class);
    }

    @Override
    public Object override(Object bean, String beanName) {
        if (MasterRunnable.class.isAssignableFrom(bean.getClass())) {
            final MasterRunnable masterRunnable = (MasterRunnable) bean;
            final SchedulerMeta schedulerMeta = masterRunnable.schedulerMeta();
            registerTask((BukkitRunnable) bean, schedulerMeta.isAsync(), schedulerMeta.getDelay(), schedulerMeta.getInterval());
        }

        final Scheduler scheduler = bean.getClass().getAnnotation(Scheduler.class);

        if (scheduler != null) {
            registerTask((BukkitRunnable) bean, scheduler.async(), scheduler.delay(), scheduler.interval());
        }

        return bean;
    }

    public void registerTask(BukkitRunnable bukkitRunnable, boolean async, int delay, int interval) {
        if (async) {
            bukkitRunnable.runTaskTimerAsynchronously(masterPlugin, delay, interval);
        } else {
            bukkitRunnable.runTaskTimer(masterPlugin, delay, interval);
        }
    }
}
