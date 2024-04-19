package com.company.CrackingTheCodingInterview.ArraysAndStrings;

import org.junit.jupiter.api.Test;

import static com.company.CrackingTheCodingInterview.ArraysAndStrings.URLify.replaceSpacesInPlace;
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;

import java.lang.reflect.Array;

import static org.junit.jupiter.api.Assertions.*;

class URLifyTest {

    @Test
    void givenEmptyCharArrayShouldNotChange() {
        char[] inputURL = new char[0];
        char[] expectedURL = new char[0];

        replaceSpacesInPlace(inputURL, 0);

        assertArrayEquals(expectedURL, inputURL);
    }

    @Test
    void givenSingleSpaceCharArrayShouldInsertSingleEncoding() {
        char[] inputURL = "   ".toCharArray();
        char[] expectedURL = "%20".toCharArray();

        replaceSpacesInPlace(inputURL, 1);
        assertArrayEquals(expectedURL, inputURL);
    }

    @Test
    void givenDoubleSpaceCharArrayShouldInsertTwoEncodings() {
        char[] inputURL = "      ".toCharArray();
        char[] expectedURL = "%20%20".toCharArray();

        replaceSpacesInPlace(inputURL, 2);
        assertArrayEquals(expectedURL, inputURL);
    }

    @Test
    void givenSingleNonSpaceCharArrayShouldMakeNoChanges() {
        char[] inputURL = "A".toCharArray();
        char[] expectedURL = "A".toCharArray();

        replaceSpacesInPlace(inputURL, 1);
        assertArrayEquals(expectedURL, inputURL);
    }

    @Test
    void givenLetterThenSpaceShouldAppendEncoding() {
        char[] inputURL = "A   ".toCharArray();
        char[] expectedURL = "A%20".toCharArray();

        replaceSpacesInPlace(inputURL, 2);
        assertArrayEquals(expectedURL, inputURL);
    }

    @Test
    void givenSpaceThenLetterShouldPrependEncoding() {
        char[] inputURL = " A  ".toCharArray();
        char[] expectedURL = "%20A".toCharArray();

        replaceSpacesInPlace(inputURL, 2);
        assertArrayEquals(expectedURL, inputURL);
    }

    @Test
    void givenManyWordManySpaceShouldInsertMultipleEncodings() {
        char[] inputURL =    "A dog eats meat      ".toCharArray();
        char[] expectedURL = "A%20dog%20eats%20meat".toCharArray();

        replaceSpacesInPlace(inputURL, 15);
        assertArrayEquals(expectedURL, inputURL);
    }
}