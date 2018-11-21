package ru.stqa.pft.sandbox;

public class MyFirstProgram {
    public static void main(String[] args) {
        hello("world");
        hello("user");
        hello("Brusnika");

        Square s = new Square (5); //Создаем новый объект s типа square (как int) класса Square
        System.out.println ("Площадь квадрата со стороной " + s.l + " = " + s.area ());

        Rectangular r = new Rectangular(4,6); //Создаем новый объект r типа rectangular (как int) класса Rectangular
        System.out.println("Площадь прямоугольника со сторонами " + r.a + " и " + r.b + " = " + r.area());
    }

    public static void hello(String somebody) {
        System.out.println("Hello, " + somebody + "!");
    }

}