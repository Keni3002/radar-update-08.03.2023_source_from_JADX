package org.osmdroid.views;

import android.animation.ValueAnimator;
import android.graphics.Canvas;
import android.os.Build;
import android.view.MotionEvent;
import android.view.animation.LinearInterpolator;
import java.lang.Thread;

public class CustomZoomButtonsController {
    /* access modifiers changed from: private */
    public boolean detached;
    /* access modifiers changed from: private */
    public float mAlpha01;
    private CustomZoomButtonsDisplay mDisplay;
    /* access modifiers changed from: private */
    public final ValueAnimator mFadeOutAnimation;
    private int mFadeOutAnimationDurationInMillis = 500;
    private boolean mJustActivated;
    /* access modifiers changed from: private */
    public long mLatestActivation;
    private OnZoomListener mListener;
    private final MapView mMapView;
    private final Runnable mRunnable;
    /* access modifiers changed from: private */
    public int mShowDelayInMillis = 3500;
    private Thread mThread;
    private final Object mThreadSync = new Object();
    private Visibility mVisibility = Visibility.NEVER;
    private boolean mZoomInEnabled;
    private boolean mZoomOutEnabled;

    public interface OnZoomListener {
        void onVisibilityChanged(boolean z);

        void onZoom(boolean z);
    }

    public enum Visibility {
        ALWAYS,
        NEVER,
        SHOW_AND_FADEOUT
    }

