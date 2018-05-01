package Java.ProgrammingMethods.Week3;

import java.util.Spliterator;
import java.util.function.Consumer;

/**
 * Created by Andrei on 29.04.2018.
 */
public class LL<A> implements Li<A>{
  private A hd;
  private LL<A> tl;

  public boolean isEmpty(){
    return hd == null && tl == null;
  }

  public void add(A a){
    if (isEmpty()){
      tl = new LL<>();
      hd = a;
    }else{
      tl.add(a);
    }
  }

  public LL(A hd, LL<A> tl){
    this.hd = hd;
    this.tl = tl;
  }

  public LL() {
    this(null, null);
  }

  public A get(int i) {
    return i==0 ? hd : tl.get(i-1);
  }

  @Override
  public Spliterator<A> getSpliterator(){
    return new MySplitter(this);
  }

  public static void main (String[] args){
    LL<String> l = new LL();
    l.add("1");
    l.add("2");
    l.add("3");
    l.add("4");
    l.add("5");
    l.add("6");
    l.add("7");
    l.add("8");
    l.add("9");
    l.add("10");
    l.add("11");
    l.add("12");
    l.add("13");

    l.stream().forEach(System.out::println);
    System.out.println();
    System.out.println("now parallel");
    System.out.println();
    l.parallelStream().forEach(System.out::println);
  }

  private class MySplitter implements Spliterator<A>{
    private LL<A> ll;
    int start;
    int last;

    MySplitter(LL<A> ll){
      this.ll = ll;
      last = findLastElem();
      this.start = 0;
    }

    MySplitter(int start, int last, LL<A> ll){
      this.ll = ll;
      this.last = last;
      this.start = start;
    }

    private int findLastElem() {
      int i = 0;
      while (this.get(i) != null){
        i++;
      }
      return i;
    }

    public A get(int i){
      return i==0 ? hd : tl.get(i-1);
    }

    @Override
    public boolean tryAdvance(Consumer<? super A> action){
      if (this.get(start) != null) {
        action.accept(ll.get(start++));
        return true;
      }else {
        return false;
      }
    }

    @Override
    public Spliterator<A> trySplit(){
      if (start + 5 > last) {
        return null;
      }else {
        return new MySplitter(start + 5, last, ll).trySplit();
      }
    }

    @Override
    public long estimateSize(){
      return Long.MAX_VALUE;
    }

    @Override
    public int characteristics(){
      return ORDERED | SUBSIZED;
    }
  }
}
