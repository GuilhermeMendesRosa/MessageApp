package model;

public class StringValidator {

    public static Boolean validate(String username) {
        if (username == null) return false;
        if (username.isBlank()) return false;

        return true;
    }
}
