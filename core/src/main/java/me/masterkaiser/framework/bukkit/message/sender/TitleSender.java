package me.masterkaiser.framework.bukkit.message.sender;

import lombok.experimental.UtilityClass;
import me.masterkaiser.framework.bukkit.message.RawTitle;
import me.masterkaiser.framework.bukkit.util.ColorUtil;
import me.masterkaiser.framework.condition.BiResult;
import me.masterkaiser.framework.condition.Result;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

import java.util.Collection;

@UtilityClass
public class TitleSender {
    public static boolean sendTitle(@NotNull Player player, @NotNull RawTitle rawTitle) {
        player.sendTitle(ColorUtil.color(rawTitle.getTitle()), ColorUtil.color(rawTitle.getSubTitle()),
                rawTitle.getFadeIn(), rawTitle.getStay(), rawTitle.getFadeOut());

        return true;
    }

    public static boolean sendTitle(@NotNull Player player, @NotNull RawTitle rawTitle, String fixedTitle, String fixedSubTitle) {
        player.sendTitle(ColorUtil.color(fixedTitle), ColorUtil.color(fixedSubTitle),
                rawTitle.getFadeIn(), rawTitle.getStay(), rawTitle.getFadeOut());

        return true;
    }

    public static boolean sendTitle(@NotNull Collection<? extends Player> players, @NotNull RawTitle rawTitle) {
        players.forEach(player -> sendTitle(player, rawTitle));

        return true;
    }

    public static boolean sendTitle(@NotNull Collection<? extends Player> players, @NotNull RawTitle rawTitle,
                                  @NotNull Result<Player, Boolean> canSend) {
        final Collection<? extends Player> conditionPlayers = players
                .stream()
                .filter(canSend::result)
                .toList();

        sendTitle(conditionPlayers, rawTitle);

        return true;
    }

    public static boolean sendTitle(@NotNull Collection<? extends Player> players, @NotNull RawTitle rawTitle,
                                  @NotNull BiResult<Player, String, String> fixMessage) {
        players
                .forEach(player ->
                        sendTitle(player,
                                rawTitle,
                                fixMessage.result(player, rawTitle.getTitle()),
                                fixMessage.result(player, rawTitle.getSubTitle())
                        )
                );

        return true;
    }

    public static boolean sendTitle(@NotNull Collection<? extends Player> players, @NotNull RawTitle rawTitle,
                                  @NotNull Result<Player, Boolean> canSend,
                                  @NotNull BiResult<Player, String, String> fixMessage) {
        players
                .stream()
                .filter(canSend::result)
                .forEach(player ->
                        sendTitle(player,
                                rawTitle,
                                fixMessage.result(player, rawTitle.getTitle()),
                                fixMessage.result(player, rawTitle.getSubTitle())
                        )
                );

        return true;
    }
}
