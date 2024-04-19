package test.CrackingTheCodingInterview.ArraysAndStrings;

import com.company.CrackingTheCodingInterview.ArraysAndStrings.IsUnique;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class IsUniqueTest {

    @Test
    void givenEmptyStringReturnTrue() {
        assertTrue(IsUnique.isUnique(""));
    }

    @Test
    void givenSingleCharStringReturnTrue() {
        assertTrue(IsUnique.isUnique("a"));
    }

    @Test
    void givenTwoDifferentCharStringReturnTrue() {
        assertTrue(IsUnique.isUnique("ab"));
    }

    @Test
    void givenTwoSameCharStringReturnFalse() {
        assertFalse(IsUnique.isUnique("aa"));
    }

    @Test
    void givenLongDuplicatesStringReturnFalse() {
        assertFalse(IsUnique.isUnique("abcdcba"));
    }

    @Test
    void givenLongNoDuplicatesStringReturnTrue() {
        assertTrue(IsUnique.isUnique("abcdef"));
    }


    @Test
    void givenEmptyStringReturnTrueHashSolution() {
        assertTrue(IsUnique.isUniqueHashSolution(""));
    }

    @Test
    void givenSingleCharStringReturnTrueHashSolution() {
        assertTrue(IsUnique.isUniqueHashSolution("a"));
    }

    @Test
    void givenTwoDifferentCharStringReturnTrueHashSolution() {
        assertTrue(IsUnique.isUniqueHashSolution("ab"));
    }

    @Test
    void givenTwoSameCharStringReturnFalseHashSolution() {
        assertFalse(IsUnique.isUniqueHashSolution("aa"));
    }

    @Test
    void givenLongDuplicatesStringReturnFalseHashSolution() {
        assertFalse(IsUnique.isUniqueHashSolution("abcdcba"));
    }

    @Test
    void givenLongNoDuplicatesStringReturnTrueHashSolution() {
        assertTrue(IsUnique.isUniqueHashSolution("abcdef"));
    }
}
