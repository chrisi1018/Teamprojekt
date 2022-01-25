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
		//TODO data.initTextFieldChar();
		//TODO data.getTextFieldChar(tableIndex) in initTextFields();
		initTextFields(new char[alphabetSize]);
		initTextLabels();
		this.tablePanel = createTablePanel();
		this.graphPanel = new JPanel(); //TODO new FAGraph().createGraphPanel();
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
	}
	
	public void shiftLeft() {
		JTextField[] shiftedFields = new JTextField[alphabetSize];
		for (int i = 0; i < alphabetSize; i++) {
			shiftedFields[i] = this.textFields[(i + 1) % alphabetSize];
		}
		this.textFields = shiftedFields;
	}
	
	public int getLetterNumber(char letter) {
		return (int) letter - (int) 'A';
	}
	
	public void swapChar(int swapIndex) {
		//TODO this.textFields[swapIndex] ?
	}
	
	private void initTextFields(char[] fieldChars) {
		//TODO
		
		this.textFields = new JTextField[alphabetSize];
		for (int i = 0; i < alphabetSize; i++) {
			this.textFields[i].setText(String.valueOf((char) (i + (int) 'A')));
		}
	}
	
	private void initTextLabels() {
		this.textLabels = new JLabel[alphabetSize];
		for (int i = 0; i < alphabetSize; i++) {
			this.textLabels[i] = new JLabel(String.valueOf((char) (i + (int) 'A')));
		}
	}

}