package Java.Algorithms.Week2;

/**
 * Created by Andrei on 21.04.2018.
 */
public class MaxSubArrayQuick {
  /*
   * Implementierung des MaxSubArrayQuick-Problems.
   * maxsubArray() sollte ein Array der Länge 3 zurückliefern. Dieses
   * Array 'result' enthält folgende Einträge:
   * - result[0]: das linke Ende des optimalen Sub-Arrays
   * - result[1]: das rechte Ende des optimalen Sub-Arrays
   * - result[2]: die Summe der Werte innerhalb des Subarray, d.h.
   *              a[result[0]] + ... + a[result[1]]
   */

  public static int[] maxSubArray(int[] a) {
    // FIXME: implement
    if (a.length == 0){
      return new int[] {-1, -1, Integer.MIN_VALUE};
    }

    int start = 0;
    int end = 0;
    int max = a[0];

    int currentSum = 0;
    int startLoop = 0;

    for (int index = 0; index < a.length; index++) {
      currentSum += a[index];

      // Check for a new record
      if (currentSum > max) {
        max = currentSum;
        start = startLoop;
        end = index;
      }

      // If we're below zero than there's no need to continue on this path.
      if (currentSum <= 0) {
        currentSum = 0;
        startLoop = index + 1;
      }
    }

    int[] output = new int[3];
    output[0] = start;
    output[1] = end;
    output[2] = max;
    return output;
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
    runTest("maxsubsimple.dat");
    runTest("maxsub.dat");
    runTest("maxsub.dat");
  }
}
