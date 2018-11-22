package ru.stqa.pft.sandbox;

public class Point { //Объект - точка(x;y)

  public double x;
  public double y;

  public Point (double a, double b) {
    this.x = a;
    this.y = b;
  }

  public double distance(double x, double y) {
    return Math.sqrt((x-this.x)*(x-this.x)+(y-this.y)*(y-this.y));
  }
}
