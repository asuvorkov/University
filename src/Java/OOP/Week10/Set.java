package Java.OOP.Week10;

import java.lang.reflect.InvocationTargetException;
import java.util.Comparator;
import java.util.List;
import java.util.function.Consumer;

public interface Set<E> {

  @SuppressWarnings("unchecked")
  public static <E> Set<E> newSet(Comparator<? super E> comp) {
    try {
      return (Set<E>) Class.forName("Java.OOP.Week10.TreeSet")
          .getDeclaredConstructor(Comparator.class)
          .newInstance(comp);
    } catch (NoSuchMethodException | SecurityException | ClassNotFoundException 
        | InstantiationException| IllegalAccessException | IllegalArgumentException 
        | InvocationTargetException e) {
      e.printStackTrace();
    }
    return null;
  }

  boolean add(E e);

  boolean isEmpty();

  long size();

  boolean contains(E e);

  boolean remove(E e);

  void forEach(Consumer<? super E> cons);

  List<E> getSorted();

  Comparator<? super E> getComparator();

}