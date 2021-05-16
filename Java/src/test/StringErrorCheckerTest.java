package test;

import com.company.Kyu7.StringErrorChecker;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.junit.jupiter.params.provider.ValueSource;

import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;

public class StringErrorCheckerTest {
    @ParameterizedTest
    @ValueSource(strings = {"a", "b","c","m"})
    public void GivenSingleValidLetter_ShouldReturnNoErrors(String input)
    {
        // Arrange
        StringErrorChecker sut = new StringErrorChecker();

        // Act
        var result = sut.GetErrors(input);

        // Assert
        assertEquals("0/1",result);
    }

    @ParameterizedTest
    @ValueSource(strings = {"z", "x","n","q"})
    public void GivenSingleInvalidLetter_ShouldReturnSingleErrors(String input)
    {
        // Arrange
        StringErrorChecker sut = new StringErrorChecker();

        // Act
        var result = sut.GetErrors(input);

        // Assert
        assertEquals("1/1", result);
    }

    public static Stream<Arguments> dataForLength5Tests() {
        return Stream.of(
                Arguments.of((Object) new String[]{ "aaaaa", "0/5" }),
                Arguments.of((Object) new String[]{ "abcde", "0/5" }),
                Arguments.of((Object) new String[]{ "exyzz", "4/5" }),
                Arguments.of((Object) new String[]{ "aqbzb", "2/5" })
        );
    }

    @ParameterizedTest
    @MethodSource("dataForLength5Tests")
    public void GivenMixedCharLength5Inputs_ShouldErrorCountAndLength5(String[] dataSetForSplit)
    {
        // Arrange
        StringErrorChecker sut = new StringErrorChecker();
        var input = dataSetForSplit[0];
        var expected = dataSetForSplit[1];

        // Act
        var result = sut.GetErrors(input);

        // Assert
        assertEquals(expected, result);
    }

    public static Stream<Arguments> dataForMixedTests() {
        return Stream.of(
                Arguments.of((Object) new String[]{ "abcdefzghijkl", "1/13" }),
                Arguments.of((Object) new String[]{ "zabcdez", "2/7" }),
                Arguments.of((Object) new String[]{ "aebxcydzz", "4/9" }),
                Arguments.of((Object) new String[]{ "tasqbtzsbg", "6/10" })
        );
    }

    @ParameterizedTest
    @MethodSource("dataForMixedTests")
    public void GivenMixedInputs_ShouldCountErrorsAndLength(String[] dataSetForSplit)
    {
        // Arrange
        StringErrorChecker sut = new StringErrorChecker();
        var input = dataSetForSplit[0];
        var expected = dataSetForSplit[1];

        // Act
        var result = sut.GetErrors(input);

        // Assert
        assertEquals(expected, result);
    }

}