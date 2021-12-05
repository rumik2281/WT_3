package by.bsuir.lab3.domain.console;

import java.util.Scanner;

public class ConsoleScanner {
    private static final Scanner consoleScanner = new Scanner(System.in);

    private ConsoleScanner() {
    }

    public static String getString() {
        return consoleScanner.nextLine();
    }

    public static int getInt() {
        while (!consoleScanner.hasNextInt()) {
            consoleScanner.next();
        }
        return consoleScanner.nextInt();
    }

    public static int getPositiveInt() {
        int result;

        do {
            result = getInt();
        } while (result <= 0);
        return result;
    }

    public static String getNonEmptyString() {
        String result;

        do {
            result = getString();
        } while (result.isEmpty());
        return result;
    }
}
