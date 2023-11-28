package me.masterkaiser.framework.maker;

public interface BeanMaker {
    boolean isSupport(Class<?> clazz);
    Object override(Object bean, String beanName);
}
