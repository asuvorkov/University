package Java.Algorithms.Week2;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.Reader;
import java.io.UnsupportedEncodingException;
import java.io.Writer;
import java.util.ArrayList;

/**
 * Algorithmen und DatenStrukturen (ADS) Tool: Hilfsroutinen, hauptsächlich
 * Ein-/Ausgabe, für Aufgaben im Praktikum.
 * <ul>
 * <li>Lesen und Speichern von Dateien als Feld(Array) von bytes, ints, doubles,
 * BigInteger, Strings und 2D Felder von ints oder doubles</li>
 * <li>Lesen, Speichern und Anzeigen von pgm Dateien (Graustufenbilder)</li>
 * <li>Einfache Datei-Operationen</li>
 * <li>Messen von Ausführungszeiten</li>
 * <li>Berechnen von Hashes</li>
 * <li>Visualisierung von einfachen x,y Plots</li>
 * <li>AusfÃ¼hren und Annehmen von einfachen HTTP-Anfragen</li>
 * </ul>
 * 
 * @author pb
 */
public class ADSTool {

	private ADSTool() {
	} // es sind keine Instanzen erlaubt

	// gen and show
	private static String error(String method, String msg, Object... args) {
		String message = "ADSTool::" + method + ": " + msg;
		message = String.format(message, args);
		System.err.println(message);
		return message;
	}

	// Datei-Operationen

	/**
	 * Test ob Datei unter angegebenem Pfad existiert und lesbar ist
	 * 
	 * @param filename
	 *            String, zu testender Dateiname
	 * @return boolean, true gdw existiert und ist lesbar
	 */
	public static boolean fileExists(String filename) {
		File file = new File(filename);
		return file.canRead();
	}

	/**
	 * Stellt sicher, dass Verzeichnis vorhanden ist, erzeugt es wenn nÃ¶tig
	 * 
	 * @param directory
	 *            String, Verzeichnis, dessen Existenz sicherzustellen ist
	 * @return boolean, true gdw Verzeichnis wurde erstellt
	 * @throws RuntimeException
	 *             , gdw kann nicht erstellt werden oder kein Zugriff
	 */
	public static boolean ensureDir(String directory) {
		File dir = new File(directory);
		if (dir.exists()) {
			if (dir.isDirectory()) {
				return false;
			}
			String msg = error("ensureDir",
					"existiert, %s ist kein Verzeichnis", directory);
			throw new RuntimeException(msg);
		}
		if (!dir.mkdirs()) {
			String msg = error("ensureDir", "%s konnte nicht erstellt werden",
					directory);
			throw new RuntimeException(msg);
		}
		return true;
	}

	// Eingabe/Ausgabe Operationen

	/**
	 * VollstÃ¤ndiges Einlesen einer Datei als Feld von Bytes.
	 * 
	 * @param filename
	 *            String, einzulesende Datei
	 * @return byte[], Feld der eingelesenen bytes
	 */
	public static byte[] readByteArray(String filename) {
		File file = new File(filename);
		if (file.length() > Integer.MAX_VALUE) {
			String msg = error("readByteArray", "File %s zu groß", filename);
			throw new RuntimeException(msg);
		}
		byte[] ret = new byte[(int) file.length()];
		try {
			InputStream is = new FileInputStream(file);
			int offset = 0;
			while (offset < ret.length) {
				int read = is.read(ret, offset, ret.length - offset);
				if (read < 0) {
					is.close();
					String msg = error("readByteArray",
							"Fehler beim Lesen von %s", filename);
					throw new RuntimeException(msg);
				}
				offset += read;
			}
			if (offset < ret.length) {
				is.close();
				String msg = error("readByteArray",
						"Lesen von %s nicht vollstÃ¤ndig", filename);
				throw new RuntimeException(msg);
			}
			is.close();
			return ret;
		} catch (IOException e) {
			String msg = error("readByteArray", "I/O-Fehler bei %s", filename);
			throw new RuntimeException(msg);
		}
	}

	/**
	 * Byte-Feld in Datei abspeichern.
	 * 
	 * @param filename
	 *            String, Dateiname der Text-Datei
	 * @param bytes
	 *            byte[], Feld aus Bytes
	 * @throws RuntimeException
	 *             bei I/O oder anderem Fehler
	 */
	public static void saveByteArray(String filename, byte[] bytes) {
		OutputStream out = null;
		try {
			out = new FileOutputStream(filename);
			out.write(bytes);
			out.close();
		} catch (Exception e) {
			String msg = error("saveByteArray", "bei %s\n " + e.getMessage(),
					filename);
			throw new RuntimeException(msg);
		} finally {
			if (out != null) {
				try {
					out.close();
				} catch (Exception ignore) {
				}
			}
		}
	}

