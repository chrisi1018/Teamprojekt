package model;

public class TableData {
	final private int TEXTFIELD_SIZE = 26;
	
	private float[] frequencyPercentage = new float[TEXTFIELD_SIZE];
	private char[] textFieldChar = new char[TEXTFIELD_SIZE];
	
	/**
	 * Der Konstruktor fuer die Klasse TableData, speichert die Prozentzahlen ab.
	 * 
	 * @param frequencyPercentage die Prozentzahlen fuer die Haefigkeitsanalyse werden uebergeben.
	 */
	public TableData(float[] frequencyPercentage) {
		this.frequencyPercentage = frequencyPercentage;
	}
	
	/**
	 * Gibt die Prozentzahlen der Analyse zurueck.
	 * 
	 * @return den Haeufigkeitsprozentsatz
	 */
	public float[] getFrequencyPercantage() {
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
	 * Initalisiert das Textfeld-Array mit den Werten A-Z
	 */
	public void initTextFieldChar() {
		char letter = 'A';
		for (int i = 0; i < 26; i++) {
			this.textFieldChar[i] = (char) (letter + i);
		}
	}
	
	/**
	 * Gibt die Haeufigkeitsprozentsatz sortiert nach der Anordnung der Buchstaben in den Textfeldern zurueck.
	 * 
	 * @return den sortierten Haeufigkeitsprozentsatz
	 */
	public float[] getForGraph() {
		float[] sortedFrequencyPercentage = new float[TEXTFIELD_SIZE];
		char letter = 'A';
		
		for (int i = 0; i < this.textFieldChar.length; i++) { //Geht alle Buchstaben von A-Z durch
			for (int j = 0; j < this.textFieldChar.length; j++) { //Geht alle eintraege in dem Textfeld-Array durch
				if (i != j && this.textFieldChar[j] == (char) (letter + i)) { //Vertauscht die Prozentzahlen falls im 
					float cachePercentage = this.frequencyPercentage[j];	  //Textfeld-Array der Buchstabe zuvor umgeordnet wurde
					this.frequencyPercentage[j] = this.frequencyPercentage[i];
					this.frequencyPercentage[i] = cachePercentage;
				}
			}
		}
		return sortedFrequencyPercentage;
	}
}
