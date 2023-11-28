package me.masterkaiser.framework.item.serializer.map;

import eu.okaeri.configs.schema.GenericsDeclaration;
import eu.okaeri.configs.serdes.DeserializationData;
import eu.okaeri.configs.serdes.ObjectSerializer;
import eu.okaeri.configs.serdes.SerializationData;
import lombok.NonNull;
import me.masterkaiser.framework.item.meta.map.MasterMapMeta;
import me.masterkaiser.framework.item.meta.map.MasterMapView;
import org.bukkit.Color;

public class MapMetaSerializer implements ObjectSerializer<MasterMapMeta> {
    @Override
    public boolean supports(@NonNull Class<? super MasterMapMeta> type) {
        return MasterMapMeta.class.isAssignableFrom(type);
    }

    @Override
    public void serialize(@NonNull MasterMapMeta object, @NonNull SerializationData data, @NonNull GenericsDeclaration generics) {
        data.add("view", object.getMasterMapView());
        data.add("scaling", object.isScaling());

        if (object.getColor() != null) {
            data.add("color", object.getColor());
        }

        if (object.getLocationName() != null) {
            data.add("locationName", object.getLocationName());
        }

        if (object.getWorld() != null) {
            data.add("world", object.getWorld());
        }
    }

    @Override
    public MasterMapMeta deserialize(@NonNull DeserializationData data, @NonNull GenericsDeclaration generics) {
        final MasterMapView masterMapView = data.get("view", MasterMapView.class);
        final boolean scaling = data.get("scaling", Boolean.class);

        final Color color = data.containsKey("color")
                ? data.get("color", Color.class)
                : null;

        final String locationName = data.containsKey("locationName")
                ? data.get("locationName", String.class)
                : null;

        final String world = data.containsKey("world")
                ? data.get("world", String.class)
                : null;

        return new MasterMapMeta(masterMapView, scaling, color, locationName, world);
    }
}
