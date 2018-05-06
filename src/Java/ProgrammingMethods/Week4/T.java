package Java.ProgrammingMethods.Week4;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Predicate;

/**
 * Created by Andrei on 05.05.2018.
 */
public class T<E> implements Tree<E> {
  private List<Tree<E>> children;
  private E element;

  @SafeVarargs
  private T(E el, T<E>... ts) {
    element = el;
    children = new ArrayList<>();
    Collections.addAll(children, ts);
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

  @Override
  public boolean contains(Predicate<E> pred) {
    if (element != null && pred.test(element)) {
      return true;
    } else {
      if (children != null){
        for (Tree<E> child : children) {
          if (!child.isEmptyTree()){
            if (child.contains(pred)){
              return true;
            }
          }
        }
      }
    }
    return false;
  }

  @Override
  public void fringe(List<E> result) {
    if (!isEmptyTree()){
      if (children.size() == 0 || children.get(0).getElement() == null){
        result.add(getElement());
      }
      for (Tree<E> child : children) {
        child.fringe(result);
      }
    }
  }

  @Override
  public void mapModify(Function<E, E> f) {
    if (element != null){
      element = f.apply(element);
    }
    if (children != null){
      for (Tree<E> child : children) {
        child.mapModify(f);
      }
    }
  }

  @Override
  public <R> T<R> mapNew(Function<E, R> f) {
    T<R> r = new T<>(f.apply(element));

    if (r.element != null){
      r.element = f.apply(element);
    }
    if (r.children != null){
      for (Tree<E> child : children) {
        if (child.getElement() != null){
          r.addChild(child.mapNew(f));
        }
      }
    }

    return r;
  }

  @Override
  public void pathTo(E elem, List<E> result) {
    if (this.toString().contains("" + elem)) {
      result.add(element);
      while (elem != element) {
        if (result.get(result.size() - 1) == elem) {
          break;
        }
        if (children.size() == 1) {
          children.get(0).pathTo(elem, result);
        }
        if (children.size() > 1) {
          for (Tree<E> currentChild : children) {
            if (findRightChild(currentChild, elem)) {
              currentChild.pathTo(elem, result);
              break;
            }
          }
        }
      }
    }
  }

  private boolean findRightChild(Tree<E> child, E elem) {
    if (child.getElement() == elem) {
      return true;
    }

    if (child.getChildNodes() != null){
      if (child.getChildNodes().size() == 1) {
        boolean out = findRightChild(child.getChildNodes().get(0), elem);
        if (out){
          return true;
        }
      }
      if (child.getChildNodes().size() > 1) {
        for (Tree<E> c : child.getChildNodes()){
          boolean out = findRightChild(c, elem);
          if (out){
            return true;
          }
        }
      }
    }
    return false;
  }
}
