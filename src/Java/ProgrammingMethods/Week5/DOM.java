package Java.ProgrammingMethods.Week5;

import java.util.Set;
import org.w3c.dom.*;

/**
 * Created by Andrei on 19.05.2018.
 */
public class DOM implements  DOMUtil {
  public long height(Node n){
    long result = 0;
    if (!n.hasChildNodes()){
      return 1;
    }else {
      for (int i = 0; i < n.getChildNodes().getLength(); i++){
        long tempResult = height(n.getChildNodes().item(i));
        if (tempResult > result){
          result = tempResult;
        }
      }
    }
    return result + 1;
  }

  public long height2(Node n){
    return 1 + new NodeListSpliterator(n.getChildNodes())
        .parallelStream()
        .reduce((long) 0, (node, child)
            -> Math.max(node, height2(child)), Math::max);
  }

  public void collectText(Node n, StringBuffer result){
    if (n.hasChildNodes()){
      NodeList nodeList = n.getChildNodes();
      for (int i = 0; i < nodeList.getLength(); i++){
        if (nodeList.item(i).getNodeType() == Node.TEXT_NODE){
          result.append(nodeList.item(i).getTextContent());
          if (nodeList.item(i).hasChildNodes()){
            collectText(nodeList.item(i), result);
          }
        }
      }
    }
  }

  public void collectText2(Node n, StringBuffer result){
    if (n != null && n.getNodeType() == Node.TEXT_NODE){
      result.append(n);
    }

    if (n != null){
      new NodeListSpliterator(n.getChildNodes())
          .parallelStream()
          .forEach((Node node) -> collectText2(node, result));
    }
  }

  @Override
  public boolean containsTag(Node n, String tagname) {
    if (n.getNodeName().equals(tagname)){
      return true;
    }

    NodeList children = n.getChildNodes();
    for (int i = 0; i < children.getLength(); i++){
      if (containsTag(children.item(i), tagname)){
        return true;
      }
    }

    return false;
  }

  @Override
  public boolean containsTag2(Node n, String tagname) {
    return new NodeListSpliterator(n.getChildNodes())
        .parallelStream()
        .reduce(n.getNodeName()
            .equals(tagname), (node, child) -> node
            || containsTag2(child, tagname)
            , (node1, child1) -> node1 || child1);
  }

  @Override
  public void collectTagnames(Node n, Set<String> result) {
    if (n.getNodeType() != Node.TEXT_NODE){
      result.add(n.getNodeName());
    }

    NodeList children = n.getChildNodes();
    for (int i = 0; i < children.getLength(); i++){
      collectTagnames(children.item(i), result);
    }
  }

  @Override
  public void collectTagnames2(Node n, Set<String> result) {
    if (n.getNodeType() != Node.TEXT_NODE){
      result.add(n.getNodeName());
    }

    new NodeListSpliterator(n.getChildNodes())
        .parallelStream()
        .forEach(child -> collectTagnames2(child, result));
  }

  @Override
  public long getMaxWIDTHAttributeValue(Node n) {
    long result = 0;

    if (n.hasAttributes()){
      for (int i = 0; i < n.getAttributes().getLength(); i++){
        if (n.getAttributes().getNamedItem("width") != null){
          Node attr = n.getAttributes().getNamedItem("width");
          if (Long.parseLong(attr.getNodeValue()) > result){
            result = Long.parseLong(attr.getNodeValue());
          }
        }
      }
    }

    if (n.hasChildNodes()){
      for (int i = 0; i < n.getChildNodes().getLength(); i++){
        long temp = getMaxWIDTHAttributeValue(n.getChildNodes().item(i));
        if ( temp> result){
          result = temp;
        }
      }
    }
    return result;
  }
}
