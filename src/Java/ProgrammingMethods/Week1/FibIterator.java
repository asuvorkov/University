package Java.ProgrammingMethods.Week1;

import java.math.BigInteger;
import java.util.Iterator;

/**
 * Created by Andrei on 17.04.2018.
 */
public class FibIterator implements Iterable<BigInteger> {
  @Override
  public Iterator<BigInteger> iterator() {
    return new Iterable();
  }

  public static void main(String[] args) {
    new FibIterator().forEach(x -> System.out.println(x));
  }

  private class Iterable implements Iterator<BigInteger>{
    BigInteger firstNumber = new BigInteger("0");
    BigInteger secondNumber = new BigInteger("1");

    Iterable() {
    }

    @Override
    public boolean hasNext() {
      return true;
    }

    @Override
    public BigInteger next() {
      BigInteger temp = firstNumber;
      firstNumber = firstNumber.add(secondNumber);
      secondNumber = temp;
      return secondNumber;
    }
  }
}
