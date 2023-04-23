package org.jsoup.helper;

import java.util.ArrayList;

public class ValidationException extends IllegalArgumentException {
    public static final String Validator = Validate.class.getName();

    public ValidationException(String str) {
        super(str);
    }

    public synchronized Throwable fillInStackTrace() {
        super.fillInStackTrace();
        StackTraceElement[] stackTrace = getStackTrace();
        ArrayList arrayList = new ArrayList();
        for (StackTraceElement stackTraceElement : stackTrace) {
            if (!stackTraceElement.getClassName().equals(Validator)) {
                arrayList.add(stackTraceElement);
            }
        }
        setStackTrace((StackTraceElement[]) arrayList.toArray(new StackTraceElement[0]));
        return this;
    }
}
