package Java.OOP.Week5;

import java.math.BigInteger;
/**
 * Created by Andrei on 23.11.2017.
 */
public class BigIntegerFactorial {
  private BigIntegerFactorial() {
  }

  static BigInteger factorial(long n){
    BigInteger result;
    result = (n == 1)
        ? BigInteger.ONE : factorial(n - 1).multiply(BigInteger.valueOf(n));
    return result;
  }

  public static void main(String[] args){
    System.out.println(factorial(5000));
  }
}
