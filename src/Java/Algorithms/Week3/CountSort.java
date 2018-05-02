package Java.Algorithms.Week3;

/**
 * Created by Andrei on 01.05.2018.
 */
public class CountSort extends Sort{
  @Override
  public void sort(int[] a) {
    int[] b = new int[searchM(a) + 1];

    for (int i = 0; lt(i, b.length); i++){
      int counter = 0;
      for (int j = 0; lt(j, a.length); j++){
        if (equal(a[j], i)){
          counter++;
        }
      }
      set(b, i, counter);
    }

    int index = 0;
    for (int i = b.length - 1; gt(i, -1); i--){
      while (gt(b[i], 0)){
        set(a, index, i);
        index++;
        set(b, i, b[i] - 1);
      }
    }
  }

  private int searchM(int[] a) {
    int output = 0;
    for (int i = 0; lt(i, a.length); i++){
      if (gt(a[i], output)){
        output = a[i];
      }
    }
    return output;
  }

  public static void main(String[] args) {
    CountSort sort = new CountSort();

    sort.runSmall(10);
    System.out.println();
    sort.runSmall(20);
    System.out.println();
    sort.runSmall(40);
    System.out.println();
    sort.runSmall(80);

    /*
    Array n length has -> 20*m*n compares and n*2 + m changes
     */
  }
}
