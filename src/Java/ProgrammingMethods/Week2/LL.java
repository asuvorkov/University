package Java.ProgrammingMethods.Week2;

import java.util.Collection;
import java.util.Comparator;
import java.util.function.BiFunction;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Predicate;

/**
 * Created by Andrei on 24.04.2018.
 */
public class LL<A> implements Li<A> {
  private A hd;
  private LL<A> tl;

  private boolean isEmpty(){
    return hd==null&&tl==null;
  }

  private LL(A hd, LL<A> tl) {
    this.hd = hd;
    this.tl = tl;
  }
  private LL() {
    this(null,null);
  }

  @Override
  public int size() {
    if (isEmpty())
      return 0;
    return 1 + tl.size();
  }

  @Override
  public void add(A a) {
    if (isEmpty()){
      tl = new LL<>();
      hd = a;
    }else{
      tl.add(a);
    }
  }

  @Override
  public A get(int i) {
    return i==0?hd:tl.get(i-1);
  }

  @Override
  public void addAll(Collection<? extends A> os) {
    for (int i = 0; i < os.toArray().length; i++){
      this.add((A) os.toArray()[i]);
    }
  }

  @Override
  public boolean containsWith(Predicate<A> p) {
    for (int i = 0; i < this.size(); i++){
      if (p.test(this.get(i))){
        return true;
      }
    }
    return false;
  }

  @Override
  public void remove(int i) {
    if (i > -1 && i < this.size()){
      if (i == 0){
        hd = tl.get(i);
        tl = tl.tl;
      }else {
        tl.remove(i - 1);
      }
    }
  }

  @Override
  public void add(A o, int i) {
    if (i > -1 && i <= this.size()){
      if (i == 0){
        tl = new LL<>(hd, tl);
        hd = o;
      }else {
        tl.add(o, i - 1);
      }
    }
  }

  @Override
  public Li<A> sublist(int from, int length) {
    if (this.size() == 0){
      return new LL<>();
    }

    if (from < 0){
      from = 0;
    }

    if (from > this.size()){
      return null;
    }

    if (from + length > this.size()){
      length = this.size();
    }else {
      length += from;
    }

    Li output = new LL<A>();
    for (int i = from ; i < length; i++){
      output.add(this.get(i));
    }
    return output;
  }

  @Override
  public void forEach(Consumer<A> con) {
    for (int i = 0; i < this.size(); i++){
      con.accept(this.get(i));
    }
  }

  @Override
  public Li<A> filter(Predicate<A> p) {
    Li output = new LL<A>();
    for (int i = 0; i < this.size(); i++){
      if (p.test(this.get(i))){
        output.add(this.get(i));
      }
    }
    return output;
  }

  @Override
  public <B> Li<B> map(Function<A, B> f) {
    Li output = new LL<A>();
    for (int i = 0; i < this.size(); i++){
      output.add(f.apply(this.get(i)));
    }
    return output;
  }

  @Override
  public void sortBy(Comparator<? super A> comp) {
    if (!this.isEmpty()){
      A[] array = (A[]) new Object[size()];
      for(int i = 0; i < array.length; i++){
        array[i] = this.get(i);
      }

      A temp;
      for (int i = 0; i < array.length; i++){
        for(int j = 1; j < (array.length - i); j++){
          A e1 = array[j - 1];
          A e2 = array[j];
          if(comp.compare(e1, e2) > 0){
            //swap elements
            temp = array[j-1];
            array[j-1] = array[j];
            array[j] = temp;
          }
        }
      }

      Li<A> output = new LL<>();
      for (A a : array) {
        output.add(a);
      }
      hd = output.get(0);
      output.remove(0);
      tl = (LL<A>) output;
    }
  }

  @Override
  public <B> B foldl(B start, BiFunction<B, A, B> op) {
    for (int i = 0; i < this.size(); i++){
      start = op.apply(start, this.get(i));
    }
    return start;
  }


  @Override
  public int hashCode() {
    final int prime = 31;
    int result = 1;
    result = prime * result + ((hd == null) ? 0 : hd.hashCode());
    result = prime * result + ((tl == null) ? 0 : tl.hashCode());
    return result;
  }

  @Override
  public boolean equals(Object obj) {
    if (this == obj){
      return true;
    }
    if (obj == null){
      return false;
    }
    if (getClass() != obj.getClass()){
      return false;
    }
    LL other = (LL) obj;
    if (hd == null) {
      if (other.hd != null){
        return false;
      }
    } else if (!hd.equals(other.hd)){
      return false;
    }
    if (tl == null) {
      if (other.tl != null){
        return false;
      }
    } else if (!tl.equals(other.tl)){
      return false;
    }
    return true;
  }
  @Override
  public String toString(){
    StringBuilder result = new StringBuilder("[");
    boolean first = true;
    for (LL<A> it = this;!it.isEmpty();it=it.tl){
      if (first){
        first = false;
      } else{
        result.append(", ");
      }
      result.append(it.hd);
    }
    result.append("]");
    return result.toString();
  }
}