package Java.AlgorithmsAndDataStructures.Week1;

/**
 * Created by Andrei on 15.04.2018.
 */
public class DeterministicAlgorithm {
  public static void main(String[] args){
    int x, y;
    x = 17;
    y = 17;
    y = 3;
    boolean foo = (++y >= 4) && (x-- < 20) && ((y >= 4) || (++x < 10));
    System.out.println(foo ? y-- - --x : y++ + ++x); // result: -11
    /**
     * deterministic
     */
  }
}
