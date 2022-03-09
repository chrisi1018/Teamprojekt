package controller;

import view.Messages;

import javax.swing.text.AbstractDocument;
import javax.swing.text.AttributeSet;
import javax.swing.text.BadLocationException;
import javax.swing.text.PlainDocument;

import utility.Utility;

import javax.swing.JTextField;
import java.awt.Color;

/**
 * Textfeld, bei dem immer nur ein Buchstabe zwischen A und Z eingegeben werden
 * darf
 * 
 * @author zes, Julian Sturm
 * @version 2.0
 */
public class LimitedTextfield extends PlainDocument {

	private int limit;
	private int index = -1;
	private JTextField[] textFields;
	/**
	 * Seriennummer
	 */
	private static final long serialVersionUID = 6389795108727999785L;
	private static int illegalKeyError = 1;
	private static int moreThanOneKeyError = 1;
	private int tooManyKeysError = 1;

	/**
	 * Konstruktor fuer ein LimitedTextfield
	 * 
	 * @param limit die maximale Anzahl an Zeichen in einem Textfeld
	 */
	public LimitedTextfield(int limit) {
		super();
		this.limit = limit;
	}

	/**
	 * Konstruktor fuer ein LimitedTextfield mit einer Alphabets Permutation
	 * 
	 * @param limit      die maximale Anzahl an Zeichen in einem Textfeld
	 * @param textFields die Textfelder, in denen die Permutation steht
	 * @param index      der Index des Textfeldes
	 */
	public LimitedTextfield(int limit, int index, JTextField[] textFields) {
		super();
		this.limit = limit;
		this.index = index;
		this.textFields = textFields;
	}

	@Override
	public void insertString(int offset, String str, AttributeSet att) throws BadLocationException {
		if (!this.checkValidString(str)) { // prueft auf gueltige Eingabe
			if (illegalKeyError % Utility.MAX_ERROR_MESSAGES == 1) {
				Messages.errorMessage("Hier k\u00f6nnen nur die Buchstaben A-Z bzw a-z eingegeben werden!");
			}
			illegalKeyError++;
		} else if (index != -1 && (getLength() + str.length()) <= limit) {
			super.insertString(offset, str.toUpperCase(), att);
			checkPermutation();
		} else if ((getLength() + str.length()) <= limit) {
			super.insertString(offset, str.toUpperCase(), att);
		} else {
			if (limit == 1) { // Unterscheidung fuer Anzahl der Buchstaben
				if (moreThanOneKeyError % Utility.MAX_ERROR_MESSAGES == 1) {
					Messages.errorMessage("Hier kann nur ein Buchstabe eingegeben werden!");
				}
				moreThanOneKeyError++;
			} else {
				if (tooManyKeysError % Utility.MAX_ERROR_MESSAGES == 1) {
					Messages.errorMessage("Hier k\u00f6nnen maximal " + limit + " Buchstaben eingegeben werden!");
				}
				tooManyKeysError++;
			}

		}
	}

	@Override
	public void removeUpdate(AbstractDocument.DefaultDocumentEvent e) {
		super.removeUpdate(e);
		if (index != -1) {
			checkPermutation();
			textFields[index].setBackground(Color.WHITE);
		}
	}

	/**
	 * Ueberprueft, ob ein String nur die Characters A-Z bzw a-z enthaelt
	 * 
	 * @param str der zu ueberpruefende String
	 * @return ob nur die erlaubten Characters enthalten sind
	 */
	private boolean checkValidString(String str) {
		boolean ok = true;
		char[] letters = new char[str.length()];
		for (int i = 0; i < str.length(); i++) {
			letters[i] = str.charAt(i);
			if (!((letters[i] >= 'a' && letters[i] <= 'z') || (letters[i] >= 'A' && letters[i] <= 'Z'))) {
				ok = false;
			}
		}
		return ok;
	}

	/**
	 * Ueberprueft, ob der eingegebene String schon in einem anderen Textfeld steht
	 * 
	 * @param str der eingegebene String
	 * @return gibt -1 wenn eine Permutation vorliegt, ansonsten den Index des
	 *         TextFeldes, in dem der Buchstabe schon steht
	 */
	private void checkPermutation() {
		for (int i = 0; i < 26; i++) {
			boolean red = false;
			String firstText = textFields[i].getText();
			for (int j = 0; j < 26; j++) {
				String secondText = textFields[j].getText();
				if (i != j && firstText.equalsIgnoreCase(secondText) && !secondText.equals("")) {
					textFields[i].setBackground(Color.RED);
					textFields[j].setBackground(Color.RED);
					red = true;
					break;
				}
			} 
			if (red) { // Zum Markieren der doppelten Buchstaben
				textFields[i].setBackground(Color.RED);
			} else {
				textFields[i].setBackground(Color.WHITE);
			}
			red = false;
		}
	}
}