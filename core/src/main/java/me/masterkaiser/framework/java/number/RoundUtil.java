package me.masterkaiser.framework.java.number;

import lombok.experimental.UtilityClass;

@UtilityClass
public class RoundUtil {
    public static double roundToDecimalPlaces(double number, int decimalPlaces) {
        if (decimalPlaces < 0) {
            throw new IllegalArgumentException("The number of decimal places cannot be less than 0");
        }

        double multiplier = Math.pow(10, decimalPlaces);
        return Math.round(number * multiplier) / multiplier;
    }

    public static float roundToDecimalPlaces(float number, int decimalPlaces) {
        if (decimalPlaces < 0) {
            throw new IllegalArgumentException("The number of decimal places cannot be less than 0");
        }

        double multiplier = Math.pow(10, decimalPlaces);
        return Math.round(number * (float) multiplier) / (float) multiplier;
    }
}
