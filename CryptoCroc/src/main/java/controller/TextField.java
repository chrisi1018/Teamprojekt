package controller;

import java.awt.BorderLayout;
import java.awt.Font;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;

/**
 * Beschreibt ein TextField in CryptoCroc
 * 
 * @author zes
 * @version 1.1
 */
public class TextField {

	private JTextArea text = new JTextArea();
	private JLabel label;

	/**
	 * Konstruktor für ein TextField
	 * 
	 * @param name der Name bzw Titel des Textfelds
	 */
	public TextField(String name) {
		label = new JLabel(name);
		text.setFont(new Font(Font.DIALOG, Font.PLAIN, 20));
	}

	/**
	 * Methode zum Erstellen eines TextFields
	 * 
	 * @return ein JPanel mit einem Label und ein Textfeld darunter
	 */
	public JPanel createTextfieldPanel() {
		JPanel panel = new JPanel(new BorderLayout());
		panel.add(label, BorderLayout.PAGE_START);
		panel.add(text, BorderLayout.CENTER);
		return panel;
	}

}
