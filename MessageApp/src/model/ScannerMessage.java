package model;

import java.util.Scanner;

public class ScannerMessage {

    public static String get(String label) {
        Scanner scanner = new Scanner(System.in);
        if (label != null) {
            System.out.println(label);
        }

        return scanner.nextLine();
    }
}
