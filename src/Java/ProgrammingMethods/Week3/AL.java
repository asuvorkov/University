package Java.ProgrammingMethods.Week3;

import java.util.Spliterator;
import java.util.stream.Stream;
import java.util.stream.StreamSupport;

/**
 * Created by Andrei on 29.04.2018.
 */
public class AL<A> {
  private int length;
  @SuppressWarnings("unchecked")
  private  A[] as = (A[]) new Object[length + 10];

  public int size() {
    return length;
  }

  public void add(A a) {
    if (as.length <= length)
      größeresRegalKaufen();
        as[length] = a;
    length = length + 1;
  }

  @SuppressWarnings("unchecked")
  private void größeresRegalKaufen() {
    A[] neueRegal = (A[]) new Object[length + 10];
    System.arraycopy(as, 0, neueRegal, 0, as.length);
    as = neueRegal;
  }

  public A get(int i) {
    return as[i];
  }

  private Spliterator<A> getSpliterator() {
    return new ALSpliterator<>(0, length, as);
  }

  private Stream<A> stream() {
    return StreamSupport.stream(getSpliterator(), false);
  }

  private Stream<A> parallelStream() {
    return StreamSupport.stream(getSpliterator(), true);
  }

  public static void main(String[] args) {
    AL<String> xs = new AL<>();
    xs.add("Freunde");
    xs.add("Römer");
    xs.add("Landsleute");
    for (int i = 0; i < 100; i++) {
      xs.add("x" + i);
    }
    xs.stream().forEach(System.out::println);
    System.out.println("jetzt parallel");
    xs.parallelStream().forEach(System.out::println);
  }
}
