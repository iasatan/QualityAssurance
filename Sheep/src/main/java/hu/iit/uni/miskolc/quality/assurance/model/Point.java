package hu.iit.uni.miskolc.quality.assurance.model;

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

    public double getAngle(Point left, Point right) {
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
}