package Java.AlgorithmsAndDataStructures.Week2;

public class MaxSubArray {
  /*
   * Implementierung des MaxSubArray-Problems.
   * maxsubArray() sollte ein Array der Länge 3 zurückliefern. Dieses
   * Array 'result' enthält folgende Einträge:
   * - result[0]: das linke Ende des optimalen Sub-Arrays
   * - result[1]: das rechte Ende des optimalen Sub-Arrays
   * - result[2]: die Summe der Werte innerhalb des Subarray, d.h.
   *              a[result[0]] + ... + a[result[1]]
   */

  private static int[] maxSubArray(int[] a) {
    // FIXME: implement
    if (a.length == 0){
      return new int[] {-1, -1, Integer.MIN_VALUE};
    }
    int[] maxSum = new int[3];
    maxSum[2] = Integer.MIN_VALUE;
    for (int i = 0; i < a.length; i++){
      int temp = 0;
      for (int j = i; j < a.length; j++){
        temp += a[j];
        if (temp > maxSum[2]){
          maxSum[0] = i;
          maxSum[1] = j;
          maxSum[2] = temp;
        }
      }
    }
    return maxSum;
  }

  // Führe den Algorithmus durch für Daten aus maxsubsimple.dat oder maxsub.dat
  public static void runTest(String filename) {
    int[] values = ADSTool.readIntArray(filename);
    int[] result;
    long startTime, endTime, ms;

    startTime = System.nanoTime();
    result = maxSubArray(values);
    endTime = System.nanoTime();
    ms = (endTime - startTime) / 1000000; // in msecs

    System.out.format("(slow) %d values solution=(%d,%d,%d) took: %.2f ms\n",
        values.length,
        result[0], result[1],
        result[2], (double)ms);
  }

  public static void main(String[] args) {
    runTest("C:/Users/Andrei/Desktop/University/src/Java/AlgorithmsAndDataStructures/Week2/maxsubsimple.dat");
    runTest("C:/Users/Andrei/Desktop/University/src/Java/AlgorithmsAndDataStructures/Week2/maxsub.dat");

    int[] a = maxSubArray(new int[] {-31,-41,-59,-26,-53,-58,-97,-93,-23,-84});
    System.out.println();
    System.out.println("start " + a[0]);
    System.out.println("end " + a[1]);
    System.out.println("sum " + a[2]);
  }
}