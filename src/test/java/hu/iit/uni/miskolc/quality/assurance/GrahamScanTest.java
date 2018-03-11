package hu.iit.uni.miskolc.quality.assurance;

import hu.iit.uni.miskolc.quality.assurance.model.Point;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

public class GrahamScanTest {
    private List<Point> points;

    @Before
    public void setUp() throws Exception {
        points = new ArrayList<>();
        points.add(new Point(0, 1));
        points.add(new Point(1, 2));
        points.add(new Point(5, 12));
        points.add(new Point(78, 23));
        points.add(new Point(54, 12));
    }

    @Test
    public void getConvexHull() {
        Assert.assertNotNull(GrahamScan.getConvexHull(points));
        Assert.assertEquals(GrahamScan.getConvexHull(points).toString(), "[0 1, 54 12, 78 23, 5 12, 0 1]");
    }
}