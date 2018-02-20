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
			angle += Math.atan2(y - coordinate.y, x - coordinate.x) * 180 / Math.PI;
		}
		System.out.println(angle);
		if (angle <= 361 && angle >= 359)
			return true;
		return false;
	}

	public boolean contains(List<Coordinate> polygon) {
		int crossings = 0;
		for (int i = 0; i < polygon.size(); i++) {
			int j = i + 1;
			boolean cond1 = (polygon.get(i).y <= y) && (y < polygon.get(j).y);
			boolean cond2 = (polygon.get(j).y <= y) && (y < polygon.get(i).y);
			if (cond1 || cond2) {
				// need to cast to double
				if (x < (polygon.get(j).x - polygon.get(i).x) * (y - polygon.get(i).y)
						/ (polygon.get(j).y - polygon.get(i).y) + polygon.get(i).x)
					crossings++;
			}
		}
		if (crossings % 2 == 1)
			return true;
		else
			return false;
	}

	@Override
	public String toString() {
		return "Coordinate [id=" + id + ", x=" + x + ", y=" + y + "]";
	}

}
