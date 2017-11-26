package Java.OOP.Week5;

import Java.OOP.Week4.Vertex;

/**
 * Created by Andrei on 23.11.2017.
 */
public class Circle extends Ellipse{
  Circle(){
    super();
  }
  Circle(Vertex corner){
    super(corner);
  }
  Circle(Vertex corner, double diameter){
    super(corner, diameter, diameter);
  }
  Circle(Vertex corner, double diameter, Vertex velocity){
    super(corner, diameter, diameter, velocity);
  }

  @Override
  public String toString() {
    return super.toString();
  }

  @Override
  public double size(){
    return super.size();
  }

  public static void main(String[] args){
    Vertex vertex1 = new Vertex(1, 1);
    Vertex v = new Vertex(0, 0);
    Circle circle1 = new Circle();
    Circle circle2 = new Circle(vertex1);
    Circle circle3 = new Circle(vertex1, 20);
    Circle circle4 = new Circle(vertex1, 20, v);

    System.out.println(circle1.size());
    System.out.println(circle2.size());
    System.out.println(circle3.size());
    System.out.println(circle4.size());
  }
}
