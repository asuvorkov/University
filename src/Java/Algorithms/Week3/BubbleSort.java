package Java.Algorithms.Week3;

/**
 * Created by Andrei on 01.05.2018.
 */
public class BubbleSort extends Sort {
  @Override
  public void sort(int[] a) {
    // FIXME: implement
    for(int i = 0; lt(i, a.length); i++){
      for(int j = 1; lt(j, (a.length - i)); j++){
        if(lt(a[j - 1], a[j])){ // descend
          //gt(a[j - 1], a[j]) ascend
          swap(a, j, j - 1);
        }
      }
    }
  }

  public static void main(String[] args) {
    BubbleSort bs = new BubbleSort();
    bs.runSmall(1000);

    /*
    Array n length has -> n^2 compares and n^2/2 changes
     */
  }
}
