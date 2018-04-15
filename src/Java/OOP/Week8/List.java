package Java.OOP.Week8;

import java.util.Comparator;
import java.util.function.Consumer;
import java.util.function.Predicate;

/**
 * Created by Andrei on 16.12.2017.
 */
interface List<E> {
  int size();
  E get(int i) ;
  void add(E e);

  void addAll(List<E> cs);
  void remove(int i);
  void insert(int i, E e);
  boolean contains(E e);
  boolean containsWith(Predicate<E> pred);
  void reverse();
  void forEach(Consumer<? super E> consumer);
  boolean startsWith(List<E> that);
  boolean endsWith(List<E> that);
  List<E> sublist(int i, int l);
  void sortBy(Comparator<? super E> cmp);
}
