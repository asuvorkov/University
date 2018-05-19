package Java.ProgrammingMethods.Week5;

import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;
import org.w3c.dom.Node;

/**
 * Created by Andrei on 19.05.2018.
 */
public interface DOMUtil {
  boolean containsTag(Node n,String tagname);
  boolean containsTag2(Node n,String tagname);

  void collectTagnames(Node n, Set<String> result);
  void collectTagnames2(Node n, Set<String> result);

  void collectText(Node n,StringBuffer result);
  void collectText2(Node n,StringBuffer result);

  long getMaxWIDTHAttributeValue(Node n);

  long height(Node n);
  long height2(Node n);

  default String text(Node n){
    StringBuffer result = new StringBuffer();
    collectText(n, result);
    return result.toString();
  }
  default String text2(Node n){
    StringBuffer result = new StringBuffer();
    collectText2(n, result);
    return result.toString();
  }

  default Set<String> collectTagnames(Node n){
    Set<String> result = ConcurrentHashMap.newKeySet();
    collectTagnames(n,result);
    return result;
  }
  default Set<String> collectTagnames2(Node n){
    Set<String> result = ConcurrentHashMap.newKeySet();
    collectTagnames2(n,result);
    return result;
  }
}
