package hu.iit.uni.miskolc.quality.assurance.model;

import java.util.List;

public class Point {

    final private int x;
    final private int y;

    public Point(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    /**
     * returns the point-leftpoint - point-rightpoint angle
     * @param left point left to this point
     * @param right point right to this point
     * @return angle in radian
     */
    double getAngle(Point left, Point right) {
        double leftAngle = Math.sqrt(Math.pow(x - left.x, 2) + Math.pow(y - left.y, 2));
        double rightAngle = Math.sqrt(Math.pow(x - right.x, 2) + Math.pow(y - right.y, 2));
        double angleLeftRight = Math.sqrt(Math.pow(right.x - left.x, 2) + Math.pow(right.y - left.y, 2));
        return Math.acos((rightAngle * rightAngle + leftAngle * leftAngle - angleLeftRight * angleLeftRight) / (2 * rightAngle * leftAngle));
    }

    @Override
    public String toString() {
        return x + " " + y;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Point point = (Point) o;

        if (x != point.x) return false;
        return y == point.y;
    }

    @Override
    public int hashCode() {
        int result = x;
        result = 31 * result + y;
        return result;
    }
    /**
     * min algorithm for points
     * @param polygon convex Point hull
     * @return the point with the smallest angle
     */
    public static Point pointWithLowestAngle(List<Point> polygon) {
        Point bestPoint = polygon.get(0);
        double minAngle = Math.toDegrees(polygon.get(1).getAngle(polygon.get(0), polygon.get(2)));
        double tempAngle;
        for (int i = 1; i < polygon.size() - 1; i++) {
            tempAngle = Math.toDegrees(polygon.get(i).getAngle(polygon.get(i - 1), polygon.get(i + 1)));
            if (tempAngle < minAngle) {
                minAngle = tempAngle;
                bestPoint = polygon.get(i);
            }
        }
        return bestPoint;
    }

}