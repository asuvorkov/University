package Java.ProgrammingMethodsAndTechniques.Week1;

import java.util.Iterator;

/**
 * Created by Andrei on 17.04.2018.
 */
public class ArrayIterable<A> implements Iterable<A> {
  private A[] as;

  private ArrayIterable(A[] as){
    this.as = as;
  }

  public static void main(String[] args) {
    String[] as = {"one", "two", "three"};
    Iterator<String> it = new ArrayIterable<>(as).iterator();
    for ( ; it.hasNext(); ){
      System.out.println(it.next());
    }
  }

  @Override
  public Iterator<A> iterator() {
    return new Iterable(as);
  }

  private class Iterable implements Iterator<A>{
    int currentIndex = 0;
    A[] as;

    Iterable(A[] as) {
      this.as = as;
    }

    @Override
    public boolean hasNext() {
      return currentIndex < as.length;
    }

    @Override
    public A next() {
      return as[currentIndex++];
    }
  }
}
