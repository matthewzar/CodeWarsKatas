package com.company.Kyu3;

import java.util.Arrays;
import java.util.HashSet;

// https://www.codewars.com/kata/585894545a8a07255e0002f1/
public class ScreenLockPatternCounter {
    private final Integer[] ID = {0,2,4,10,12,14,20,22,24};

    public int CountPatterns(char startPosition, int patternLength){
        if (patternLength > 9 || patternLength <= 0)
            return 0;
        return rec(ID[startPosition-'A'], patternLength, new HashSet(Arrays.asList(ID)));
    }

    private int rec(int st, int len, HashSet<Integer> left) {
        if (len <= 1)
            return len;
        left.remove(st);
        return left.stream()
                .filter(n -> !left.contains((st+n)/2))
                .mapToInt(n -> rec(n, len-1, new HashSet(left)))
                .sum();
    }
}
