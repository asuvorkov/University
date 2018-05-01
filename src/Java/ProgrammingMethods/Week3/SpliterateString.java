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
    if(i<=end){
      action.accept(s.charAt(i));
      i++;
      return true;
    }
    return false;
  }

  @Override
  public Spliterator<Character> trySplit() {
    if(estimateSize() < 4){
      return null;
    }else{
      int iOld = i;
      int mid = (i + end) / 2;
      i = mid;
      return new SpliterateString(iOld,mid - 1, s);
    }
  }

  @Override
  public long estimateSize() {
    return end - i;
  }

  @Override
  public int characteristics() {
    return ORDERED | SIZED | IMMUTABLE | SUBSIZED;
  }

  public static void main(String[] args) {
    StreamSupport.stream(new SpliterateString("abcde"), false)
        .forEach(x -> System.out.println(x));

    System.out.println("und nun parallel");

    StreamSupport.stream(new SpliterateString("abcde"), true)
        .forEach(x -> System.out.println(x));
  }
}
