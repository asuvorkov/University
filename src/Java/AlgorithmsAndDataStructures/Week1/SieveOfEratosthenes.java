package Java.AlgorithmsAndDataStructures.Week1;

/**
 * Created by Andrei on 15.04.2018.
 */
class SieveOfEratosthenes {
  static boolean[] sieve(int n) {
    boolean[] result = new boolean[n + 1];
    result[0] = false;
    result[1] = false;
    for (int a = 2; a < result.length; a++){
      result[a] = true;
    }

    for (int i = 2; i < result.length; i++){
      if (result[i]){
        for (int j = i * i; j < result.length; j = j + i){
          result[j] = false;
        }
      }
    }
    return result;
  }

  public static void main(String[] args) {
    boolean[] b = sieve(10000);
    int count = 0;
    for (int i = 2; i < b.length; i++) {
      if (b[i]){
        System.out.println((i) + " is primary");
        count++;
      }
    }
    System.out.println("\n" + count + " Primary numbers");
  }
}
