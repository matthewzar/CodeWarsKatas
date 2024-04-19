package com.company.CrackingTheCodingInterview.ArraysAndStrings;

import java.util.HashSet;
import java.util.Hashtable;
import java.util.Set;

public class IsUnique {
    // Horrible n^2 performance. Trying for an approach with no extra datastructures
    public static boolean isUnique(String target) {
        for (int i = 0; i < target.length()-1; i++) {
            for (int j = i+1; j < target.length(); j++) {
                if (target.charAt(i) == target.charAt(j)) return false;
            }
        }
        return true;
    }

    //
    public static boolean isUniqueHashSolution(String target) {
        Set<Character> chars = new HashSet<>();
        for (char c: target.toCharArray()) {
            if (chars.contains(c)) return false;
            chars.add(c);
        }
        return true;
    }
}
