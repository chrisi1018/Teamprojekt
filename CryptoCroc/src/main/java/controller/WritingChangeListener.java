package controller;

import javax.swing.JTextArea;
import javax.swing.SwingUtilities;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;

import model.TextEdit;

/**
 * DocumentListener fuer die beschreibbaren Textfelder, welcher Umlaute direkt
 * beim Eintippen umwandelt und alles in Grossbuchstaben anzeigt
 * 
 * @author zes
 * @version 1.1
 */
public class WritingChangeListener implements DocumentListener {

	private JTextArea text;
	private static boolean activated = false;

	/**
	 * Konstruktor fuer einen WritingChangeListener
	 * 
	 * @param text das Textfeld
	 */
	public WritingChangeListener(JTextArea text) {
		this.text = text;
	}

	@Override
	public void insertUpdate(DocumentEvent e) {
		// wenn gepastet wird ist die Laenge groesser 1
		int changeLength = e.getLength();
		if (changeLength == 1) {
			Runnable overwrite = new Runnable() {
				@Override
				public void run() {
					int caretPosition = text.getCaretPosition();
					text.setText(TextEdit.editText(text.getText()));
					text.setCaretPosition(caretPosition);
				}
			};

			SwingUtilities.invokeLater(overwrite);
		} else {
			// bei update laenger als 1 soll es nur einmal getriggert werden
			if (!activated) {
				Runnable overwrite = new Runnable() {
					@Override
					public void run() {
						int caretPosition = text.getCaretPosition();
						text.setText(TextEdit.editText(text.getText()));
						text.setCaretPosition(caretPosition);
						activated = false;
					}
				};
				SwingUtilities.invokeLater(overwrite);
				activated = true;
			}
		}
	}

	@Override
	public void removeUpdate(DocumentEvent e) {
		// nicht noetig
	}

	@Override
	public void changedUpdate(DocumentEvent e) {
		// nicht aufgerufen bei Textfeldern
	}

}
