package Java.ProgrammingMethods.Week1;

import java.util.Iterator;

/**
 * Created by Andrei on 18.04.2018.
 */
public class OddIterable extends GenerationIterable<Long> {
  public OddIterable() {
    super((long) 1, aLong -> aLong % 2 == 0 ? aLong++ + 1 : ++aLong + 1);
  }

  public static void main (String[] args){
    Iterator<Long> it = new OddIterable().iterator();
    System.out.println(it.next());
    System.out.println(it.next());
    System.out.println(it.next());
    System.out.println(it.next());
  }
}
