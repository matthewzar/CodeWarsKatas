package test.CrackingTheCodingInterview.ArraysAndStrings;

import com.company.CrackingTheCodingInterview.ArraysAndStrings.CheckIfPermutation;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class CheckIfPermutationTest {

    @Test
    void givenTwoIdenticalStringsShouldReturnTrue() {
        assertTrue(CheckIfPermutation.arePermutations("", ""));
        assertTrue(CheckIfPermutation.arePermutations("abc", "abc"));
    }

    @Test
    void givenTwoDifferentLengthStringShouldReturnFalse() {
        assertFalse(CheckIfPermutation.arePermutations("a", "aa"));
        assertFalse(CheckIfPermutation.arePermutations("abc", "a"));
    }

    @Test
    void given1CharLongMismatchedStringsReturnFalse() {
        assertFalse(CheckIfPermutation.arePermutations("a", "b"));
        assertFalse(CheckIfPermutation.arePermutations("c", "d"));
    }

    @Test

    void given2CharLongPermutationsReturnTrue() {
        assertTrue(CheckIfPermutation.arePermutations("ab", "ba"));
        assertTrue(CheckIfPermutation.arePermutations("xy", "yx"));
    }
}