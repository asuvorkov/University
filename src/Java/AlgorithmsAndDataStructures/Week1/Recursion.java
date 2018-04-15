package Java.AlgorithmsAndDataStructures.Week1;

/**
 * Created by Andrei on 15.04.2018.
 */
public class Recursion {
  private static int foo(int a, int b) {
    // FIXME: implement
    assert (a > -1 && b > -1) : "'a' and 'b' should be positive!";
    if (a == 0){
      return 0;
    }
    return bar(b, a, b);
  }

  private static int bar(int i, int a, int b) {
    // FIXME: implement
    if (i == 0){
      return foo(a - 1, b);
    }
    return a + bar(i - 1, a, b);
  }
  public static void main(String[] args) {
    System.out.println(foo(20, 5));
  }
}
