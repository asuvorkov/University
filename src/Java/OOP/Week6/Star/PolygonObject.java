package Java.OOP.Week6.Star;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Paint;

public abstract class PolygonObject extends GeometricObject {
  Polygon p = new Polygon();

  public PolygonObject(Vertex corner, double width, 
      double height, Vertex velocity){
    super(corner, width, height, velocity);
  }

  abstract void initPolygon();

  @Override
  public void paintMeTo(GraphicsContext g) {
    g.setFill(Paint.valueOf("red"));
    super.paintMeTo(g);

    double[] xs = p.getXs();
    double[] ys = p.getYs();
    for (int i = 0; i < p.size(); i++) {
      xs[i] = xs[i] + corner.x;
      ys[i] = ys[i] + corner.y;
    }
    g.setFill(Paint.valueOf("yellow"));
    g.fillPolygon(xs, ys, p.size());
    g.setFill(Paint.valueOf("black"));
  }
}