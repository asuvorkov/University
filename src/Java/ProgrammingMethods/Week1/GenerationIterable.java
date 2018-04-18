package Java.ProgrammingMethods.Week1;

import java.util.Iterator;
import java.util.function.Function;

/**
 * Created by Andrei on 18.04.2018.
 */
public class GenerationIterable<A> implements Iterable<A> {
  private Function<A, A> function;
  private A a;
  public GenerationIterable(A a, Function<A,A> f){
    this.function = f;
    this.a = a;
  }

  @Override
  public Iterator<A> iterator() {
    return new Iterable(a, function);
  }

  private class Iterable implements Iterator<A> {
    Function<A, A> function;
    A a;
    boolean firstTime = true;

    Iterable(A a, Function<A, A> f) {
      function = f;
      this.a = a;
    }

    @Override
    public boolean hasNext() {
      return true;
    }

    @Override
    public A next() {
      if (firstTime){
        firstTime = false;
        return a;
      }
      a = function.apply(a);
      return a;
    }
  }
}
