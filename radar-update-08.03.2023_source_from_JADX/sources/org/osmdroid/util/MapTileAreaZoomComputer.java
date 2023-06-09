package org.osmdroid.util;

public class MapTileAreaZoomComputer implements MapTileAreaComputer {
    private final int mZoomDelta;

    public MapTileAreaZoomComputer(int i) {
        this.mZoomDelta = i;
    }

    public MapTileArea computeFromSource(MapTileArea mapTileArea, MapTileArea mapTileArea2) {
        if (mapTileArea2 == null) {
            mapTileArea2 = new MapTileArea();
        }
        if (mapTileArea.size() == 0) {
            mapTileArea2.reset();
            return mapTileArea2;
        }
        int zoom = this.mZoomDelta + mapTileArea.getZoom();
        if (zoom < 0 || zoom > MapTileIndex.mMaxZoomLevel) {
            mapTileArea2.reset();
            return mapTileArea2;
        } else if (this.mZoomDelta <= 0) {
            mapTileArea2.set(zoom, mapTileArea.getLeft() >> (-this.mZoomDelta), mapTileArea.getTop() >> (-this.mZoomDelta), mapTileArea.getRight() >> (-this.mZoomDelta), mapTileArea.getBottom() >> (-this.mZoomDelta));
            return mapTileArea2;
        } else {
            mapTileArea2.set(zoom, mapTileArea.getLeft() << this.mZoomDelta, mapTileArea.getTop() << this.mZoomDelta, ((mapTileArea.getRight() + 1) << this.mZoomDelta) - 1, ((mapTileArea.getBottom() + 1) << this.mZoomDelta) - 1);
            return mapTileArea2;
        }
    }
}
