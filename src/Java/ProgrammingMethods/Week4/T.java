package Java.ProgrammingMethods.Week4;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Predicate;

/**
 * Created by Andrei on 05.05.2018.
 */
public class T<E> implements Tree<E> {
  List<Tree<E>> children;
  E element;

  @SafeVarargs
  public T(E el, T<E>... ts) {
    element = el;
    children = new ArrayList<>();
    for (T<E> t : ts)
      children.add(t);
  }

  public T(E el, List<Tree<E>> child) {
    this.children = child;
    this.element = el;
  }

  public T() {
    element = null;
    children = null;
  }

  @Override
  public boolean isEmptyTree() {
    return children == null && element == null;
  }

  @Override
  public E getElement() {
    return element;
  }

  @Override
  public void setElement(E element) {
    this.element = element;
  }

  @Override
  public List<Tree<E>> getChildNodes() {
    return children;
  }

  @Override
  public void addChild(E element) {
    getChildNodes().add(new T<>(element));
  }

  public int size() {
    if (isEmptyTree())
      return 0;
    int result = 0;

    for (Tree<E> child : children)
      result += child.size();
    return result + 1;
  }

  public String toString() {
    StringBuffer result = new StringBuffer();
    toString(result, "");
    return result.toString();
  }

  @Override
  public void forEach(Consumer<E> con) {
    if (isEmptyTree())
      return;
    con.accept(element);
    children.parallelStream().forEach(tail -> tail.forEach(con));
  }

  public static void main(String[] args) {
    T<String> xs = new T<String>("Pizza",
        new T<String>("liebt",
            new T<String>("rote",
                new T<String>("Lukas",
                    new T<String>()))));
    System.out.println(xs.size());
    System.out.println(xs.contains("rote"));
    System.out.println(xs.contains("Julia"));
    xs.forEach(x -> System.out.println(x.toUpperCase()));
  }

  @Override
  public boolean contains(Predicate<E> pred) {
    return false;
  }

  @Override
  public void fringe(List<E> result) {
  }

  @Override
  public void mapModify(Function<E, E> f) {
  }

  @Override
  public <R> T<R> mapNew(Function<E, R> f) {
    return new T<>();
  }

  @Override
  public void pathTo(E elem, List<E> result) {
  }
}
