package ru.stqa.pft.sandbox;

public class Point { //Объект - точка(x;y)

  public double x;
  public double y;

  public Point (double a, double b) {
    this.x = a;
    this.y = b;
  }

  public double distance(double x, double y) { //Метод, который принимает Х и У в качестве параметров

    return Math.sqrt((x - this.x)*(x - this.x) + (y - this.y) * (y - this.y));
    //this.x, this.y - ссылка на атрибуты объекта, в котором был вызван метод, т.е. р1
    //а в Х и У будут переданы значения, которые при вызове метода указаны в скобках

  }
}
