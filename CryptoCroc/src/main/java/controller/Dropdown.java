package controller;

import java.awt.BorderLayout;
import javax.swing.JComboBox;
import javax.swing.JPanel;

/**
 * Beschreibt ein Dropdown Menü für die Verschlüsselungen
 * 
 * @author zes
 * @version 1.0
 */
public class Dropdown {

	private JComboBox<String> dropDown = new JComboBox<String>();
	private String[] options;

	/**
	 * Konstruktor, der ein Dropdown Menü initialisiert
	 */
	public Dropdown(String[] options) {
		this.options = options;
		for (int i = 0; i < options.length; i++) {
			dropDown.addItem(options[i]);
		}
	}

	public JPanel createDropdown() {
		JPanel panel = new JPanel(new BorderLayout());
		panel.add(dropDown);
		return panel;
	}

}
