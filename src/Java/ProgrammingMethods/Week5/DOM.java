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

  public long height2(Node n){ //TODO
    if (!n.hasChildNodes()){
      return 1;
    }else {
      return 0;
    }
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
    //TODO
  }

  @Override
  public boolean containsTag(Node n, String tagname) {
    if (n.getNodeType() == Node.ELEMENT_NODE) {
      Element element = (Element) n;
      if (element.getTagName().equals(tagname)) {
        return true;
      }
    }
    if (n.hasChildNodes()) {
      for (int i = 0; i < n.getChildNodes().getLength(); i++){
        if (containsTag(n.getChildNodes().item(i), tagname)){
          return true;
        }
      }
    }
    return false;
  }

  @Override
  public boolean containsTag2(Node n, String tagname) {
    return false;
  }

  @Override
  public void collectTagnames(Node n, Set<String> result) {
    if (n.getNodeType() == Node.ELEMENT_NODE) {
      Element element = (Element) n;
      result.add(element.getTagName());
    }
    if (n.hasChildNodes()) {
      for (int i = 0; i < n.getChildNodes().getLength(); i++){
        collectTagnames(n.getChildNodes().item(i), result);
      }
    }
  }

  @Override
  public void collectTagnames2(Node n, Set<String> result) {

  }

  @Override
  public long getMaxWIDTHAttributeValue(Node n) {
    long result = 0;

    if (n.hasAttributes()){
      for (int i = 0; i < n.getAttributes().getLength(); i++){
        if (n.getAttributes().getNamedItem("width") != null){
          Attr attr = (Attr) n.getAttributes().getNamedItem("width");
          if (Long.parseLong(attr.getValue()) > result){
            result = Long.parseLong(attr.getValue());
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
