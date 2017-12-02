package Java.OOP.Week6.Quadrate;

import java.util.LinkedList;

public class Polygon extends LinkedList<Vertex>{
  void add(double x,double y){
    add(new Vertex(x,y));
  }
  
  double[] getXs(){
    double[] xs = new double[size()];
    int i=0;
    for (Vertex v:this)xs[i++]=v.x;
    return xs;
  }
  double[] getYs(){
    double[] ys = new double[size()];
    int i=0;
    for (Vertex v:this)ys[i++]=v.y;
    return ys;
  }
}