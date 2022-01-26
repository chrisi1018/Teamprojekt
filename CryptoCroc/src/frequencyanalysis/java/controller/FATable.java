package controller;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import model.TableData;

public class FATable {
	
	private final int alphabetSize = 26;
	
	private JTextField[] textFields;
	private JLabel[] textLabels;
	private JPanel tablePanel;
	private JPanel graphPanel;
	private int index;
	
	public FATable(TableData data, int tableIndex) {
		/*TODO data.initTextFieldChar();
		 * initTextFields(data.getTextFieldChar(tableIndex));
		 * initTextLabels(data.getTextFieldChar(TableIndex));
		 */
		this.tablePanel = createTablePanel();
		/*TODO FAGraph graph = new FAGraph();
		 		graph.createGraphPanel();
	     		this.graphPanel = graph.getGraphPanel(); */
		this.index = tableIndex;
	}
	
	/**
	 * Dummy
	 */
	public JPanel createTablePanel() {
		//TODO
		return new JPanel();
	}
	
	public void shiftRight() {
		JTextField[] shiftedFields = new JTextField[alphabetSize];
		for (int i = 0; i < alphabetSize; i++) {
			shiftedFields[i] = this.textFields[(i + 25) % alphabetSize];
		}
		this.textFields = shiftedFields;
		//TODO GraphPanel anpassen
	}
	
	public void shiftLeft() {
		JTextField[] shiftedFields = new JTextField[alphabetSize];
		for (int i = 0; i < alphabetSize; i++) {
			shiftedFields[i] = this.textFields[(i + 1) % alphabetSize];
		}
		this.textFields = shiftedFields;
		//TODO GraphPanel anpassen
	}
	
	public int getLetterNumber(char letter) {
		return (int) letter - (int) 'A';
	}
	
	public void swapChar(int swapIndex) {
		//TODO this.textFields[swapIndex] ?
	}
	
	private void initTextFields(char[] fieldChars) {
		this.textFields = new JTextField[fieldChars.length];
		for (int i = 0; i < fieldChars.length; i++) {
			this.textFields[i].setText(String.valueOf(fieldChars[i]));
		}
	}
	
	private void initTextLabels(char[] fieldChars) {
		this.textLabels = new JLabel[fieldChars.length];
		for (int i = 0; i < fieldChars.length; i++) {
			this.textLabels[i] = new JLabel(String.valueOf(fieldChars[i]));
		}
	}

}