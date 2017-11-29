package Java.OOP.Week6;

public class Play extends Gapplication {
  public Play() {
    super(new GeometricObject(new Vertex(0, 0), 10, 10, new Vertex(0.2, 0.7)),
      new GeometricObject(new Vertex(200, 150), 40, 80, new Vertex(0.1, 0.2))
    );
  }
}