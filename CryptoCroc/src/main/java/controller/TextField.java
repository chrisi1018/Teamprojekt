package controller;

import java.awt.BorderLayout;
import java.awt.Font;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

/**
 * Beschreibt ein TextField in CryptoCroc
 * 
 * @author zes
 * @version 1.1
 */
public class TextField {

	private JTextArea text = new JTextArea(10, 10);
	private JLabel label;

	/**
	 * Konstruktor für ein TextField
	 * 
	 * @param name der Name bzw Titel des Textfelds
	 */
	public TextField(String name) {
		label = new JLabel(name);
		text.setFont(new Font(Font.DIALOG, Font.PLAIN, 20));
		text.setLineWrap(true);
		// sorgt dafür das ganzes Wort in die nächste Zeile wandert
		text.setWrapStyleWord(true);
		
		label.setFont(new Font(Font.DIALOG, Font.BOLD, 20));
	}

	/**
	 * Methode zum Erstellen eines TextFields
	 * 
	 * @return ein JPanel mit einem Label und ein Textfeld darunter
	 */
	public JPanel createTextfieldPanel() {
		BorderLayout bLayout = new BorderLayout();
		JPanel panel = new JPanel(bLayout);
		panel.add(label, BorderLayout.PAGE_START);
		panel.add(text, BorderLayout.CENTER);
		
		// zum scrollen durch die TextArea; wird nur angezeigt wenn nötig
		JScrollPane scroll = new JScrollPane(text);
		scroll.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
		scroll.setVisible(true);
		panel.add(scroll);
		
		// vertikale Lücke zwischen Elementen im Panel
		bLayout.setVgap(10);
		return panel;
	}

}
