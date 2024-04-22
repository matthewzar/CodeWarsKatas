package com.company.CrackingTheCodingInterview.ArraysAndStrings;

public class OneAway {
    /// Check if the 2 words are only one of the follow edits away:
    // - Insert char
    // - Remove char
    // - Replace char
    public static boolean oneEditAway(String word1, String word2) {
        if (tooLargeLengthDifference(word1, word2)) return false;

        // TODO:
        //  - check the lengths are the same (this implies add+remove, or only modify)
        //  - loop through index by index, note the index of the first difference
        //  - reset your main index tracker so difference is offet
        //  - keep looping, if everything aligns to the end there's only been 1 modify, otherwise return false

        return true;
    }

    public static boolean tooLargeLengthDifference(String word1, String word2) {
        return Math.abs(word1.length() - word2.length()) > 1;
    }
}
