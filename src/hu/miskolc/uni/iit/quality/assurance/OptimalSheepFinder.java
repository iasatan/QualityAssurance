package hu.miskolc.uni.iit.quality.assurance;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Collection;

import hu.miskolc.uni.iit.quality.assurance.model.Coordinate;
import javafx.print.Collation;

public class OptimalSheepFinder {

	public static void main(String[] args) {
		Collection<Coordinate> coordinates = readCoordinatesFromFile("coordinates.txt");
		for (Coordinate coordinate : coordinates) {
			System.out.println(coordinate.toString());
		}
	}
	
	private static Collection<Coordinate> readCoordinatesFromFile(String filename){
		Collection<Coordinate> coordinates = new ArrayList<>();
		try {
			BufferedReader br = new BufferedReader(new FileReader(filename));
			Long count = Long.parseLong(br.readLine());
			String[] temp;
			for (int i = 1; i <= count; i++) {
				temp =br.readLine().split(" ");
				coordinates.add(new Coordinate(i, Integer.parseInt(temp[0]), Integer.parseInt(temp[1])));
			}
		} catch (Exception e) {
			// TODO: handle exception
		}
		return coordinates;
	}

}
