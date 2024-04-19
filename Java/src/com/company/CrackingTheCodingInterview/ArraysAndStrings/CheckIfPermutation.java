package com.company.CrackingTheCodingInterview.ArraysAndStrings;

import org.jetbrains.annotations.NotNull;

import java.util.HashMap;

public class CheckIfPermutation {
    // This solution uses a charachter-count based approach with a hashmap
    // A 128 item int array would work too, if inputs are limited to ANCII chars.
    public static boolean arePermutations(String word1, String word2) {
        if (word1.length() != word2.length()) return false;
        if (word1 == word2) return true;

        HashMap<Character, Integer> charCount = countCharacters(word1);
        return doesMapMatchString(charCount, word2);
    }

    @NotNull
    private static HashMap<Character, Integer> countCharacters(String word) {
        HashMap<Character, Integer> charCount = new HashMap<>();
        for (char ch : word.toCharArray()) {
            charCount.put(ch, charCount.getOrDefault(ch, 0) + 1);
        }
        return charCount;
    }

    private static boolean doesMapMatchString(HashMap<Character, Integer> charCount, String word) {
        for (char ch : word.toCharArray()) {
            int count = charCount.getOrDefault(ch, 0);
            if (count == 0) {
                return false;
            }
            charCount.put(ch, count - 1);
        }
        return true;
    }
}
