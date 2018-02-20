package hu.miskolc.uni.iit.quality.assurance;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import hu.miskolc.uni.iit.quality.assurance.model.Coordinate;
import javafx.print.Collation;

public class OptimalSheepFinder {

	public static void main(String[] args) {
		List<Coordinate> coordinates = readCoordinatesFromFile("coordinates.txt");
		List<Coordinate> polygon = new ArrayList<>();
		//No need to coontinue if no coordinates found
		if(coordinates.isEmpty()) {
			System.out.println("no sheep found");
			return;
		}
		//If there are less than 3 points, doesn't matter which we choose
		if(coordinates.size()<3) {
			System.out.println(coordinates.get(0));
			return;
		}
		//initial polygon
		for (int i = 0; i <= 3; i++) {
			polygon.add(coordinates.get(i));
		}
		for (int i = 4; i < coordinates.size(); i++) {
			if(!coordinates.get(i).contains(polygon)/*insidePolygon(polygon)*/) {
				System.out.println(coordinates.get(i));
				polygon.add(coordinates.get(i));
			}
		}
		System.out.println("asd");
		for (Coordinate coordinate : polygon) {
			System.out.println(coordinate);
		}
	}
	
	private static List<Coordinate> readCoordinatesFromFile(String filename){
		List<Coordinate> coordinates = new ArrayList<>();
		try {
			BufferedReader br = new BufferedReader(new FileReader(filename));
			Integer count = Integer.parseInt(br.readLine());
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
	/*TODO: algoritmus: megkeresni a külső pontokat, a többit ki lehet dobni
	 * első három pont alkot egy háromszöget
	 * következő pont ha benne van a háromszögben törlődik,
	 * ha nincs akkor új pontként felvesszük és négyszögre vizsgáljuk a következőt stb
	 * 
	 * Compute the oriented sum of angles between the point p and each of the polygon apices. If the total oriented angle is 360 degrees, the point is inside. If the total is 0, the point is outside.
	 * I like this method better because it is more robust and less dependent on numerical precision.
	 * Methods that compute evenness of number of intersections are limited because you can 'hit' an apex during the computation of the number of intersections.
	*/
}
