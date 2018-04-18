package Java.ProgrammingMethodsAndTechniques.Week1;

import java.util.Iterator;
import java.util.Objects;

/**
 * Created by Andrei on 18.04.2018.
 */
public class Lines implements Iterable<String> {
  private static String NEW_LINE = System.getProperty("line.separator");
  private String s;

  Lines(String str){
    this.s = str;
  }

  @Override public Iterator<String> iterator() {
    return new Iterable(s);
  }

  public static void main(String[] args) {
    for (String s: new Lines("hallo"+ NEW_LINE +"welt"+ NEW_LINE)){
      System.out.println(s);
    }
  }

  private class Iterable implements Iterator<String> {
    boolean start = false;
    String[] strings;
    String word;
    int currentIndex;

    Iterable(String s) {
      word = s;
      strings = s.split(NEW_LINE);
      this.currentIndex = 0;
    }

    @Override
    public boolean hasNext() {

      return !Objects.equals(word, "") && currentIndex < strings.length;
    }

    @Override
    public String next() {
      if (!start && currentIndex == 0 && word.startsWith(NEW_LINE)){
        start = true;
        return "";
      }
      if(currentIndex == strings.length && word.endsWith(NEW_LINE)){
        return "";
      }
      if (Objects.equals(word, NEW_LINE)){
        return "";
      }
      return strings[currentIndex++];
    }
  }
}
