package test;

import com.company.Kyu2.Point;
import com.company.Kyu2.VoronoiGraph;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class VoronoiGraphTest {

    @Test
    public void GivenThreePoints_ShouldGiveOrderAgnositAnswer()
    {
        // Arrange
        var sut = new VoronoiGraph();
        var pointA = new Point(0,0);
        var pointB = new Point(1.732,1.732);
        var pointC = new Point(-1.732,1.732);

        // Act
        var result1 = sut.getEquidistantTriple(pointA, pointB, pointC);
        var result2 = sut.getEquidistantTriple(pointC, pointB, pointA);
        var result3 = sut.getEquidistantTriple(pointB, pointB, pointC);

        // Assert
        assertEquals(0, result1.x);
        assertEquals(1.732/2, result1.y);

//        assertEquals(0.5, result2.x);
//        assertEquals(0.5, result2.y);
//
//        assertEquals(0.5, result3.x);
//        assertEquals(0.5, result3.y);
    }

    @Test
    public void GivenThreeMorePoints_ShouldGiveOrderAgnositAnswer()
    {
        // Arrange
        var sut = new VoronoiGraph();
        var pointA = new Point(5,5);
        var pointB = new Point(10,2);
        var pointC = new Point(-2,-3);

        // Act
        var result1 = sut.getEquidistantTriple(pointA, pointB, pointC);
        var result2 = sut.getEquidistantTriple(pointC, pointB, pointA);
        var result3 = sut.getEquidistantTriple(pointB, pointB, pointC);

        // Assert
        assertEquals(4.450, result1.x, 0.001);
        assertEquals(-1.581, result1.y, 0.001);

//        assertEquals(0.5, result2.x);
//        assertEquals(0.5, result2.y);
//
//        assertEquals(0.5, result3.x);
//        assertEquals(0.5, result3.y);
    }
}