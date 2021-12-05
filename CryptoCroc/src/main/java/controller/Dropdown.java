package controller;

import java.awt.BorderLayout;
import javax.swing.JComboBox;
import javax.swing.JPanel;

/**
 * Beschreibt ein Dropdown Menue für die Verschluesselungen
 * 
 * @author zes
 * @version 1.0
 */
public class Dropdown {

	private JComboBox<String> dropDown = new JComboBox<String>();
	private String[] options;

	/**
	 * Konstruktor, der ein Dropdown Menue initialisiert
	 * 
	 * @param options ein String mit allen Optionen des Dropdown Menue's
	 */
	public Dropdown(String[] options) {
		this.options = options;
		for (int i = 0; i < options.length; i++) {
			dropDown.addItem(options[i]);
		}
	}

	/**
	 * Erstellt ein Panel mit dem Dropdown Menue
	 * 
	 * @return ein panel mit dem Dropdown Menue
	 */
	public JPanel createDropdown() {
		JPanel panel = new JPanel(new BorderLayout());
		panel.add(dropDown);
		return panel;
	}

	/**
	 * Gibt die aktuelle Option aus
	 * 
	 * @return ein string, der die aktuelle Option enthält
	 */
	public String status() {
		return dropDown.getSelectedItem().toString();
	}
}
