package Java.OOP.Week6.Quadrate;

public class Play extends Gapplication {
  public Play() {
    super(new GeometricObject(new Vertex(0, 0), 10, 10, new Vertex(0.2, 0.7)),
        new SquaresObject(1, new Vertex(180, 180), 170, new Vertex(0, 0.0))
    );
  }
}