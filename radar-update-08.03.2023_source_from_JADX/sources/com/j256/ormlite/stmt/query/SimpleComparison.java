package com.j256.ormlite.stmt.query;

import com.j256.ormlite.field.FieldType;
import com.j256.ormlite.p006db.DatabaseType;
import java.sql.SQLException;
import java.util.List;

public class SimpleComparison extends BaseComparison {
    public static final String EQUAL_TO_OPERATION = "=";
    public static final String GREATER_THAN_EQUAL_TO_OPERATION = ">=";
    public static final String GREATER_THAN_OPERATION = ">";
    public static final String LESS_THAN_EQUAL_TO_OPERATION = "<=";
    public static final String LESS_THAN_OPERATION = "<";
    public static final String LIKE_OPERATION = "LIKE";
    public static final String NOT_EQUAL_TO_OPERATION = "<>";
    private final String operation;

    public /* bridge */ /* synthetic */ void appendSql(DatabaseType databaseType, String str, StringBuilder sb, List list) throws SQLException {
        super.appendSql(databaseType, str, sb, list);
    }

    public /* bridge */ /* synthetic */ void appendValue(DatabaseType databaseType, StringBuilder sb, List list) throws SQLException {
        super.appendValue(databaseType, sb, list);
    }

    public /* bridge */ /* synthetic */ String getColumnName() {
        return super.getColumnName();
    }

    public /* bridge */ /* synthetic */ String toString() {
        return super.toString();
    }

    public SimpleComparison(String str, FieldType fieldType, Object obj, String str2) throws SQLException {
        super(str, fieldType, obj, true);
        this.operation = str2;
    }

    public void appendOperation(StringBuilder sb) {
        sb.append(this.operation);
        sb.append(' ');
    }
}
