package model;

import utility.Utility;

/**
 * Die Klasse verwaltet den Haeufigkeitsprozentsatz und die
 * Buchstabenreihenfolge des Textfeldes.
 * 
 * @author chrisi
 * @version 1.0
 *
 */
public class TableData {

	private float[] frequencyPercentage = new float[Utility.ALPHABET_SIZE];
	private char[] textFieldChar = new char[Utility.ALPHABET_SIZE];

	/**
	 * Der Konstruktor fuer die Klasse TableData speichert die Prozentzahlen ab.
	 * 
	 * @param frequencyPercentage die Prozentzahlen fuer die Haeufigkeitsanalyse
	 *                            werden uebergeben.
	 */
	public TableData(float[] frequencyPercentage) {
		this.frequencyPercentage = frequencyPercentage;
	}

	/**
	 * Gibt die Prozentzahlen der Analyse zurueck.
	 * 
	 * @return den Haeufigkeitsprozentsatz
	 */
	public float[] getFrequencyPercentage() {
		return this.frequencyPercentage;
	}

	/**
	 * Belegt das i-te Feld in den Char-Textfeldern neu.
	 * 
	 * @param c der neue Buchstabe
	 * @param i die Stelle im Array
	 */
	public void setTextFieldChar(char c, int i) {
		this.textFieldChar[i] = c;
	}

	/**
	 * Gibt das i-te Feld in den Char-Textfeldern zurueck.
	 * 
	 * @param i die Stelle im Array
	 * @return der Buchstabe aus dem Array an der i-ten Stelle
	 */
	public char getTextFieldChar(int i) {
		return this.textFieldChar[i];
	}

	/**
	 * Initialisiert das Textfeld-Array mit den Werten A-Z
	 */
	public void initTextFieldChar() {
		for (int i = 0; i < Utility.ALPHABET_SIZE; i++) {
			this.textFieldChar[i] = (char) (Utility.FIRST_LETTER + i);
		}
	}

	/**
	 * Gibt den Haeufigkeitsprozentsatz sortiert nach der Anordnung der Buchstaben
	 * in den Textfeldern zurueck.
	 * 
	 * @return den sortierten Haeufigkeitsprozentsatz
	 */
	public float[] getForGraph() {
		float[] sortedFrequencyPercentage = new float[Utility.ALPHABET_SIZE];

		for (int i = 0; i < this.textFieldChar.length; i++) { // Geht alle Buchstaben von A-Z durch
			for (int j = 0; j < this.textFieldChar.length; j++) { // Geht alle Eintraege in dem Textfeld-Array durch
				if (this.textFieldChar[j] == (char) (Utility.FIRST_LETTER + i)) { // Vertauscht die Prozentzahlen
					sortedFrequencyPercentage[j] = this.frequencyPercentage[i];
				}
			}
		}
		return sortedFrequencyPercentage;
	}
}
