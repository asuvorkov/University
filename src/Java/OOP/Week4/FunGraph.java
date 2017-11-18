package Java.OOP.Week4;

import static java.lang.Math.sqrt;

/**
 * Created by Andrei on 18.11.2017.
 */
public final class FunGraph {
  private FunGraph(){

  }

  static String mkStringGraph(int xMin,int xMax,int yMin,int yMax){
    String result="";
    //Hier jetzt zeilenweise den String erzeugen.

    for (int y = yMax; y > yMin - 1; y--){
      int tempXpos = (int)sqrt(y);
      int tempXneg = -1 * tempXpos;

      for (int x = xMin; x < xMax + 1; x++){
        if (tempXpos == x || tempXneg == x){
          result += "*";
        }else {
          if (x == 0){
            result += "|";
          } else {
            if (y == 0){
              result += "-";
            }else {
              result += " ";
            }
          }
        }
      }
      result += "\n";
    }
    return result;
  }
  public static void main(String[] args) {
    System.out.println(mkStringGraph(-2,2,0,5));
    System.out.println(mkStringGraph(-5,5,0,25));
  }
}
