package Java.OOP.Week6.IsoscelesTriangle;

public class Play extends Gapplication {
  public Play() {
    super(new GeometricObject(new Vertex(0, 0), 10, 10, new Vertex(0.2, 0.7)),
        new IsoscelesTriangle(new Vertex(0,0), 10, 111, new Vertex(0,0))
    );
    IsoscelesTriangle i = new IsoscelesTriangle(new Vertex(0,0), 10, 111, new Vertex(0,0));
    System.out.println(i.toString());
    System.out.println(i.size());
  }
}