package Java.Algorithms.Week7;

/**
 * Created by Andrei on 26.05.2018.
 */
public class SortedLinkedList<K extends Comparable<K>, T> {
  Node head;
  Node tail;

  public class Node {
    T obj;
    K key;
    Node next;

    public Node(K key, T obj) {
      this.obj = obj;
      this.key = key;
    }
  }

  public SortedLinkedList() {
    head = new Node(null, null);
    tail = new Node(null, null);
    head.next = tail;
  }

  public String toString() {
    StringBuilder s = new StringBuilder();
    s.append("");

    Node currentNode = head;
    if (currentNode.obj != null) {
      s.append("(")
          .append(head.key)
          .append(":")
          .append(head.obj)
          .append(")");


      currentNode = currentNode.next;
      while (currentNode.key != null) {
        s.append(" -> ")
            .append("(")
            .append(currentNode.key)
            .append(":")
            .append(currentNode.obj)
            .append(")");

        currentNode = currentNode.next;
      }
    }
    return s.toString();
  }

  public void add(K key, T obj) {
    if (head.key == null || head.key.compareTo(key) > 0){
      if (head.key == null){
        head.key = key;
        head.obj = obj;
      }else {
        Node temp = new Node(key, obj);
        temp.next = head;
        head = temp;
      }
    } else {
      Node currentNode = head;
      while (currentNode.next != null && currentNode.next.key != null
          && currentNode.next.key.compareTo(key) < 0){
        currentNode = currentNode.next;
      }

      if (currentNode.key == null){
        currentNode.key = key;
        currentNode.obj = obj;
        currentNode.next = new Node(null, null);
      }else {
        if (currentNode.next == null){
          currentNode.next = new Node(key, obj);
          currentNode.next.next = new Node(null, null);
        }else {
          Node temp = new Node(key, obj);
          temp.next = currentNode.next;
          currentNode.next = temp;
        }

      }
    }
  }

  public void remove(K key) {
    if (head != null || head.key != null){
      Node currentNode = head;

      if (currentNode.key.compareTo(key) == 0){
        while (head != null && head.next != null
            && head.key.compareTo(key) == 0){
          head = head.next;
        }
      }

      while (currentNode.next != null && currentNode.next.key != null
          && key.compareTo(currentNode.next.key) != 0){
        currentNode = currentNode.next;
      }
      while (currentNode.next != null && currentNode.next.key != null
          && key.compareTo(currentNode.next.key) == 0){
        currentNode.next = currentNode.next.next;
      }
    }
  }

  public static void main (String[] args){
    SortedLinkedList l = new SortedLinkedList();
    System.out.println("Add");
    l.add(1, "obj1");
    l.add(2, "obj2");
    l.add(3, "obj3");
    System.out.println(l.toString());

    System.out.println("--------------------------------");
    System.out.println("Remove");
    l.remove(4);
    System.out.println(l.toString());

    System.out.println("Merge:");
    SortedLinkedList other = new SortedLinkedList();
    other.add(1, "other");
    other.add(1, "other");
    other.add(4, "other");
    other.add(5, "other");

    l.merge(other);
    System.out.println(l.toString());
  }

  public void merge(SortedLinkedList<K,T> other) {
    Node currentNodeThis = head;
    Node currentNodeThat = other.head;

    if (currentNodeThis.key == null){
      head = currentNodeThat;
    }else {

      while (currentNodeThis.key != null && currentNodeThat.key != null) {
        if (currentNodeThis.next.key == null ||
            currentNodeThat.key.compareTo(currentNodeThis.next.key) < 1) {
          Node temp = new Node(currentNodeThat.key, currentNodeThat.obj);
          temp.next = currentNodeThis.next;
          currentNodeThis.next = temp;
          currentNodeThat = currentNodeThat.next;
        }
        currentNodeThis = currentNodeThis.next;
      }
    }
  }
}
