package test;

import com.company.Kyu3.ArraySizeAnalyzer;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.Arrays;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class ArraySizeAnalyzerTest {
    public static Stream<Arguments> dataSetOneElementArray() {
        return Stream.of(
                Arguments.of((Object) new int[][]{new int[]{0}, new int[]{0}}),
                Arguments.of((Object) new int[][]{new int[]{4}, new int[]{0}}),
                Arguments.of((Object) new int[][]{new int[]{-2}, new int[]{0}})
        );
    }

    @ParameterizedTest
    @MethodSource("dataSetOneElementArray")
    void GivenSingleElementArray_ShouldAlwaysReturnZeroArray(int[][] dataSet) {
        // Arrange
        var sut = new ArraySizeAnalyzer();
        int[] input = dataSet[0];
        int[] expected = dataSet[1];

        // Act
        var result = sut.countSmaller(input);

        // Assert
        assertArrayEquals(expected, result);
    }


    public static Stream<Arguments> dataSetTwoElementArray() {
        return Stream.of(
                Arguments.of((Object) new int[][]{new int[]{1, 1}, new int[]{0, 0}}),
                Arguments.of((Object) new int[][]{new int[]{5, 4}, new int[]{1, 0}}),
                Arguments.of((Object) new int[][]{new int[]{2, 0}, new int[]{1, 0}})
        );
    }

    @ParameterizedTest
    @MethodSource("dataSetTwoElementArray")
    void GivenDualElementArray_ShouldEndWithZero(int[][] dataSet) {
        // Arrange
        var sut = new ArraySizeAnalyzer();
        int[] input = dataSet[0];
        int[] expected = dataSet[1];

        // Act
        int[] result = sut.countSmaller(input);

        // Assert
        assertArrayEquals(expected, result);
    }


    public static Stream<Arguments> dataSetMediumSizedArray() {
        return Stream.of(
                Arguments.of((Object) new int[][]{new int[]{5, 4, 3, 2, 1}, new int[]{4, 3, 2, 1, 0}}),
                Arguments.of((Object) new int[][]{new int[]{1, 2, 3}, new int[]{0, 0, 0}}),
                Arguments.of((Object) new int[][]{new int[]{3, 2, 1}, new int[]{2, 1, 0}}),
                Arguments.of((Object) new int[][]{new int[]{1, 2, 0}, new int[]{1, 1, 0}}),
                Arguments.of((Object) new int[][]{new int[]{1, 2, 1}, new int[]{0, 1, 0}}),
                Arguments.of((Object) new int[][]{new int[]{1, 1, -1, 0, 0}, new int[]{3, 3, 0, 0, 0}})
        );
    }

    @ParameterizedTest
    @MethodSource("dataSetMediumSizedArray")
    void GivenMultiElementArray_ShouldCountLessThans(int[][] dataSet) {
        // Arrange
        var sut = new ArraySizeAnalyzer();
        int[] input = dataSet[0];
        int[] expected = dataSet[1];

        // Act
        int[] result = sut.countSmaller(input);

        // Assert
        assertArrayEquals(expected, result);
    }


    // Alternative tests for alternative (efficient) solution
    public static Stream<Arguments> altDataSet() {
        return Stream.of(
                Arguments.of((Object) new int[][]{new int[]{5, 4, 3, 2, 1}, new int[]{4, 3, 2, 1, 0}}),
                Arguments.of((Object) new int[][]{new int[]{1, 2, 3}, new int[]{0, 0, 0}}),
                Arguments.of((Object) new int[][]{new int[]{3, 2, 1}, new int[]{2, 1, 0}}),
                Arguments.of((Object) new int[][]{new int[]{1, 2, 0}, new int[]{1, 1, 0}}),
                Arguments.of((Object) new int[][]{new int[]{1, 2, 1}, new int[]{0, 1, 0}}),
                Arguments.of((Object) new int[][]{new int[]{1, 1, -1, 0, 0}, new int[]{3, 3, 0, 0, 0}}),
                Arguments.of((Object) new int[][]{new int[]{2, 0}, new int[]{1, 0}})
        );
    }

    @ParameterizedTest
    @MethodSource("altDataSet")
    void GivenArray_ShouldCountLessThans(int[][] dataSet) {
        // Arrange
        int[] input = dataSet[0];
        int[] expected = dataSet[1];

        // Act
        int[] result = ArraySizeAnalyzer.smaller(input);

        // Assert
        assertArrayEquals(expected, result);
    }
}
