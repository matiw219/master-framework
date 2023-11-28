package me.masterkaiser.framework.condition;

@FunctionalInterface
public interface BiResult<T, O, R> {
    R result(T t, O result);
}
