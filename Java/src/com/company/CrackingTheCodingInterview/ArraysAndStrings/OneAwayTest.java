package com.company.CrackingTheCodingInterview.ArraysAndStrings;

import org.junit.jupiter.api.Test;

import static com.company.CrackingTheCodingInterview.ArraysAndStrings.OneAway.oneEditAway;
import static org.junit.jupiter.api.Assertions.*;

class OneAwayTest {

    @Test
    void givenNoDifferenceReturnTrue() {
        assertTrue(oneEditAway("same", "same"));
    }

    @Test
    void givenTwoAddedCharsReturnFalse() {
        assertFalse(oneEditAway("same", "same12"));
    }

    @Test
    void givenTwoRemoveCharsReturnFalse() {
        assertFalse(oneEditAway("same", "me"));
    }

    @Test
    void givenTwoModifiedReturnFalse() {
        assertFalse(oneEditAway("AB", "12"));
    }
}