package controller;

import java.util.ArrayList;
import javax.swing.JComboBox;

/**
 * Beschreibt ein Dropdown Menü für die Verschlüsselungen
 * 
 * @author zes
 * @version 1.0
 */
public class Dropdown {

	private JComboBox<String> dropDown = new JComboBox<String>();
	private ArrayList<String> options = new ArrayList<String>();

	/**
	 * Konstruktor, der ein Dropdown Menü initialisiert
	 */
	public Dropdown() {
		options.add("Caesar");
		options.add("Monoalphabetisch");
		for (String opt : options) {
			dropDown.addItem(opt);
		}

	}

	/**
	 * Methode die es erlaubt neue Optionen hinzuzufügen
	 * 
	 * @param name Name der neuen Option, der zur ArrayList hinzugefügt wird
	 */
	public void addOption(String name) {
		options.add(name);
	}
}
