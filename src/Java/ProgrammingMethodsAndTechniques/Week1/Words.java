package Java.ProgrammingMethodsAndTechniques.Week1;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Objects;

/**
 * Created by Andrei on 18.04.2018.
 */
public class Words implements Iterable<String> {
  private String s;
  Words(String text){
    s = text;
  }

  @Override public Iterator<String> iterator() {
    return new Iterable(s);
  }

  public static void main (String[] args){
    for (String s: new Words("hallo")){
      System.out.println(s);
    }
  }

  private class Iterable implements Iterator<String> {
    int currentIndex = 0;
    String[] words;
    List<String> list = new ArrayList<>();

    Iterable(String s) {
      words = assembleArray(s);
    }

    private String[] assembleArray(String s) {
      words = s.split("\\s+");

      for (String word : words) {
        if (!Objects.equals(word, "")) {
          list.add(word);
        }
      }

      String[] temp = new String[list.size()];

      for (int i = 0; i < temp.length; i++){
        temp[i] = list.get(i);
      }
      return temp;
    }

    @Override
    public boolean hasNext() {
      return currentIndex < words.length;
    }

    @Override
    public String next() {
      return words[currentIndex++];
    }
  }
}
