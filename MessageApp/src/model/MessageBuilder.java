package model;

public class MessageBuilder {

    public static String defaultColor(String message) {
        return ConsoleColors.RESET + message;
    }

    public static String error(String message) {
        return ConsoleColors.RED + message;
    }
}
