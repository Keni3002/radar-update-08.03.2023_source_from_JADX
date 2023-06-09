package com.google.android.gms.internal.location;

import android.app.PendingIntent;
import android.location.Location;
import android.os.IInterface;
import android.os.RemoteException;
import com.google.android.gms.common.api.internal.IStatusCallback;
import com.google.android.gms.common.internal.ICancelToken;
import com.google.android.gms.location.ActivityTransitionRequest;
import com.google.android.gms.location.CurrentLocationRequest;
import com.google.android.gms.location.GeofencingRequest;
import com.google.android.gms.location.LastLocationRequest;
import com.google.android.gms.location.LocationAvailability;
import com.google.android.gms.location.LocationRequest;
import com.google.android.gms.location.LocationSettingsRequest;
import com.google.android.gms.location.SleepSegmentRequest;
import com.google.android.gms.location.zzb;

/* compiled from: com.google.android.gms:play-services-location@@21.0.1 */
public interface zzo extends IInterface {
    @Deprecated
    Location zzd() throws RemoteException;

    @Deprecated
    ICancelToken zze(CurrentLocationRequest currentLocationRequest, zzq zzq) throws RemoteException;

    @Deprecated
    LocationAvailability zzf(String str) throws RemoteException;

    void zzg(GeofencingRequest geofencingRequest, PendingIntent pendingIntent, zzm zzm) throws RemoteException;

    void zzh(LocationSettingsRequest locationSettingsRequest, zzs zzs, String str) throws RemoteException;

    void zzi(zzk zzk) throws RemoteException;

    @Deprecated
    void zzj(LastLocationRequest lastLocationRequest, zzq zzq) throws RemoteException;

    void zzk(zzdb zzdb, LocationRequest locationRequest, IStatusCallback iStatusCallback) throws RemoteException;

    void zzl(PendingIntent pendingIntent, IStatusCallback iStatusCallback) throws RemoteException;

    void zzm(PendingIntent pendingIntent) throws RemoteException;

    void zzn(PendingIntent pendingIntent, zzm zzm, String str) throws RemoteException;

    void zzo(String[] strArr, zzm zzm, String str) throws RemoteException;

    void zzp(PendingIntent pendingIntent, IStatusCallback iStatusCallback) throws RemoteException;

    void zzq(ActivityTransitionRequest activityTransitionRequest, PendingIntent pendingIntent, IStatusCallback iStatusCallback) throws RemoteException;

    void zzr(long j, boolean z, PendingIntent pendingIntent) throws RemoteException;

    void zzs(zzb zzb, PendingIntent pendingIntent, IStatusCallback iStatusCallback) throws RemoteException;

    void zzt(PendingIntent pendingIntent, SleepSegmentRequest sleepSegmentRequest, IStatusCallback iStatusCallback) throws RemoteException;

    @Deprecated
    void zzu(Location location) throws RemoteException;

    void zzv(Location location, IStatusCallback iStatusCallback) throws RemoteException;

    @Deprecated
    void zzw(boolean z) throws RemoteException;

    void zzx(boolean z, IStatusCallback iStatusCallback) throws RemoteException;

    void zzy(zzdb zzdb, IStatusCallback iStatusCallback) throws RemoteException;

    @Deprecated
    void zzz(zzdf zzdf) throws RemoteException;
}
