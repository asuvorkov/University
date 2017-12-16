package Java.OOP.Week8;

import java.util.Comparator;
import java.util.function.Consumer;
import java.util.function.Predicate;

/**
 * Created by Andrei on 16.12.2017.
 */
public class AL<E> implements List<E> {
  public static void main(String[] args){
    AL test1 = new AL<>(5, 6, 3, 1);
    test1.remove(1);
    for (int i = 0; i < test1.size; i++){
      System.out.print(test1.get(i) + "  ");
    }
    System.out.println("size ---> " + test1.size);

    test1.addAll(new AL(8, 9, 12));
    for (int i = 0; i < test1.size; i++){
      System.out.print(test1.get(i) + "  ");
    }
    System.out.println("size ---> " + test1.size);
    test1.add("world");
    test1.insert(-1, 10);
    test1.add("hello");
    for (int i = 0; i < test1.size; i++){
      System.out.print(test1.get(i) + "  ");
    }
    System.out.println("size ---> " + test1.size);

    System.out.println(test1.contains(1));

    test1.reverse();
    for (int i = 0; i < test1.size; i++){
      System.out.print(test1.get(i) + "  ");
    }
    System.out.println("size ---> " + test1.size);

    AL t = (AL) test1.sublist(5, 30);
    for (int i = 0; i < t.size; i++){
      System.out.print(t.get(i) + "  ");
    }
    System.out.println("size ---> " + t.size);

    t.addAll(test1);
    System.out.println(test1.endsWith(t));

  }

  protected E[] store = (E[]) new Object[5];
  protected int size = 0;

  public AL(E... es) {
    for (E e : es)
      add(e);
  }

  private void mkNewStore() {
    E[] newStore = (E[]) new Object[size + 5];
    for (int i = 0; i < store.length; i++)
      newStore[i] = store[i];
    store = newStore;
  }

  public void add(E e) {
    if (store.length <= size)
      mkNewStore();
    store[size++] = e;
  }

  @Override
  public void addAll(List<E> cs) {
    for (int i = 0; i < cs.size(); i++) {
      this.add(cs.get(i));
    }
  }

  @Override
  public void remove(int i) {
    if (i == size){
      size--;
    }
    if (i < size && i > -1){
      System.arraycopy(store, i + 1, store, i, size - 1 - i);
      store[size - 1] = null;
      size--;
    }
  }

  @Override
  public void insert(int i, E e) {
    if (i >= size){
      this.add(e);
    }
    if (i > -1 && i < size){
      this.add(e);
      System.arraycopy(store, i, store, i + 1, size - i);
      store[i] = e;
    }
  }

  @Override
  public boolean contains(E e) {
    for (int i = 0; i < size; i++){
      if (store[i] == e){
        return true;
      }
    }
    return false;
  }

  @Override
  public boolean containsWith(Predicate<E> pred) {
    for(int i = 0; i < size; i++){
      if (store[i].getClass() == pred.getClass()){
        return true;
      }
    }
    return false;
  }

  @Override
  public void reverse() {
    for(int i = 0; i < size / 2; i++){
      E temp = store[i];
      store[i] = store[size - i - 1];
      store[size - i - 1] = temp;
    }
  }

  @Override
  public void forEach(Consumer<? super E> consumer) {
    for (int i = 0; i < size; i++){
      store[i] = (E) consumer;
    }
  }

  @Override
  public boolean startsWith(List<E> that) {
    for (int i = 0; i < that.size(); i++){
      if (store[i] != that.get(i)) return false;
    }
    return true;
  }

  @Override
  public boolean endsWith(List<E> that) {
    if (that.size() > size) return false;
    int temp = size - that.size() - 1;
    if (that.size() == size) temp = 0;
    for (int i = temp; i < that.size(); i++){
      if (store[i] != that.get(i)) return false;
    }
    return true;
  }

  @Override
  public List<E> sublist(int i, int l) {
    List<E> output = new AL<>();
    for (int a = i; a < size && l > 0; a++, l--){
      output.add(store[a]);
    }
    return output;
  }

  @Override
  public void sortBy(Comparator<? super E> cmp) {

  }

  public int size() {
    return size;
  }

  public E get(int i) {
    return store[i];
  }
}
