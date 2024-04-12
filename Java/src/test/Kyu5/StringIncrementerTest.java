package test.Kyu5;

import com.company.Kyu5.StringIncrementer;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class StringIncrementerTest {

    @Test
    public void testIncrementStringWithNoNumbers() {
        assertEquals("foo1", StringIncrementer.incrementString("foo"), "String without numbers should have '1' appended.");
    }

    @Test
    public void testIncrementStringWithNumbers() {
        assertEquals("foo24", StringIncrementer.incrementString("foo23"), "Increment the number at the end of the string.");
    }

    @Test
    public void testIncrementStringWithLeadingZeros() {
        assertEquals("foo010", StringIncrementer.incrementString("foo009"), "Preserve leading zeros when incrementing.");
    }

    @Test
    public void testIncrementStringWithLargeNumber() {
        assertEquals("foo10000000001", StringIncrementer.incrementString("foo10000000000"), "Correctly increment very large numbers.");
    }

    @Test
    public void testIncrementStringThatEndsWithMaxInt() {
        assertEquals("foo2147483648", StringIncrementer.incrementString("foo2147483647"), "Handle case that ends with max int value.");
    }

    @Test
    public void testEmptyString() {
        assertEquals("1", StringIncrementer.incrementString(""), "Empty string should return '1'.");
    }

    @Test
    public void testStringWithDigitsOnly() {
        assertEquals("12346", StringIncrementer.incrementString("12345"), "Correctly increment a string that contains only digits.");
    }

    @Test
    public void testIncrementStringWithNonNumericEnding() {
        assertEquals("foo123bar1", StringIncrementer.incrementString("foo123bar"), "Append '1' if the string ends with non-numeric characters.");
    }

    @Test
    public void testIncrementStringWithAllZeros() {
        assertEquals("00001", StringIncrementer.incrementString("00000"), "Correctly handle incrementing a string of all zeros.");
    }
}