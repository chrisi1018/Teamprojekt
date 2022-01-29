package controller;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import model.TableData;

/**
 * Diese Klasse modelliert die Zuordnungstabelle der Buchstaben fuer die Haeufigkeitsanalyse
 * und ihr zugehoeriges Balkendiagramm
 * 
 * @author Julian Singer
 * @version 1.0
 */
public class FATable {
	
	private final int alphabetSize = 26;
	
	private TableData data;
	private JTextField[] textFields;
	private JLabel[] textLabels;
	private JPanel tablePanel;
	private FAGraph graph;
	
	/**
	 * Konstruktor, der die Haeufigkeiten der Sprache und des Geheimtextes uebergibt
	 * 
	 * @param tableData Daten der Haeufigkeitsanalyse
	 * @param language Haeufigkeit der Zeichen in spezieller Sprache
	 */
	public FATable(TableData tableData, float[] language) {
		this.data = tableData;
		//initTextFields();
		//initTextLabels();
		this.tablePanel = createTablePanel();
		/*TODO this.graph = new FAGraph();
		 		graph.createGraphPanel(); */
	}
	
	/**
	 * Dummy
	 */
	public JPanel createTablePanel() {
		//TODO
		return new JPanel();
	}
	
	/**
	 * Schiebt den Inhalt der Textfelder in ihre rechten Nachbarfelder und den Inhalt des letzten
	 * Textfeldes in das erste Textfeld. Ausserdem verschieben sich die Balken der Geheimtextbuchstaben
	 * ihrer neuen Zuordnung entsprechend nach rechts
	 */
	public void shiftRight() {
		String lastChar = this.textFields[25].getText();
		for (int i = this.textFields.length - 1; i < 0; i--) {
			this.textFields[i].setText(this.textFields[(i + 25) % alphabetSize].getText());
			//this.data.setTextFieldChar(this.data.getTextFieldChar(((i + 25) % alphabetSize), i);
		}
		this.textFields[0].setText(lastChar);
		//this.data.setTextFieldChar(this.data.getTextFieldChar(lastChar.charAt(0), 0);
		//TODO GraphPanel anpassen
	}
	
	/**
	 * Schiebt den Inhalt der Textfelder in ihre linken Nachbarfelder und den Inhalt des ersten
	 * Textfeldes in das letzte Textfeld. Ausserdem verschieben sich die Balken der Geheimtextbuchstaben
	 * ihrer neuen Zuordnung entsprechend nach links
	 */
	public void shiftLeft() {
		String firstChar = this.textFields[0].getText();
		for (int i = 0; i < this.textFields.length - 1; i++) {
			this.textFields[i].setText(this.textFields[(i + 1) % alphabetSize].getText());
			//this.data.setTextFieldChar(this.data.getTextFieldChar(((i + 1) % alphabetSize), i);
		}
		this.textFields[this.textFields.length - 1].setText(firstChar);
		//this.data.setTextFieldChar(this.data.getTextFieldChar(firstChar.charAt(0), alphabetSize - 1);
		//TODO GraphPanel anpassen
	}
	
	/**
	 * Setzt den vorigen/alten Character des Textfeldes an der uebergebenen Stelle in das Textfeld, in dem der
	 * neue Character zuvor allein stand
	 * 
	 * @param swapIndex Index des Textfeldes, in dem der alte Character durch einen neuen ersetzt wurde
	 */
	public void swapChar(int swapIndex) {
		/*char oldChar = this.data.getTextFieldChar(swapIndex);
		char newChar = this.textFields[swapIndex].getText().charAt(0);
		int newIndex = 0;
		for (int i = 0; i < this.textFields.length; i++) {
			if (this.data.getTextFieldChar(i) == newChar) {
				newIndex = i;
			}
		}
		this.textFields[newIndex].setText(String.valueOf(oldChar));
		this.data.setTextFieldChar(newChar, swapIndex);
		this.data.setTextFieldChar(oldChar, newIndex); */
	}
	
	/**
	 * Initialisiert die 26 nicht-editierbaren, grauen Textfelder und setzt in diese die Buchstaben von 'A' bis 'Z'
	 */
	private void initTextFields() {
		this.textFields = new JTextField[alphabetSize];
		for (int i = 0; i < alphabetSize; i++) {
			this.textFields[i].setText(String.valueOf((char) i + 65));
			this.textFields[i].setEditable(false);
			this.textFields[i].setEnabled(false);
		}
	}
	
	/**
	 * Initialisiert die 26 Labels und setzt in diese die Buchstaben von 'A' bis 'Z'
	 */
	private void initTextLabels() {
		this.textLabels = new JLabel[alphabetSize];
		for (int i = 0; i < alphabetSize; i++) {
			this.textLabels[i] = new JLabel(String.valueOf((char) i + 65), JLabel.CENTER);
		}
	}
}