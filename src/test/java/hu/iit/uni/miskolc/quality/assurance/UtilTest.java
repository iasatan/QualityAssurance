package hu.iit.uni.miskolc.quality.assurance;

import hu.iit.uni.miskolc.quality.assurance.model.Point;
import hu.iit.uni.miskolc.quality.assurance.model.exception.NoPointsException;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;
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

    @Test
    public void fileReaderTest() throws IOException, NoPointsException {
        List<Point> points = Util.readCoordinatesFromFile(this.getClass().getResource("/A/A2.in").getPath());
        Assert.assertNotNull(points);
        Assert.assertEquals(points.toString(), "[43 7562, 44 8499, 152 1455, 154 448, 175 6110, 187 2007, 405 3453, 430 3181, 441 580, 452 7711, 453 1039, 506 4995, 725 5331, 956 2903, 1098 9135, 1194 9680, 1257 2334, 1307 3504, 1409 2906, 1438 5752, 1482 2110, 1517 9112, 1599 1700, 1795 4636, 1829 9670, 1850 9560, 1866 2330, 1875 3419, 1913 2996, 1945 9328, 1990 4160, 2016 1594, 2136 422, 2255 8795, 2319 6078, 2321 3118, 2356 6445, 2425 5360, 2517 5361, 2519 8909, 2551 5001, 2601 9234, 2643 8271, 2661 5971, 2780 935, 2820 6372, 2867 4476, 2919 5651, 2923 9000, 3045 5000, 3057 9608, 3183 5067, 3269 9033, 3284 5935, 3285 453, 3331 4639, 3425 920, 3499 2186, 3600 9014, 3652 7274, 3654 8381, 3968 6251, 3990 5806, 4044 9331, 4085 7432, 4117 3636, 4127 4408, 4144 6740, 4175 6599, 4285 2325, 4379 5619, 4612 4944, 4617 4065, 4807 329, 4810 7201, 4852 1993, 4897 147, 4898 5317, 5037 606, 5062 2078, 5092 7884, 5419 2462, 5508 2183, 5582 3489, 5695 9949, 5807 9595, 5890 3534, 5927 8079, 5965 4588, 6041 2083, 6065 3861, 6223 5885, 6236 4879, 6244 3989, 6314 1423, 6355 3564, 6384 7084, 6406 4892, 6441 912, 6505 9759, 6529 3618, 6538 142, 6871 2416, 6911 8684, 6912 4030, 6987 609, 7035 4115, 7099 9827, 7125 5956, 7165 3211, 7290 5187, 7379 3164, 7474 2153, 7522 7172, 7836 5123, 8185 3912, 8293 7715, 8389 9749, 8538 5634, 8636 7101, 8738 6448, 8749 1484, 8829 5982, 8866 9540, 8882 9994, 8955 6983, 8993 2388, 9107 5872, 9127 4668, 9131 770, 9182 7873, 9212 5774, 9261 1744, 9441 8401, 9465 9825, 9508 5359, 9616 3725, 9650 9176, 9708 6508, 9721 4937, 9773 2430, 9826 1495, 9972 8388, 9982 7117]");
    }

    @Test(expected = NoPointsException.class)
    public void noFileFileReaderTest() throws IOException, NoPointsException {
        Util.readCoordinatesFromFile(this.getClass().getResource("/A/A0.in").getPath());
    }
}