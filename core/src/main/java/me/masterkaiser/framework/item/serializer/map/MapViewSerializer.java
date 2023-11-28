package me.masterkaiser.framework.item.serializer.map;

import eu.okaeri.configs.schema.GenericsDeclaration;
import eu.okaeri.configs.serdes.DeserializationData;
import eu.okaeri.configs.serdes.ObjectSerializer;
import eu.okaeri.configs.serdes.SerializationData;
import lombok.NonNull;
import me.masterkaiser.framework.item.meta.map.MasterMapPixel;
import me.masterkaiser.framework.item.meta.map.MasterMapView;
import org.bukkit.map.MapCursor;
import org.bukkit.map.MapView;

import java.util.List;

public class MapViewSerializer implements ObjectSerializer<MasterMapView> {
    @Override
    public boolean supports(@NonNull Class<? super MasterMapView> type) {
        return MasterMapView.class.isAssignableFrom(type);
    }

    @Override
    public void serialize(@NonNull MasterMapView object, @NonNull SerializationData data, @NonNull GenericsDeclaration generics) {
        data.add("scale", object.getScale());
        data.add("centerX", object.getCenterX());
        data.add("centerZ", object.getCenterZ());
        data.add("trackingPosition", object.isTrackingPosition());
        data.add("unlimitedTracking", object.isUnlimitedTracking());
        data.add("locked", object.isLocked());

        if (object.getMapCursors() != null) {
            data.add("cursors", object.getMapCursors());
        }

        if (object.getMasterMapPixels() != null) {
            data.add("pixels", object.getMasterMapPixels());
        }
    }

    @Override
    public MasterMapView deserialize(@NonNull DeserializationData data, @NonNull GenericsDeclaration generics) {
        final MapView.Scale scale = data.get("scale", MapView.Scale.class);
        final int centerX = data.get("centerX", Integer.class);
        final int centerZ = data.get("centerZ", Integer.class);
        final boolean trackingPosition = data.get("trackingPosition", Boolean.class);
        final boolean unlimitedTracking = data.get("unlimitedTracking", Boolean.class);
        final boolean locked = data.get("locked", Boolean.class);

        final List<MapCursor> cursors = data.containsKey("cursors")
                ? data.getAsList("cursors", MapCursor.class)
                : null;
        final List<MasterMapPixel> pixels = data.containsKey("pixels")
                ? data.getAsList("pixels", MasterMapPixel.class)
                : null;

        return new MasterMapView(scale, centerX, centerZ, trackingPosition, unlimitedTracking, locked, pixels, cursors);
    }
}
