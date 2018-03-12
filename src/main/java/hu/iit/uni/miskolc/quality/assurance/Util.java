package hu.iit.uni.miskolc.quality.assurance;

import hu.iit.uni.miskolc.quality.assurance.model.Point;
import hu.iit.uni.miskolc.quality.assurance.model.exception.NoPointsException;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

class Util {

    /**
     * Reads the given file, and returns the list of points in that file
     * @param filename name of the file, or path to the file
     * @return List of points
     * @throws IOException when no file found
     * @throws NoPointsException when the file had no points
     */
    static List<Point> readCoordinatesFromFile(String filename) throws IOException, NoPointsException {
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
