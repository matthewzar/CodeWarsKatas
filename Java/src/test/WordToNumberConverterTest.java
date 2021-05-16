package test;

import com.company.Kyu6.WordToNumberConverter;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.junit.jupiter.params.provider.ValueSource;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class WordToNumberConverterTest {
    @Test
    public void GivenEmptyString_ShouldReturnZero(){
        // Arrange
        var sut = new WordToNumberConverter();

        // Act
        long result = sut.Convert("");

        // Assert
        assertEquals(0, result);
    }

    @ParameterizedTest
    @ValueSource(strings = {"a", "b", "c", "x", "y", "z"})
    public void GivenSingleLetter_ShouldReturn1(String input){
        // Arrange
        var sut = new WordToNumberConverter();

        // Act
        long result = sut.Convert(input);

        // Assert
        assertEquals(1, result);
    }

    @ParameterizedTest
    @ValueSource(strings = {"aa", "bb", "dd", "qq", "vv", "EE"})
    public void GivenIdenticalLetterPair_ShouldReturn11(String input){
        // Arrange
        var sut = new WordToNumberConverter();

        // Act
        long result = sut.Convert(input);

        // Assert
        assertEquals(11, result);
    }

    @ParameterizedTest
    @ValueSource(strings = {"ab", "ba", "zd", "qe", "bv", "nE"})
    public void GivenDifferentLetterPair_ShouldReturn10(String input){
        // Arrange
        var sut = new WordToNumberConverter();

        // Act
        long result = sut.Convert(input);

        // Assert
        assertEquals(10, result);
    }
}