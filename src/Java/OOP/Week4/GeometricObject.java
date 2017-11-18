package Java.OOP.Week4;

/**
 * Created by Andrei on 18.11.2017.
 */
public class GeometricObject {
  Vertex corner;
  double width;
  double height;
  Vertex velocity;

  GeometricObject(Vertex corner, double width, double height, Vertex velocity) {
    this.corner = corner;
    this.width = width;
    this.height = height;
    this.velocity = velocity;
  }
  //Hier jetzt die Methoden implementieren!
  double size(){
    return this.width * this.height;
  }
  boolean isLargerThan(GeometricObject that){
    return this.size() > that.size();
  }
  boolean isAbove(GeometricObject that){
    return this.corner.y + this.height < that.corner.y;
  }
  boolean isUnderneath(GeometricObject that){
    return this.corner.y > that.corner.y + that.height;
  }
  boolean isLeftOf(GeometricObject that){
    return this.corner.x + this.width < that.corner.x;
  }
  boolean isRightOf(GeometricObject that){
    return this.corner.x > that.corner.x + that.width;
  }
  boolean touches(GeometricObject that){
    return !isAbove(that) && !isUnderneath(that)
        && !isLeftOf(that) && !isRightOf(that);
  }
  public static void main(String[] args){
    Vertex vertex1 = new Vertex(1, 1);
    Vertex vertex2 = new Vertex(10, 10);
    Vertex v = new Vertex(0, 0);
    GeometricObject geometricObject1 = new GeometricObject(vertex1, 2, 2, v);
    GeometricObject geometricObject2 = new GeometricObject(vertex2, 1, 1, v);
    System.out.println(geometricObject1.isAbove(geometricObject2));
    System.out.println(geometricObject1.isLargerThan(geometricObject2));
    System.out.println(geometricObject1.isLeftOf(geometricObject2));
    System.out.println(geometricObject1.isRightOf(geometricObject2));
    System.out.println(geometricObject1.isUnderneath(geometricObject2));
    System.out.println(geometricObject1.touches(geometricObject2));
  }
}
