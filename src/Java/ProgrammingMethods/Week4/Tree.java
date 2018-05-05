package Java.ProgrammingMethods.Week4;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Predicate;

/**
 * Created by Andrei on 05.05.2018.
 */
public interface Tree<E> {
  E getElement();

  void setElement(E element);

  List<Tree<E>> getChildNodes();

  boolean isEmptyTree();

  void addChild(E element);

  int size();

  void fringe(List<E> result);

  default List<E> fringe(){
    List<E> result = new ArrayList<>();
    fringe(result);
    return result;
  }

  void pathTo(E elem, List<E> result);

  default List<E> pathTo(E elem){
    List<E> result = new ArrayList<>();
    pathTo(elem, result);
    return result;
  }

  boolean contains(Predicate<E> pred);

  default boolean contains(E el){
    return contains((x)->el.equals(x));
  }

  void forEach(Consumer<E> con);

  void mapModify(Function<E, E> f);

  <R> Tree<R> mapNew(Function<E, R> f);

  default void toString(StringBuffer result, String indent) {
    if (isEmptyTree()){
      result.append("<>");
    }else{
      result.append(getElement().toString());
      result.append("[\n");
      result.append(indent+"  ");
      for (Tree<E> child:getChildNodes())
        child.toString(result,indent+"  ");
      result.append("\n");
      result.append(indent);
      result.append("]");
    }
  }

  default void toXML(StringBuffer result, String indent) {
    if (isEmptyTree()){
      result.append("");
    }else{
//      result.append(indent);
      result.append("");
      result.append("\"\n  +\"\\n");
      result.append(indent+"  ");
      result.append("");
      result.append(getElement().toString());
      result.append("");
      for (Tree child:getChildNodes()){
        result.append("\"\n  +\"\\n");
        result.append(indent+"  ");
        child.toXML(result,indent+"  ");
      }
      result.append("\"\n  +\"\\n");
      result.append(indent);
      result.append("");
    }
  }


  default void addChild(Tree<E> child){
    if (!isEmptyTree())getChildNodes().add(child);
  }
}
