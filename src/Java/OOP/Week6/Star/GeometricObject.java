package Java.OOP.Week6.Star;

import javafx.scene.canvas.GraphicsContext;

public class GeometricObject {
  Vertex corner;
  double width;
  double height;
  Vertex velocity;

  public GeometricObject(Vertex corner, double width,
      double height, Vertex velocity) {
    super();
    this.corner = corner;
    this.width = width;
    this.height = height;
    this.velocity = velocity;
  }

  public void paintMeTo(GraphicsContext gc) {    
    gc.fillRect(corner.x, corner.y, width, height); 
  }

  public void move() {
    corner.x += velocity.x;
    corner.y += velocity.y;
  }

  double size(){
    return width*height;
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

  @Override
  public String toString() {
     return "Geo("+corner+","+width+","+height+","+velocity+")";
  }
}