package controller;

import view.Messages;
import javax.swing.text.AttributeSet;
import javax.swing.text.BadLocationException;
import javax.swing.text.PlainDocument;

/**
 * Textfeld bei der immer nur ein Buchstabe zwischen A und Z eingegeben werden
 * darf
 * 
 * @author zes
 * @version 1.0
 */
public class LimitedTextfield extends PlainDocument {

	private int limit;

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
	 * serial Nummer
	 */
	private static final long serialVersionUID = 6389795108727999785L;

	@Override
	public void insertString(int offset, String str, AttributeSet att) throws BadLocationException {
		if (!this.checkValidString(str)) {
			Messages.errorMessage("Hier können nur die Buchstaben A-Z bzw a-z eingegeben werden");
		} else if ((getLength() + str.length()) <= limit) {
			super.insertString(offset, str, att);
		} else {
			Messages.errorMessage("Hier kann nur ein Buchstabe eingegeben werden!");
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
			if (!(letters[i] >= 'a' && letters[i] <= 'z') || (letters[i] >= 'A' && letters[i] <= 'Z')) {
				ok = false;
			}
		}
		return ok;
	}
}