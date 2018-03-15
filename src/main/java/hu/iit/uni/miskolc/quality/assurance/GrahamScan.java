package hu.iit.uni.miskolc.quality.assurance;

import hu.iit.uni.miskolc.quality.assurance.model.Point;
import hu.iit.uni.miskolc.quality.assurance.model.Turn;
import hu.iit.uni.miskolc.quality.assurance.model.exception.CollinearPointsException;

import java.util.*;

public class GrahamScan {
    /**
     * returns true if all points are collinear
     * @param points list of points
     * @return
     */
    private static void areAllCollinear(List<Point> points) throws CollinearPointsException {

        final Point a = points.get(0);
        final Point b = points.get(1);

        for (int i = 2; i < points.size(); i++) {

            Point c = points.get(i);

            if (getTurn(a, b, c) != Turn.COLLINEAR) {
                return;
            }
        }

        throw new CollinearPointsException("All points are collinear");
    }

    /**
     *  Returns the convex hull of the points created from the list
     * points. Note that the first and last point in the
     * returned List are the same
     * point.
     * @param points list of points
     * @return the convex hull of the points created from the point list
     * @throws CollinearPointsException when all points are collinear
     */
    public static List<Point> getConvexHull(List<Point> points) throws CollinearPointsException {

        List<Point> sorted = new ArrayList<>(getSortedPointSet(points));

        areAllCollinear(sorted);

        Stack<Point> stack = new Stack<>();
        stack.push(sorted.get(0));
        stack.push(sorted.get(1));

        for (int i = 2; i < sorted.size(); i++) {

            Point head = sorted.get(i);
            Point middle = stack.pop();
            Point tail = stack.peek();

            Turn turn = getTurn(tail, middle, head);

            switch (turn) {
                case COUNTER_CLOCKWISE:
                    stack.push(middle);
                    stack.push(head);
                    break;
                case CLOCKWISE:
                    i--;
                    break;
                case COLLINEAR:
                    stack.push(head);
                    break;
                default:
                    break;

            }
        }

        // close the hull
        stack.push(sorted.get(0));

        return new ArrayList<>(stack);
    }

    /**
     * Returns the points with the lowest y coordinate. In case more than 1 such
     * point exists, the one with the lowest x coordinate is returned.
     * @param points the list of points to return the lowest point from.
     * @return the points with the lowest y coordinate. In case more than
     *               1 such point exists, the one with the lowest x coordinate
     *               is returned
     */
    private static Point getLowestPoint(List<Point> points) {

        Point lowest = points.get(0);

        for (int i = 1; i < points.size(); i++) {

            Point temp = points.get(i);

            if (temp.getY() < lowest.getY() || (temp.getY() == lowest.getY() && temp.getX() < lowest.getX())) {
                lowest = temp;
            }
        }

        return lowest;
    }

    /**
     * Returns a sorted set of points from the list >points.
     * The set of points are sorted in increasing order of the angle they and the
     * lowest point make with the x-axis.
     * If tow (or more) points form the same angle towards the lowest point
     * the one closest to the lowest point comes first.
     * @param points the list of points to sort.
     * @return a sorted set of points from the list points.
     */
    private static Set<Point> getSortedPointSet(List<Point> points) {

        final Point lowest = getLowestPoint(points);

        TreeSet<Point> set = new TreeSet<>(new Comparator<Point>() {
            @Override
            public int compare(Point a, Point b) {

                if (a.equals(b)) {
                    return 0;
                }

                // use longs to guard against int-underflow
                double thetaA = Math.atan2((long) a.getY() - lowest.getY(), (long) a.getX() - lowest.getX());
                double thetaB = Math.atan2((long) b.getY() - lowest.getY(), (long) b.getX() - lowest.getX());

                if (thetaA < thetaB) {
                    return -1;
                } else if (thetaA > thetaB) {
                    return 1;
                } else {
                    // collinear with the 'lowest' point, let the point closest to it come first

                    // use longs to guard against int-over/underflow
                    double distanceA = Math.sqrt((((long) lowest.getX() - a.getX()) * ((long) lowest.getX() - a.getX())) +
                            (((long) lowest.getY() - a.getY()) * ((long) lowest.getY() - a.getY())));
                    double distanceB = Math.sqrt((((long) lowest.getX() - b.getX()) * ((long) lowest.getX() - b.getX())) +
                            (((long) lowest.getY() - b.getY()) * ((long) lowest.getY() - b.getY())));

                    if (distanceA < distanceB) {
                        return -1;
                    } else {
                        return 1;
                    }
                }
            }
        });

        set.addAll(points);

        return set;
    }
    /**
     * Returns the GrahamScan#Turn formed by traversing through the
     * ordered points a, b and c.
     * More specifically, the cross product C between the
     * 3 points (vectors) is calculated:
     *
     * (b.x-a.x * c.y-a.y) - (b.y-a.y * c.x-a.x)
     *
     * and if C is less than 0, the turn is CLOCKWISE, if
     * C is more than 0, the turn is COUNTER_CLOCKWISE, else
     * the three points are COLLINEAR.
     *
     * @param a the starting point.
     * @param b the second point.
     * @param c the end point.
     * @return the GrahamScan#Turn formed by traversing through the
     *         ordered points a, b and c.
     */
    private static Turn getTurn(Point a, Point b, Point c) {

        // longs used to guard against integer over/underflow
        long crossProduct = (((long) b.getX() - a.getX()) * ((long) c.getY() - a.getY())) -
                (((long) b.getY() - a.getY()) * ((long) c.getX() - a.getX()));

        if (crossProduct > 0) {
            return Turn.COUNTER_CLOCKWISE;
        } else if (crossProduct < 0) {
            return Turn.CLOCKWISE;
        } else {
            return Turn.COLLINEAR;
        }
    }
}
