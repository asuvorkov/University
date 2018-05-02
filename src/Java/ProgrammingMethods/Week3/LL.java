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

    MySplitter(LL<A> ll){
      this.ll = ll;
    }

    public A get(int i){
      return i==0 ? hd : tl.get(i-1);
    }

    @Override
    public boolean tryAdvance(Consumer<? super A> action){
      if (this.get(0) != null) {
        action.accept(ll.get(5));
        return true;
      }else {
        return false;
      }
    }

    @Override
    public Spliterator<A> trySplit(){
      if(tl != null && tl.tl.hd != null && tl.tl.tl != null && tl.tl.tl.tl != null){
        return new MySplitter(tl.tl.tl.tl);
      }
      return null;
    }

    @Override
    public long estimateSize(){
      return Long.MAX_VALUE;
    }

    @Override
    public int characteristics(){
      return ORDERED;
    }
  }
}
