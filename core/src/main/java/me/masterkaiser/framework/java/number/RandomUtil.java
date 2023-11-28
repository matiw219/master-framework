package me.masterkaiser.framework.java.number;

import lombok.Getter;
import lombok.experimental.UtilityClass;

import java.util.Random;

@UtilityClass
public class RandomUtil {
    private static final @Getter Random random = new Random();

    public static int randomInt(int a, int b) {
        return RandomUtil.random.nextInt(Math.max(a, b) - Math.min(a, b) + 1) + Math.min(a, b);
    }

   public static float randomFloat(float a, float b) {
        return Math.min(a, b) + random.nextFloat() * (Math.max(a, b) - Math.min(a, b));
   }

    public static double randomDouble(double a, double b) {
        return Math.min(a, b) + random.nextDouble() * (Math.max(a, b) - Math.min(a, b));
    }

    public static boolean reachChance(double chance) {
        return (chance >= 100.0 || (random.nextDouble() * 100.0 < chance));
    }
}
