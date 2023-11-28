package me.masterkaiser.framework.condition;

@FunctionalInterface
public interface Result<T, R> {
    R result(T t);
}
