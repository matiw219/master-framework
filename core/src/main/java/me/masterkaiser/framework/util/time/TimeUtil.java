package me.masterkaiser.framework.util.time;

import lombok.experimental.UtilityClass;
import org.jetbrains.annotations.NotNull;

import java.time.Duration;
import java.time.Instant;
import java.util.concurrent.TimeUnit;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@UtilityClass
public class TimeUtil {

    public static boolean hasElapsed(@NotNull Instant instant) {
        return instant.isBefore(Instant.now());
    }

    public static Duration parseString(@NotNull String string) {
        Pattern pattern = Pattern.compile("(?i)(\\d+)([dhms])");
        Matcher matcher = pattern.matcher(string);
        long totalSeconds = 0;
        while (matcher.find()) {
            long value = Long.parseLong(matcher.group(1));
            String unit = matcher.group(2).toLowerCase();
            switch (unit) {
                case "d" -> totalSeconds += (value * 24 * 60 * 60);
                case "h" -> totalSeconds += (value * 60 * 60);
                case "m" -> totalSeconds += (value * 60);
                case "s" -> totalSeconds += value;
            }
        }

        return (totalSeconds == 0 ? Duration.ZERO : Duration.ofSeconds(totalSeconds));
    }

    public static String formatTime(long value, @NotNull TimeUnit inputType) {
        switch (inputType) {
            case DAYS -> {
                return formatSeconds((value * 24L * 60 * 60));
            }
            case HOURS -> {
                return formatSeconds((value * 60 * 60));
            }
            case MINUTES -> {
                return formatSeconds((value * 60));
            }
            case MILLISECONDS -> {
                return formatSeconds((value / 1000));
            }
            default -> {
                return formatSeconds(value);
            }
        }
    }

    public static String formatSeconds(long seconds) {
        final long weeks = TimeUnit.SECONDS.toDays(seconds) / 7;
        final long days = TimeUnit.SECONDS.toDays(seconds) % 7;
        final long hours = TimeUnit.SECONDS.toHours(seconds) % 24;
        final long minutes = TimeUnit.SECONDS.toMinutes(seconds) % 60;
        final long remainingSeconds = seconds % 60;
        final StringBuilder result = new StringBuilder();

        if (weeks > 0) {
            result.append(weeks).append("w");
        }

        if (days > 0) {
            result.append(days).append("d");
        }

        if (hours > 0) {
            result.append(hours).append("h");
        }

        if (minutes > 0) {
            result.append(minutes).append("m");
        }

        if (remainingSeconds > 0) {
            result.append(remainingSeconds).append("s");
        }

        final String resulTime = result.toString();

        if (resulTime.isEmpty()) {
            return "<1s";
        }

        return resulTime;
    }

    public static String formatSecondsSpaceAfterUnit(long seconds) {
        final long weeks = TimeUnit.SECONDS.toDays(seconds) / 7;
        final long days = TimeUnit.SECONDS.toDays(seconds) % 7;
        final long hours = TimeUnit.SECONDS.toHours(seconds) % 24;
        final long minutes = TimeUnit.SECONDS.toMinutes(seconds) % 60;
        final long remainingSeconds = seconds % 60;
        final StringBuilder result = new StringBuilder();

        if (weeks > 0) {
            result.append(weeks).append("w ");
        }

        if (days > 0) {
            result.append(days).append("d ");
        }

        if (hours > 0) {
            result.append(hours).append("h ");
        }

        if (minutes > 0) {
            result.append(minutes).append("m ");
        }

        if (remainingSeconds > 0) {
            result.append(remainingSeconds).append("s ");
        }

        final String resulTime = result.toString();

        if (resulTime.isEmpty()) {
            return "<1s";
        }

        return resulTime.substring(0, result.length() - 1);
    }

    public static String formatDuration(@NotNull Duration duration) {
        return formatTime(duration.getSeconds(), TimeUnit.SECONDS);
    }

    public static String formatInstant(@NotNull Instant instant) {
        return formatTime(instant.minusSeconds(Instant.now().getEpochSecond()).getEpochSecond(), TimeUnit.SECONDS);
    }

}
