package ksenia.ta_java.sandbox;

public class Program {

  public static void main(String[] args) {

    Point point1 = new Point(5, 4);
    Point point2 = new Point(-4, 125);
    System.out.println("Расстояние между точками " + point1 + " и " + point2 + " равно: " + point1.distance(point2));
    int i = 1+'1'-1;

  }
}