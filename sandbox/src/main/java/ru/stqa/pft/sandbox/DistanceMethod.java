package ru.stqa.pft.sandbox;

public class DistanceMethod {

  public static void main (String[] args) {

    Point p = new Point(2,3,5,7);

    System.out.println("Расстояние между точками p1(" +p.x1+ ";" +p.y1+ ") и p2(" +p.x2+ ";" +p.y2+ ") = " + p.distance());
  }

}
