package Java.OOP.Week6.IsoscelesTriangle;

public class IsoscelesTriangle extends PolygonObject {
  double legLength;

  public IsoscelesTriangle(Vertex corner, double baseLength,
                           double legLength, Vertex velocity) {
    super(corner, baseLength, legLength, velocity);
    this.legLength = legLength;
    initPolygon();
  }

  @Override
  double size() {
    return 0.25 * this.width * Math.sqrt(4 * Math.pow(legLength, 2)
        - Math.pow(this.width, 2));
  }

  @Override
  void initPolygon() {
    double h = 2 * size() / width;
    this.p.add(0, h);
    this.p.add(width / 2, 0);
    this.p.add(width, h);
  }

  @Override
  public String toString() {
    return "new IsoscelesTriangle(new Vertex"
        +corner+", "+width+", "+legLength+", new Vertex"+velocity+")";
  }
}