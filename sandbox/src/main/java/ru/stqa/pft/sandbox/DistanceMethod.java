package ru.stqa.pft.sandbox;


public class DistanceMethod {

  public static void main(String[] args) {

    Point p1 = new Point(2, 3);
    Point p2 = new Point(5, 7);

    Point p3 = new Point(1, 2);
    Point p4 = new Point(-4, -2);

    System.out.println("Расстояние между точками p1(" + p1.x + ";" + p1.y + ") и p2("
            + p2.x + ";" + p2.y + ") = " + p1.distance(p2.x, p2.y));

    System.out.println("Расстояние между точками p3(" + p3.x + ";" + p3.y + ") и p4("
            + p4.x + ";" + p4.y + ") = " + p3.distance(p4.x, p4.y));

  }

}