package model;

public class StringValidator {

    public static Boolean isNotBlank(String string) {
        if (string == null) return false;
        if (string.isBlank()) return false;

        return true;
    }
}
