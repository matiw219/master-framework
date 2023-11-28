package me.masterkaiser.framework.item.meta.map;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.bukkit.map.MapCursor;
import org.bukkit.map.MapView;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter
public class MasterMapView {
    private @NotNull MapView.Scale scale;
    private int centerX;
    private int centerZ;
    private boolean trackingPosition;
    private boolean unlimitedTracking;
    private boolean locked;
    private @Nullable List<MasterMapPixel> masterMapPixels;
    private @Nullable List<MapCursor> mapCursors;

    public MasterMapView(@NotNull MapView mapView) {
        this.scale = mapView.getScale();
        this.centerX = mapView.getCenterX();
        this.centerZ = mapView.getCenterZ();
        this.trackingPosition = mapView.isTrackingPosition();
        this.unlimitedTracking = mapView.isUnlimitedTracking();
        this.locked = mapView.isLocked();
    }

    public MasterMapView setScale(MapView.Scale scale) {
        this.scale = scale;
        return this;
    }

    public MasterMapView setCenterX(int centerX) {
        this.centerX = centerX;
        return this;
    }

    public MasterMapView setCenterZ(int centerZ) {
        this.centerZ = centerZ;
        return this;
    }

    public MasterMapView setTrackingPosition(boolean trackingPosition) {
        this.trackingPosition = trackingPosition;
        return this;
    }

    public MasterMapView setUnlimitedTracking(boolean unlimitedTracking) {
        this.unlimitedTracking = unlimitedTracking;
        return this;
    }

    public MasterMapView setLocked(boolean locked) {
        this.locked = locked;
        return this;
    }

    public MasterMapView setMasterMapPixels(List<MasterMapPixel> masterMapPixels) {
        this.masterMapPixels = masterMapPixels;
        return this;
    }

    public MasterMapView setMapCursors(List<MapCursor> mapCursors) {
        this.mapCursors = mapCursors;
        return this;
    }
}
