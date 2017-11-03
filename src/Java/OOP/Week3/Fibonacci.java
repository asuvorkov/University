package Java.OOP.Week3;

/**
 * Created by Andrei on 03.11.2017.
 */
public class Fibonacci {
  static long fib(int n) {
    if(n == 0){
      return 0;
    }else {
      if(n == 1) {
        return 1;
      }else {
        long n1 = 0;
        long n2 = 1;
        long n3 = 1;
        for(int i = 2; i < n + 1; ++i) {
          n3 = n1 + n2;
          n1 = n2;
          n2 = n3;
        }
        return n3;
      }
    }
  }
  public static void main(String[] args){
    System.out.println(fib(80));
  }
}
