package com.company.Kyu2;

import java.util.*;

public class Line {
    // Useful for preventing division by zero, and other meaningless queries.
    private boolean isVertical;
    private boolean isHorizontal;

    /**
     * If the line is flat, you need to track it's x-axis distance
     */
    private double horizontalHeight;

    /**
     * 'C' in the equation y=mx+c
     */
    private final double intercept;

    /**
     * M in the equation y=mc+c
     */
    private final double slope;

    public Line(Point a, Point b) {
        if (a.y == b.y) {
            intercept = a.x;
            slope = Double.POSITIVE_INFINITY;
            isVertical = true;
            return;
        }

        if (a.x == b.x) {
            intercept = Double.NaN;
            slope = 0;
            horizontalHeight = a.x;
            isHorizontal = true;
            return;
        }

        // y = Mx + c ... find M
        slope = (a.y - b.y) / (a.x - b.x);
        intercept = a.y - slope*a.x;
    }

    public Line(double intercept, double slope, double horizontalHeight){
        this.slope = slope;
        this.intercept = intercept;

        if(slope == 0){
            isHorizontal = true;
            this.horizontalHeight = horizontalHeight;
        }
        if(intercept == Double.NaN){
            isVertical = true;
        }
    }

    public double solveForY(double x){
        return slope*x+intercept;
    }

    public double solveForX(double y){
        return (y-intercept)/slope;
    }

    public Line getPerpendicularLine(Point intersectionPoint)
    {
        //Check for division-by-zero-type lines.
        if (isVertical)
        {
            return new Line(Double.NaN, 0, intersectionPoint.y);
        }
        if (isHorizontal){
            return new Line(intersectionPoint.x, Double.POSITIVE_INFINITY, 0);
        }

        // Invert gradient and solve for C
        double perpendicularSlope = -1 / slope;
        double perpendicularIntercept = intersectionPoint.y-perpendicularSlope*intersectionPoint.x;
        return new Line(perpendicularIntercept, perpendicularSlope, 0);
    }

    public boolean isPointOnLine(Point point){
        if (isVertical) return point.y==intercept;
        if (isHorizontal) return point.x==horizontalHeight;

        return solveForX(point.y) == point.x;
    }

    public boolean isPointAboveLine(Point point){
        return point.y > solveForY(point.x);
    }

    public boolean isPointBelowLine(Point point){
        return point.y < solveForY(point.x);
    }
}
