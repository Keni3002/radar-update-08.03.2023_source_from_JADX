package kotlinx.coroutines.channels;

import kotlin.Metadata;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.jvm.internal.ContinuationImpl;
import kotlin.coroutines.jvm.internal.DebugMetadata;

@Metadata(mo19895k = 3, mo19896mv = {1, 6, 0}, mo19898xi = 48)
@DebugMetadata(mo20608c = "kotlinx.coroutines.channels.ChannelsKt__DeprecatedKt", mo20609f = "Deprecated.kt", mo20610i = {0, 0, 1, 1}, mo20611l = {149, 152}, mo20612m = "singleOrNull", mo20613n = {"$this$consume$iv", "iterator", "$this$consume$iv", "single"}, mo20614s = {"L$0", "L$1", "L$0", "L$1"})
/* compiled from: Deprecated.kt */
final class ChannelsKt__DeprecatedKt$singleOrNull$1<E> extends ContinuationImpl {
    Object L$0;
    Object L$1;
    int label;
    /* synthetic */ Object result;

    ChannelsKt__DeprecatedKt$singleOrNull$1(Continuation<? super ChannelsKt__DeprecatedKt$singleOrNull$1> continuation) {
        super(continuation);
    }

    public final Object invokeSuspend(Object obj) {
        this.result = obj;
        this.label |= Integer.MIN_VALUE;
        return ChannelsKt__DeprecatedKt.singleOrNull((ReceiveChannel) null, this);
    }
}
