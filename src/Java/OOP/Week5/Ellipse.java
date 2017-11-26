package Java.OOP.Week5;

import Java.OOP.Week4.GeometricObject;
import Java.OOP.Week4.Vertex;

/**
 * Created by Andrei on 23.11.2017.
 */
public class Ellipse extends GeometricObject {
  Ellipse(){
    super(new Vertex(0, 0), 100, 100, new Vertex(0, 0));
  }

  Ellipse(Vertex corner){
    this(corner, 100, 100, new Vertex(0, 0));
  }

  Ellipse(Vertex corner, double width, double height){
    this(corner, width, height, new Vertex(0, 0));
  }

  Ellipse(Vertex corner, double width, double height, Vertex velocity) {
    super(corner, width, height, velocity);
  }

  @Override
  public String toString() {
    return super.toString();
  }

  @Override
  public double size(){
    return super.size() / 4 * Math.PI;
  }

  public static void main(String[] args){
    Vertex vertex1 = new Vertex(1, 1);
    Vertex v = new Vertex(0, 0);
    Ellipse ellipse1 = new Ellipse();
    Ellipse ellipse2 = new Ellipse(vertex1);
    Ellipse ellipse3 = new Ellipse(vertex1, 20, 50);
    Ellipse ellipse4 = new Ellipse(vertex1, 58, 100, v);
    System.out.println(ellipse1.size());
    System.out.println(ellipse2.size());
    System.out.println(ellipse3.size());
    System.out.println(ellipse4.size());
    System.out.println(ellipse3.width);
  }
}


