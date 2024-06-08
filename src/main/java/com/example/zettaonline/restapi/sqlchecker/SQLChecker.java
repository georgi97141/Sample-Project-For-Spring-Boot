package com.example.zettaonline.restapi.sqlchecker;

import java.util.Arrays;
import java.util.List;

public class SQLChecker {

    private static final List<String> SQL_KEYWORDS = Arrays.asList(
            "SELECT", "INSERT", "UPDATE", "DELETE", "DROP", "ALTER", "CREATE",
            "EXEC", "UNION", "ALL", "GRANT", "REVOKE", "TRUNCATE"
    );

    public static boolean containsSQL(String input) {
        if (input == null) {
            return false;
        }
        String upperInput = input.toUpperCase();
        for (String keyword : SQL_KEYWORDS) {
            if (upperInput.contains(keyword)) {
                return true;
            }
        }
        return false;
    }
}
