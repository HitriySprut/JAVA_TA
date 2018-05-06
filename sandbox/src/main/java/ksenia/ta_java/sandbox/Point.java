package ksenia.ta_java.sandbox;


/**
 * Created by admin on 06.05.2018.
 */
public class Point {
  private int x, y;

  public static double distance(Point p1, Point p2) {
    double res = Math.sqrt(Math.pow((p1.x - p2.x), 2) + Math.pow((p1.y - p2.y), 2));
    return res;
  }

  public Point(int x, int y) {
    this.x = x;
    this.y = y;
  }

  public void setX(int x) {
    this.x = x;

  }

  public void setY(int y) {
    this.y = y;

  }

  public int getX() {
    return x;

  }

  public int getY() {
    return y;

  }

  public String toString(){
    return "("+x+":"+y+")";
  }
}
