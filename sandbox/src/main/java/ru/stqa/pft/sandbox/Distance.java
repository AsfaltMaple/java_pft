package ru.stqa.pft.sandbox;


public class Distance {

  public static void main(String[] args) {

    Point p1 = new Point(2, 3);
    Point p2 = new Point(5,7);

    Point p3 = new Point(2, 0);
    Point p4 = new Point(0, 0);

    System.out.println("Расстояние между точками p1(" +p1.x+ ";" +p1.y+ ") и p2(" +p2.x+ ";" +p2.y+ ") = " + distance(p1,p2));
    //через функцию


    System.out.println("Расстояние между точками p1(" + p1.x + ";" + p1.y + ") и p2("
            + p2.x + ";" + p2.y + ") = " + p1.distance(p2)); //через метод
    //(p2.x, p2.y) - значения для р2 передаются в метод как параметры, а для р1 (p1.distance) - через ссылку this в методе

    System.out.println("Расстояние между точками p3(" + p3.x + ";" + p3.y + ") и p4("
            + p4.x + ";" + p4.y + ") = " + p3.distance(p4));

  }
  public static double distance(Point p1, Point p2) {
    return Math.sqrt((p2.x-p1.x)*(p2.x-p1.x) + (p2.y-p1.y)*(p2.y-p1.y));}
}