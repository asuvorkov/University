package name.panitz.util;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.util.Arrays;
import java.util.Collection;

import org.junit.Before;
import org.junit.Test;


public class ALTest {
  private static class A<E> extends AL<E>{
    A(E... es){super(es);}

    @Override
    public boolean equals(Object obj) {
      if (this == obj)
        return true;
      if (obj == null)
        return false;
      if (!(obj instanceof AL))
        return false;
      AL other = (AL) obj;
      if (size != other.size)
        return false;
      for (int i=0;i<size;i++)if (!store[i].equals(other.store[i]))
        return false;
      return true;
    }

    @Override
    public String toString() {
      if (size()==0) return "[]";
      StringBuffer result = new StringBuffer("[");
      forEach(x -> result.append(x+", "));
      result.replace(result.length()-2,result.length(), "]");
      return result.toString();
    }


    @Override
    public int hashCode() {
      final int prime = 31;
      int result = 1;
      result = prime * result + size;
      result = prime * result + Arrays.hashCode(store);
      return result;
    }


  }
  
  AL<String> xs;
  AL<String> ys;
  AL<Integer> is;

  @Before
  public void setUp() throws Exception {
    xs = new A<>();
    ys = new A<>();
    is = new A<>();
  }

  @Test
  public void testAdd1() {
    assertEquals("Konstruktor erzeugt leere Liste", 0, xs.size());
    xs.add("hallo");
    assertEquals(1, xs.size());
    assertEquals("hallo", xs.get(0));
    xs.add("welt");
    assertEquals(2, xs.size());
    assertEquals("hallo", xs.get(0));
    assertEquals("welt", xs.get(1));
  }

  @Test
  public void testAdd2() {
    is.add(1);
    is.add(1);
    is.add(1);
    is.add(1);
    is.add(1);
    is.add(1);
    is.add(1);
    is.add(1);
    is.add(1);
    is.add(1);
    is.add(1);
    is.add(1);
    is.add(1);
    assertEquals(13, is.size());
  }

  @Test
  public void testRemove1() {
    xs.add("hallo");
    xs.remove(0);
    assertEquals(0, xs.size());
  }

  @Test
  public void testRemove2() {
    xs.add("hallo");
    xs.add("welt");
    xs.remove(0);
    assertEquals(1, xs.size());
    assertEquals("welt", xs.get(0));
  }

  @Test
  public void testRemove3() {
    xs.add("hallo");
    xs.add("welt");
    xs.remove(1);
    assertEquals(1, xs.size());
    assertEquals("hallo", xs.get(0));
  }

  @Test
  public void testRemove4() {
    xs.add("hallo");
    xs.add("welt");
    xs.add("hello");
    xs.remove(0);
    assertEquals(2, xs.size());
    assertEquals("hello", xs.get(1));
  }

  @Test
  public void testRemove5() {
    xs.add("hallo");
    xs.add("welt");
    xs.add("hello");
    xs.add("illja");
    xs.remove(0);
    assertEquals(3, xs.size());
    assertEquals("illja", xs.get(2));
  }

  @Test
  public void testRemove6() {
    xs.add("hallo");
    xs.add("welt");
    xs.add("hello");
    xs.add("illja");
    xs.remove(10);
    assertEquals(4, xs.size());

  }

  @Test
  public void testRemove7() {
    xs.add("hallo");
    xs.add("welt");
    xs.add("hello");
    xs.add("illja");
    xs.remove(1);

    ys.add("hallo");
    ys.add("hello");
    ys.add("illja");

    assertEquals(ys, xs);
  }

  @Test
  public void testAddAll1() {
    List<String> cs = new A<>("hallo", "freunde", "hallo", "what");
    xs.addAll(cs);
    assertEquals(4, xs.size());
    assertEquals("hallo", xs.get(0));
    assertEquals("freunde", xs.get(1));
    assertEquals("hallo", xs.get(2));
    assertEquals("what", xs.get(3));
  }

  @Test
  public void testAddAll2() {
    List<String> cs = new A<>();
    xs.addAll(cs);
    assertEquals(0, xs.size());
  }

