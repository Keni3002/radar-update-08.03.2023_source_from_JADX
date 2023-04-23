package okhttp3.internal.p014ws;

import kotlin.Metadata;
import okio.ByteString;

@Metadata(mo19893d1 = {"\u0000\u000e\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0000\"\u000e\u0010\u0000\u001a\u00020\u0001X\u0004¢\u0006\u0002\n\u0000\"\u000e\u0010\u0002\u001a\u00020\u0003XT¢\u0006\u0002\n\u0000¨\u0006\u0004"}, mo19894d2 = {"EMPTY_DEFLATE_BLOCK", "Lokio/ByteString;", "LAST_OCTETS_COUNT_TO_REMOVE_AFTER_DEFLATION", "", "okhttp"}, mo19895k = 2, mo19896mv = {1, 6, 0}, mo19898xi = 48)
/* renamed from: okhttp3.internal.ws.MessageDeflaterKt */
/* compiled from: MessageDeflater.kt */
public final class MessageDeflaterKt {
    /* access modifiers changed from: private */
    public static final ByteString EMPTY_DEFLATE_BLOCK = ByteString.Companion.decodeHex("000000ffff");
    private static final int LAST_OCTETS_COUNT_TO_REMOVE_AFTER_DEFLATION = 4;
}
