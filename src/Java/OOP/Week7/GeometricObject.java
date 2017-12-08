package Java.OOP.Week7;

import javafx.scene.canvas.GraphicsContext;
/**
 * Created by Andrei on 08.12.2017.
 */
public class GeometricObject {
  Vertex corner;
  double width;
  double height;
  Vertex velocity;

  public GeometricObject(Vertex corner, double width, double height,
                         Vertex velocity) {
    super();
    this.corner = corner;
    this.width = width;
    this.height = height;
    this.velocity = velocity;
  }
  double size(){
    return width*height;
  }

  public void paintMeTo(GraphicsContext gc) {
    gc.fillRect(corner.x, corner.y, width, height);
  }

  public void move() {
    corner.x += velocity.x;
    corner.y += velocity.y;
  }
  @Override
  public String toString() {
    return corner+" "+width+" "+height;
  }

  boolean isLargerThan(GeometricObject that){
    return size()>that.size();
  }
  boolean isAbove(GeometricObject that){
    return corner.y+height<that.corner.y;
  }
  boolean isUnderneath(GeometricObject that){
    return that.isAbove(this);
  }
  boolean isLeftOf(GeometricObject that){
    return corner.x+width<that.corner.x;
  }
  boolean isRightOf(GeometricObject that){
    return that.isLeftOf(this);
  }
  boolean touches(GeometricObject that){
    return !(isLeftOf(that)||isRightOf(that)
        ||isAbove(that)||isUnderneath(that));
  }

  //TODO equals überschreiben
  @Override
  public boolean equals(Object other){
    if (null == other) return false;
    if (this == other) return true;
    if (other.getClass() != this.getClass()) return false;
    GeometricObject that = (GeometricObject) other;
    return (this.corner.equals(that.corner)
        && (this.height == that.height || this.width == that.width));
  }
}
