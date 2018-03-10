package hu.iit.uni.miskolc.quality.assurance;

import hu.iit.uni.miskolc.quality.assurance.model.Point;

import java.io.BufferedReader;
import java.io.FileReader;
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

    public static List<Point> readCoordinatesFromFile(String filename) {
        List<Point> coordinates = new ArrayList<>();
        try {
            BufferedReader br = new BufferedReader(new FileReader(filename));
            Integer count = Integer.parseInt(br.readLine());
            String[] temp;
            for (int i = 1; i <= count; i++) {
                temp = br.readLine().split(" ");
                coordinates.add(new Point(Integer.parseInt(temp[0]), Integer.parseInt(temp[1])));
            }
        } catch (Exception e) {
            System.out.println("something went wrong with reading the file");
        }
        return coordinates;
    }
}
