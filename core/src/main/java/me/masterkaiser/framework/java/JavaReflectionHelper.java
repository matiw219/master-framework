package me.masterkaiser.framework.java;

import lombok.experimental.UtilityClass;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.lang.reflect.Field;

@UtilityClass
public class JavaReflectionHelper {
    public static void setFieldValue(@NotNull Class<?> clazz, @NotNull String fieldName, @Nullable Object value) {
        try {
            final Field field = clazz.getDeclaredField(fieldName);
            setFieldValue(field, value);
        } catch (NoSuchFieldException e) {
            throw new RuntimeException(e);
        }
    }

    public static void setFieldValue(@NotNull Field field, @Nullable Object value) {
        if (!field.canAccess(null)) {
            field.setAccessible(true);
            try {
                field.set(null, value);
            } catch (IllegalAccessException e) {
                throw new RuntimeException(e);
            }
        }
    }
}
