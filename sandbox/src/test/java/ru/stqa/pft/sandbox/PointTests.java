package ru.stqa.pft.sandbox;

import org.testng.Assert;
import org.testng.annotations.Test;

public class PointTests {

  @Test
  public void distPos() { //точки в первой четверти
    Point p1 = new Point(0,0);
    Point p2 = new Point (4,3);

    Assert.assertEquals(p1.distance(p2), 5.0);
  }

  @Test
  public void tochkiSovpadaut() { // Точки совпадают
    Point p1 = new Point(8,1);
    Point p2 = new Point (8,1);

    Assert.assertEquals(p1.distance(p2), 0.0);
  }

  @Test
  public void tochkaNeg() { // c отрицательными координатами
    Point p1 = new Point(-4,-5);
    Point p2 = new Point (-7,-9);

    Assert.assertEquals(p1.distance(p2), 5.0);
  }

  @Test
  public void tochka2i4chet() { // во 2 и 4 четвертях
    Point p1 = new Point(-3,1);
    Point p2 = new Point (1,-2);

    Assert.assertEquals(p1.distance(p2), 5.0);
  }
}
