package controller;

import javax.swing.JPanel;

/**
 * Erstellt die Grafik zur Buchstabenverteilung der Haeufigkeitsanalyse
 * 
 * @author zes
 * @version 1.0
 */
public class FAGraph {

	private JPanel graphPanel;

	/**
	 * Konstruktor fuer einen FAGraph, erstellt das GraphPanel
	 */
	public FAGraph() {
		createGraphPanel();
	}

	/**
	 * Erstellt das GraphPanel
	 */
	public void createGraphPanel() {

	}

	/**
	 * Getter fuer das GraphPanel
	 * 
	 * @return das graphPanel
	 */
	public JPanel getGraphPanel() {
		return graphPanel;
	}

	/**
	 * Setter fuer das GraphPanel
	 * 
	 * @param graphPanel das neue graphPanel
	 */
	public void setGraphPanel(JPanel graphPanel) {
		this.graphPanel = graphPanel;
	}
}
