package controller;

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
		if ((getLength() + str.length()) <= limit) {
			super.insertString(offset, str, att);
		} else {
			
		}
	}

}