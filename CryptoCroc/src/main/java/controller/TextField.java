package controller;

import java.awt.BorderLayout;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

/**
 * Beschreibt ein TextField in CryptoCroc
 * 
 * @author zes
 * @version 1.0
 */
public class TextField {

	private JTextField text;
	private JLabel label;

	/**
	 * Konstruktor für ein TextField
	 * 
	 * @param name der Name bzw Titel des Textfelds
	 */
	public TextField(String name) {
		label = new JLabel(name);
	}

	/**
	 * Methode zum Erstellen eines TextFields
	 * 
	 * @return ein JPanel mit einem Label und ein Textfeld darunter
	 */
	public JPanel createTextfieldPanel() {
		JPanel panel = new JPanel(new BorderLayout());
		panel.add(label, BorderLayout.PAGE_START);
		panel.add(text, BorderLayout.PAGE_END);
		return panel;
	}

}
