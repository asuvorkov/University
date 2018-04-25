package Java.ProgrammingMethods.Week2;

import java.util.function.BiFunction;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Predicate;

public interface Li<A> {
  int size();
  void add(A a);
  A get(int i);
  void addAll(java.util.Collection<? extends A> os);
  boolean containsWith(Predicate<A> p);
  default boolean contains(A o){
	  return containsWith(x->x.equals(o));
  };
  void remove(int i);
  void add(A o, int i);
  
  Li<A> sublist(int from, int length);

  void forEach(Consumer<A> con);
  Li<A> filter(Predicate<A> p);
  <B> Li<B> map(Function<A, B> f);
  default Li<A> copy(){return map(x->x);}

  void sortBy(java.util.Comparator<? super A> comp);

  <B> B foldl(B start,BiFunction<B, A, B> op); 
}