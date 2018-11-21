package ru.stqa.pft.sandbox;

public class Point {

  public double x1;
  public double y1;
  public double x2;
  public double y2;

  public Point (double a1, double b1, double a2, double b2) {
    this.x1 = a1;
    this.y1 = b1;
    this.x2 = a2;
    this.y2 = b2;
  }

  public double distance() {
    return Math.sqrt((this.x2-this.x1)*(this.x2-this.x1) + (this.y2-this.y1)*(this.y2-this.y1));
  }
}
