package test;

import com.company.Kyu6.CamelCaseSplitter;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;

public class CamelCaseSplitterTest {
    @Test
    void split() {
        // arrange

        // act
        var result = CamelCaseSplitter.split("camelCaseTest");

        // assert
        assertEquals("camel Case Test", result, "Basic test failed... so sad");
    }

    /////////////

//    @ParameterizedTest
//    @ValueSource(strings = { "raceCar", "radarRed", "ableWasIEverMore" })
//    void palindromes(String candidate) {
//        assertTrue(!CamelCaseSplitter.split(candidate).equals(""));
//    }

    //////////////

    public static Stream<Arguments> dataSetForSplit() {
        return Stream.of(
                Arguments.of((Object) new String[]{ "raceCar", "race Car" }),
                Arguments.of((Object) new String[]{ "seriouslyLongWinded", "seriously Long Winded" }),
                Arguments.of((Object) new String[]{ "whyNotJustTestCase", "why Not Just Test Case" })
        );
    }

    @ParameterizedTest
    @MethodSource("dataSetForSplit")
    void GivenValidInput_ShouldAddSpaces(String[] dataSetForSplit) {
        // Arrange
        String input = dataSetForSplit[0];
        String expected = dataSetForSplit[1];

        // Act
        var result = CamelCaseSplitter.split(input);

        // Assert
        assertEquals(expected, result);
    }
}
