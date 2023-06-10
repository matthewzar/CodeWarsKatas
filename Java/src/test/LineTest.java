package test;

import com.company.Kyu2.Line;
import com.company.Kyu2.Point;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class LineTest {

    @Test
    void solveForY() {
    }

    @Test
    void solveForX() {
    }

    @Test
    void getPerpendicularLine() {
    }

    @Test
    void isPointOnLine() {
        var line = new Line(1, 2, 0);
        var point = new Point(3, 2*3+1);

        var result = line.isPointOnLine(point);

        assertTrue(result);
    }

    @Test
    void isPointAboveLine() {
        var line = new Line(0, 1, 0);
        var point = new Point(2, 4);

        var result = line.isPointAboveLine(point);

        assertTrue(result);
    }

    @Test
    void isPointBelowLine() {
        var line = new Line(0, 1, 0);
        var point = new Point(1, -1);

        var result = line.isPointBelowLine(point);

        assertTrue(result);
    }
}