package com.google.android.gms.internal.location;

import android.os.RemoteException;
import com.google.android.gms.common.api.Api;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.internal.ListenerHolders;
import com.google.android.gms.location.LocationListener;

/* compiled from: com.google.android.gms:play-services-location@@21.0.1 */
final class zzan extends zzat {
    final /* synthetic */ LocationListener zza;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    zzan(zzau zzau, GoogleApiClient googleApiClient, LocationListener locationListener) {
        super(googleApiClient);
        this.zza = locationListener;
    }

    /* access modifiers changed from: protected */
    public final /* bridge */ /* synthetic */ void doExecute(Api.AnyClient anyClient) throws RemoteException {
        Class<LocationListener> cls = LocationListener.class;
        ((zzda) anyClient).zzC(ListenerHolders.createListenerKey(this.zza, "LocationListener"), true, zzau.zza(this));
    }
}
