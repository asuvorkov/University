package Java.OOP.Week4;

import static java.lang.Integer.MIN_VALUE;

/**
 * Created by Andrei on 18.11.2017.
 */
public class SimpleArrayFuns {
  private SimpleArrayFuns() {
  }

  static boolean contains(int[] xs,int y){
    for (int x : xs) {
      if (x == y) {
        return true;
      }
    }
    return false;
  }
  static long sum(int[] xs){
    long sum = 0;
    for (int x : xs) {
      sum += x;
    }
    return sum;
  }
  static int avg(int[] xs){
    if (xs.length == 0){
      return 0;
    }
    return (int)(sum(xs) / xs.length);
  }
  static boolean isSorted(int[] xs){
    for (int i = 1; i < xs.length; i++){
      if (xs[i] < xs[i - 1]){
        return false;
      }
    }
    return true;
  }
  static int max(int[] xs){
    if (xs.length == 0){
      return MIN_VALUE;
    }
    int max = MIN_VALUE;
    for (int i = 1; i < xs.length; i++){
      if (xs[i] > max){
        max = xs[i];
      }
    }
    return max;
  }
}
