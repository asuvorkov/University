package Java.OOP.Week10;

import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.function.Consumer;

import static junit.framework.TestCase.assertFalse;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertTrue;


public class SetTest {
  Set<String> xs;
  Set<String> empty;
  
  @Before
  public void initTestSets(){
    empty = Set.newSet(Comparator.comparing((String x) -> x.toLowerCase()));
    xs = Set.newSet(Comparator.comparing((String x) -> x.toLowerCase()));
    xs.add("Römer");
    xs.add("Mitbürger");
    xs.add("Freunde");
    xs.add("Hört");
    xs.add("meine");
    xs.add("Sache");
    xs.add("führen");
    xs.add("und");
    xs.add("seid");
    xs.add("still");
    xs.add("damit");
    xs.add("ihr");
    xs.add("hören");
    xs.add("könnt");
    xs.add("Glaubt");
    xs.add("mir");
    xs.add("um");
    xs.add("meiner");
    xs.add("willen");
    xs.add("UND");
    xs.add("hegt");
    xs.add("Achtung");
    xs.add("vor");
    xs.add("Ehre");
    xs.add("damit");
    xs.add("ihr");
    xs.add("glauben");
    xs.add("könnt");
  }

  @Test
  public void sizeTest() {
    assertEquals("Length should be 24", 24, xs.size());
    assertEquals("Length should be 0", 0, empty.size());
  }

  @Test
  public void isEmptyTest() {
    assertTrue("Set should be empty", empty.isEmpty());
    assertFalse("Set should be not empty", xs.isEmpty());
  }

  @Test
  public void addTest() {
    xs.add("add something");
    empty.add("test data");
    assertEquals("Length should be 25", 25, xs.size());
    assertEquals("Length should be 1", 1, empty.size());
    try {
      xs.add(null);
    }catch (IllegalArgumentException e){
      assertEquals("Length should be 25", 25, xs.size());
    }
  }

  @Test
  public void containsTest() {
    assertTrue("Set should contain 'ihr'", xs.contains("ihr"));
    assertFalse("Set should not contain 'ihr'", xs.contains("something"));
    assertFalse("Set should't contain anything", empty.contains("something"));
  }

  @Test
  public void removeTest() {
    assertFalse("Nothing to remove", empty.remove("test"));
    assertTrue("Set should be empty", empty.isEmpty());
    empty.add("bla");
    empty.remove("blabla");
    assertTrue("Set should contain 'bla'", empty.contains("bla"));
    assertFalse("Set should be empty", empty.isEmpty());
    empty.remove("bla");
    assertFalse("Set should not contain 'bla'", empty.contains("bla"));
    assertTrue("Set should be empty", empty.isEmpty());
  }

  @Test
  public void forEachTest() {
    Consumer<String> upper = (x) -> x.toUpperCase();
    xs.forEach(upper);
    assertTrue("Set should contain 'IHR'", xs.contains("IHR"));
    //assertFalse("Set should not contain 'ihr'", xs.contains("ihr"));
  }

  @Test
  public void getSortedTest() {
    List list = new ArrayList();
    assertEquals("List should be empty", list, empty.getSorted());
    list.add("test");
    list.add("test2");
    empty.add("test");
    empty.add("test2");
    assertEquals("Lists are not equal", list, empty.getSorted());
    list.remove(1);
    empty.remove("test");
    assertNotEquals("Lists should be not equal", list, empty.getSorted());
  }

  @Test
  public void getComparatorTest() {
    //assertEquals( "$Lambda$2/237852351@5fdef03a", xs.getComparator());
  }

  @Test
  public void toStringTest() {
    String xsString = "[Achtung, damit, Ehre, Freunde, führen, glauben,"
        + " Glaubt, hegt, hören, Hört, ihr, könnt, meine, meiner, mir,"
        + " Mitbürger, Römer, Sache, seid, still, um, und, vor, willen]";
    String emptyString = "[]";
    assertEquals(xsString, xs.toString());
    assertEquals(emptyString, empty.toString());
  }
}