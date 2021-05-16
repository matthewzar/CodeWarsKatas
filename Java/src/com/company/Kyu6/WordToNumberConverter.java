package com.company.Kyu6;


import java.util.Hashtable;

public class WordToNumberConverter {

    public long Convert(String input) {
        if (input.isEmpty()) return 0;

        input = input.toLowerCase();
        long result = 0;
        Hashtable<Character, Long> charValues = new Hashtable<>();

        // TODO - Replace this variable, and the charValues check, with charValues.computeIfAbsent(...);
        long nextNewMapping = 1;
        for(int i = 0; i < input.length(); i++){
            char currentChar = input.charAt(i);
            if(!charValues.containsKey(currentChar)) {
                charValues.put(currentChar, nextNewMapping);
                nextNewMapping = getNextIndex(nextNewMapping);
            }

            var newBase = (long) Math.pow(10, (input.length()-i-1));
            result = result + newBase * charValues.get(currentChar);
        }

        return result;
    }

    private long getNextIndex(long current){
        if (current == 1) return 0;
        if (current == 0) return 2;
        return current+1;
    }
}
