package Java.OOP.Week6.Star;

public class Play extends Gapplication {
  public Play() {
    super(new GeometricObject(new Vertex(123.29629131445341, 87.94095225512604), 5, 5, new Vertex(0, 0)),
          new Star(12,50, new Vertex(280, 90), 150, new Vertex(0.01, 0.0))
    );
  }
}