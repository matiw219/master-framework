package me.masterkaiser.framework.component.makers;

import me.masterkaiser.framework.MasterPlugin;
import me.masterkaiser.framework.maker.BeanMaker;
import me.masterkaiser.framework.maker.BeanProcess;
import me.masterkaiser.framework.maker.BeanProcessType;
import org.bukkit.event.Listener;

@BeanProcess(initializationLevel = BeanProcessType.AFTER)
public class EventsMaker implements BeanMaker {
    private MasterPlugin masterPlugin;

    public EventsMaker(MasterPlugin masterPlugin) {
        this.masterPlugin = masterPlugin;
    }

    @Override
    public boolean isSupport(Class<?> clazz) {
        return Listener.class.isAssignableFrom(clazz);
        /*return Arrays.stream(clazz.getMethods())
                .anyMatch(method -> method.isAnnotationPresent(EventHandler.class));*/
    }

    @Override
    public Object override(Object bean, String beanName) {
        final Listener listener = (Listener) bean;

        masterPlugin.getServer().getPluginManager().registerEvents(listener, masterPlugin);

        return bean;
        /*final Listener listener = new Listener() {};
        final Class<?> clazz = bean.getClass();

        Arrays.stream(clazz.getMethods())
                .filter(method -> method.isAnnotationPresent(EventHandler.class))
                .forEach(method -> {
                    final EventHandler eventHandler = method.getAnnotation(EventHandler.class);

                    if (method.getParameterTypes().length != 1) {
                        return;
                    }

                    final Class<?> eventClass = method.getParameterTypes()[0];

                    if (!Event.class.isAssignableFrom(eventClass)) {
                        return;
                    }

                    this.masterPlugin.getServer().getPluginManager().registerEvent(
                            (Class<? extends Event>) eventClass,
                            listener,
                            eventHandler.priority(),
                            (listen, event) -> {
                                if (event instanceof Cancellable cancellable && cancellable.isCancelled() && eventHandler.ignoreCancelled()) {
                                    return;
                                }

                                try {
                                    method.invoke(bean, event);
                                } catch (IllegalAccessException | InvocationTargetException e) {
                                    throw new RuntimeException(e);
                                }
                            },
                            this.masterPlugin);
                });

        return bean;*/
    }
}
