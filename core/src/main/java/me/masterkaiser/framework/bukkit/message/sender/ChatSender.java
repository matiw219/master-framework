package me.masterkaiser.framework.bukkit.message.sender;

import lombok.experimental.UtilityClass;

import me.masterkaiser.framework.bukkit.util.ColorUtil;
import me.masterkaiser.framework.condition.BiResult;
import me.masterkaiser.framework.condition.Result;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

import java.util.Collection;
import java.util.List;

@UtilityClass
public class ChatSender {
    /**
     * send message to source
     */
    public static boolean sendMessage(@NotNull CommandSender source, @NotNull String message) {
        source.sendMessage(ColorUtil.color(message));

        return true;
    }

    /**
     * send messages to source
     */
    public static boolean sendMessage(@NotNull CommandSender source, @NotNull List<String> messages) {
        messages.forEach(s -> sendMessage(source, s));

        return true;
    }

    /**
     * send message to players
     */
    public static boolean sendMessage(@NotNull Collection<? extends Player> players, @NotNull String message) {
        players.forEach(player -> sendMessage(player, message));

        return true;
    }

    /**
     * send messages to players
     */
    public static boolean sendMessage(@NotNull Collection<? extends Player> players, @NotNull List<String> messages) {
        messages.forEach(s -> sendMessage(players, s));

        return true;
    }

    /**
     * send message to players when condition return true
     */
    public static boolean sendMessage(@NotNull Collection<? extends Player> players, @NotNull String message,
                                @NotNull Result<Player, Boolean> canSend) {
        players
                .stream()
                .filter(canSend::result)
                .forEach(player -> sendMessage(player, message));

        return true;
    }

    /**
     * send messages to players when condition return true
     */
    public static boolean sendMessage(@NotNull Collection<? extends Player> players, @NotNull List<String> messages,
                                @NotNull Result<Player, Boolean> canSend) {
        players
                .stream()
                .filter(canSend::result)
                .forEach(player ->
                        messages
                                .forEach(message ->
                                        sendMessage(player, message)));

        return true;
    }

    /**
     * send fixed message to players
     */
    public static boolean sendMessage(@NotNull Collection<? extends Player> players, @NotNull String message,
                                @NotNull BiResult<Player, String, String> fixMessage) {
        players
                .forEach(player -> sendMessage(player, fixMessage.result(player, message)));

        return true;
    }

    /**
     * send fixed messages to players
     */
    public static boolean sendMessage(@NotNull Collection<? extends Player> players, @NotNull List<String> messages,
                                @NotNull BiResult<Player, String, String> fixMessage) {
        messages.forEach(message -> sendMessage(players, message, fixMessage));

        return true;
    }

    /**
     * send fixed message to players when condition return true
     */
    public static boolean sendMessage(@NotNull Collection<? extends Player> players, @NotNull String message,
                                @NotNull Result<Player, Boolean> canSend,
                                @NotNull BiResult<Player, String, String> fixMessage) {
        players
                .stream()
                .filter(canSend::result)
                .forEach(player -> {
                    sendMessage(player, fixMessage.result(player, message));
                });

        return true;
    }

    /**
     * send fixed messages to players when condition return true
     */
    public static boolean sendMessage(@NotNull Collection<? extends Player> players, @NotNull List<String> messages,
                                @NotNull Result<Player, Boolean> canSend,
                                @NotNull BiResult<Player, String, String> fixMessage) {

        players
                .stream()
                .filter(canSend::result)
                .forEach(player ->
                        messages
                                .forEach(message ->
                                        sendMessage(player, fixMessage.result(player, message))));

        return true;
    }
}
