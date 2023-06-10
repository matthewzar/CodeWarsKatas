package com.company.Kyu2;


public class VoronoiGraph {
    public double[] voronoi_areas(Point[] p)
    {
        return new double[0];
    }

    public Point getMidpoint(Point pointA, Point pointB){
//        double xMid = Math.max(pointA.x, pointB.x) - Math.min(pointA.x, pointB.x);
//        double yMid = Math.max(pointA.y, pointB.y) - Math.min(pointA.y, pointB.y);
        double xMid = (pointA.x + pointB.x)/2;
        double yMid = (pointA.y + pointB.y)/2;
        return new Point(xMid, yMid);
    }


    /**
     * The line segments of the Voronoi diagram are all the points in the plane that are equidistant to the
     * two nearest sites.
     * @param a
     * @param b
     * @param c
     * @return The point equidistant to a, b, and c
     */
    public Point getEquidistantTriple(Point a, Point b, Point c) {
        // TODO - cater to perfectly horizontal/vertical lines (they result infinite gradients)
        // The only time 2 lines will cause NaNs is right-angled triangles. Otherwise just take the 2 'normal' edges

        // y = Mx + c ... find M
        double abSlope = (a.y - b.y) / (a.x - b.x);
        // Invert gradient
        double abPerpendicularSlope = -1 / abSlope;
        // Midpoint has x and y
        Point abMidpoint = getMidpoint(a, b);
        // C = y - mc
        double abPerpC = abMidpoint.y - abPerpendicularSlope * abMidpoint.x;


        // Find second (bc) perpendicular equation
        double bcSlope = (b.y - c.y) / (b.x - c.x);
        double bcPerpendicularSlope = -1 / bcSlope;
        Point bcMidpoint = getMidpoint(b, c);
        double bcPerpC = bcMidpoint.y - bcPerpendicularSlope * bcMidpoint.x;

        // Solve for interception of perpendicular lines.
        double xFinal = (bcPerpC - abPerpC) / (abPerpendicularSlope - bcPerpendicularSlope);
        // y = mx + c
        double yFinal = abPerpendicularSlope * xFinal + abPerpC;
        return new Point(xFinal, yFinal);
    }
}
