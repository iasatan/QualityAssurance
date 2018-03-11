package hu.iit.uni.miskolc.quality.assurance;

import hu.iit.uni.miskolc.quality.assurance.model.Point;
import hu.iit.uni.miskolc.quality.assurance.model.exception.NoPointsException;

import java.io.IOException;
import java.util.List;

class OptimalSheepFinder {

    public static void main(String[] args) throws IOException, NoPointsException {
        List<Point> coordinates = Util.readCoordinatesFromFile("src/main/resources/A/A9.in");
        List<Point> polygon;

        polygon = GrahamScan.getConvexHull(coordinates);

        System.out.println(Util.pointWithLowestAngle(polygon));
    }


}
