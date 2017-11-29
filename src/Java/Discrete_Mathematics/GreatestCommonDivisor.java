package Java.Discrete_Mathematics;

import java.math.BigInteger;
import java.util.Objects;

/**
 * Created by Andrei on 26.11.2017.
 */
public class GreatestCommonDivisor {
  public static void main(String[] args) {
    gcd(BigInteger.valueOf(1000000));
  }

  /*
  This method calculates if for all possible values below m(arbitrarily large)
  gcd(n^5 + 5, (n + 1)^5 + 5) = 1
   */
  private static void gcd(BigInteger m) {
    for (BigInteger x = BigInteger.valueOf(2);
         Objects.equals((x.add(BigInteger.valueOf(1))).pow(5).add(BigInteger.valueOf(5)).max(m), m);
         x = x.add(BigInteger.valueOf(1))) {
      BigInteger y = (x.subtract(BigInteger.valueOf(1)).pow(5)).add(BigInteger.valueOf(5));
      BigInteger temp = x.pow(5).add(BigInteger.valueOf(5));
      if (!Objects.equals(temp.gcd(y), BigInteger.valueOf(1))) {
        System.out.println("FAIL! GCD of " + temp + " and " + y + " is " + temp.gcd(y).toString());
      }
    }
  }
}
