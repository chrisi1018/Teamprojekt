package model;

public class TableData {
	final private int TEXTFIELD_SIZE = 26;
	
	private float[] frequencyPercentage = new float[TEXTFIELD_SIZE];
	private char[] textFieldChar = new char[TEXTFIELD_SIZE];
	
	public TableData(float[] frequencyPercentage) {
		this.frequencyPercentage = frequencyPercentage;
		initTextFieldChar();
	}
	
	public float[] getFrequencyPercantage() {
		return this.frequencyPercentage;
	}
	
	public void setTextFieldChar(char c, int i) {
		this.textFieldChar[i] = c;
	}
	
	public char getTextFieldChar(int i) {
		return this.textFieldChar[i];
	}
	
	public void initTextFieldChar() {
		this.textFieldChar[0] = 'A';
		for (int i = 1; i < 26; i++) {
			this.textFieldChar[i] = (char) (this.textFieldChar[i - 1] + 1);
		}
	}
	
	public float[] getForGraph() {
		float[] sortedFrequencyPercentage = new float[TEXTFIELD_SIZE];
		char letter = 'A';
		for (int i = 0; i < this.textFieldChar.length; i++) {
			for (int j = 0; j < this.textFieldChar.length; j++) {
				if (i != j && this.textFieldChar[j] == (char) (letter + i)) {
					float cachePercentage = this.frequencyPercentage[j];
					this.frequencyPercentage[j] = this.frequencyPercentage[i];
					this.frequencyPercentage[i] = cachePercentage;
				}
			}
		}
		return sortedFrequencyPercentage;
	}
}
