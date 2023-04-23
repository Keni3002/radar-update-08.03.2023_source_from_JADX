package org.jsoup.internal;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.Arrays;
import java.util.Collection;
import java.util.Iterator;
import java.util.Stack;
import java.util.regex.Pattern;
import javax.annotation.Nullable;
import org.jsoup.helper.Validate;

public final class StringUtil {
    private static final int MaxCachedBuilderSize = 8192;
    private static final int MaxIdleBuilders = 8;
    private static final Pattern controlChars = Pattern.compile("[\\x00-\\x1f]*");
    private static final Pattern extraDotSegmentsPattern = Pattern.compile("^/((\\.{1,2}/)+)");
    static final String[] padding = {"", " ", "  ", "   ", "    ", "     ", "      ", "       ", "        ", "         ", "          ", "           ", "            ", "             ", "              ", "               ", "                ", "                 ", "                  ", "                   ", "                    "};
    private static final ThreadLocal<Stack<StringBuilder>> threadLocalBuilders = new ThreadLocal<Stack<StringBuilder>>() {
        /* access modifiers changed from: protected */
        public Stack<StringBuilder> initialValue() {
            return new Stack<>();
        }
    };
    private static final Pattern validUriScheme = Pattern.compile("^[a-zA-Z][a-zA-Z0-9+-.]*:");

    public static boolean isActuallyWhitespace(int i) {
        return i == 32 || i == 9 || i == 10 || i == 12 || i == 13 || i == 160;
    }

    public static boolean isInvisibleChar(int i) {
        return i == 8203 || i == 173;
    }

    public static boolean isWhitespace(int i) {
        return i == 32 || i == 9 || i == 10 || i == 12 || i == 13;
    }

    public static String join(Collection<?> collection, String str) {
        return join(collection.iterator(), str);
    }

    public static String join(Iterator<?> it, String str) {
        if (!it.hasNext()) {
            return "";
        }
        String obj = it.next().toString();
        if (!it.hasNext()) {
            return obj;
        }
        StringJoiner stringJoiner = new StringJoiner(str);
        stringJoiner.add(obj);
        while (it.hasNext()) {
            stringJoiner.add(it.next());
        }
        return stringJoiner.complete();
    }

    public static String join(String[] strArr, String str) {
        return join((Collection<?>) Arrays.asList(strArr), str);
    }

    public static class StringJoiner {
        boolean first = true;
        @Nullable

        /* renamed from: sb */
        StringBuilder f371sb = StringUtil.borrowBuilder();
        final String separator;

        public StringJoiner(String str) {
            this.separator = str;
        }

        public StringJoiner add(Object obj) {
            Validate.notNull(this.f371sb);
            if (!this.first) {
                this.f371sb.append(this.separator);
            }
            this.f371sb.append(obj);
            this.first = false;
            return this;
        }

        public StringJoiner append(Object obj) {
            Validate.notNull(this.f371sb);
            this.f371sb.append(obj);
            return this;
        }

        public String complete() {
            String releaseBuilder = StringUtil.releaseBuilder(this.f371sb);
            this.f371sb = null;
            return releaseBuilder;
        }
    }

    public static String padding(int i) {
        return padding(i, 30);
    }

    public static String padding(int i, int i2) {
        boolean z = true;
        Validate.isTrue(i >= 0, "width must be >= 0");
        if (i2 < -1) {
            z = false;
        }
        Validate.isTrue(z);
        if (i2 != -1) {
            i = Math.min(i, i2);
        }
        String[] strArr = padding;
        if (i < strArr.length) {
            return strArr[i];
        }
        char[] cArr = new char[i];
        for (int i3 = 0; i3 < i; i3++) {
            cArr[i3] = ' ';
        }
        return String.valueOf(cArr);
    }

