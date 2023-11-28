package me.masterkaiser.framework.util.time;

import org.jetbrains.annotations.NotNull;

import java.time.Instant;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.Optional;

public class DateUtil {

    public static ZoneId defaultZoneId = ZoneId.of("Poland");

    public static Optional<String> formatDate(@NotNull Instant instant, @NotNull String pattern, @NotNull ZoneId zoneId) {
        try {
            DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern(pattern).withZone(zoneId);
            return Optional.of(dateTimeFormatter.format(instant));
        }
        catch (Exception e) {
            return Optional.empty();
        }
    }

    public static Optional<String> formatDateWithTime(@NotNull Instant instant, @NotNull ZoneId zoneId) {
        return formatDate(instant, "yyyy-MM-dd HH:mm:ss", zoneId);
    }

    public static Optional<String> formatDate(@NotNull Instant instant, @NotNull ZoneId zoneId) {
        return formatDate(instant, "yyyy-MM-dd", zoneId);
    }

    public static Optional<String> formatTime(@NotNull Instant instant, @NotNull ZoneId zoneId) {
        return formatDate(instant, "HH:mm:ss", zoneId);
    }

    public static Optional<String> formatDateWithTime(@NotNull Instant instant) {
        return formatDateWithTime(instant, DateUtil.defaultZoneId);
    }

    public static Optional<String> formatDate(@NotNull Instant instant) {
        return formatDate(instant, DateUtil.defaultZoneId);
    }

    public static Optional<String> formatTime(@NotNull Instant instant) {
        return formatTime(instant, DateUtil.defaultZoneId);
    }
}
