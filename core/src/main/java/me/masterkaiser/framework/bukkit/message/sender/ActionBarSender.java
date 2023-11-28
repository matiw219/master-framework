package me.masterkaiser.framework.bukkit.message.sender;

import lombok.experimental.UtilityClass;
import me.masterkaiser.framework.bukkit.util.ColorUtil;
import me.masterkaiser.framework.condition.BiResult;
import me.masterkaiser.framework.condition.Result;
import net.md_5.bungee.api.ChatMessageType;
import net.md_5.bungee.api.chat.TextComponent;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

import java.util.Collection;

@UtilityClass
public class ActionBarSender {
    public static boolean sendActionBar(@NotNull Player player, @NotNull String message) {
        player.spigot().sendMessage(ChatMessageType.ACTION_BAR, new TextComponent(ColorUtil.color(message)));

        return true;
    }

    public static boolean sendActionBar(@NotNull Collection<? extends Player> players, @NotNull String message) {
        players.forEach(player -> sendActionBar(player, message));

        return true;
    }

    public static boolean sendActionBar(@NotNull Collection<? extends Player> players, @NotNull String message,
                                  @NotNull Result<Player, Boolean> canSend) {
        final Collection<? extends Player> conditionPlayers = players
                .stream()
                .filter(canSend::result)
                .toList();

        sendActionBar(conditionPlayers, message);

        return true;
    }

    public static boolean sendActionBar(@NotNull Collection<? extends Player> players, @NotNull String message,
                                  @NotNull BiResult<Player, String, String> fixMessage) {
        players
                .forEach(player ->
                        sendActionBar(player, fixMessage.result(player, message))
                );

        return true;
    }

    public static boolean sendActionBar(@NotNull Collection<? extends Player> players, @NotNull String message,
                                  @NotNull Result<Player, Boolean> canSend,
                                  @NotNull BiResult<Player, String, String> fixMessage) {
        players
                .stream()
                .filter(canSend::result)
                .forEach(player ->
                        sendActionBar(player, fixMessage.result(player, message))
                );

        return true;
    }
}
