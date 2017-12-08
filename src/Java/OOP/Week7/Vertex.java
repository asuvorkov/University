package Java.OOP.Week7;

/**
 * Created by Andrei on 08.12.2017.
 */
public class Vertex {
  double x;
  double y;

  public Vertex(double x, double y) {
    super();
    this.x = x;
    this.y = y;
  }
  @Override
  public String toString() {
    return "("+x+","+y+")";
  }

  //TODO equals überschreiben
  @Override
  public boolean equals(Object other){
    if (null == other) return false;
    if (this == other) return true;
    if (other.getClass() != this.getClass()) return false;
    Vertex that = (Vertex) other;
    return (this.x == that.x || this.y == that.y);
  }
}
