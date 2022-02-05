package controller;

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

	private FAGui gui;
	private FAGraph graph;

	/**
	 * JavaDoc
	 * 
	 * @param gui
	 * @param graph
	 */
	public TextChangeListener(FAGui gui, FAGraph graph) {
		this.gui = gui;
		this.graph = graph;
	}

	@Override
	public void insertUpdate(DocumentEvent e) {
		// fuer monoalphabetisch
		gui.updateGraph(graph);
		gui.repaint();
	}

	@Override
	public void removeUpdate(DocumentEvent e) {
		// fuer monoalphabetisch
	}

	@Override
	public void changedUpdate(DocumentEvent e) {
		// standard
		gui.updateGraph(graph);
		gui.repaint();
	}

}
