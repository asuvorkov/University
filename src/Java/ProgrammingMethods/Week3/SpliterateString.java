package Java.ProgrammingMethods.Week3;

import java.util.Spliterator;
import java.util.function.Consumer;
import java.util.stream.StreamSupport;

/**
 * Created by Andrei on 29.04.2018.
 */
public class SpliterateString implements Spliterator<Character> {
  int i = 0;
  int end;
  String s;

  public SpliterateString(String s) {
    this(0, s.length() - 1, s);
  }

  public SpliterateString(int i, int end, String s) {
    this.i = i;
    this.end = end;
    this.s = s;
  }

  @Override
  public boolean tryAdvance(Consumer<? super Character> action) {
    if (i <= end) {
      action.accept(s.charAt(i++));
      return true;
    }else {// cannot advance
      return false;
    }
  }

  @Override
  public Spliterator<Character> trySplit() {
    int low = i; // divide range in half
    int mid = ((low + end) >>> 1) & ~1; // force midpoint to be even
    if (low < mid) { // split out left half
      i = mid; // reset this Spliterator's origin
      return new SpliterateString(low, mid, s);
    }else {// too small to split
      return null;
    }
  }

  @Override
  public long estimateSize() {
    return (long)((end - i) / 2);
  }

  @Override
  public int characteristics() {
    return SIZED| SUBSIZED;
  }

  public static void main(String[] args) {
    StreamSupport.stream(new SpliterateString("my name is Andrew"), false)
        .forEach(x -> System.out.println(x));

    System.out.println("und nun parallel");

    StreamSupport.stream(new SpliterateString("my name is Andrew"), true)
        .forEach(x -> System.out.println(x));
  }
}
