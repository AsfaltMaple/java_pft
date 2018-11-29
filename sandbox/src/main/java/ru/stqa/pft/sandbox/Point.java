package ru.stqa.pft.sandbox;

public class Point { //Объект - точка(x;y)

  public double x;
  public double y;

  public Point (double a, double b) {
    this.x = a;
    this.y = b;
  }

  public double distance(Point p2) { //Метод, который принимает Х и У в качестве параметров

    return Math.sqrt((p2.x - this.x)*(p2.x - this.x) + (p2.y - this.y) * (p2.y - this.y));
    //this.x, this.y - ссылка на атрибуты объекта, в котором был вызван метод, т.е. р1
    //а в p2.Х и p2.У будут переданы значения, которые при вызове метода указаны в скобках

  }

}
