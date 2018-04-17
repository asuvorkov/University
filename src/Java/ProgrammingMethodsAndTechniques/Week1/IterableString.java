package Java.ProgrammingMethodsAndTechniques.Week1;

import java.util.Iterator;

/**
 * Created by Andrei on 17.04.2018.
 */
public class IterableString implements Iterable<Character> {
  private String s;

  private IterableString(String str){
    this.s = str;
  }

  @Override
  public Iterator<Character> iterator() {
    return new Iterable(s);
  }

  public static void main(String[] args){
    for (char c:new IterableString("Hello world!")){
      System.out.println(c);
    }
  }

  private class Iterable implements Iterator<Character> {
    char[] chars;
    int currentIndex;

    Iterable(String s) {
      this.chars = s.toCharArray();
      this.currentIndex = 0;
    }

    @Override
    public boolean hasNext() {
      return currentIndex < chars.length;
    }

    @Override
    public Character next() {
      return chars[currentIndex++];
    }
  }
}