	/**
	 * Zeilenweises Einlesen einer UTF-8 Datei. Kommentare und Leerzeilen werden
	 * ignoriert.
	 * 
	 * @param filename
	 *            String, einzulesende Datei
	 * @return ArrayList<String>, Liste der eingelesenen Strings
	 */
	private static ArrayList<String> readStringArrayList(String filename) {
		try {
			ArrayList<String> lines = new ArrayList<String>();
			// Kein Scanner, wÃ¤re viel zu langsam
			// Erzwingen von UTF-8 (sonst komische Ergebnisse unter Windows)
			Reader in = new InputStreamReader(new FileInputStream(filename),
					"UTF-8");
			BufferedReader reader = new BufferedReader(in);
			String s;
			while ((s = reader.readLine()) != null) {
				// Ignoriere Leerzeilen und Kommentare
				if (s.length() != 0 && s.charAt(0) != '#') {
					lines.add(s);
				}
			}
			reader.close();
			return lines;
		} catch (IOException e) {
			String msg = error("readStringArray",
					"I/O-Fehler bei %s\n" + e.getMessage(), filename);
			throw new RuntimeException(msg);
		}
	}

	/**
	 * Zeilenweise Text-Datei einlesen. Kommentarzeilen (# am Anfang)
	 * ignorieren. Leerzeilen (keine Whitespaces) ignorieren. Leerzeilen mit
	 * Whitespaces werden mit aufgenommen.
	 * 
	 * @param filename
	 *            String, Dateiname der Text-Datei
	 * @return String[], Feld aus Zeichenketten
	 * @throws RuntimeException
	 *             bei I/O-Fehler
	 */
	public static String[] readStringArray(String filename) {
		ArrayList<String> linesAL = readStringArrayList(filename);
		String[] lines = new String[linesAL.size()];
		return linesAL.toArray(lines);
	}

	/**
	 * Zeilenweise Feld aus Zeichenketten abspeichern. Zeilenendezeichen werden
	 * automatisch fÃ¼r jede Zeichenkette hinzugefÃ¼gt, also eine Zeile je
	 * Zeichenkette.
	 * 
	 * @param filename
	 *            String, Dateiname der Text-Datei
	 * @param sarray
	 *            String[], Feld aus Zeichenketten
	 * @throws RuntimeException
	 *             bei I/O-Fehler
	 */
	public static void saveStringArray(String filename, String[] sarray) {
		Writer out = null;
		try {
			out = new OutputStreamWriter(new FileOutputStream(filename),
					"UTF-8");
		} catch (UnsupportedEncodingException e) {
			String msg = error("saveStringArray",
					"bei %s\n" + e.getLocalizedMessage(), filename);
			throw new RuntimeException(msg);
		} catch (FileNotFoundException e) {
			String msg = error("saveStringArray",
					"bei %s\n" + e.getLocalizedMessage(), filename);
			throw new RuntimeException(msg);
		}
		BufferedWriter writer = new BufferedWriter(out);
		int rowCounter = 0;
		try {
			for (String s : sarray) {
				writer.append(s);
				writer.append("\n");
				rowCounter += 1;
			}
			writer.close();
		} catch (IOException e) {
			error("saveStringArray", // side effect, print
					"%s:%d: %s", filename, rowCounter, e.getLocalizedMessage());
			throw new RuntimeException(e);
		}
	}

	/**
	 * Einlesen einer Datei von ganzen Zahlen. Eine Zahl je Zeile Kommentare wie
	 * {@link #readStringArray}.
	 * 
	 * @param filename
	 *            String, Dateiname der Datei mit ganzen Zahlen
	 * @return int[], Feld aus eingelesenen ganzen Zahlen
	 * @throws RuntimeException
	 *             bei I/O-Fehler oder Formatfehler
	 */
	public static int[] readIntArray(String filename) {
		ArrayList<String> lines = readStringArrayList(filename);
		if (lines == null) {
			String msg = error("readIntArray", "%s: keine Strings", filename);
			throw new RuntimeException(msg);
		}
		int[] numbers = new int[lines.size()];
		for (int i = 0; i < numbers.length; i++) {
			try {
				numbers[i] = Integer.parseInt(lines.get(i));
			} catch (NumberFormatException e) {
				String msg = error("readIntArray", "%s: kein Integer bei: %s ",
						filename, lines.get(i));
				throw new RuntimeException(msg);
			}
		}
		return numbers;
	}

