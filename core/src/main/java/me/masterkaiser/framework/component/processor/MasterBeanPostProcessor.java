package me.masterkaiser.framework.component.processor;

import me.masterkaiser.framework.maker.BeanMaker;
import me.masterkaiser.framework.maker.BeanProcess;
import me.masterkaiser.framework.maker.BeanProcessType;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.util.List;

public class MasterBeanPostProcessor implements BeanPostProcessor {
    private final AnnotationConfigApplicationContext context;

    public MasterBeanPostProcessor(AnnotationConfigApplicationContext context) {
        this.context = context;
    }

    @Override
    public Object postProcessBeforeInitialization(Object bean, String beanName) throws BeansException {
        final BeanMaker maker = context.getBeansOfType(BeanMaker.class).values()
                .stream()
                .filter(beanMaker -> beanMaker.getClass().isAnnotationPresent(BeanProcess.class))
                .filter(beanMaker -> beanMaker.getClass().getAnnotation(BeanProcess.class).initializationLevel().equals(BeanProcessType.BEFORE))
                .filter(beanMaker -> beanMaker.isSupport(bean.getClass()))
                .findAny()
                .orElse(null);

        if (maker != null) {
            return maker.override(bean, beanName);
        }

        return BeanPostProcessor.super.postProcessBeforeInitialization(bean, beanName);
    }

    @Override
    public Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException {
        final List<BeanMaker> overrides = context.getBeansOfType(BeanMaker.class).values()
                .stream()
                .filter(beanMaker -> beanMaker.getClass().isAnnotationPresent(BeanProcess.class))
                .filter(beanMaker -> beanMaker.getClass().getAnnotation(BeanProcess.class).initializationLevel().equals(BeanProcessType.AFTER))
                .filter(beanMaker -> beanMaker.isSupport(bean.getClass()))
                .toList();

        overrides.forEach(beanMaker -> {
            beanMaker.override(bean, beanName);
        });

        return BeanPostProcessor.super.postProcessAfterInitialization(bean, beanName);
    }
}
