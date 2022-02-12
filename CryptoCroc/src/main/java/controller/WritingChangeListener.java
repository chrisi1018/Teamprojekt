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
 * @version 1.0
 */
public class WritingChangeListener implements DocumentListener {

	private JTextArea text;

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
		// ruft die editText methode auf die alles in Grossbuchstaben umwandelt und die
		// Umlaute umwandelt
		Runnable overwrite = new Runnable() {
			@Override
			public void run() {
				text.setText(TextEdit.editText(text.getText()));
			}
		};
		SwingUtilities.invokeLater(overwrite);
	}

	@Override
	public void removeUpdate(DocumentEvent e) {
		// nicht noetig
	}

	@Override
	public void changedUpdate(DocumentEvent e) {
		// nicht genutzt bei Textfeldern
	}

}
