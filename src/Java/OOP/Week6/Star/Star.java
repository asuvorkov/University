package Java.OOP.Week6.Star;

/**
 * Created by Andrei on 01.12.2017.
 */
public class Star extends PolygonObject{
  int beams;
  double innerRadius;

  public Star(int beams,double innerRadius, Vertex corner,
              double width, Vertex velocity) {
    // TODO
    // korrekte Initalisierung des Super-Objekts
    // Initialisierung der Felder dieser Klasse
    // Aufruf von initPolygon
    super(corner, width, width, velocity);
    this.innerRadius = innerRadius;
    this.beams = beams;
    initPolygon();
  }
  @Override
  public String toString() {
    return "new Star("+beams+", "+innerRadius+", new Vertex"
        +corner+", "+width+", new Vertex"+velocity+")";
  }

  @Override
  void initPolygon() {
    //TODO Die Punkte des Sterns ins Polygon einfügen
    double angle = 2 * Math.PI / beams;
    double currentInnerAngle = 0;
    double currentOuterAngle = 0;
    for (int i = 0; i < beams; i++){
      p.add((innerRadius * Math.cos(currentInnerAngle - angle / 2) / 2)
              + width / 2,
          (innerRadius * Math.sin(currentInnerAngle - angle / 2) / 2)
              + width / 2);
      currentInnerAngle += angle;
      p.add((width * Math.cos(currentOuterAngle) / 2) + width / 2,
          (width * Math.sin(currentOuterAngle) / 2) + width / 2);
      currentOuterAngle += angle;
    }
  }
}
