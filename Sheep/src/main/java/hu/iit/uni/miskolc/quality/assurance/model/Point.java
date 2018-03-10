package hu.iit.uni.miskolc.quality.assurance.model;

public class Point {

    private int id;
    private int x;
    private int y;

    public Point(int id, int x, int y) {
        this.id = id;
        this.x = x;
        this.y = y;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    public double getAngle(Point left, Point right) {
        double leftAngle = Math.sqrt(Math.pow(x - left.x, 2) + Math.pow(y - left.y, 2));
        double rightAngle = Math.sqrt(Math.pow(x - right.x, 2) + Math.pow(y - right.y, 2));
        double angleLeftRigt = Math.sqrt(Math.pow(right.x - left.x, 2) + Math.pow(right.y - left.y, 2));
        return Math.acos((rightAngle * rightAngle + leftAngle * leftAngle - angleLeftRigt * angleLeftRigt) / (2 * rightAngle * leftAngle));
    }

    @Override
    public String toString() {
        return x + " " + y;
    }

}