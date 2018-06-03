package Java.Algorithms.Week7;

/**
 * Created by Andrei on 29.05.2018.
 */
public class DLinkedList<T> {
  Node head;
  Node tail;

  public class Node {
    T obj;
    Node next;
    Node prev;

    public Node(T obj) {
      this.obj = obj;
    }
  }

  public DLinkedList() {
    head = new Node(null);
    tail = new Node(null);
    head.next = tail.prev;
    tail.prev = head.next;
  }

  public void addFirst(T obj) {
    /* allocate node */
    Node new_node = new Node(obj);

    /* since we are adding at the beginning,
     prev is always NULL */
    new_node.prev = null;

    /* link the old list off the new node */
    new_node.next = head;

    /* change prev of head node to new node */
    if (head != null) {
      head.prev = new_node;
    }

    /* move the head to point to the new node */
    head = new_node;
  }

  public void revert() {
    Node temp = null;
    Node current = head;

    /* swap next and prev for all nodes of
     doubly linked list */
    while (current != null) {
      temp = current.prev;
      current.prev = current.next;
      current.next = temp;
      current = current.prev;
    }

    /* Before changing head, check for the cases like empty
     list and list with only one node */
    if (temp != null) {
      head = temp;
    }
  }

  public static void main (String[] args){
    DLinkedList l = new DLinkedList();
    System.out.println("Add");
    l.addFirst("obj1");
    l.addFirst("obj2");
    l.addFirst("obj3");
    System.out.println(l.toString());

    System.out.println("--------------------------------");
    System.out.println("Revert");
    l.revert();
    System.out.println(l.toString());
  }

  public String toString() {
    StringBuilder s = new StringBuilder();
    s.append("");

    Node currentNode = head;
    if (currentNode != null) {
      s.append(head.obj);

      currentNode = currentNode.next;
      while (currentNode != null && currentNode.obj != null) {
        s.append(" -> ")
            .append(currentNode.obj);

        currentNode = currentNode.next;
      }

      String[] parts = s.toString().split(" ");
      s.append(" ( ");
      for (int i = parts.length - 1; i >= 0; i--){
        s.append(parts[i]).append(" ");
      }
      s.append(")");
    }
    return s.toString();
  }
}
