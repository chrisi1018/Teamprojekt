package controller;

import javax.swing.SwingUtilities;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;

/**
 * Wechselt zwei Schluesselfelder miteinander aus
 * 
 * @author zes
 * @version 1.0
 */
public class TextChangeListener implements DocumentListener {

	private FATable table;
	private int index;

	/**
	 * Konstruktor fuer einen TextChangeListener
	 * 
	 * @param table die aktuelle FATable instanz, welche die Daten und den Graphen
	 *              enthaelt
	 * @param index den index beim dem eine aenderung stattfand
	 */
	public TextChangeListener(FATable table, int index) {
		this.table = table;
		this.index = index;
	}

	@Override
	public void insertUpdate(DocumentEvent e) {
		// fuer monoalphabetisch
		Runnable doHighlight = new Runnable() {
			@Override
			public void run() {
				table.swapChar(index);
			}
		};
		SwingUtilities.invokeLater(doHighlight);
	}

	@Override
	public void removeUpdate(DocumentEvent e) {
		// kein update wenn etwas entfernt wird
	}

	@Override
	public void changedUpdate(DocumentEvent e) {
		// nicht gebraucht bei textfeldern
	}

}
