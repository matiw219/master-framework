package me.masterkaiser.framework.component.makers;

import me.masterkaiser.framework.Initializable;
import me.masterkaiser.framework.maker.BeanMaker;
import me.masterkaiser.framework.maker.BeanProcess;
import me.masterkaiser.framework.maker.BeanProcessType;

@BeanProcess(initializationLevel = BeanProcessType.AFTER)
public class InitializeMaker implements BeanMaker {
    @Override
    public boolean isSupport(Class<?> clazz) {
        return Initializable.class.isAssignableFrom(clazz);
    }

    @Override
    public Object override(Object bean, String beanName) {
        final Initializable initializable = (Initializable) bean;
        initializable.initialize();

        return bean;
    }
}
