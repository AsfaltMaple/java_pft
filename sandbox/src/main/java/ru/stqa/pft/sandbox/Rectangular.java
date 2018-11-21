package ru.stqa.pft.sandbox;

public class Rectangular {

    public double a; // атрибуты а и б типа double для объекта класса Прямоугольник
    public double b;

    public Rectangular (double a, double b) { //ф-я Конструктор - инструкция по заполнению параметров объектов этого класса

        this.a = a;
        this.b = b;
    }

    public double area () { //функция, вычисляющая площадь прямоугольника
        return this.a * this.b;
    }
}
