package ksenia.ta_java.sandbox;

import org.testng.Assert;
import org.testng.annotations.Test;

/**
 * Created by admin on 07.05.2018.
 */
public class PointTests {
  @Test
  public void testDistance() {
    Point p1 = new Point(0, 0);
    Point p2 = new Point(3, 4);
    double res = p1.distance(p2);
    Assert.assertEquals(res, 5.00);
  }

  @Test
  public void testZeroDistance() {
    Point p1 = new Point(0, 0);
    Point p2 = new Point(0, 0);
    double res = p1.distance(p2);
    Assert.assertEquals(res, 0.0);
  }

  @Test
  public void testMaxDistance() {
    Point p1 = new Point(-2147483648, -2147483648);
    Point p2 = new Point(2147483647, 2147483647);
    double res = Math.ceil(p1.distance(p2));
    Assert.assertEquals(res, 6.074000999E9);
  }


}
