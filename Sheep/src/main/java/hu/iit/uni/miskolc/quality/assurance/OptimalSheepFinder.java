package hu.iit.uni.miskolc.quality.assurance;

import hu.iit.uni.miskolc.quality.assurance.model.Point;
import hu.iit.uni.miskolc.quality.assurance.model.exception.NoPointsException;

import java.io.IOException;
import java.util.List;

class OptimalSheepFinder {

    public static void main(String[] args) throws IOException, NoPointsException {
        List<Point> coordinates = Util.readCoordinatesFromFile("A/A9.in");
        List<Point> polygon;
        // If there are less than 3 points, doesn't matter which we choose
        if (coordinates.size() < 3) {
            System.out.println(coordinates.get(0));
            return;
        }

        polygon = GrahamScan.getConvexHull(coordinates);

        System.out.println(Util.pointWithLowestAngle(polygon));
    }


}
