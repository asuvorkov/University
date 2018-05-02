package Java.Algorithms.Week3;

/**
 * Created by Andrei on 01.05.2018.
 */
public class TernarySearch {
  private static int ternarySearch(int[] a, int value) {
    // FIXME: implement
    return ternarySearch(a, value, 0, a.length - 1);
  }

  private static int ternarySearch(int[] array, int value, int start, int end) {
    if (start > end){
      return -1;
    }

    // Delete interval from start to end on 3 parts
    int mid1 = start + (end - start) / 3;
    int mid2 = start + 2 * (end - start) / 3;

    if (array[mid1] == value){
      return mid1;
    }else {
      if (array[mid2] == value){
        return mid2;
      }else {
        if (value < array[mid1]){ //search in 1st third
          return ternarySearch(array, value, start, mid1 - 1);
        }else {
          if (value > array[mid2]){ //search in 3st third
            return ternarySearch(array, value, mid2 + 1, end);
          }else { //search in 2st third
            return ternarySearch(array, value, mid1, mid2);
          }
        }
      }
    }
  }

  public static void main(String[] args){
    int[] array = new int[100];
    for (int i = 0; i < 100; i++){
      array[i] = i;
    }
    System.out.println(ternarySearch(array, 39));
  }
}
