package model;

/**
 * Eine Hilfsklasse, die fuer unterschiedliche Sprachen die
 * Haeufigkeitswahrscheinlichkeiten hat
 * 
 * @author Julian
 * @version 1.0
 */
public final class FAData {

	/**
	 * Gibt die Haeufigkeitsverteilung der Buchstaben der Deutschen Sprache an, mit
	 * zwei Nachkommastellen Quelle: Wikipedia Notiz: Die Tabelle war mit einem '?',
	 * die Prozentzahl fuer s wurde wie folgt berechnet: per(s*) = (per(s) + 2*
	 * per(?))/(100 % + per(?)
	 */
	public static final float[] GERMAN = { 6.51f /* A */, 1.89f /* B */, 3.06f /* C */, 5.08f /* D */, 17.4f /* E */,
			1.66f /* F */, 3.01f /* G */, 4.76f /* H */, 7.55f /* I */, 0.27f /* J */, 1.21f /* K */, 3.44f /* L */,
			2.53f /* M */, 9.78f /* N */, 2.51f /* O */, 0.79f /* P */, 0.02f /* Q */, 7.0f /* R */, 7.87f /* S */,
			6.15f /* T */, 4.35f /* U */, 0.67f /* V */, 1.89f /* W */, 0.03f /* X */, 0.04f /* Y */, 1.13f /* Z */ };

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
		int alphabetSize = 26;
		int index = 0;
		int[] countChars = new int[keyLength];
		int[][] count = new int[keyLength][alphabetSize];
		float[][] per = new float[keyLength][alphabetSize];
		// Initialisiert die Arrays
		for (int j = 0; j < keyLength; j++) {
			countChars[j] = 0;
			for (int i = 0; i < alphabetSize; i++) {
				count[j][i] = 0;
				per[j][i] = 0.0f;
			}
		}

		String editedText = TextEdit.editText(text);
		for (int i = 0; i < editedText.length(); i++) { // Schleife zaehlt ueber den Text
			char temp = editedText.charAt(i);
			if (temp >= 'A' && temp <= 'Z') { // ueberprueft, ob Zeichen ein Buchstabe ist
				for (int j = 0; j < count.length; j++) {
					count[j][(temp - 'A')]++;
				}
				countChars[index]++;

			}
		}
		// Rechnet die Prozente aus
		for (int j = 0; j < keyLength; j++) {
			if (countChars[j] != 0) {
				for (int i = 0; i < alphabetSize; i++) {
					float digit = 100 * ((float) count[j][i] / countChars[j]);
					per[j][i] = Math.round(digit * 10.0f) / 10.0f;
				}
			}
		}
		return per;
	}
}
