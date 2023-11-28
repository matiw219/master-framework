package me.masterkaiser.framework.bukkit.util;

import lombok.experimental.UtilityClass;
import org.bukkit.entity.Player;
import org.bukkit.entity.Projectile;
import org.bukkit.event.entity.EntityDamageByEntityEvent;

import java.util.Optional;

@UtilityClass
public class DamageUtil {
    public static Optional<Player> getDamager(EntityDamageByEntityEvent event) {
        if (event.getDamager() instanceof Player player) {
            return Optional.of(player);
        }

        if (event.getDamager() instanceof Projectile projectile) {
            if (projectile.getShooter() instanceof Player player) {
                return Optional.of(player);
            }
        }

        return Optional.empty();
    }
}
