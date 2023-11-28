package me.masterkaiser.framework.bukkit.util;

import com.iridium.iridiumcolorapi.IridiumColorAPI;
import lombok.experimental.UtilityClass;
import org.jetbrains.annotations.NotNull;

import java.util.List;

@UtilityClass
public class ColorUtil {
    public static String color(String message) {
        if (message == null) {
            return "";
        }

        return IridiumColorAPI.process(message);
    }

    public static List<String> color(@NotNull List<String> list) {
        return IridiumColorAPI.process(list);
    }
}
