package me.masterkaiser.framework.bukkit.message;

import lombok.AllArgsConstructor;
import lombok.Getter;
import me.masterkaiser.framework.bukkit.message.sender.ActionBarSender;
import me.masterkaiser.framework.bukkit.message.sender.ChatSender;
import me.masterkaiser.framework.bukkit.message.sender.TitleSender;
import me.masterkaiser.framework.condition.BiResult;
import me.masterkaiser.framework.condition.Result;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@AllArgsConstructor
@Getter
public class RawMessage {
    private List<String> chat = new ArrayList<>();
    private String actionBar;
    private RawTitle title;

    public RawMessage(@NotNull List<String> chat) {
        this.chat = chat;
    }

    public RawMessage(String message) {
        this.chat.add(message);
    }

    public RawMessage setChat(@NotNull List<String> chat) {
        this.chat = chat;
        return this;
    }

    public RawMessage resetChat() {
        this.chat = new ArrayList<>();
        return this;
    }

    public RawMessage setChat(@NotNull String message) {
        this.chat = List.of(message);
        return this;
    }

    public RawMessage setActionBar(String actionBar) {
        this.actionBar = actionBar;
        return this;
    }

    public RawMessage setTitle(RawTitle title) {
        this.title = title;
        return this;
    }

    public boolean send(@NotNull CommandSender commandSender) {
        if (!getChat().isEmpty()) {
            getChat().forEach(message -> ChatSender.sendMessage(commandSender, message));
        }

        if (commandSender instanceof Player player) {
            if (getActionBar() != null) {
                ActionBarSender.sendActionBar(player, getActionBar());
            }

            if (getTitle() != null) {
                TitleSender.sendTitle(player, getTitle());
            }
        }

        return true;
    }

    public String findOneNotNullMessage() {
        if (this.chat.size() != 0) {
            return this.chat.get(0);
        }
        if (this.actionBar != null) {
            return this.actionBar;
        }
        if (this.title != null) {
            if (this.title.getTitle() != null) {
                return this.title.getTitle();
            }
            if (this.title.getSubTitle() != null) {
                return this.title.getSubTitle();
            }
        }

        return "";
    }

    public boolean send(@NotNull CommandSender commandSender, @NotNull Result<CommandSender, Boolean> canSend) {
        if (canSend.result(commandSender)) {
            return send(commandSender);
        }

        return false;
    }

    public boolean send(@NotNull Player player, @NotNull Result<Player, Boolean> canSend) {
        if (canSend.result(player)) {
            return send(player);
        }

        return false;
    }

    public boolean send(@NotNull CommandSender commandSender, @NotNull BiResult<CommandSender, String, String> fixMessage) {
        if (!getChat().isEmpty()) {
            getChat().forEach(message -> ChatSender.sendMessage(commandSender, fixMessage.result(commandSender, message)));
        }

        if (commandSender instanceof Player player) {
            if (getActionBar() != null) {
                ActionBarSender.sendActionBar(player, fixMessage.result(commandSender, getActionBar()));
            }

            if (getTitle() != null) {
                TitleSender.sendTitle(
                        player,
                        getTitle(),
                        fixMessage.result(commandSender, getTitle().getTitle()),
                        fixMessage.result(commandSender, getTitle().getSubTitle())
                );
            }
        }

        return false;
    }

    public boolean send(@NotNull CommandSender commandSender, @NotNull Result<CommandSender, Boolean> canSend,
                        @NotNull BiResult<CommandSender, String, String> fixMessage) {
        if (!canSend.result(commandSender)) {
            return false;
        }

        send(commandSender, fixMessage);

        return false;
    }

    public boolean send(@NotNull Collection<? extends Player> players) {
        players.forEach(this::send);

        return true;
    }

    public boolean send(@NotNull Collection<? extends Player> players, @NotNull Result<Player, Boolean> canSend) {
        players
                .stream()
                .filter(canSend::result)
                .forEach(this::send);

        return true;
    }

    public boolean send(@NotNull Collection<? extends Player> players, @NotNull BiResult<Player, String, String> fixMessage) {
        players
                .forEach(player -> {
                    if (!getChat().isEmpty()) {
                        getChat().forEach(message -> ChatSender.sendMessage(player, fixMessage.result(player, message)));
                    }

                    if (getActionBar() != null) {
                        ActionBarSender.sendActionBar(player, fixMessage.result(player, getActionBar()));
                    }

                    if (getTitle() != null) {
                        TitleSender.sendTitle(
                                player,
                                getTitle(),
                                fixMessage.result(player, getTitle().getTitle()),
                                fixMessage.result(player, getTitle().getSubTitle())
                        );
                    }
                });

        return true;
    }

    public boolean send(@NotNull Collection<? extends Player> players, @NotNull Result<Player, Boolean> canSend,
                        @NotNull BiResult<Player, String, String> fixMessage) {
        final Collection<? extends Player> conditionPlayers = players
                .stream()
                .filter(canSend::result)
                .toList();

        return send(conditionPlayers, fixMessage);
    }
}
