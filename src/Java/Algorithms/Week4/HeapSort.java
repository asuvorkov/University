package Java.Algorithms.Week4;

/**
 * Created by Andrei on 08.05.2018.
 */
public class HeapSort {
  private int leftChild(int i) {
    return 2 * i + 1;
  }

  private int rightChild(int i) {
    return 2 * i + 2;
  }

  private void min_heapify(int[] a, int n, int i) {
    // Find smallest among root, left child and right child
    int smallest = i;
    int l = leftChild(i);
    int r = rightChild(i);

    if (l < n && a[l] < a[smallest]) {
      smallest = l;
    }

    if (r < n && a[r] < a[smallest]) {
      smallest = r;
    }

    // Swap and continue heapifying if root is not largest
    if (smallest != i) {
      int swap = a[i];
      a[i] = a[smallest];
      a[smallest] = swap;

      min_heapify(a, n, smallest);
    }
  }

  private void build_min_heap(int[] a) {
    for (int i = a.length / 2 - 1; i >= 0; i--) {
      min_heapify(a, a.length, i);
    }
  }

  public void sort(int[] a) {
    build_min_heap(a);

    // Heap sort
    for (int i = a.length - 1; i >= 0; i--) {
      int temp = a[0];
      a[0] = a[i];
      a[i] = temp;

      // Heapify root element
      min_heapify(a, i, 0);
    }
  }

  static void printArray(int arr[]) {
    for (int anArr : arr) System.out.print(anArr + " ");
    System.out.println();
  }

  public static void main(String args[]) {
    int arr[] = {1,12,9,5,6,10};

    HeapSort hs = new HeapSort();
    hs.sort(arr);
    int[] k = hs.sortBestK(arr, 2);

    System.out.println("Sorted array is:");
    printArray(arr);

    System.out.println();

    System.out.println("Sorted k array is:");
    printArray(k);
  }

  // HeapSort-Variante: Beste K Werte
  public int[] sortBestK(int[] a, int k) {
    build_min_heap(a);

    // Heap sort
    for (int i = a.length - 1; i >= a.length - 1 - k; i--) {
      int temp = a[0];
      a[0] = a[i];
      a[i] = temp;

      // Heapify root element
      min_heapify(a, i, 0);
    }

    int[] output = new int[k];
    for (int i = 0; i < k; i++){
      output[i] = a[a.length - 1 - i];
    }
    return output;
  }
}
