package Java.OOP.Week7.GeoGame;

import Java.OOP.Week7.GeometricObject;

/**
 * Created by Andrei on 08.12.2017.
 */
public class SimpleGeoGame implements GeoGame{
  GeometricObject[] geos;
  int width;
  int height;

  public SimpleGeoGame(int width,int height, GeometricObject...
      geometricObjects) {
    geos = geometricObjects;
    this.width = width;
    this.height = height;
  }

  public SimpleGeoGame(GeometricObject... geometricObjects) {
    this(800,600,geometricObjects);
  }

  //fehlenden Methoden nach Spezifikation implementieren
  @Override
  public void move() {
    for (GeometricObject geo : geos) {
      geo.move();
    }
  }

  @Override
  public void collisionCheck() {
    for (GeometricObject geo : geos) {

    }
  }

  @Override
  public GeometricObject[] getGeos() {
    return geos;
  }

  @Override
  public int getWidth() {
    return width;
  }

  @Override
  public int getHeight() {
    return height;
  }
}
