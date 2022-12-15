package model;

public class StringValidator {

    public static boolean isNotBlank(String string) {
        if (string == null) return false;
        if (string.isBlank()) return false;

        return true;
    }

    public static boolean isValidServerAddress(String value) {
        if (!StringValidator.isNotBlank(value)) return false;
        if (!value.contains(":")) return false;

        if (!StringValidator.isNotBlank(value.split(":")[0].toString())) return false;
        if (!StringValidator.isNotBlank(value.split(":")[1].toString())) return false;

        return true;
    }
}
