package Java.ProgrammingMethodsAndTechniques.Week1;

import static org.junit.Assert.*;

import java.util.Iterator;

import Java.ProgrammingMethodsAndTechniques.Week1.Words;
import org.junit.Before;
import org.junit.Test;

public class WordsTest {
  private static int size(Iterable<?> it){
    int result = 0;
    for (Object i : it) {
      result += 1;
    }
    return result;
  }
  
  @Before
  public void setUp() throws Exception {
  }

  @Test
  public void test1() {
    Iterable<String> ws = new Words("hallo");
    assertEquals("falsche Wortanzahl",1,size(ws));

    Iterator<String> it = ws.iterator();
    assertTrue("ein Wort muss erkannt werden",it.hasNext());
    assertEquals("erste Wort darf nichts abgeschnitten haben","hallo",it.next());
  }
  @Test
  public void test2() {
    Iterable<String> ws = new Words("");
    assertEquals("falsche Wortanzahl",0,size(ws));

    Iterator<String> it = ws.iterator();
    assertFalse("leerer String darf kein Wort enthalten",it.hasNext());
  }
  @Test
  public void test3() {
    Iterable<String> ws = new Words("    hallo");
    assertEquals("falsche Wortanzahl",1,size(ws));

    Iterator<String> it = ws.iterator();
    assertTrue("ein Wort muss erkannt werden",it.hasNext());
    assertEquals("erste Wort darf nichts abgeschnitten haben","hallo",it.next());
  }
  @Test
  public void test4() {
    Iterable<String> ws = new Words("           ");
    assertEquals("falsche Wortanzahl",0,size(ws));

    Iterator<String> it = ws.iterator();
    assertFalse("leerer String darf kein Wort enthalten",it.hasNext());
  }

  @Test
  public void test5() {
    Iterable<String> ws = new Words("    hallo freunde, wie gehts   ");
    assertEquals("falsche Wortanzahl",4,size(ws));

    Iterator<String> it = ws.iterator();
    assertTrue("ein Wort muss erkannt werden",it.hasNext());
    assertEquals("erste Wort darf nichts abgeschnitten haben","hallo",it.next());
    assertEquals("zweite Wort darf nichts abgeschnitten haben","freunde,",it.next());
    assertEquals("dritte Wort darf nichts abgeschnitten haben","wie",it.next());
    assertEquals("vierte Wort darf nichts abgeschnitten haben","gehts",it.next());
    
  }

  @Test
  public void test6() {
    Iterable<String> ws = new Words("    hallo freunde, wie gehts   ");
    assertEquals("falsche Wortanzahl",4,size(ws));

    Iterator<String> it = ws.iterator();
    assertTrue("ein Wort muss erkannt werden",it.hasNext());
    assertEquals("erste Wort darf nichts abgeschnitten haben","hallo",it.next());
    assertEquals("zweite Wort darf nichts abgeschnitten haben","freunde,",it.next());
    assertEquals("dritte Wort darf nichts abgeschnitten haben","wie",it.next());
    assertEquals("vierte Wort darf nichts abgeschnitten haben","gehts",it.next());
    
    it = ws.iterator();
    assertTrue("ein Wort muss erkannt werden",it.hasNext());
    assertEquals("erste Wort darf nichts abgeschnitten haben","hallo",it.next());
    assertEquals("zweite Wort darf nichts abgeschnitten haben","freunde,",it.next());
    assertEquals("dritte Wort darf nichts abgeschnitten haben","wie",it.next());
    assertEquals("vierte Wort darf nichts abgeschnitten haben","gehts",it.next());
      
  }
}