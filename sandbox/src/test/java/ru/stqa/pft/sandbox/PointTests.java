package ru.stqa.pft.sandbox;

import org.testng.Assert;
import org.testng.annotations.Test;

public class PointTests {

  @Test
  public void distPos() { //точки в первой четверти
    Point p1 = new Point(0,0);
    Point p2 = new Point (4,3);

    Assert.assertEquals(p1.distance(p2.x, p2.y), 5.0);
  }

  @Test
  public void tochkiSovpadaut() { // Точки совпадают
    Point p1 = new Point(8,1);
    Point p2 = new Point (8,1);

    Assert.assertEquals(p1.distance(p2.x, p2.y), 0.0);
  }

  @Test
  public void tochkaNeg() { // c отрицательными координатами
    Point p1 = new Point(-4,-5);
    Point p2 = new Point (-7,-9);

    Assert.assertEquals(p1.distance(p2.x, p2.y), 5.0);
  }
}
