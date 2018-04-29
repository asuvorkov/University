package Java.ProgrammingMethods.Week3;

import java.util.Spliterator;
import java.util.function.Consumer;

/**
 * Created by Andrei on 29.04.2018.
 */
public class ALSpliterator<A> implements Spliterator<A> {
  private int start;
  private int end;
  private A[] as;

  ALSpliterator(int start, int end, A[] as) {
    this.start = start;
    this.end = end;
    this.as = as;
  }

  @Override
  public boolean tryAdvance(Consumer<? super A> action) {
    if (start < end) {
      action.accept(as[start]);
      start += 1;
      return true;
    }else { // cannot advance
      return false;
    }
  }

  @Override
  public Spliterator<A> trySplit() {
    int low = start; // divide range in half
    int mid = ((low + end) >>> 1) & ~1; // force midpoint to be even
    if (low < mid) { // split out left half
      start = mid; // reset this Spliterator's origin
      return new ALSpliterator<>(low, mid, as);
    }else { // too small to split
      return null;
    }
  }

  @Override
  public long estimateSize() {
    return (long)((end - start) / 2);
  }

  @Override
  public int characteristics() {
    return ORDERED | SIZED | SUBSIZED;
  }
}
