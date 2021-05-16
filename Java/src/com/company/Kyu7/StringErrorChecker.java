package com.company.Kyu7;

import org.jetbrains.annotations.NotNull;

import java.util.function.IntPredicate;

public class StringErrorChecker {
    public String GetErrors(String printerMessage) {
        var errors = 0;
        for(char c : printerMessage.toCharArray())
        {
            if(c > 'm') {
                errors++;
            }
        }

        return String.format("%d/%d", errors, printerMessage.length());
    }

    /// Alternative *interesting* solutions (some are bad practice):

    // Use a regex to remove all valid chars. Count what's left. Done
    public static String printerError_alt1(String s) {
        return s.replaceAll("[a-m]", "").length() + "/" + s.length();
    }

    // Create a char stream, and filter out non-errors. This should be fast and low-memory.
    public static String printerError_alt2(String s) {

        var errs = s.chars().filter(ch -> ch > 'm').count();

        return errs+"/"+s.length();
    }
}
