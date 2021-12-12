package controller;

import javax.swing.BoxLayout;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.Font;
import java.awt.GridLayout;

/**
 * Definiert 'createKeyPanel'-Methode fuer monoalphabetische Verschluesselung
 * 
 * @author zes
 * @version 1.0
 */
public class MKeyPanel extends KeyPanel {

	private final int alphabetSize = 26;
	private JTextField[] keys = new JTextField[alphabetSize];
	private JLabel[] names = new JLabel[alphabetSize];

	/**
	 * Konstruktor, der der neuen Instanz eine monoalphabetische Verschluesselung
	 * zuordnet
	 * 
	 */
	public MKeyPanel() {
		// super.setCrypt(new MCrypt());
		// TODO
	}

	@Override
	public JPanel createKeyPanel() {
		// Alphabet in Array schreiben
		this.initNames();

		// erstellt Panel mit JLabel als titel
		JPanel title = new JPanel();
		JLabel description = new JLabel("Schl\u00fcssel");
		title.setLayout(new BoxLayout(title, BoxLayout.PAGE_AXIS));
		description.setAlignmentX(Component.CENTER_ALIGNMENT);
		description.setFont(new Font(Font.DIALOG, Font.BOLD, 20));
		title.add(description, BorderLayout.SOUTH);

		// intialisiert Eintraege von keys und setzt Textstil
		for (int i = 0; i < alphabetSize; i++) {
			names[i].setFont(new Font(Font.DIALOG, Font.BOLD, 15));
			keys[i] = new JTextField();
			keys[i].setFont(new Font(Font.DIALOG, Font.BOLD, 15));
		}

		// grid für die Buchstaben und Textfelder
		GridLayout gLayout = new GridLayout(0, 6, 0, 5);
		JPanel inputPanel = new JPanel(gLayout);
		for (int i = 0; i < alphabetSize; i++) {
			if (i % 2 == 0) {
				inputPanel.add(names[i]);
				inputPanel.add(keys[i]);
				inputPanel.add(new JLabel(""));
				inputPanel.add(new JLabel(""));	
			} else {
				inputPanel.add(names[i]);
				inputPanel.add(keys[i]);
			}
			
		}

		// alles zusammenfügen und Buttons dazu machen
		JPanel total = new JPanel(new GridLayout(3, 0));
		total.add(title);
		total.add(inputPanel);
		total.add(this.createButtonPanel());

		return total;
	}

	/**
	 * Methode zum Initialisieren der Namen der Textfelder (Alphabet)
	 */
	private void initNames() {
		for (int i = 0; i < alphabetSize; i++) {
			int val = 65 + i;
			char c = (char) val;
			this.names[i] = new JLabel(Character.toString(c));
		}
	}
}
