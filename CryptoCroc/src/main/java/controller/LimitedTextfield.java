package controller;

import view.Messages;
import javax.swing.text.AttributeSet;
import javax.swing.text.BadLocationException;
import javax.swing.text.PlainDocument;
import javax.swing.JTextField;

/**
 * Textfeld bei der immer nur ein Buchstabe zwischen A und Z eingegeben werden
 * darf
 * 
 * @author zes, Julian Sturm
 * @version 1.1
 */
public class LimitedTextfield extends PlainDocument {

	private int limit;
	private int index = -1;
	private JTextField[] textFields;

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
	 *  @param limit die maximale Anzahl an Zeichen in einem Textfeld
	 *  @param textFields die Textfelder in den die Permutation steht
	 *  @param index der Index des Textfeldes
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
		if (!this.checkValidString(str)) {
			Messages.errorMessage("Hier k\u00f6nnen nur die Buchstaben A-Z bzw a-z eingegeben werden!");
		} else if (index != -1) {
			int number = checkPermuation(str);
			if (number != -1) {
				char c = (char) ('A' + number);
				Messages.errorMessage("Du hast diesen Buchstaben schon verwendet im Textfeld " + c + " !");
			} else if ((getLength() + str.length()) <= limit) {
				super.insertString(offset, str, att);
			}
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
	 * @retunr gibt -1 wenn eine Permuationvorliegt ansonsten den Index des TextFeldes, indem der Buchstabe schon
	 * steht
	 */
	private int checkPermuation(String str) {
		int ret = -1;
		for (int i = 0; i < 26; i++) {
			if (i != index) {
				String otherString = textFields[i].getText();
				System.out.println(otherString);
				if (!otherString.equals("")) {
					if (str.equalsIgnoreCase(otherString)) {
						ret = i;
						break;
					}
				}
			}
		}
		return ret;
	}
}