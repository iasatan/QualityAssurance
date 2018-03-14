package hu.iit.uni.miskolc.quality.assurance;

import hu.iit.uni.miskolc.quality.assurance.model.Point;
import hu.iit.uni.miskolc.quality.assurance.model.exception.CollinearPointsException;
import hu.iit.uni.miskolc.quality.assurance.model.exception.NoPointsException;
import org.junit.Assert;
import org.junit.Test;

import java.io.IOException;
import java.util.List;


public class OptimalSheepFinderTest {

    private List<Point> points;
    private List<Point> polygon;

    @Test
    public void a8BestSheep() throws IOException, NoPointsException, CollinearPointsException {
        points = Util.readCoordinatesFromFile("src/main/resources/A/A8.in");
        polygon = GrahamScan.getConvexHull(points);

        Point bestSheep = Point.pointWithLowestAngle(polygon);
        System.out.println(bestSheep);
        Assert.assertEquals(bestSheep, new Point(20, 15));
    }

    @Test
    public void a9BestSheep() throws IOException, NoPointsException, CollinearPointsException {
        points = Util.readCoordinatesFromFile("src/main/resources/A/A9.in");
        polygon = GrahamScan.getConvexHull(points);

        Point bestSheep = Point.pointWithLowestAngle(polygon);
        System.out.println(bestSheep);
        Assert.assertEquals(bestSheep, new Point(6, 10000));
    }
    @Test
    public void a10BestSheep() throws IOException, NoPointsException, CollinearPointsException {
        points = Util.readCoordinatesFromFile("src/main/resources/A/A10.in");
        polygon = GrahamScan.getConvexHull(points);

        Point bestSheep = Point.pointWithLowestAngle(polygon);
        System.out.println(bestSheep);
        Assert.assertEquals(bestSheep, new Point(9128, 2179));
    }
}