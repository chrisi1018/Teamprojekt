package controller;

import java.awt.BorderLayout;
import javax.swing.JComboBox;
import javax.swing.JPanel;
import java.awt.event.ActionListener;

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
	 * @param change der Actionlistener
	 */
	public Dropdown(String[] options, ActionListener change) {
		dropDown.setEditable(false);
		this.options = options;
		for (int i = 0; i < options.length; i++) {
			dropDown.addItem(options[i]);
		}
		dropDown.addActionListener(change);
	}

	/**
	 * Erstellt ein Panel mit dem Dropdown Menue
	 * 
	 * @return ein panel mit dem Dropdown Menue
	 */
	protected JPanel createDropdown() {
		JPanel panel = new JPanel(new BorderLayout());
		panel.add(dropDown);
		return panel;
	}

	/**
	 * Gibt die aktuelle Option aus
	 * 
	 * @return ein string, der die aktuelle Option enthält
	 */
	protected String status() {
		return dropDown.getSelectedItem().toString();
	}
}
