package Java.OOP.Week7.GeoGame;

import Java.OOP.Week7.GeometricObject;

public interface GeoGame {
  void move();
  void collisionCheck();
  GeometricObject[] getGeos();
  int getWidth();
  int getHeight();
}