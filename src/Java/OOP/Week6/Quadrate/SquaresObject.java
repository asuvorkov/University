package Java.OOP.Week6.Quadrate;

import com.sun.org.apache.bcel.internal.generic.NEW;

import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

/**
 * Created by Andrei on 01.12.2017.
 */
public class SquaresObject extends PolygonObject {
  int depth;

  public SquaresObject(int depth,Vertex corner,double width,Vertex velocity){
    //TODO super,...
    super(corner, width, width, velocity);
    this.depth = depth;
    initPolygon();
  }

  @Override
  public String toString() {
    return "new SquaresObject("+depth+", new Vertex"+corner+", "
        +width+", new Vertex"+velocity+")";
  }

  @Override
  void initPolygon() {
    //TODO Die Punkte der Figur ins Polygon einfügen
    // Berechnung der Kantenlänge des inneresten Quadrats
    double oo = 1;
    for (int i = 1; i <= depth; i++){
      oo +=  2 / Math.pow(3, i);
    }
    //w ist die Kantenlänge des inneren Quadrats
    int w = (int)(width / oo);

    Vertex firstPoint = new Vertex(width / 2 - w / 2, width / 2 - w / 2);
    addPoints(w, firstPoint, depth, 1);
    // definieren Sie dann eine rekursive Hilfsmethode, die Sie für alle
    // vier Seiten des innersten Ausgangsquadrats aufrufen
  }

  private void addPoints(double w, Vertex v, int currentDepth,int orientation){
    p.add(v);
    if (currentDepth == 0){
      if (orientation == 1){
        p.add(v.x + w, v.y);
        p.add(v.x + w, v.y + w);
        p.add(v.x, v.y + w);
      }
      if (orientation == 2){
        p.add(v.x, v.y + w);
        p.add(v.x - w, v.y + w);
        p.add(v.x - w, v.y);
      }
      if (orientation == 3){
        p.add(v.x - w, v.y);
        p.add(v.x - w, v.y - w);
        p.add(v.x, v.y - w);
      }
      if (orientation == 4){
        p.add(v.x, v.y - w);
        p.add(v.x + w, v.y - w);
        p.add(v.x + w, v.y);
      }
    }else {
      if (currentDepth == 1){
        if (orientation == 1) {
          addPoints(w / 3, new Vertex(v.x + w / 3, v.y), currentDepth - 1, 4);
          addPoints(w, new Vertex(v.x + w, v.y), currentDepth, 2);
        }
        if (orientation == 2) {
          addPoints(w / 3, new Vertex(v.x, v.y + w / 3), currentDepth - 1, 1);
          addPoints(w, new Vertex(v.x, v.y + w), currentDepth, 3);
        }
        if (orientation == 3) {
          addPoints(w / 3, new Vertex(v.x - w / 3, v.y), currentDepth - 1, 2);
          addPoints(w, new Vertex(v.x - w, v.y), currentDepth, 4);
        }
        if (orientation == 4) {
          addPoints(w / 3, new Vertex(v.x, v.y - w / 3), currentDepth - 1, 3);
          addPoints(w, new Vertex(v.x, v.y - w), currentDepth - 1, 1);
        }
      }
    }
  }
}
