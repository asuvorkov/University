package Java.Algorithms.Week3;

import java.util.Random;
import java.util.Arrays;

/**
 * Vaterklasse zur Implementierung der Sortierverfahren Vorgehen für zum
 * Beispiel SelectionSort
 * <ul>
 * <li>Von Sort erben mit public class SelectionSort extends Sort {...</li>
 * <li>In dieser Klasse SortierAlgorithmus als Methode sort implementieren
 * public void sort(int[] a) {... Achtung: Die Methode sort muss das vohandene
 * Array a verändern!</li>
 * <li>Für Vergleiche von Werten (nicht von Indizes) lt, lte, gt, gte verwenden
 * (int, int), zum Vertauschen swap(a, i, j) verwenden (int[], int, int) oder
 * swap(a, i, b, j) verwenden (int[], int, int[], int)</li>
 * <li>Sortieren eines Feldes zum Beispiel mit dem Aufruf new
 * SelectionSort().run(a);</li>
 * <li>Automatische Überprüfung ob Sortierung korrekt war, Ausgabe der Statistik
 * </li>
 * </ul>
 */
public abstract class Sort {

  /**
   * Die Methode sort sortiert das eigentliche Feld. Diese Methode muss
   * implementiert werden. Das Feld wird "in place" sortiert, das heißt das
   * ursprüngilche Feld muss danach sortiert sein.
   * 
   * @param a
   *          zu sortierendes Feld
   */
  abstract public void sort(int[] a);

  /**
   * Die Methode run muss aufgerufen werden, aber nicht überschrieben werden.
   * 
   * @param a
   *          zu sortiererndes Feld
   */
  final public void run(int[] a) {
    reset();
    dorun(a);
  }

  /**
   * Die Methode runSmall laesst ein paar typische nicht so grosse Probleme
   * laufen.
   */
  final public void runSmall() {
    runSmall(DEFAULT_RAND_MAX);
  }

  /**
   * Die Methode runSmall laesst ein paar typische nicht so grosse Probleme
   * laufen mit begrenzt großen Werten.
   * 
   * @param max
   *          int, obere Grenze der Zufallszahlen
   */
  final public void runSmall(int max) {
    int sizes[] = { 10, 100, 1000, 10000, 20000, 40000, 60000 };
    for (int size : sizes) {
      int a[] = createRandomIntArray(size, max);
      run(a);
    }
  }

  /**
   * Die Methode runLarge laesst ein paar typische grosse Probleme laufen.
   */
  final public void runLarge() {
    runLarge(DEFAULT_RAND_MAX);
  }

  /**
   * Die Methode runLarge laesst ein paar typische grosse Probleme laufen.
   * 
   * @param max
   *          int, obere Grenze der Zufallszahlen
   */
  final public void runLarge(int max) {
    int sizes[] = { 100000, 200000, 400000, 800000, 1000000 };
    for (int size : sizes) {
      int a[] = createRandomIntArray(size, max);
      run(a);
    }
  }

  /**
   * Vergleichsfunktion "less than". Statt < verwenden um Wertevergleiche zu
   * zählen.
   * 
   * @param l
   *          Zahl
   * @param r
   *          Zahl
   * @return wahr gdw l < r
   */
  final public boolean lt(int l, int r) {
    comps++;
    return l < r;
  }

  /**
   * Vergleichsfunktion "less than or equals". Statt <= verwenden um
   * Wertevergleiche zu zählen.
   * 
   * @param l
   *          Zahl
   * @param r
   *          Zahl
   * @return wahr gdw l <= r
   */
  final public boolean lte(int l, int r) {
    comps++;
    return l <= r;
  }

  /**
   * Vergleichsfunktion "less than or equals". Statt <= verwenden um
   * Wertevergleiche zu zählen.
   * 
   * @param l
   *          Zahl
   * @param r
   *          Zahl
   * @return wahr gdw l <= r
   */
  final public boolean equal(int l, int r) {
    comps++;
    return l == r;
  }

  /**
   * Vergleichsfunktion "greater than". Statt > verwenden um Wertevergleiche zu
   * zählen.
   * 
   * @param l
   *          Zahl
   * @param r
   *          Zahl
   * @return wahr gdw l > r
   */
  final public boolean gt(int l, int r) {
    comps++;
    return l > r;
  }

  /**
   * Vergleichsfunktion "greater than or equals". Statt >= verwenden um
   * Wertevergleiche zu zählen.
   * 
   * @param l
   *          Zahl
   * @param r
   *          Zahl
   * @return wahr gdw l >= r
   */
  final public boolean gte(int l, int r) { // größer gleich
    comps++;
    return l >= r;
  }

  /**
   * Setzen einer Zahl in einem Feld. Kein Boundscheck. Zählt Änderungen. Bsp.:
   * Ersetze a[i] = val durch set(a, i, val)
   * 
   * @param a
   *          Feld von Zahlen
   * @param pos
   *          Zielposition
   * @param val
   *          Ausgangsposition der Bewegung
   */
  final public void set(int[] a, int pos, int val) {
    changes++;
    a[pos] = val;
  }

