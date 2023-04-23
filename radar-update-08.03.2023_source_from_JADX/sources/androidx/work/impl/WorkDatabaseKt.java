package androidx.work.impl;

import java.util.concurrent.TimeUnit;
import kotlin.Metadata;

@Metadata(mo19893d1 = {"\u0000\u0010\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0010\t\n\u0000\"\u000e\u0010\u0000\u001a\u00020\u0001XT¢\u0006\u0002\n\u0000\"\u000e\u0010\u0002\u001a\u00020\u0001XT¢\u0006\u0002\n\u0000\"\u000e\u0010\u0003\u001a\u00020\u0004X\u0004¢\u0006\u0002\n\u0000¨\u0006\u0005"}, mo19894d2 = {"PRUNE_SQL_FORMAT_PREFIX", "", "PRUNE_SQL_FORMAT_SUFFIX", "PRUNE_THRESHOLD_MILLIS", "", "work-runtime_release"}, mo19895k = 2, mo19896mv = {1, 7, 1}, mo19898xi = 48)
/* compiled from: WorkDatabase.kt */
public final class WorkDatabaseKt {
    private static final String PRUNE_SQL_FORMAT_PREFIX = "DELETE FROM workspec WHERE state IN (2, 3, 5) AND (last_enqueue_time + minimum_retention_duration) < ";
    private static final String PRUNE_SQL_FORMAT_SUFFIX = " AND (SELECT COUNT(*)=0 FROM dependency WHERE     prerequisite_id=id AND     work_spec_id NOT IN         (SELECT id FROM workspec WHERE state IN (2, 3, 5)))";
    /* access modifiers changed from: private */
    public static final long PRUNE_THRESHOLD_MILLIS = TimeUnit.DAYS.toMillis(1);
}
