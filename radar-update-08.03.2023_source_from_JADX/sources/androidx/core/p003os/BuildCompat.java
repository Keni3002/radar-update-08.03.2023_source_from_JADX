package androidx.core.p003os;

import android.os.Build;
import java.util.Locale;

/* renamed from: androidx.core.os.BuildCompat */
public class BuildCompat {

    /* renamed from: androidx.core.os.BuildCompat$PrereleaseSdkCheck */
    public @interface PrereleaseSdkCheck {
    }

    private BuildCompat() {
    }

    protected static boolean isAtLeastPreReleaseCodename(String str, String str2) {
        if (!"REL".equals(str2) && str2.toUpperCase(Locale.ROOT).compareTo(str.toUpperCase(Locale.ROOT)) >= 0) {
            return true;
        }
        return false;
    }

    @Deprecated
    public static boolean isAtLeastN() {
        return Build.VERSION.SDK_INT >= 24;
    }

    @Deprecated
    public static boolean isAtLeastNMR1() {
        return Build.VERSION.SDK_INT >= 25;
    }

    @Deprecated
    public static boolean isAtLeastO() {
        return Build.VERSION.SDK_INT >= 26;
    }

    @Deprecated
    public static boolean isAtLeastOMR1() {
        return Build.VERSION.SDK_INT >= 27;
    }

    @Deprecated
    public static boolean isAtLeastP() {
        return Build.VERSION.SDK_INT >= 28;
    }

    @Deprecated
    public static boolean isAtLeastQ() {
        return Build.VERSION.SDK_INT >= 29;
    }

    @Deprecated
    public static boolean isAtLeastR() {
        return Build.VERSION.SDK_INT >= 30;
    }

    @Deprecated
    public static boolean isAtLeastS() {
        return Build.VERSION.SDK_INT >= 31 || (Build.VERSION.SDK_INT >= 30 && isAtLeastPreReleaseCodename("S", Build.VERSION.CODENAME));
    }

    @Deprecated
    public static boolean isAtLeastSv2() {
        return Build.VERSION.SDK_INT >= 32 || (Build.VERSION.SDK_INT >= 31 && isAtLeastPreReleaseCodename("Sv2", Build.VERSION.CODENAME));
    }

    public static boolean isAtLeastT() {
        return Build.VERSION.SDK_INT >= 33 || (Build.VERSION.SDK_INT >= 32 && isAtLeastPreReleaseCodename("Tiramisu", Build.VERSION.CODENAME));
    }

    public static boolean isAtLeastU() {
        return Build.VERSION.SDK_INT >= 33 && isAtLeastPreReleaseCodename("UpsideDownCake", Build.VERSION.CODENAME);
    }
}
