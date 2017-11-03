package Java.OOP.Week3;

/**
 * Created by Andrei on 03.11.2017.
 */
public class FibonacciGenerator {
  long n1 = -1;
  long n2 = 1;
  long nextFib(){
    long output = n1 + n2;
    n1 = n2;
    n2 = output;
    return output;
  }
  public static void main(String[] args){
    FibonacciGenerator fib = new FibonacciGenerator();
    for (int i = 0; i <= 10; i = i + 1){
      System.out.println(fib.nextFib());
    }
  }
}
