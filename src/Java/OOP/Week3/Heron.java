package Java.OOP.Week3;

/**
 * Created by Andrei on 03.11.2017.
 */
public class Heron {
  static double sqrt(double x){
    assert x >= 0;
    return sqrt(x , (x + 1) / 2) ;
  }
  static double sqrt(double x, double approx) {
    //hier die Implementierung
    for (int i = 0; i < 10; i++){
      approx = 0.5 * (approx + x / approx);
    }
    return approx;
  }
  public static void main(String[] args){
    System.out.println(sqrt(2));
  }
}
