package me.masterkaiser.framework.bukkit.permission;

import lombok.experimental.UtilityClass;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

@UtilityClass
public class PermissionHelper {
    public static boolean hasPermission(@NotNull Player player, @Nullable String permission) {
        if (permission == null || permission.isEmpty()) {
            return true;
        }

        return player.hasPermission(permission);
    }
}