	/**
	 * Zeilenweise Feld aus Integerzahlen in Dezimaldarstellung abspeichern.
	 * Zeilenendezeichen werden automatisch nach jeder Zahl hinzugefÃ¼gt, also
	 * eine Zeile je Zahl.
	 * 
	 * @param filename
	 *            String, Dateiname der Text-Datei der Zahlen
	 * @param iarray
	 *            int[], Feld aus Integer
	 * @throws RuntimeException
	 *             bei I/O-Fehler
	 */
	public static void saveIntArray(String filename, int[] iarray) {
		String[] sarray = new String[iarray.length];
		for (int i = 0; i < iarray.length; i++) {
			sarray[i] = Integer.toString(iarray[i]);
		}
		saveStringArray(filename, sarray);
	}

	// Zeitmessung

	private static long nanos = 0; // Zeitpunkt von nanoTime

	/**
	 * RÃ¼cksetzen der Zeitmessung. Interner ZÃ¤hler auf 0 (nanos).
	 */
	public static void resetTime() {
		nanos = System.nanoTime();
	}

	/**
	 * Abgelaufene Zeit seit Beginn der Zeitmessung.
	 * 
	 * @return long, millisecs seit Initialisierung der Zeitmessung
	 */
	public static long getTime() {
		return (System.nanoTime() - nanos) / 1000000;
	}

	/**
	 * Abgelaufene Zeit seit Beginn der Zeitmessung.
	 * 
	 * @return long, microsecs seit Initialisierung der Zeitmessung
	 */
	public static long getTimeMikro() {
		return (System.nanoTime() - nanos) / 1000;
	}

	/**
	 * Abgelaufene Zeit seit Beginn der Zeitmessung.
	 * 
	 * @return long, nanosecs seit Initialisierung der Zeitmessung
	 */
	public static long getTimeNano() {
		return System.nanoTime() - nanos;
	}

	/**
	 * String "%5d.%02s" der Zeit seit RÃ¼cksetzen der Zeitmessung.
	 * 
	 * @return String, Zeit in Sekunden (100stel Sekunden AuflÃ¶sung) seit Beginn
	 *         der Zeitmessung
	 */
	public static String stringTime() {
		return stringTime("s");
	}

	/**
	 * Ausgabe (stdout) von {@link #stringTime()} und neue Zeile.
	 */
	public static void showTime() {
		System.out.println(stringTime());
	}

	/**
	 * Wie {@link #stringTime()} nur Inhalt von s statt "s".
	 * 
	 * @param s
	 *            String, anzuhÃ¤ngende Zeichenkette
	 * @return String, Zeit in Sekunden (1000stel Sekunden AuflÃ¶sung) seit
	 *         Beginn der Zeitmessung und s angehÃ¤ngt
	 */
	public static String stringTime(String s) {
		return stringSecs(getTime()) + s;
	}

	/**
	 * Ausgabe von {@link #stringTime(String)} und neue Zeile.
	 * 
	 * @param s
	 *            String, anzuhÃ¤ngende Zeichenkette
	 */
	public static void showTime(String s) {
		System.out.println(stringTime(s));
	}

	/**
	 * {@link #stringTime(String)} und {@link #resetTime()}
	 * 
	 * @param s
	 *            String, anzuhÃ¤ngende Zeichenkette
	 * @return String, verbrauchte Zeit
	 */
	public static String stringRTime(String s) {
		String ret = stringTime(s);
		resetTime();
		return ret;
	}

	/**
	 * {@link #showTime()} und {@link #resetTime()}.
	 * 
	 * @return String, verbrauchte Zeit
	 */
	public static String stringRTime() {
		String ret = stringTime();
		resetTime();
		return ret;
	}

	/**
	 * {@link #showTime()} und {@link #resetTime()}.
	 */
	public static void showRTime() {
		showTime();
		resetTime();
	}

	/**
	 * String aus msecs (Millisekunden) in Sekunden
	 * 
	 * @param ticks
	 *            long, in msecs
	 * @return Formatierter String in Sekunden mit drei Nachkommastellen
	 */
	public static String stringSecs(long ticks) {
		return String.format("%5d.%03d", ticks / 1000, ticks % 1000);
	}

}