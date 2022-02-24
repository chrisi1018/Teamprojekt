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
 * Textfeld bei der immer nur ein Buchstabe zwischen A und Z eingegeben werden
 * darf
 * 
 * @author zes, Julian Sturm
 * @version 2.0
 */
public class LimitedTextfield extends PlainDocument {

	private int limit;
	private int index = -1;
	private JTextField[] textFields;
	private static int illegalKeyError = 0;
	private static int moreThanOneKeyError = 0;
	private int tooManyKeysError = 0;

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
	 * @param textFields die Textfelder in den die Permutation steht
	 * @param index      der Index des Textfeldes
	 */
	public LimitedTextfield(int limit, int index, JTextField[] textFields) {
		super();
		this.limit = limit;
		this.index = index;
		this.textFields = textFields;
	}

	/**
	 * serial Nummer
	 */
	private static final long serialVersionUID = 6389795108727999785L;

	@Override
	public void insertString(int offset, String str, AttributeSet att) throws BadLocationException {
		String up = str.toUpperCase();
		if (!this.checkValidString(up)) {
			if (illegalKeyError < Utility.MAX_ERROR_MESSAGES) {
				Messages.errorMessage("Hier k\u00f6nnen nur die Buchstaben A-Z bzw a-z eingegeben werden!");
				illegalKeyError++;
			}
		} else if (index != -1 && (getLength() + str.length()) <= limit) {
			super.insertString(offset, up, att);
			checkPermutation();
		} else if ((getLength() + up.length()) <= limit) {
			super.insertString(offset, up, att);
		} else {
			if (limit == 1) {
				if (moreThanOneKeyError < Utility.MAX_ERROR_MESSAGES) {
					Messages.errorMessage("Hier kann nur ein Buchstabe eingegeben werden!");
					moreThanOneKeyError++;
				}
			} else {
				if (tooManyKeysError < Utility.MAX_ERROR_MESSAGES) {
					Messages.errorMessage("Hier k\u00f6nnen maximal " + limit + " Buchstaben eingegeben werden!");
					tooManyKeysError++;
				}
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
	 * Ueberprueft ob ein String nur die Characters A-Z bzw a-z enthaelt
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
	 * Ueberprueft ob der der eingebene String schon in einem anderen Textfeld steht
	 * 
	 * @param str der eingebene String
	 * @retunr gibt -1 wenn eine Permuationvorliegt ansonsten den Index des
	 *         TextFeldes, indem der Buchstabe schon steht
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
			if (red) {
				textFields[i].setBackground(Color.RED);
			} else {
				textFields[i].setBackground(Color.WHITE);
			}
			red = false;
		}
	}
}