  /**
   * Verschieben einer Zahl in einem Feld. Kein Boundscheck. Zählt Änderungen.
   * Bsp.: Ersetze a[i] = a[j] durch move(a, i, j)
   * 
   * @param a
   *          Feld von Zahlen
   * @param to
   *          Zielposition der Bewegung
   * @param from
   *          Ausgangsposition der Bewegung
   */
  final public void move(int[] a, int to, int from) {
    changes++;
    a[to] = a[from];
  }

  /**
   * Verschieben einer Zahl von einem Feld in ein anderes. Kein Boundscheck.
   * Zählt Änderungen. Bsp.: Ersetze b[i] = a[j] durch move(b, a, i, j)
   * 
   * @param b
   *          Feld von Zahlen (Zielfeld)
   * @param a
   *          Feld von Zahlen (Ausgangsfeld)
   * @param to
   *          Zielposition der Bewegung
   * @param from
   *          Ausgangsposition der Bewegung
   */
  final public void move(int[] b, int[] a, int to, int from) {
    changes++;
    b[to] = a[from];
  }

  /**
   * Vertauschen von zwei Zahlen in einem Feld. Kein Boundscheck. Zählt
   * Änderungen,
   * 
   * @param a
   *          Feld von Zahlen
   * @param i
   *          Position von einer zu tauschender Zahl
   * @param j
   *          Position von anderer zu tauschender Zahl
   */
  final public void swap(int[] a, int i, int j) {
    changes++;
    changes++;
    int h = a[i];
    a[i] = a[j];
    a[j] = h;
  }

  /**
   * Vertauschen von zwei Zahlen in zwei Feldern. Kein Boundscheck. Zählt
   * Änderungen.
   * 
   * @param a
   *          Ein Feld von Zahlen
   * @param i
   *          Position von zu tauschender Zahl in a
   * @param b
   *          anderes Feld von Zahlen
   * @param j
   *          Position von zu tauschender Zahl in b
   */
  final public void swap(int[] a, int i, int[] b, int j) {
    changes++;
    changes++;
    int h = a[i];
    a[i] = b[j];
    b[j] = h;
  }

  // ab hier private Implementierung
  private long comps = 0;
  private long changes = 0;
  private long ticks = 0;

  /**
   * Die Methode setzt die internen Zähler für die Anzahl der Vergleiche und
   * Änderungen zurück. Sollte aufgerufen werden wenn mehrfach ein Benchmarking
   * mit demselben Sort()-Objekt durchgeführt wird.
   */
  private void reset() {
    changes = 0;
    comps = 0;
  }

  private boolean isSorted(int[] a, int[] unsorted) {
    int[] b = new int[unsorted.length];
    System.arraycopy(unsorted, 0, b, 0, unsorted.length);
    Arrays.sort(b); // b ist sortiertes Array von Original
    return Arrays.equals(a, b);
  }

  private void dorun(int[] a) {
    int[] unsorted = new int[a.length];
    System.arraycopy(a, 0, unsorted, 0, a.length); // Kopie, Parameter
                                                   // unverändert
    long startTime = System.nanoTime(); // Zeitmessung starten
    sort(a); // benutzerdefinierte Funktion (beim Erben überschrieben
    long stopTime = System.nanoTime(); // verbrauchte Zeit
    ticks = (stopTime - startTime) / 1000000; // in msecs
    if (isSorted(a, unsorted)) {
      System.out.println("Sorry, Feld ist nicht sortiert!");
      return;
    }
    System.out.format("a[%10d]", a.length);
    System.out.format("%11d Vergleiche", comps);
    System.out.format("%11d Änderungen", changes);
    System.out.format("%8d.%02d secs\n", ticks / 1000, (ticks % 1000) / 10);
  }

  // Parameter für die Zufallszahlengenerierung
  public static final Integer DEFAULT_RAND_MAX = Integer.valueOf(1073741824);
  private static final Integer DEFAULT_SEED = Integer.valueOf(123456);

  /**
   * Erzeugen eines Feld von Zufallszahlen vorgegebener Länge.
   * 
   * @param n
   *          int, wie viele Zufallszahlen
   * @return int[], Feld von Zufallszahlen der Länge n
   */
  public static int[] createRandomIntArray(int n) {
    Random rand = new Random(DEFAULT_SEED * n); // immer dieselben Zahlen!
    int a[] = new int[n];
    for (int i = 0; i < n; i += 1) {
      a[i] = rand.nextInt(DEFAULT_RAND_MAX);
    }
    return a;
  }

  /**
   * Erzeugen eines Feld von Zufallszahlen vorgegebener Länge.
   * 
   * @param n
   *          int, wie viele Zufallszahlen
   * @param max
   *          int, Zufallszahlen zwischen 0 und max (exklusive)
   * @return int[], Feld von Zufallszahlen der Länge n
   */
  public static int[] createRandomIntArray(int n, int max) {
    Random rand = new Random(DEFAULT_SEED * n); // immer dieselben Zahlen!
    int a[] = new int[n];
    for (int i = 0; i < n; i += 1) {
      a[i] = rand.nextInt(max);
    }
    return a;
  }
}