package Java.OOP.Week7.GeoGame;

public class Play extends GeoApplication {
  public Play() {
    super(new SimpleGeoGame(
        new Java.OOP.Week7.GeometricObject(new Java.OOP.Week7.Vertex(0, 0), 10, 10, new Java.OOP.Week7.Vertex(1.2, 1.7))
        ));
  }
}