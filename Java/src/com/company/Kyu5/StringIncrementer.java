package com.company.Kyu5;
import java.math.BigInteger;

public class StringIncrementer {
    public static String incrementString(String str) {
        int digitIndex = findDigitStartIndex(str) + 1;
        if (digitIndex == str.length()) return str + "1";

        return getPreface(str, digitIndex) + getIncreasedDigits(str, digitIndex);
    }

    public static int findDigitStartIndex(String str) {
        int index = str.length() - 1;
        while (index >= 0 && Character.isDigit(str.charAt(index))) {
            index--;
        }
        return index;
    }

    public static String getPreface(String str, int digitIndex) {
        return str.substring(0, digitIndex);
    }

    public static String getIncreasedDigits(String str, int digitIndex){
        String digits = str.substring(digitIndex);
        BigInteger newDigits = new BigInteger(digits).add(BigInteger.ONE);
        return String.format("%0" + digits.length() + "d", newDigits);
    }
}
