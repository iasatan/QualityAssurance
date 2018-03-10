package hu.iit.uni.miskolc.quality.assurance;

import hu.iit.uni.miskolc.quality.assurance.model.Point;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class OptimalSheepFinder {

    public static void main(String[] args) {
        List<Point> coordinates = readCoordinatesFromFile("A/A10.in");
        List<Point> polygon;
        // No need to coontinue if no coordinates found
        if (coordinates.isEmpty()) {
            System.out.println("no sheep found");
            return;
        }
        // If there are less than 3 points, doesn't matter which we choose
        if (coordinates.size() < 3) {
            System.out.println(coordinates.get(0));
            return;
        }
        polygon=GrahamScan.getConvexHull(coordinates);

        System.out.println("asd");
        for (Point coordinate : polygon) {
            System.out.println(coordinate);
        }
        System.out.println(polygon.size());
    }

    private static List<Point> readCoordinatesFromFile(String filename) {
        List<Point> coordinates = new ArrayList<>();
        try {
            BufferedReader br = new BufferedReader(new FileReader(filename));
            Integer count = Integer.parseInt(br.readLine());
            System.out.println(count);
            String[] temp;
            for (int i = 1; i <= count; i++) {
                temp = br.readLine().split(" ");
                coordinates.add(new Point(i, Integer.parseInt(temp[0]), Integer.parseInt(temp[1])));
            }
        } catch (Exception e) {
        }
        return coordinates;
    }
}
