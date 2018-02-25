package hu.miskolc.uni.iit.quality.assurance.model;

import java.util.Collection;
import java.util.List;

public class Coordinate {
	private int id;
	private int x;
	private int y;

	public Coordinate(int id, int x, int y) {
		this.id = id;
		this.x = x;
		this.y = y;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getX() {
		return x;
	}

	public void setX(int x) {
		this.x = x;
	}

	public int getY() {
		return y;
	}

	public void setY(int y) {
		this.y = y;
	}

	public boolean insidePolygon(Collection<Coordinate> polygon) {
		double angle = 0.0;
		for (Coordinate coordinate : polygon) {
			double tempAngle;
			if(coordinate.y<y && coordinate.x < x){
				tempAngle =Math.abs(Math.toDegrees(Math.atan2(y - coordinate.y, x - coordinate.x)));
				
			}
			else
				tempAngle =Math.abs(Math.toDegrees(Math.atan2(coordinate.y - y, coordinate.x - x)));
			//double tempAngle =Math.toDegrees(Math.atan2(y - coordinate.y, x - coordinate.x));
			
			/*if(tempAngle<0)
				tempAngle+=180;*/
			System.out.println(tempAngle);
			angle += tempAngle;
			//angle += Math.atan2(y - coordinate.y, x - coordinate.x) * 180 / Math.PI;
		}
		System.out.println(angle);
		if (angle <= 361 && angle >= 359)
			return true;
		return false;
	}

	@Override
	public String toString() {
		return "Coordinate [id=" + id + ", x=" + x + ", y=" + y + "]";
	}

}
