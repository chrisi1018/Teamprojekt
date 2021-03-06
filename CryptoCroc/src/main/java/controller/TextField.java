package controller;

import java.awt.BorderLayout;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

import utility.Utility;

/**
 * Beschreibt ein TextField in CryptoCroc
 * 
 * @author zes, chrisi
 * @version 1.2
 */
public class TextField {

	private JTextArea text = new JTextArea(10, 10);
	private JLabel label;
	private WritingChangeListener wcl;

	/**
	 * Konstruktor fuer ein TextField
	 * 
	 * @param name der Name bzw Titel des Textfelds
	 */
	public TextField(String name) {
		wcl = new WritingChangeListener(text);
		label = new JLabel(name);
		text.setFont(Utility.TEXT_FONT);
		text.setBorder(Utility.TEXTFIELD_BORDER);
		text.setLineWrap(true);
		// sorgt dafuer, dass das ganze Wort in die naechste Zeile wandert
		text.setWrapStyleWord(true);
		text.getDocument().addDocumentListener(wcl);
		label.setFont(Utility.HEADLINE_LABEL_FONT);
		label.setForeground(Utility.DARK_GREEN);
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
		
		// zum Scrollen durch die TextArea; wird nur angezeigt, wenn noetig
		JScrollPane scroll = new JScrollPane(text);
		scroll.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
		scroll.setVisible(true);
		panel.add(scroll);
		
		// vertikale Luecke zwischen Elementen im Panel
		bLayout.setVgap(0);
		return panel;
	}
	
	/**
	 * Gibt den aktuellen Text im Textfeld zurueck
	 * 
	 * @return den Text im Textfeld
	 */
	public String getText() {
		return this.text.getText();
	}
	
	/**
	 * Gibt den Text in dem Textfeld aus
	 * 
	 * @param text 
	 */
	public void setText(String text) {
		this.text.setText(text);
	}

	/**
	 * Gibt das JTextArea Objekt zurueck
	 * 
	 * @return das JTextArea Objekt
	 */
	public JTextArea getTextArea() {
		return this.text;

	}
	
	/**
	 * Gibt zurueck, ob das Textfeld leer ist
	 * 
	 * @return ob Feld leer
	 */
	public boolean isEmpty() {
		return this.getText().equals("");
	}
}
