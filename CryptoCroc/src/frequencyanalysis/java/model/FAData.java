package model;

import utility.Utility;

/**
 * Eine Hilfsklasse, die fuer unterschiedliche Sprachen die
 * Haeufigkeitswahrscheinlichkeiten hat
 * 
 * @author Julian, zes
 * @version 1.1
 */
public final class FAData {

	/**
	 * Gibt die Haeufigkeitsverteilung der Buchstaben der Deutschen Sprache an, mit
	 * zwei Nachkommastellen. Quelle: @see <a href="Deutsche
	 * Buchstabenverteilung">https://de.wikipedia.org/wiki/Buchstabenhäufigkeit</a>
	 * Notiz: Die Tabelle war mit einem '?', die Prozentzahl fuer s wurde wie folgt
	 * berechnet: per(s*) = (per(s) + 2* per(?))/(100 % + per(?)
	 */
	public static final float[] GERMAN = { 6.51f /* A */, 1.89f /* B */, 3.06f /* C */, 5.08f /* D */, 17.4f /* E */,
			1.66f /* F */, 3.01f /* G */, 4.76f /* H */, 7.55f /* I */, 0.27f /* J */, 1.21f /* K */, 3.44f /* L */,
			2.53f /* M */, 9.78f /* N */, 2.51f /* O */, 0.79f /* P */, 0.02f /* Q */, 7.0f /* R */, 7.87f /* S */,
			6.15f /* T */, 4.35f /* U */, 0.67f /* V */, 1.89f /* W */, 0.03f /* X */, 0.04f /* Y */, 1.13f /* Z */ };
	/**
	 * Gibt die Haeufigkeitsverteilung der Buchstaben der englischen Sprache an, mit
	 * zwei Nachkommastellen. Quelle: @see <a href="Englische
	 * Buchstabenverteilung">https://en.wikipedia.org/wiki/Letter_frequency</a>
	 */
	public static final float[] ENGLISCH = { 8.17f /* A */, 1.49f /* B */, 2.78f /* C */, 4.25f /* D */, 12.70f /* E */,
			2.22f /* F */, 2.02f /* G */, 6.09f /* H */, 6.97f /* I */, 0.15f /* J */, 0.77f /* K */, 4.03f /* L */,
			2.41f /* M */, 6.75f /* N */, 7.51f /* O */, 1.93f /* P */, 0.10f /* Q */, 5.99f /* R */, 6.33f /* S */,
			9.06f /* T */, 2.76f /* U */, 0.98f /* V */, 2.36f /* W */, 0.15f /* X */, 1.97f /* Y */, 0.07f /* Z */ };
	// TODO max fuer Englisch anpassen

	/**
	 * privater Konstruktor fur Hilfsklasse
	 */
	private FAData() {
	}

	/**
	 * Erstellt eine Haeufigkeitsanlyse der Buchstaben fuer einen gegebenen Text in
	 * Abhaengigkeit von der Laenge eines Schuesselworts und rundet diesen auf eine
	 * Nachkommastelle
	 * 
	 * @param text      der Text, an dem die Hauefigkeitsanalyse gemacht wird
	 * @param keyLength die Laenge des Schluesselworts
	 * @return ein 2D-Array, die erste Dimension gibt den Index des Schluesselwortes
	 *         an und die zweite Dimension die Haufigkeit von Buchstaben in Prozent
	 *         von A - Z
	 */
	public static float[][] analyse(String text, int keyLength) {
		int index = 0;
		int[] countChars = new int[keyLength];
		int[][] count = new int[keyLength][Utility.ALPHABETSIZE];
		float[][] per = new float[keyLength][Utility.ALPHABETSIZE];
		// Initialisiert die Arrays
		for (int j = 0; j < keyLength; j++) {
			countChars[j] = 0;
			for (int i = 0; i < Utility.ALPHABETSIZE; i++) {
				count[j][i] = 0;
				per[j][i] = 0.0f;
			}
		}

		String editedText = TextEdit.editText(text);
		for (int i = 0; i < editedText.length(); i++) { // Schleife zaehlt ueber den Text
			char temp = editedText.charAt(i);
			if (temp >= 'A' && temp <= 'Z') { // ueberprueft ob Zeichen ein Buchstabe ist
				count[index][(temp - 'A')]++;
				countChars[index]++;
				index++;
				if (index == keyLength) {
					index = 0;
				}
			}

		}
		// Rechnet die Prozente aus
		for (int j = 0; j < keyLength; j++) {
			if (countChars[j] != 0) {
				for (int i = 0; i < Utility.ALPHABETSIZE; i++) {
					float digit = 100 * ((float) count[j][i] / countChars[j]);
					per[j][i] = Math.round(digit * 10.0f) / 10.0f;
				}
			}
		}
		return per;
	}
}
