package hu.iit.uni.miskolc.quality.assurance;

import hu.iit.uni.miskolc.quality.assurance.model.Point;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

public class UtilTest {
    private List<Point> points;

    @Before
    public void setUp() {
        points = new ArrayList<>();
        points.add(new Point(0, 1));
        points.add(new Point(1, 2));
        points.add(new Point(5, 12));
        points.add(new Point(78, 23));
        points.add(new Point(54, 12));
        points.add(new Point(0, 1));
    }

    @Test
    public void pointWithLowestAngle() {
        Assert.assertNotNull(Util.pointWithLowestAngle(points));
        Assert.assertEquals(new Point(78, 23), Util.pointWithLowestAngle(points));
    }
}