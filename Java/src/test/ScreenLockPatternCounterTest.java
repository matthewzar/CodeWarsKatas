package test;

import com.company.Kyu3.ScreenLockPatternCounter;
import com.company.Kyu7.StringErrorChecker;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.junit.jupiter.params.provider.ValueSource;

import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

public class ScreenLockPatternCounterTest {
    @ParameterizedTest
    @ValueSource(ints = {-2, 0, 10, 100})
    public void GivenOutOfBoundsLength_ShouldReturnZero(int length)
    {
        // Arrange
        ScreenLockPatternCounter sut = new ScreenLockPatternCounter();

        // Act
        int result = sut.CountPatterns('A', length);

        // Assert
        assertEquals(0, result);
    }

    @ParameterizedTest
    @ValueSource(ints = {1, 2, 4, 9})
    public void GivenInBoundsLength_ShouldNotReturnZero(int length)
    {
        // Arrange
        ScreenLockPatternCounter sut = new ScreenLockPatternCounter();

        // Act
        int result = sut.CountPatterns('A', length);

        // Assert
        assertNotEquals(0, result, "We don't know what this number should be... but it's greater than zero");
    }


    public static Stream<Arguments> dataForLength2Tests() {
        return Stream.of(
                Arguments.of((Object)  new LockData('A', 2, 5)),
                Arguments.of((Object)  new LockData('B', 2, 7)),
                Arguments.of((Object)  new LockData('C', 2, 5)),
                Arguments.of((Object)  new LockData('D', 2, 7)),
                Arguments.of((Object)  new LockData('E', 2, 8)),
                Arguments.of((Object)  new LockData('F', 2, 7)),
                Arguments.of((Object)  new LockData('G', 2, 5)),
                Arguments.of((Object)  new LockData('H', 2, 7)),
                Arguments.of((Object)  new LockData('I', 2, 5))
        );
    }

    @ParameterizedTest
    @MethodSource("dataForLength2Tests")
    public void GivenLengthTwoTargets_ShouldReturnFewPossibilities(LockData data)
    {
        // Arrange
        ScreenLockPatternCounter sut = new ScreenLockPatternCounter();

        // Act
        int result = sut.CountPatterns(data.Start, data.Length);

        // Assert
        assertEquals(data.TotalPatterns, result,
                "Start: '%c'. Len: %d. Expected patterns: %d".formatted(data.Start, data.Length, data.TotalPatterns));
    }

    public static Stream<Arguments> dataForLength3Tests() {
        return Stream.of(
                Arguments.of((Object)  new LockData('A', 3, 31)),
                Arguments.of((Object)  new LockData('C', 3, 31)),
                Arguments.of((Object)  new LockData('G', 3, 31)),
                Arguments.of((Object)  new LockData('I', 3, 31)),
                Arguments.of((Object)  new LockData('B', 3, 37)),
                Arguments.of((Object)  new LockData('D', 3, 37)),
                Arguments.of((Object)  new LockData('F', 3, 37)),
                Arguments.of((Object)  new LockData('H', 3, 37)),
                Arguments.of((Object)  new LockData('E', 3, 4*7+4*5))
        );
    }

    @ParameterizedTest
    @MethodSource("dataForLength3Tests")
    public void GivenLengthThreeTargets_ShouldReturnAtLeast20Possibilities(LockData data)
    {
        // Arrange
        ScreenLockPatternCounter sut = new ScreenLockPatternCounter();

        // Act
        int result = sut.CountPatterns(data.Start, data.Length);

        // Assert
        assertEquals(data.TotalPatterns, result,
                "Start: '%c'. Len: %d. Expected patterns: %d".formatted(data.Start, data.Length, data.TotalPatterns));
    }

    private static class LockData{
        public final int TotalPatterns;
        public final int Length;
        public final char Start;

        public LockData(char start, int length, int totalPatterns)
        {
            this.Start = start;
            this.Length = length;
            this.TotalPatterns = totalPatterns;
        }
    }
}