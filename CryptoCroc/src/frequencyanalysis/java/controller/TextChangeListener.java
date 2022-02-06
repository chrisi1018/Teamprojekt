package controller;

import javax.swing.SwingUtilities;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;

import view.FAGui;

/**
 * Aktualisiert den Graphen der GUI bei Veraenderung der Textfelder aus FATable
 * 
 * @author zes
 * @version 1.0
 */
public class TextChangeListener implements DocumentListener {

	private FATable graph;
	private int index;
	private static int one = 0;

	/**
	 * JavaDoc
	 * 
	 * @param graph
	 * @param index
	 */
	public TextChangeListener(FATable graph, int index) {
		this.graph = graph;
		this.index = index;
	}

	@Override
	public void insertUpdate(DocumentEvent e) {
		// fuer monoalphabetisch
		Runnable doHighlight = new Runnable() {
			@Override
			public void run() {
				graph.swapChar(index);
				one++;
			}
		};
		SwingUtilities.invokeLater(doHighlight);
		System.out.println(one);
	}

	@Override
	public void removeUpdate(DocumentEvent e) {
		// wenn etwas entfernt wird
	}

	@Override
	public void changedUpdate(DocumentEvent e) {
		// nicht gebraucht bei textfeldern
	}

}
