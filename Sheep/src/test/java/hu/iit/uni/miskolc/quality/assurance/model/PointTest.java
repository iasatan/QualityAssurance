package hu.iit.uni.miskolc.quality.assurance.model;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class PointTest {
    Point point;
    Point pointLeft;
    Point pointRight;

    @Before
    public void setUp() throws Exception {
        point = new Point(5, 4);
        pointLeft = new Point(1, 2);
        pointRight = new Point(10, 8);
    }

    @Test
    public void getX() {
        Assert.assertEquals(point.getX(), 5);
        Assert.assertEquals(pointLeft.getX(), 1);
        Assert.assertEquals(pointRight.getX(), 10);
    }

    @Test
    public void getY() {
        Assert.assertEquals(point.getY(), 4);
        Assert.assertEquals(pointLeft.getY(), 2);
        Assert.assertEquals(pointRight.getY(), 8);
    }

    @Test
    public void getAngle() {
        double angle = point.getAngle(pointLeft, pointRight);
        Assert.assertEquals(angle, 2.9304, 0.01);
    }

    @Test
    public void toStringTest() {
        Assert.assertEquals(point.toString(), "5 4");
    }
}