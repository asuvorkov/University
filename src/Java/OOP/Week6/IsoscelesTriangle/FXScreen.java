package Java.OOP.Week6.IsoscelesTriangle;

import javafx.animation.AnimationTimer;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;

public class FXScreen extends Canvas {
  GeometricObject[] geos;

  public FXScreen(GeometricObject[] geos) {
    this.geos = geos;
    this.setWidth(800);
    this.setHeight(600);

    setFocusTraversable(true);
    GraphicsContext gc = getGraphicsContext2D();
    gc.clearRect(0, 0, getWidth(), getHeight());
    for (GeometricObject geo:geos){
      geo.paintMeTo(gc);
    }

    timer.start();
  }

  AnimationTimer timer = new AnimationTimer() {
    @Override
    public void handle(long l) {
      setFocused(true);

      for (GeometricObject geo:geos){
        geo.move();
      }

      GraphicsContext gc = getGraphicsContext2D();
      gc.clearRect(0, 0, getWidth(), getHeight());
      for (GeometricObject geo:geos){
        geo.paintMeTo(gc);
      }
    }
  };
}