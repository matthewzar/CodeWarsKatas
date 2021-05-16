package com.company.Kyu6;

public class CamelCaseSplitter {
    public static String split(String input) {
        StringBuilder builder = new StringBuilder();

        for (char ch : input.toCharArray()) {
            if (Character.isUpperCase(ch))
                builder.append(' ');
            builder.append(ch);
        }

        return builder.toString();
    }

    // Find a capital letter using a regex, and stick a space before it.
    public static String split_regex(String input) {
        return input.replaceAll("([A-Z])", " $1");
    }
}
