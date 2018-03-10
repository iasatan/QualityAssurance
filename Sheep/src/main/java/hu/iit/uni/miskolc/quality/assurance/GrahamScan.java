package hu.iit.uni.miskolc.quality.assurance;

import hu.iit.uni.miskolc.quality.assurance.model.Point;
import hu.iit.uni.miskolc.quality.assurance.model.Turn;

import java.util.*;

public class GrahamScan {
    private static boolean areAllCollinear(List<Point> points) {

        final Point a = points.get(0);
        final Point b = points.get(1);

        for (int i = 2; i < points.size(); i++) {

            Point c = points.get(i);

            if (getTurn(a, b, c) != Turn.COLLINEAR) {
                return false;
            }
        }

        return true;
    }

    public static List<Point> getConvexHull(List<Point> points) throws IllegalArgumentException {

        List<Point> sorted = new ArrayList<>(getSortedPointSet(points));

        if (areAllCollinear(sorted)) {
            throw new IllegalArgumentException("cannot create a convex hull from collinear points");
        }

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
            }
        }

        // close the hull
        stack.push(sorted.get(0));

        return new ArrayList<>(stack);
    }

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

    private static Set<Point> getSortedPointSet(List<Point> points) {

        final Point lowest = getLowestPoint(points);

        TreeSet<Point> set = new TreeSet<>(new Comparator<Point>() {
            @Override
            public int compare(Point a, Point b) {

                if (a == b || a.equals(b)) {
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
