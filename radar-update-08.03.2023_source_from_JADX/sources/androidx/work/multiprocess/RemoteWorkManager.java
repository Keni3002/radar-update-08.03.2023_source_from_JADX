package androidx.work.multiprocess;

import android.content.Context;
import androidx.work.Data;
import androidx.work.ExistingPeriodicWorkPolicy;
import androidx.work.ExistingWorkPolicy;
import androidx.work.ForegroundInfo;
import androidx.work.OneTimeWorkRequest;
import androidx.work.PeriodicWorkRequest;
import androidx.work.WorkContinuation;
import androidx.work.WorkInfo;
import androidx.work.WorkQuery;
import androidx.work.WorkRequest;
import androidx.work.impl.WorkManagerImpl;
import com.google.common.util.concurrent.ListenableFuture;
import java.util.Collections;
import java.util.List;
import java.util.UUID;

public abstract class RemoteWorkManager {
    public abstract RemoteWorkContinuation beginUniqueWork(String str, ExistingWorkPolicy existingWorkPolicy, List<OneTimeWorkRequest> list);

    public abstract RemoteWorkContinuation beginWith(List<OneTimeWorkRequest> list);

    public abstract ListenableFuture<Void> cancelAllWork();

    public abstract ListenableFuture<Void> cancelAllWorkByTag(String str);

    public abstract ListenableFuture<Void> cancelUniqueWork(String str);

    public abstract ListenableFuture<Void> cancelWorkById(UUID uuid);

    public abstract ListenableFuture<Void> enqueue(WorkContinuation workContinuation);

    public abstract ListenableFuture<Void> enqueue(WorkRequest workRequest);

    public abstract ListenableFuture<Void> enqueue(List<WorkRequest> list);

    public abstract ListenableFuture<Void> enqueueUniquePeriodicWork(String str, ExistingPeriodicWorkPolicy existingPeriodicWorkPolicy, PeriodicWorkRequest periodicWorkRequest);

    public abstract ListenableFuture<Void> enqueueUniqueWork(String str, ExistingWorkPolicy existingWorkPolicy, List<OneTimeWorkRequest> list);

    public abstract ListenableFuture<List<WorkInfo>> getWorkInfos(WorkQuery workQuery);

    public abstract ListenableFuture<Void> setForegroundAsync(String str, ForegroundInfo foregroundInfo);

    public abstract ListenableFuture<Void> setProgress(UUID uuid, Data data);

    protected RemoteWorkManager() {
    }

    public final ListenableFuture<Void> enqueueUniqueWork(String str, ExistingWorkPolicy existingWorkPolicy, OneTimeWorkRequest oneTimeWorkRequest) {
        return enqueueUniqueWork(str, existingWorkPolicy, (List<OneTimeWorkRequest>) Collections.singletonList(oneTimeWorkRequest));
    }

    public final RemoteWorkContinuation beginWith(OneTimeWorkRequest oneTimeWorkRequest) {
        return beginWith((List<OneTimeWorkRequest>) Collections.singletonList(oneTimeWorkRequest));
    }

    public final RemoteWorkContinuation beginUniqueWork(String str, ExistingWorkPolicy existingWorkPolicy, OneTimeWorkRequest oneTimeWorkRequest) {
        return beginUniqueWork(str, existingWorkPolicy, (List<OneTimeWorkRequest>) Collections.singletonList(oneTimeWorkRequest));
    }

    public static RemoteWorkManager getInstance(Context context) {
        RemoteWorkManager remoteWorkManager = WorkManagerImpl.getInstance(context).getRemoteWorkManager();
        if (remoteWorkManager != null) {
            return remoteWorkManager;
        }
        throw new IllegalStateException("Unable to initialize RemoteWorkManager");
    }
}
