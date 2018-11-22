package ru.stqa.pft.sandbox;

public class DistanceFunc { //расчет растояния через Функцию

  public static void main (String[] args) {

    Point p1 = new Point(2,3);
    Point p2 = new Point(5,7);

    double a = (p2.x -p1.x)*(p2.x -p1.x);
    double b = (p2.y-p1.y)*(p2.y-p1.y);

    System.out.println("Расстояние между точками p1(" +p1.x+ ";" +p1.y+ ") и p2(" +p2.x+ ";" +p2.y+ ") = " + distance(p1,p2));
    }

  public static double distance(Point p1, Point p2) {
    return Math.sqrt((p2.x-p1.x)*(p2.x-p1.x) + (p2.y-p1.y)*(p2.y-p1.y));
  }
}
