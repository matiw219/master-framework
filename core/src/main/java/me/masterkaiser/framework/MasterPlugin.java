package me.masterkaiser.framework;

import eu.okaeri.configs.serdes.OkaeriSerdesPack;
import lombok.Getter;
import me.masterkaiser.command.CommandManager;
import me.masterkaiser.framework.component.makers.CommandMaker;
import me.masterkaiser.framework.component.makers.EventsMaker;
import me.masterkaiser.framework.component.makers.InitializeMaker;
import me.masterkaiser.framework.component.makers.SchedulerMaker;
import me.masterkaiser.framework.component.makers.YamlMaker;
import me.masterkaiser.framework.component.processor.MasterBeanPostProcessor;
import me.masterkaiser.framework.object.pair.Pair;
import me.masterkaiser.framework.persistence.EnablePersistence;
import me.masterkaiser.framework.persistence.PersistenceManager;
import me.masterkaiser.framework.persistence.RepositoryFactory;
import me.masterkaiser.framework.yaml.DatabaseConfig;
import org.bukkit.plugin.java.JavaPlugin;
import org.jetbrains.annotations.NotNull;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

abstract public class MasterPlugin extends JavaPlugin {
    private @Getter AnnotationConfigApplicationContext context;

    public void preSpring() {

    }

    @Override
    public void onLoad() {
        preSpring();
        this.context = new AnnotationConfigApplicationContext();
        this.context.setClassLoader(this.getClassLoader());

        final Pair<Boolean, String> loaded = load();
        if (loaded.getLeft() == null || !loaded.getLeft()) {
            throw new MasterAppException(loaded.getRight() == null ? "Plugin cannot be load!" : loaded.getRight());
        }
    }

    @Override
    public void onEnable() {
        GlobalSettings.PREFIX = this.getDescription().getName().replace("-", "_");
        GlobalSettings.DATA_FOLDER = this.getDataFolder().getPath();

        this.context.registerBean(CommandManager.class, CommandManager::new);
        this.context.getBeanFactory().registerSingleton("", this);
        this.context.register(
                CommandMaker.class,
                SchedulerMaker.class,
                EventsMaker.class,
                YamlMaker.class,
                InitializeMaker.class
        );

        if (this.getClass().isAnnotationPresent(EnablePersistence.class)) {
            try {
                Class.forName("eu.okaeri.persistence.document.DocumentPersistence");
                this.context.register(DatabaseConfig.class);
                this.context.register(PersistenceManager.class);
                this.context.register(RepositoryFactory.class);
            } catch (ClassNotFoundException e) {
                throw new MasterAppException("Persistence enabled but repository library not included in project");
            }
        }

        makers();

        this.context.register(MasterBeanPostProcessor.class);

        context.scan(ComponentHelper.getScans(getClass()));

        final Pair<Boolean, String> enabled = enable();
        if (enabled.getLeft() == null || !enabled.getLeft()) {
            throw new MasterAppException(enabled.getRight() == null ? "Plugin cannot be start!" : enabled.getRight());
        }
    }

    @Override
    public void onDisable() {
        this.context.getBeansOfType(Disableable.class).forEach((s, disableable) -> disableable.onDisable());
        disable();
    }


    public @NotNull Pair<Boolean, String> load() {
        return new Pair<>(true, null);
    }

    public void makers() {}

    abstract public @NotNull Pair<Boolean, String> enable();

    public void disable() {}

    abstract public OkaeriSerdesPack serializationPack();
}
