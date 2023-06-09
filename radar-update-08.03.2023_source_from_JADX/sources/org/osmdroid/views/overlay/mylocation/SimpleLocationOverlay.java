package org.osmdroid.views.overlay.mylocation;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Point;
import android.graphics.drawable.BitmapDrawable;
import org.osmdroid.library.C1340R;
import org.osmdroid.util.GeoPoint;
import org.osmdroid.views.MapView;
import org.osmdroid.views.Projection;
import org.osmdroid.views.overlay.Overlay;

public class SimpleLocationOverlay extends Overlay {
    protected Point PERSON_HOTSPOT;
    protected Bitmap PERSON_ICON;
    protected GeoPoint mLocation;
    protected final Paint mPaint;
    private final Point screenCoords;

    public void onDetach(MapView mapView) {
    }

    @Deprecated
    public SimpleLocationOverlay(Context context) {
        this(((BitmapDrawable) context.getResources().getDrawable(C1340R.C1341drawable.person)).getBitmap());
    }

    public SimpleLocationOverlay(Bitmap bitmap) {
        this.mPaint = new Paint();
        this.PERSON_HOTSPOT = new Point(24, 39);
        this.screenCoords = new Point();
        this.PERSON_ICON = bitmap;
    }

    public void setLocation(GeoPoint geoPoint) {
        this.mLocation = geoPoint;
    }

    public GeoPoint getMyLocation() {
        return this.mLocation;
    }

    public void draw(Canvas canvas, Projection projection) {
        GeoPoint geoPoint = this.mLocation;
        if (geoPoint != null) {
            projection.toPixels(geoPoint, this.screenCoords);
            canvas.drawBitmap(this.PERSON_ICON, (float) (this.screenCoords.x - this.PERSON_HOTSPOT.x), (float) (this.screenCoords.y - this.PERSON_HOTSPOT.y), this.mPaint);
        }
    }

    public void setPersonIcon(Bitmap bitmap, Point point) {
        this.PERSON_ICON = bitmap;
        this.PERSON_HOTSPOT = point;
    }
}