    public static boolean isBlank(String str) {
        if (!(str == null || str.length() == 0)) {
            int length = str.length();
            for (int i = 0; i < length; i++) {
                if (!isWhitespace(str.codePointAt(i))) {
                    return false;
                }
            }
        }
        return true;
    }

    public static boolean startsWithNewline(String str) {
        if (str == null || str.length() == 0 || str.charAt(0) != 10) {
            return false;
        }
        return true;
    }

    public static boolean isNumeric(String str) {
        if (str == null || str.length() == 0) {
            return false;
        }
        int length = str.length();
        for (int i = 0; i < length; i++) {
            if (!Character.isDigit(str.codePointAt(i))) {
                return false;
            }
        }
        return true;
    }

    public static String normaliseWhitespace(String str) {
        StringBuilder borrowBuilder = borrowBuilder();
        appendNormalisedWhitespace(borrowBuilder, str, false);
        return releaseBuilder(borrowBuilder);
    }

    public static void appendNormalisedWhitespace(StringBuilder sb, String str, boolean z) {
        int length = str.length();
        int i = 0;
        boolean z2 = false;
        boolean z3 = false;
        while (i < length) {
            int codePointAt = str.codePointAt(i);
            if (isActuallyWhitespace(codePointAt)) {
                if ((!z || z2) && !z3) {
                    sb.append(' ');
                    z3 = true;
                }
            } else if (!isInvisibleChar(codePointAt)) {
                sb.appendCodePoint(codePointAt);
                z3 = false;
                z2 = true;
            }
            i += Character.charCount(codePointAt);
        }
    }

    /* renamed from: in */
    public static boolean m146in(String str, String... strArr) {
        for (String equals : strArr) {
            if (equals.equals(str)) {
                return true;
            }
        }
        return false;
    }

    public static boolean inSorted(String str, String[] strArr) {
        return Arrays.binarySearch(strArr, str) >= 0;
    }

    public static boolean isAscii(String str) {
        Validate.notNull(str);
        for (int i = 0; i < str.length(); i++) {
            if (str.charAt(i) > 127) {
                return false;
            }
        }
        return true;
    }

    public static URL resolve(URL url, String str) throws MalformedURLException {
        String stripControlChars = stripControlChars(str);
        if (stripControlChars.startsWith("?")) {
            stripControlChars = url.getPath() + stripControlChars;
        }
        URL url2 = new URL(url, stripControlChars);
        String replaceFirst = extraDotSegmentsPattern.matcher(url2.getFile()).replaceFirst("/");
        if (url2.getRef() != null) {
            replaceFirst = replaceFirst + "#" + url2.getRef();
        }
        return new URL(url2.getProtocol(), url2.getHost(), url2.getPort(), replaceFirst);
    }

    public static String resolve(String str, String str2) {
        String stripControlChars = stripControlChars(str);
        String stripControlChars2 = stripControlChars(str2);
        try {
            try {
                return resolve(new URL(stripControlChars), stripControlChars2).toExternalForm();
            } catch (MalformedURLException unused) {
                return validUriScheme.matcher(stripControlChars2).find() ? stripControlChars2 : "";
            }
        } catch (MalformedURLException unused2) {
            return new URL(stripControlChars2).toExternalForm();
        }
    }

    private static String stripControlChars(String str) {
        return controlChars.matcher(str).replaceAll("");
    }

    public static StringBuilder borrowBuilder() {
        Stack stack = threadLocalBuilders.get();
        if (stack.empty()) {
            return new StringBuilder(8192);
        }
        return (StringBuilder) stack.pop();
    }

    public static String releaseBuilder(StringBuilder sb) {
        Validate.notNull(sb);
        String sb2 = sb.toString();
        if (sb.length() > 8192) {
            sb = new StringBuilder(8192);
        } else {
            sb.delete(0, sb.length());
        }
        Stack stack = threadLocalBuilders.get();
        stack.push(sb);
        while (stack.size() > 8) {
            stack.pop();
        }
        return sb2;
    }
}
