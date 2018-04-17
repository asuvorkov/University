package Java.ProgrammingMethodsAndTechniques;

/**
 * Created by Andrei on 17.04.2018.
 */
import java.util.Iterator;
public class IntRange implements Iterable<Integer>{
  int from;
  int to;
  int step;
  boolean infinite;

  private IntRange(int from, int to, int step){
    this.from = from;
    this.to = to;
    this.step = step;
  }
  private IntRange(int from, int to){
    this(from,to,1);
  }
  private IntRange(int from){
    this(from,Integer.MAX_VALUE);
    this.infinite = true;
  }
  public IntRange(){
    this(0);
  }

  @Override
  public Iterator<Integer> iterator(){
    return new Iterable(from, to, step);
  }

  public static void main(String[] args) {
    Iterator<Integer> i = new IntRange(20, 25, 2).iterator();
    for (; i.hasNext();){
      System.out.println(i.next());
    }
  }

  private class Iterable implements Iterator<Integer>{
    int from;
    int to;
    int step;

    Iterable(int from, int to, int step) {
      this.from = from;
      this.to = to;
      this.step = step;
    }

    @Override
    public boolean hasNext() {
      return step < 0 ? from >= to : from <= to;
    }

    @Override
    public Integer next() {
      int result = from;
      from += step;
      return result;
    }
  }
}