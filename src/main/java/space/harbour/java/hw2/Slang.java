package space.harbour.java.hw2;

import java.util.HashMap; // import the HashMap class
import java.util.Scanner;

public class Slang {
    public static void main(String[] args) {
        System.out.print("Enter a string : ");
        Scanner scanner = new Scanner(System.in);
        String testText = scanner.nextLine();

        testText = fixSmiles(fixAbbreviations(testText));
        System.out.println(testText);
    }

    public static String fixAbbreviations(String text) {
        HashMap<String, String> replaces = new HashMap<String, String>();
        replaces.put("PLZ", "please");
        replaces.put("FYI", "for your information");
        replaces.put("GTFO", "please, leave me alone");
        replaces.put("ASAP", "as soon as possible");

        for (HashMap.Entry<String, String> entry : replaces.entrySet()) {
            String key = entry.getKey();
            String value = entry.getValue();
            text = text.replaceAll(key, value);
        }
        return text;

    }

    public static String fixSmiles(String text) {
        HashMap<String, String> replaces = new HashMap<String, String>();
        replaces.put(":\\)", "[smiling]");
        replaces.put(":\\(", "[sad]");
        // regex not matching :( [sad]
        replaces.put("¯\\_\\(ツ\\)_\\/¯", "[such is life]");

        for (HashMap.Entry<String, String> entry : replaces.entrySet()) {
            String key = entry.getKey();
            String value = entry.getValue();
            text = text.replaceAll(key, value);
        }
        return text;

    }
}