    public CustomZoomButtonsController(MapView mapView) {
        this.mMapView = mapView;
        this.mDisplay = new CustomZoomButtonsDisplay(mapView);
        if (Build.VERSION.SDK_INT >= 11) {
            ValueAnimator ofFloat = ValueAnimator.ofFloat(new float[]{0.0f, 1.0f});
            this.mFadeOutAnimation = ofFloat;
            ofFloat.setInterpolator(new LinearInterpolator());
            ofFloat.setDuration((long) this.mFadeOutAnimationDurationInMillis);
            ofFloat.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
                public void onAnimationUpdate(ValueAnimator valueAnimator) {
                    if (CustomZoomButtonsController.this.detached) {
                        CustomZoomButtonsController.this.mFadeOutAnimation.cancel();
                        return;
                    }
                    float unused = CustomZoomButtonsController.this.mAlpha01 = 1.0f - ((Float) valueAnimator.getAnimatedValue()).floatValue();
                    CustomZoomButtonsController.this.invalidate();
                }
            });
        } else {
            this.mFadeOutAnimation = null;
        }
        this.mRunnable = new Runnable() {
            public void run() {
                while (true) {
                    long access$400 = (CustomZoomButtonsController.this.mLatestActivation + ((long) CustomZoomButtonsController.this.mShowDelayInMillis)) - CustomZoomButtonsController.this.nowInMillis();
                    if (access$400 <= 0) {
                        CustomZoomButtonsController.this.startFadeOut();
                        return;
                    }
                    try {
                        Thread.sleep(access$400, 0);
                    } catch (InterruptedException unused) {
                    }
                }
            }
        };
    }

    public void setZoomInEnabled(boolean z) {
        this.mZoomInEnabled = z;
    }

    public void setZoomOutEnabled(boolean z) {
        this.mZoomOutEnabled = z;
    }

    public CustomZoomButtonsDisplay getDisplay() {
        return this.mDisplay;
    }

    public void setOnZoomListener(OnZoomListener onZoomListener) {
        this.mListener = onZoomListener;
    }

    /* renamed from: org.osmdroid.views.CustomZoomButtonsController$4 */
    static /* synthetic */ class C13764 {

        /* renamed from: $SwitchMap$org$osmdroid$views$CustomZoomButtonsController$Visibility */
        static final /* synthetic */ int[] f561xd7d73fa4;

        /* JADX WARNING: Can't wrap try/catch for region: R(6:0|1|2|3|4|(3:5|6|8)) */
        /* JADX WARNING: Failed to process nested try/catch */
        /* JADX WARNING: Missing exception handler attribute for start block: B:3:0x0012 */
        /* JADX WARNING: Missing exception handler attribute for start block: B:5:0x001d */
        static {
            /*
                org.osmdroid.views.CustomZoomButtonsController$Visibility[] r0 = org.osmdroid.views.CustomZoomButtonsController.Visibility.values()
                int r0 = r0.length
                int[] r0 = new int[r0]
                f561xd7d73fa4 = r0
                org.osmdroid.views.CustomZoomButtonsController$Visibility r1 = org.osmdroid.views.CustomZoomButtonsController.Visibility.ALWAYS     // Catch:{ NoSuchFieldError -> 0x0012 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0012 }
                r2 = 1
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0012 }
            L_0x0012:
                int[] r0 = f561xd7d73fa4     // Catch:{ NoSuchFieldError -> 0x001d }
                org.osmdroid.views.CustomZoomButtonsController$Visibility r1 = org.osmdroid.views.CustomZoomButtonsController.Visibility.NEVER     // Catch:{ NoSuchFieldError -> 0x001d }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x001d }
                r2 = 2
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x001d }
            L_0x001d:
                int[] r0 = f561xd7d73fa4     // Catch:{ NoSuchFieldError -> 0x0028 }
                org.osmdroid.views.CustomZoomButtonsController$Visibility r1 = org.osmdroid.views.CustomZoomButtonsController.Visibility.SHOW_AND_FADEOUT     // Catch:{ NoSuchFieldError -> 0x0028 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0028 }
                r2 = 3
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0028 }
            L_0x0028:
                return
            */
            throw new UnsupportedOperationException("Method not decompiled: org.osmdroid.views.CustomZoomButtonsController.C13764.<clinit>():void");
        }
    }

    public void setVisibility(Visibility visibility) {
        this.mVisibility = visibility;
        int i = C13764.f561xd7d73fa4[this.mVisibility.ordinal()];
        if (i == 1) {
            this.mAlpha01 = 1.0f;
        } else if (i == 2 || i == 3) {
            this.mAlpha01 = 0.0f;
        }
    }

    public void setShowFadeOutDelays(int i, int i2) {
        this.mShowDelayInMillis = i;
        this.mFadeOutAnimationDurationInMillis = i2;
    }

    public void onDetach() {
        this.detached = true;
        stopFadeOut();
    }

    /* access modifiers changed from: private */
    public long nowInMillis() {
        return System.currentTimeMillis();
    }

    /* access modifiers changed from: private */
    public void startFadeOut() {
        if (!this.detached) {
            if (Build.VERSION.SDK_INT >= 11) {
                this.mFadeOutAnimation.setStartDelay(0);
                this.mMapView.post(new Runnable() {
                    public void run() {
                        CustomZoomButtonsController.this.mFadeOutAnimation.start();
                    }
                });
                return;
            }
            this.mAlpha01 = 0.0f;
            invalidate();
        }
    }

    private void stopFadeOut() {
        if (Build.VERSION.SDK_INT >= 11) {
            this.mFadeOutAnimation.cancel();
        }
    }

    /* access modifiers changed from: private */
    public void invalidate() {
        if (!this.detached) {
            this.mMapView.postInvalidate();
        }
    }

    public void activate() {
        if (!this.detached && this.mVisibility == Visibility.SHOW_AND_FADEOUT) {
            float f = this.mAlpha01;
            boolean z = false;
            if (!this.mJustActivated) {
                if (f == 0.0f) {
                    z = true;
                }
                this.mJustActivated = z;
            } else {
                this.mJustActivated = false;
            }
            stopFadeOut();
            this.mAlpha01 = 1.0f;
            this.mLatestActivation = nowInMillis();
            invalidate();
            Thread thread = this.mThread;
            if (thread == null || thread.getState() == Thread.State.TERMINATED) {
                synchronized (this.mThreadSync) {
                    Thread thread2 = this.mThread;
                    if (thread2 == null || thread2.getState() == Thread.State.TERMINATED) {
                        Thread thread3 = new Thread(this.mRunnable);
                        this.mThread = thread3;
                        thread3.setName(getClass().getName() + "#active");
                        this.mThread.start();
                    }
                }
            }
        }
    }

    private boolean checkJustActivated() {
        if (!this.mJustActivated) {
            return false;
        }
        this.mJustActivated = false;
        return true;
    }

    public boolean isTouched(MotionEvent motionEvent) {
        OnZoomListener onZoomListener;
        OnZoomListener onZoomListener2;
        if (this.mAlpha01 == 0.0f || checkJustActivated()) {
            return false;
        }
        if (this.mDisplay.isTouched(motionEvent, true)) {
            if (this.mZoomInEnabled && (onZoomListener2 = this.mListener) != null) {
                onZoomListener2.onZoom(true);
            }
            return true;
        } else if (!this.mDisplay.isTouched(motionEvent, false)) {
            return false;
        } else {
            if (this.mZoomOutEnabled && (onZoomListener = this.mListener) != null) {
                onZoomListener.onZoom(false);
            }
            return true;
        }
    }

    @Deprecated
    public boolean onSingleTapConfirmed(MotionEvent motionEvent) {
        return isTouched(motionEvent);
    }

    @Deprecated
    public boolean onLongPress(MotionEvent motionEvent) {
        return isTouched(motionEvent);
    }

    public void draw(Canvas canvas) {
        this.mDisplay.draw(canvas, this.mAlpha01, this.mZoomInEnabled, this.mZoomOutEnabled);
    }
}
