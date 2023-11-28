package me.masterkaiser.framework.component.makers;

import me.masterkaiser.command.CommandManager;
import me.masterkaiser.command.MasterCommand;
import me.masterkaiser.framework.maker.BeanMaker;
import me.masterkaiser.framework.maker.BeanProcess;
import me.masterkaiser.framework.maker.BeanProcessType;

@BeanProcess(initializationLevel = BeanProcessType.AFTER)
public class CommandMaker implements BeanMaker {

    private final CommandManager commandManager;

    public CommandMaker(CommandManager commandManager) {
        this.commandManager = commandManager;
    }

    @Override
    public boolean isSupport(Class<?> clazz) {
        return MasterCommand.class.isAssignableFrom(clazz);
    }

    @Override
    public Object override(Object bean, String beanName) {
        final MasterCommand masterCommand = (MasterCommand) bean;

        if (masterCommand.commandMeta().getEnabled() == null || masterCommand.commandMeta().getEnabled()) {
            commandManager.registerCommand(masterCommand);
        }

        return bean;
    }
}
