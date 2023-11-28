package me.masterkaiser.framework.item.meta.map;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import me.masterkaiser.framework.item.MasterMeta;
import org.bukkit.Bukkit;
import org.bukkit.Color;
import org.bukkit.entity.Player;
import org.bukkit.inventory.meta.MapMeta;
import org.bukkit.map.MapCanvas;
import org.bukkit.map.MapCursor;
import org.bukkit.map.MapCursorCollection;
import org.bukkit.map.MapPalette;
import org.bukkit.map.MapRenderer;
import org.bukkit.map.MapView;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.List;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class MasterMapMeta implements MasterMeta<MapMeta> {
    private @Nullable MasterMapView masterMapView;
    private boolean scaling;
    private @Nullable Color color;
    private @Nullable String locationName;
    private @Nullable String world;

    public MasterMapMeta setMasterMapView(MasterMapView masterMapView) {
        this.masterMapView = masterMapView;
        return this;
    }

    public MasterMapMeta setScaling(boolean scaling) {
        this.scaling = scaling;
        return this;
    }

    public MasterMapMeta setColor(Color color) {
        this.color = color;
        return this;
    }

    public MasterMapMeta setLocationName(String locationName) {
        this.locationName = locationName;
        return this;
    }

    public MasterMapMeta setWorld(String world) {
        this.world = world;
        return this;
    }

    @Override
    public void applyFor(MapMeta mapMeta) {
        mapMeta.setScaling(this.scaling);

        if (this.locationName != null) {
            mapMeta.setLocationName(this.locationName);
        }

        if (this.color != null) {
            mapMeta.setColor(this.color);
        }

        assert this.world != null;
        final MapView mapView = Bukkit.createMap(Bukkit.getWorld(this.world));

        if (this.masterMapView != null) {
            mapView.setScale(this.masterMapView.getScale());
            mapView.setTrackingPosition(this.masterMapView.isTrackingPosition());
            mapView.setUnlimitedTracking(this.masterMapView.isUnlimitedTracking());
            mapView.setLocked(this.masterMapView.isLocked());
            mapView.setCenterX(this.masterMapView.getCenterX());
            mapView.setCenterZ(this.masterMapView.getCenterZ());
            mapView.addRenderer(new MasterRenderer(this.masterMapView.getMasterMapPixels(), this.getMasterMapView().getMapCursors()));
        }

        mapMeta.setMapView(mapView);
    }

    @EqualsAndHashCode(callSuper = true)
    @AllArgsConstructor
    @Getter
    public static class MasterRenderer extends MapRenderer {
        private List<MasterMapPixel> masterMapPixelList;
        private List<MapCursor> mapCursorList;

        @Override
        public void render(@NotNull MapView mapView, @NotNull MapCanvas mapCanvas, @NotNull Player player) {
            if (this.masterMapPixelList != null) {
                this.masterMapPixelList.forEach(masterMapPixel ->
                        mapCanvas.setPixel(masterMapPixel.getX(),
                                masterMapPixel.getY(),
                                MapPalette.matchColor(
                                        masterMapPixel.getColor().getRed(),
                                        masterMapPixel.getColor().getGreen(),
                                        masterMapPixel.getColor().getBlue()
                                )
                        ));
            }

            if (this.mapCursorList != null) {
                final MapCursorCollection mapCursorCollection = new MapCursorCollection();

                this.mapCursorList.forEach(mapCursorCollection::addCursor);

                mapCanvas.setCursors(mapCursorCollection);
            }
        }
    }
}
