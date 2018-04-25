package Java.AlgorithmsAndDataStructures.Week2;

import java.time.Duration;
import java.time.ZonedDateTime;

/**
 * Created by Andrei on 21.04.2018.
 */
public class HasDups {
  private static boolean hasDups(int[] xs) {
    for (int i = 0; i < xs.length; i++){
      if (linearSearch(xs, i + 1, xs[i])){
        return true;
      }
    }
    return false;
  }

  private static boolean linearSearch(int[] xs, int start, int v) {
    for ( ; start < xs.length; start++){
      if (xs[start] == v){
        return true;
      }
    }
    return false;
  }

  private static boolean hasDupsFaster(int[] xs) {
    for (int i = 0; i < xs.length - 1; i++){
      if (xs[i] == xs[i + 1]){
        return true;
      }
    }
    return false;
  }

  private static int[] populateArray(int n) {
    int[] array = new int[n];
    for (int i = 0; i < array.length; i++){
      array[i] = i;
    }
    return array;
  }

  public static void main (String[] args){
    int[] array1 = populateArray(1000);
    ZonedDateTime before = ZonedDateTime.now();
    System.out.println("Array[1000] -> " + hasDups(array1));
    ZonedDateTime after = ZonedDateTime.now();
    Duration duration = Duration.between(before, after);
    System.out.println("took time: " + duration.toMillis() + " millis");
    System.out.println();


    int[] array2 = populateArray(10000);
    before = ZonedDateTime.now();
    System.out.println("Array[10 000] -> " + hasDups(array2));
    after = ZonedDateTime.now();
    duration = Duration.between(before, after);
    System.out.println("took time: " + duration.toMillis() + " millis");
    System.out.println();

    int[] array3 = populateArray(100000);
    before = ZonedDateTime.now();
    System.out.println("Array[100 000] -> " + hasDups(array3));
    after = ZonedDateTime.now();
    duration = Duration.between(before, after);
    System.out.println("took time: " + duration.toMillis() + " millis");
    System.out.println();

    int[] array4 = populateArray(200000);
    before = ZonedDateTime.now();
    System.out.println("Array[200 000] -> " + hasDups(array4));
    after = ZonedDateTime.now();
    duration = Duration.between(before, after);
    System.out.println("took time: " + duration.toMillis() + " millis");
    System.out.println();

    System.out.println("-------------------------------------------------------");

    int[] array5 = populateArray(1000);
    before = ZonedDateTime.now();
    System.out.println("Quick alg Array[1000] -> " + hasDupsFaster(array5));
    after = ZonedDateTime.now();
    duration = Duration.between(before, after);
    System.out.println("took time: " + duration.toMillis() + " millis");
    System.out.println();


    int[] array6 = populateArray(10000);
    before = ZonedDateTime.now();
    System.out.println("Quick alg Array[10 000] -> " + hasDupsFaster(array6));
    after = ZonedDateTime.now();
    duration = Duration.between(before, after);
    System.out.println("took time: " + duration.toMillis() + " millis");
    System.out.println();

    int[] array7 = populateArray(100000);
    before = ZonedDateTime.now();
    System.out.println("Quick alg Array[100 000] -> " + hasDupsFaster(array7));
    after = ZonedDateTime.now();
    duration = Duration.between(before, after);
    System.out.println("took time: " + duration.toMillis() + " millis");
    System.out.println();

    int[] array8 = populateArray(200000);
    before = ZonedDateTime.now();
    System.out.println("Quick alg Array[200 000] -> " + hasDupsFaster(array8));
    after = ZonedDateTime.now();
    duration = Duration.between(before, after);
    System.out.println("took time: " + duration.toMillis() + " millis");
    System.out.println();

    int[] array9 = populateArray(200000000);
    before = ZonedDateTime.now();
    System.out.println("Quick alg Array[200 000 000] -> " + hasDupsFaster(array9));
    after = ZonedDateTime.now();
    duration = Duration.between(before, after);
    System.out.println("took time: " + duration.toMillis() + " millis");
    System.out.println();
  }
}
