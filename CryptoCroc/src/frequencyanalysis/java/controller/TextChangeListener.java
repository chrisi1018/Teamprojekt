package controller;

import javax.swing.SwingUtilities;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;

/**
 * Vertauscht zwei Schluesselfelder miteinander
 * 
 * @author zes
 * @version 1.5
 */
public class TextChangeListener implements DocumentListener {

	private FATable table;
	private int index;

	/**
	 * Konstruktor fuer einen TextChangeListener
	 * 
	 * @param table die aktuelle FATable-Instanz, welche die Daten und den Graphen
	 *              enthaelt
	 * @param index der Index, bei dem eine Aenderung stattfand
	 */
	public TextChangeListener(FATable table, int index) {
		this.table = table;
		this.index = index;
	}

	@Override
	public void insertUpdate(DocumentEvent e) {
		// fuer monoalphabetisch; vertauscht zwei Schluesselfelder und deaktiviert
		// zeitlich in einem den DocumentListener
		Runnable swap = new Runnable() {
			@Override
			public void run() {
				table.swapChar(index);
			}
		};
		SwingUtilities.invokeLater(swap);
	}

	@Override
	public void removeUpdate(DocumentEvent e) {
		// kein Update noetig, wenn etwas entfernt wird
	}

	@Override
	public void changedUpdate(DocumentEvent e) {
		// nicht gebraucht bei Textfeldern
	}

}
