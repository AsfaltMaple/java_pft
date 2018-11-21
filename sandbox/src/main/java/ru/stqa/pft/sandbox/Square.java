package ru.stqa.pft.sandbox;

public class Square {

    public double l; // атрибут "l" типа double для объекта класса Квадрат

    public Square(double l) { //ф-я Конструктор - инструкция по заполнению параметров объектов этого класса
        this.l = l;
    }
    public double area () { //метод, вычисляющая площадь квадрата, тк ассоциир. с объектом, в который передан параметр, тут передавать ничего не нужно
        return this.l * this.l; // this. ссылка на объект этого класс, с которым метод ассоциирован
    }
}
