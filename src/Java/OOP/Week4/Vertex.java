package Java.OOP.Week4;

/**
 * Created by Andrei on 18.11.2017.
 */
public class Vertex {
  double x;
  double y;

  public Vertex(double x, double y) {
    this.x = x;
    this.y = y;
  }

  void move(Vertex v){
    x += v.x;
    y += v.y;
  }
  void moveTo(Vertex v){
    x = v.x;
    y = v.y;
  }
  public String toString() {
    return "("+x+", "+y+")";
  }
}
