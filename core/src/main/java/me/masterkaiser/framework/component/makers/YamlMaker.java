package me.masterkaiser.framework.component.makers;

import eu.okaeri.configs.ConfigManager;
import eu.okaeri.configs.OkaeriConfig;
import eu.okaeri.configs.yaml.bukkit.YamlBukkitConfigurer;
import me.masterkaiser.framework.MasterPlugin;
import me.masterkaiser.framework.maker.BeanMaker;
import me.masterkaiser.framework.maker.BeanProcess;
import me.masterkaiser.framework.maker.BeanProcessType;
import me.masterkaiser.framework.yaml.YamlConfig;

@BeanProcess(initializationLevel = BeanProcessType.BEFORE)
public class YamlMaker implements BeanMaker {
    private final MasterPlugin masterPlugin;

    public YamlMaker(MasterPlugin masterPlugin) {
        this.masterPlugin = masterPlugin;
    }

    @Override
    public boolean isSupport(Class<?> clazz) {
        return OkaeriConfig.class.isAssignableFrom(clazz) && clazz.isAnnotationPresent(YamlConfig.class);
    }

    @Override
    public Object override(Object bean, String beanName) {
        final YamlConfig yamlConfig = bean.getClass().getAnnotation(YamlConfig.class);
        final String fileName = (
                yamlConfig.fileName().endsWith(".yml") ?
                        yamlConfig.fileName() :
                        yamlConfig.fileName() + ".yml"
        );

        return ConfigManager.create((Class<? extends OkaeriConfig>) bean.getClass(), config -> {
            config.withConfigurer(new YamlBukkitConfigurer(), masterPlugin.serializationPack());
            config.withBindFile(masterPlugin.getDataFolder().getPath() + "/" + fileName);
            config.withRemoveOrphans(true);
            config.saveDefaults();
            config.load(true);
        });
    }
}
