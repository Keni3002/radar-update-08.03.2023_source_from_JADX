package androidx.work.impl.workers;

import androidx.work.ListenableWorker;
import androidx.work.Logger;
import androidx.work.impl.utils.futures.SettableFuture;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

@Metadata(mo19893d1 = {"\u0000\u001a\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\u001a\u0012\u0010\u0003\u001a\u00020\u0004*\b\u0012\u0004\u0012\u00020\u00060\u0005H\u0002\u001a\u0012\u0010\u0007\u001a\u00020\u0004*\b\u0012\u0004\u0012\u00020\u00060\u0005H\u0002\"\u000e\u0010\u0000\u001a\u00020\u0001XT¢\u0006\u0002\n\u0000\"\u000e\u0010\u0002\u001a\u00020\u0001X\u0004¢\u0006\u0002\n\u0000¨\u0006\b"}, mo19894d2 = {"ARGUMENT_CLASS_NAME", "", "TAG", "setFailed", "", "Landroidx/work/impl/utils/futures/SettableFuture;", "Landroidx/work/ListenableWorker$Result;", "setRetry", "work-runtime_release"}, mo19895k = 2, mo19896mv = {1, 7, 1}, mo19898xi = 48)
/* compiled from: ConstraintTrackingWorker.kt */
public final class ConstraintTrackingWorkerKt {
    public static final String ARGUMENT_CLASS_NAME = "androidx.work.impl.workers.ConstraintTrackingWorker.ARGUMENT_CLASS_NAME";
    /* access modifiers changed from: private */
    public static final String TAG;

    /* access modifiers changed from: private */
    public static final boolean setFailed(SettableFuture<ListenableWorker.Result> settableFuture) {
        return settableFuture.set(ListenableWorker.Result.failure());
    }

    /* access modifiers changed from: private */
    public static final boolean setRetry(SettableFuture<ListenableWorker.Result> settableFuture) {
        return settableFuture.set(ListenableWorker.Result.retry());
    }

    static {
        String tagWithPrefix = Logger.tagWithPrefix("ConstraintTrkngWrkr");
        Intrinsics.checkNotNullExpressionValue(tagWithPrefix, "tagWithPrefix(\"ConstraintTrkngWrkr\")");
        TAG = tagWithPrefix;
    }
}
