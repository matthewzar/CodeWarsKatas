package com.company.CrackingTheCodingInterview.ArraysAndStrings;

public class URLify {
    public static void replaceSpacesInPlace(char[] rawURL, int trueLength) {
        int endIndex = rawURL.length-1;
        for (int i = trueLength-1; i >= 0; i--) {
            if (rawURL[i] == ' ') {
                insertEncodingAt(rawURL, endIndex - 2);
                endIndex -= 3;
                continue;
            }

            rawURL[endIndex] = rawURL[i];
            endIndex--;
        }
    }

    public static void insertEncodingAt(char[] target, int targetIndex) {
        target[targetIndex+0] = '%';
        target[targetIndex+1] = '2';
        target[targetIndex+2] = '0';
    }
}
