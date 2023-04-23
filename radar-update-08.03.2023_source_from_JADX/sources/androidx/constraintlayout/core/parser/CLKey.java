package androidx.constraintlayout.core.parser;

import java.util.ArrayList;

public class CLKey extends CLContainer {
    private static ArrayList<String> sections;

    static {
        ArrayList<String> arrayList = new ArrayList<>();
        sections = arrayList;
        arrayList.add("ConstraintSets");
        sections.add("Variables");
        sections.add("Generate");
        sections.add("Transitions");
        sections.add("KeyFrames");
        sections.add("KeyAttributes");
        sections.add("KeyPositions");
        sections.add("KeyCycles");
    }

    public CLKey(char[] cArr) {
        super(cArr);
    }

    public static CLElement allocate(char[] cArr) {
        return new CLKey(cArr);
    }

    public static CLElement allocate(String str, CLElement cLElement) {
        CLKey cLKey = new CLKey(str.toCharArray());
        cLKey.setStart(0);
        cLKey.setEnd((long) (str.length() - 1));
        cLKey.set(cLElement);
        return cLKey;
    }

    public String getName() {
        return content();
    }

    /* access modifiers changed from: protected */
    public String toJSON() {
        if (this.mElements.size() > 0) {
            return getDebugName() + content() + ": " + ((CLElement) this.mElements.get(0)).toJSON();
        }
        return getDebugName() + content() + ": <> ";
    }

    /* access modifiers changed from: protected */
    public String toFormattedJSON(int i, int i2) {
        StringBuilder sb = new StringBuilder(getDebugName());
        addIndent(sb, i);
        String content = content();
        if (this.mElements.size() > 0) {
            sb.append(content);
            sb.append(": ");
            if (sections.contains(content)) {
                i2 = 3;
            }
            if (i2 > 0) {
                sb.append(((CLElement) this.mElements.get(0)).toFormattedJSON(i, i2 - 1));
            } else {
                String json = ((CLElement) this.mElements.get(0)).toJSON();
                if (json.length() + i < MAX_LINE) {
                    sb.append(json);
                } else {
                    sb.append(((CLElement) this.mElements.get(0)).toFormattedJSON(i, i2 - 1));
                }
            }
            return sb.toString();
        }
        return content + ": <> ";
    }

    public void set(CLElement cLElement) {
        if (this.mElements.size() > 0) {
            this.mElements.set(0, cLElement);
        } else {
            this.mElements.add(cLElement);
        }
    }

    public CLElement getValue() {
        if (this.mElements.size() > 0) {
            return (CLElement) this.mElements.get(0);
        }
        return null;
    }
}
