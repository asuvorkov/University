package Java.Algorithms.Week4;

/**
 * Created by Andrei on 08.05.2018.
 */
public class MergeSort {
  private int[] b;

  private void sort(int[] a) {
    this.b = new int[a.length];
    sort(a, 0, a.length - 1);
  }

  private void sort(int[] a, int left, int right) {
    if (right <= left){
      ;
    }else {
      int m = (left + right) / 2;
      sort(a, left, m);
      sort(a, m + 1, right);
      merge(a, left, m, right);
    }
  }

  public static void main (String[] args){
    MergeSort mergeSort = new MergeSort();
    int[] a = {6, 1, 3, 4, 8, 7, 2, 9};
    mergeSort.sort(a);

    for (Integer el : a) {
      System.out.println(el);
    }
  }

  private void merge(int[] a, int left, int m, int right){
    int target = left;
    int p1 = left;
    int p2 = m + 1;

    while (p1 <= m || p2 <= right) {
      //right half complete -> use element from left half
      if (p2 > right){
        b[target++] = a[p1++];
        // left half complete  -> use element from right half
      }else if (p1 > m) {
        b[target++] = a[p2++];
        //use element from left half
      }else if (a[p1] < a[p2]) {
        b[target++] = a[p1++];
        //use element from right half
      }else {
        b[target++] = a[p2++];
      }
    }

    for (int i = left; i <= right; i++){
      a[i] = b[i];
    }
  }
}
