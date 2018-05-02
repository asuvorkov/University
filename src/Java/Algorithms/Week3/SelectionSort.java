package Java.Algorithms.Week3;

/**
 * Created by Andrei on 01.05.2018.
 */
public class SelectionSort extends Sort{
  public void sort(int[] a) {
    // FIXME: implement
    for (int i = 0; lt(i, a.length - 1); i++){
      int index = i;
      for (int j = i + 1; lt(j, a.length); j++) {
        if (gt(a[j], a[index])) {
          index = j;
        }
      }
      swap(a, index, i);
    }
  }

  public static void main(String[] args) {
    SelectionSort sort = new SelectionSort();
    sort.runSmall(1000);

    /*
    Array n length has -> n^2 compares and n*2 changes
     */
  }
}
