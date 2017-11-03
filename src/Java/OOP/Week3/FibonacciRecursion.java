package Java.OOP.Week3;

/**
 * Created by Andrei on 03.11.2017.
 */
public class FibonacciRecursion {
  static int fib(int n) {
    if(n == 0){
      return 0;
    }else {
      if(n == 1) {
        return 1;
      }else {
        return fib(n - 1) + fib(n - 2);
      }
    }
  }
  public static void main(String[] args){
    System.out.println(fib(6));
  }
}
