package me.masterkaiser.framework.java.number;

import lombok.experimental.UtilityClass;
import org.jetbrains.annotations.Nullable;

import java.util.Optional;

@UtilityClass
public class ParseUtil {
    public static Optional<Integer> parseInt(@Nullable String string) {
        if (string == null) {
            return Optional.empty();
        }

        try {
            int i = Integer.parseInt(string);
            return Optional.of(i);
        } catch (NumberFormatException e) {
            return Optional.empty();
        }
    }

    public static Optional<Double> parseDouble(@Nullable String string) {
        if (string == null) {
            return Optional.empty();
        }

        try {
            double d = Double.parseDouble(string);
            return Optional.of(d);
        } catch (NumberFormatException e) {
            return Optional.empty();
        }
    }

    public static Optional<Float> parseFloat(@Nullable String string) {
        if (string == null) {
            return Optional.empty();
        }

        try {
            float f = Float.parseFloat(string);
            return Optional.of(f);
        } catch (NumberFormatException e) {
            return Optional.empty();
        }
    }

    public static Optional<Long> parseLong(@Nullable String string) {
        if (string == null) {
            return Optional.empty();
        }

        try {
            long l = Long.parseLong(string);
            return Optional.of(l);
        } catch (NumberFormatException e) {
            return Optional.empty();
        }
    }
}
