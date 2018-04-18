package Java.ProgrammingMethodsAndTechniques.Week1;

import org.junit.Before;
import org.junit.Test;

import java.util.Iterator;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;

/**
 * Created by Andrei on 18.04.2018.
 */
public class LinesTest {
  static String NEW_LINE = System.getProperty("line.separator");

  @Before
  public void setUp() throws Exception {
  }

  @Test
  public void test1() {
    Lines lns = new Lines("");
    Iterator<String> ls = lns.iterator();
    assertFalse("leerer String enthält keine Zeilen", ls.hasNext());
  }
  @Test
  public void test2() {
    Lines lns = new Lines("hallo");
    Iterator<String> ls = lns.iterator();
    assertEquals("hallo", ls.next());
    assertFalse("String ohne Zeilenende hat nur eineZeile", ls.hasNext());
  }
  @Test
  public void test3() {
    Lines lns = new Lines("hallo"+NEW_LINE+"welt"+NEW_LINE);
    Iterator<String> ls = lns.iterator();
    assertEquals("hallo", ls.next());
    assertEquals("welt", ls.next());
    assertEquals("String endet mit leerer Zeile","", ls.next());
    assertFalse("leerer String enthält keine Zeilen", ls.hasNext());
  }

  @Test
  public void test5() {
    Lines lns = new Lines("hallo"+NEW_LINE+"welt"+NEW_LINE+"world");
    Iterator<String> ls = lns.iterator();
    assertEquals("hallo", ls.next());
    assertEquals("welt", ls.next());
    assertEquals("world", ls.next());
    assertFalse("leerer String enthält keine Zeilen", ls.hasNext());
  }
  @Test
  public void test6() {
    Lines lns = new Lines("hallo"+NEW_LINE+NEW_LINE+NEW_LINE+"welt");
    Iterator<String> ls = lns.iterator();
    assertEquals("hallo", ls.next());
    assertEquals("", ls.next());
    assertEquals("", ls.next());
    assertEquals("welt", ls.next());
    assertFalse("leerer String enthält keine Zeilen", ls.hasNext());
  }

  @Test
  public void test4() {
    Lines lns = new Lines(NEW_LINE);
    Iterator<String> ls = lns.iterator();
    assertEquals("", ls.next());
    assertEquals("", ls.next());
    assertFalse("leerer String enthält keine Zeilen", ls.hasNext());
  }
  @Test
  public void test7() {
    Lines lns = new Lines(NEW_LINE+NEW_LINE);
    Iterator<String> ls = lns.iterator();
    assertEquals("", ls.next());
    assertEquals("", ls.next());
    assertEquals("", ls.next());
    assertFalse("leerer String enthält keine Zeilen", ls.hasNext());
  }
}
