package me.masterkaiser.framework.item.meta;

import lombok.Getter;
import me.masterkaiser.framework.item.MasterMeta;
import org.bukkit.Bukkit;
import org.bukkit.OfflinePlayer;
import org.bukkit.inventory.meta.SkullMeta;

import java.util.UUID;

@Getter
public class MasterSkullMeta implements MasterMeta<SkullMeta> {
    private String owner;
    private UUID uuid;

    public MasterSkullMeta(String owner) {
        this.owner = owner;
    }

    public MasterSkullMeta(UUID uuid) {
        this.uuid = uuid;
    }

    public MasterSkullMeta(String owner, UUID uuid) {
        this.owner = owner;
        this.uuid = uuid;
    }

    public MasterSkullMeta setUuid(UUID uuid) {
        this.uuid = uuid;
        return this;
    }

    public MasterSkullMeta setOwner(String owner) {
        this.owner = owner;
        return this;
    }

    @Override
    public void applyFor(SkullMeta skullMeta) {
        if (this.owner != null) {
            skullMeta.setOwner(this.owner);

            return;
        }
        if (this.uuid != null) {
            final OfflinePlayer offlinePlayer = Bukkit.getOfflinePlayer(this.uuid);

            skullMeta.setOwningPlayer(offlinePlayer);

            return;
        }
    }
}
