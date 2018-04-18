package Java.ProgrammingMethods.Week1;

import java.util.Iterator;
import java.util.function.Function;

/**
 * Created by Andrei on 18.04.2018.
 */
public class IndexIterable<A> implements Iterable<A> {
  Function<Long, A> function;

  public IndexIterable(Function<Long, A> f) {
    function = f;
  }

  @Override
  public Iterator<A> iterator() {
    return new Iterable(function);
  }

  private class Iterable implements Iterator<A> {
    long currentIndex = 1;
    Function<Long, A> function;

    Iterable(Function<Long, A> f) {
      function = f;
    }

    @Override
    public boolean hasNext() {
      return true;
    }

    @Override
    public A next() {
      return function.apply(currentIndex++);
    }
  }
}
