package me.masterkaiser.framework.item.serializer;

import eu.okaeri.configs.schema.GenericsDeclaration;
import eu.okaeri.configs.serdes.DeserializationData;
import eu.okaeri.configs.serdes.ObjectSerializer;
import eu.okaeri.configs.serdes.SerializationData;
import lombok.NonNull;
import me.masterkaiser.framework.item.meta.MasterSkullMeta;

import java.util.UUID;

public class MasterSkullMetaSerializer implements ObjectSerializer<MasterSkullMeta> {
    @Override
    public boolean supports(@NonNull Class<? super MasterSkullMeta> type) {
        return MasterSkullMeta.class.isAssignableFrom(type);
    }

    @Override
    public void serialize(@NonNull MasterSkullMeta object, @NonNull SerializationData data, @NonNull GenericsDeclaration generics) {
        if (object.getOwner() != null) {
            data.add("owner", object.getOwner());
        }

        if (object.getUuid() != null) {
            data.add("uuid", object.getUuid());
        }
    }

    @Override
    public MasterSkullMeta deserialize(@NonNull DeserializationData data, @NonNull GenericsDeclaration generics) {
        final String owner = data.get("owner", String.class);
        final UUID uuid = data.get("uuid", UUID.class);

        return new MasterSkullMeta(owner, uuid);
    }
}
