package kotlinx.coroutines.channels;

import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Lambda;

@Metadata(mo19893d1 = {"\u0000\u000e\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\u0003\n\u0000\u0010\u0000\u001a\u00020\u00012\b\u0010\u0002\u001a\u0004\u0018\u00010\u0003H\n¢\u0006\u0002\b\u0004"}, mo19894d2 = {"<anonymous>", "", "cause", "", "invoke"}, mo19895k = 3, mo19896mv = {1, 6, 0}, mo19898xi = 48)
/* compiled from: Deprecated.kt */
final class ChannelsKt__DeprecatedKt$consumes$1 extends Lambda implements Function1<Throwable, Unit> {
    final /* synthetic */ ReceiveChannel<?> $this_consumes;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    ChannelsKt__DeprecatedKt$consumes$1(ReceiveChannel<?> receiveChannel) {
        super(1);
        this.$this_consumes = receiveChannel;
    }

    public /* bridge */ /* synthetic */ Object invoke(Object obj) {
        invoke((Throwable) obj);
        return Unit.INSTANCE;
    }

    public final void invoke(Throwable th) {
        ChannelsKt.cancelConsumed(this.$this_consumes, th);
    }
}
