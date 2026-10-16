package com.sikavo.libraflow.utils;

public final class CleanStrings {

    private CleanStrings () {
        throw new UnsupportedOperationException("This is a utility method");
    }

    public static String stripWhiteSpace(String value) {
        if (value == null || value.isBlank()) {
            return null;
        };

        return value.trim();
    }
}