  @Test
  public void testContains1() {
    assertFalse(xs.contains(""));
    assertFalse(xs.contains(""));
    xs.add("hallo");
    xs.add("freunde");
    xs.add("welt");
    xs.add("hello");
    assertTrue(xs.contains("freunde"));
    xs.add("illja");
    assertFalse(xs.contains("friends"));
  }

  
  @Test
  public void testContainsWith1() {
    assertFalse(xs.containsWith(x -> true));
    xs.add("freunde");
    assertFalse(xs.containsWith(x -> false));
    assertTrue(xs.containsWith(x -> true));
    assertTrue(xs.containsWith(x -> x.equals("freunde")));
    assertFalse(xs.containsWith(x -> x.equals("friends")));
  }
  @Test
  public void testContainsWith2() {
    assertFalse(xs.containsWith(x -> true));
    assertFalse(xs.containsWith(x -> false));
    assertFalse(xs.containsWith(x -> x.equals("freunde")));
    assertFalse(xs.containsWith(x -> x.equals("friends")));
  }

  
  @Test
  public void testSublist1() {
    xs.add("A");
    xs.add("B");
    xs.add("C");
    xs.add("D");
    xs.add("E");
    xs.add("F");
    xs.add("G");
    ys.add("C");
    ys.add("D");
    ys.add("E");
    assertEquals(ys, xs.sublist(2, 3));
  }
  @Test
  public void testSublist3() {
    List<Integer> is1 = new A<>(1,2,3,4,5,6,7,8,9,10);
    List<Integer> is2 = new A<>(2,3,4,5,6,7,8,9,10);
    assertEquals(is1,is1.sublist(0, 10));
    assertEquals(is1,is1.sublist(0, 100));
    assertEquals(is2,is1.sublist(1, 9));
    is2 = new A<>(5,6,7);
    assertEquals(is2,is1.sublist(4, 3));
  }
  
  
  @Test
  public void testSublist2() {
    assertEquals(ys, xs.sublist(2, 4));
  }

  
  @Test
  public void reverse1() {
    AL<Integer> is = new A<>(1,2,3,4,5,6);
    is.reverse();
    assertEquals(is,new A<>(6,5,4,3,2,1));
  }

  @Test
  public void reverse2() {
    AL<Integer> is = new A<>(1,2,3,4,5);
    is.reverse();
    assertEquals(is,new A<>(5,4,3,2,1));
  }

  @Test
  public void reverse3() {
    AL<Integer> is = new A<>(5);
    is.reverse();
    assertEquals(is,new A<>(5));
  }

  @Test
  public void testSortBy1() {
    is.add(1);
    is.add(31);
    is.add(14);
    is.add(14324);
    is.add(-1);
    is.add(0);
    is.add(12);
    is.add(-43211);
    is.add(11);
    is.add(1443);
    is.add(321);
    is.add(12);
    is.add(12);
    AL<Integer> result = new A<>();
    result.add(-43211);
    result.add(-1);
    result.add(0);
    result.add(1);
    result.add(11);
    result.add(12);
    result.add(12);
    result.add(12);
    result.add(14);
    result.add(31);
    result.add(321);
    result.add(1443);
    result.add(14324);
    is.sortBy((Integer x, Integer y) -> x - y);
    assertEquals(result, is);
  }

  @Test
  public void testSortBy2() {
    AL<String> xs = new A<>("aber","wer","wird","denn","gleich","wer");
    xs.sortBy((x,y)->x.compareTo(y));
    assertEquals(xs, new A<>("aber", "denn", "gleich", "wer", "wer", "wird"));
  }
  @Test
  public void testSortBy3() {
    AL<String> xs = new A<>("aber");
    xs.sortBy((x,y)->x.compareTo(y));
    assertEquals(xs, new A<>("aber"));
  }
  @Test
  public void testSortBy4() {
    AL<String> xs = new A<>();
    xs.sortBy((x,y)->x.compareTo(y));
    assertEquals(xs, new A<>());
  }
  @Test
  public void testStartsWith1(){
    assertTrue(new A<Integer>().startsWith(new A<Integer>()));
  }
  @Test
  public void testStartsWith2(){
    assertTrue(new A<Integer>(1).startsWith(new A<Integer>()));
  }
  @Test
  public void testStartsWith3(){
    assertFalse(new A<Integer>().startsWith(new A<Integer>(1)));
  }
  @Test
  public void testStartsWith4(){
    assertTrue(new A<>("A","B","C","D","E","F").startsWith(new A<>("A","B","C","D","E")));
  }

  @Test
  public void testEndsWith1(){
    assertTrue(new A<Integer>().endsWith(new A<Integer>()));
  }
  @Test
  public void testEndsWith2(){
    assertTrue(new A<Integer>(2).endsWith(new A<Integer>()));
  }
  @Test
  public void testEndsWith3(){
    assertFalse(new A<Integer>().endsWith(new A<Integer>(1)));
    }
  @Test
  public void testInsert1(){
    List<Integer> is = new A<>();
    is.insert(0, 1);
    assertEquals(new A<Integer>(1),is);
  }
  @Test
  public void testInsert2(){
    List<Integer> is = new A<>(1,2,3,4,5,6);
    is.insert(3, 33);
    assertEquals(new A<Integer>(1,2,3,33,4,5,6),is);
  }
  @Test
  public void testInsert3(){
    List<Integer> is = new A<>(1,2,3,4,5,6);
    is.insert(10, 33);
    assertEquals(new A<Integer>(1,2,3,4,5,6,33),is);
  }
  @Test
  public void testInsert4(){
    List<Integer> is = new A<>(1,2,3,4,5,6);
    is.insert(-1, 33);
    assertEquals(new A<Integer>(1,2,3,4,5,6),is);
  }

}
