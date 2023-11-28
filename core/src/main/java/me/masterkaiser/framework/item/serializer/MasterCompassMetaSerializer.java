package me.masterkaiser.framework.item.serializer;

import eu.okaeri.configs.schema.GenericsDeclaration;
import eu.okaeri.configs.serdes.DeserializationData;
import eu.okaeri.configs.serdes.ObjectSerializer;
import eu.okaeri.configs.serdes.SerializationData;
import lombok.NonNull;
import me.masterkaiser.framework.item.meta.MasterCompassMeta;
import org.bukkit.Location;

public class MasterCompassMetaSerializer implements ObjectSerializer<MasterCompassMeta> {
    @Override
    public boolean supports(@NonNull Class<? super MasterCompassMeta> type) {
        return MasterCompassMeta.class.isAssignableFrom(type);
    }

    @Override
    public void serialize(@NonNull MasterCompassMeta object, @NonNull SerializationData data, @NonNull GenericsDeclaration generics) {
        data.add("lodestone", object.getLodestone());
        data.add("lodestoneTracked", object.isLodestoneTracked());
    }

    @Override
    public MasterCompassMeta deserialize(@NonNull DeserializationData data, @NonNull GenericsDeclaration generics) {
        final Location lodestone = data.get("lodestone", Location.class);
        final boolean lodestoneTracked = data.get("lodestoneTracked", Boolean.class);

        return new MasterCompassMeta(lodestone, lodestoneTracked);
    }
}
