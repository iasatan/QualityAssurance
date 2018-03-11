package hu.iit.uni.miskolc.quality.assurance;

import hu.iit.uni.miskolc.quality.assurance.model.Point;
import hu.iit.uni.miskolc.quality.assurance.model.exception.NoPointsException;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

class Util {
    public static Point pointWithLowestAngle(List<Point> polygon) {
        Point bestPoint = polygon.get(0);
        double minAngle = Math.toDegrees(polygon.get(1).getAngle(polygon.get(0), polygon.get(2)));
        double tempAngle;
        for (int i = 2; i < polygon.size() - 1; i++) {
            tempAngle = Math.toDegrees(polygon.get(i).getAngle(polygon.get(i - 1), polygon.get(i + 1)));
            if (tempAngle < minAngle) {
                minAngle = tempAngle;
                bestPoint = polygon.get(i);
            }
        }
        return bestPoint;
    }

    public static List<Point> readCoordinatesFromFile(String filename) throws IOException, NoPointsException {
        List<Point> coordinates = new ArrayList<>();
        BufferedReader br = new BufferedReader(new FileReader(filename));
        Integer count = Integer.parseInt(br.readLine());
        String[] temp;
        for (int i = 1; i <= count; i++) {
            temp = br.readLine().split(" ");
            coordinates.add(new Point(Integer.parseInt(temp[0]), Integer.parseInt(temp[1])));
        }
        if (coordinates.size() == 0) {
            throw new NoPointsException("No points found in file");
        }
        return coordinates;
    }
}
