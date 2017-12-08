package Java.OOP.Week7.GeoGame;

import Java.OOP.Week7.GeometricObject;
import javafx.animation.AnimationTimer;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;

public class FXScreen extends Canvas {
  GeoGame game;

  public FXScreen(GeoGame game) {
    this.game = game;
    this.setWidth(game.getWidth());
    this.setHeight(game.getHeight());

    setFocusTraversable(true);
    showAnimation();
    timer.start();
  }
  private void showAnimation() {
    GraphicsContext gc = getGraphicsContext2D();
    gc.clearRect(0, 0, getWidth(), getHeight());
    for (GeometricObject geo : game.getGeos()) {
      geo.paintMeTo(gc);
    }
  }
  AnimationTimer timer = new AnimationTimer() {
    private long lastUpdate = 0 ;
    
    @Override
    public void handle(long now) {
      setFocused(true);
      game.move();
      game.collisionCheck();
      if (now - lastUpdate >= 28_000_000) {
        showAnimation();
        lastUpdate = now ;
      }
    }
  };
}