package Java.OOP.Week10;

import java.util.Comparator;
import java.util.LinkedList;
import java.util.List;
import java.util.function.Consumer;

public class TreeSet<E> implements Set<E> {
  Comparator<? super E> comp;

  public TreeSet(Comparator<? super E> comp) {
    super();
    this.comp = comp;
  }

  private TreeSet<E> left = null;
  private E element = null;
  private TreeSet<E> right = null;

  @Override
  public boolean isEmpty() {
    return element == null;
  }

  @Override
  public boolean add(E e) {
    if (e==null) throw new IllegalArgumentException("null in Set not allowed");
    if (isEmpty()) {
      element = e;
      left = new TreeSet<>(comp);
      right = new TreeSet<>(comp);
      return true;
    } else {
      final int c = comp.compare(e, element);
      if (c < 0) {
        return left.add(e);
      } else if (c > 0) {
        return right.add(e);
      }
      return false;
    }
  }

  @Override
  public long size() {
    class R{long r=0;}
    R result = new R();
    forEach((e)->result.r++);
    return result.r;
  }

  @Override
  public boolean contains(E e) {
    if (isEmpty())
      return false;
    final int c = comp.compare(e, element);
    if (c == 0)
      return true;
    if (c < 0)
      return left.contains(e);
    return right.contains(e);
  }
  
  @Override
  public boolean remove(E e){
    if (isEmpty()) return false;
    final int c = comp.compare(e, element);
    if (c==0){
      if (left.isEmpty() && right.isEmpty()){
        left = null;
        right = null;
        element = null;
      }else if (left.isEmpty()){
        left = right.left;
        element = right.element;
        right = right.right;
      }else if (right.isEmpty()){
        right = left.right;
        element = left.element;
        left = left.left;
      }else {
        element = left.element;
        left.remove(left.element);
      }
      return true;
    }else if (c<0){
      return left.remove(e);
    }else {
      return right.remove(e);
    }
  }
  
  @Override
  public void forEach(Consumer<? super E> cons){
    if (isEmpty()) return;
    left.forEach(cons);
    cons.accept(element);
    right.forEach(cons);
  }
  
  @Override 
  public List<E> getSorted(){
    List<E> result = new LinkedList<>();
    forEach(e -> result.add(e));
    return result;
  }

  @Override
  public Comparator<? super E> getComparator() {
    return comp;
  }
  
  @Override
  public String toString() {
    return getSorted().toString();
  }
}