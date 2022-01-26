package controller;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import model.MCrypt;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;

/**
 * Definiert 'createKeyPanel'-Methode fuer monoalphabetische Verschluesselung
 * 
 * @author zes, Julian Sturm
 * @version 1.4
 */
public class MKeyPanel extends KeyPanel {

	private final int alphabetSize = 26;
	private String key = this.keyAsString();
	private JTextField[] keys = new JTextField[alphabetSize];
	private JLabel[] names = new JLabel[alphabetSize];
	private JPanel[] nameKeyPanels = new JPanel[alphabetSize];
	private int maxInput = 1;

	/**
	 * Konstruktor, der der neuen Instanz eine monoalphabetische Verschluesselung
	 * zuordnet und die aktuelle MainController-Instanz wird gesichert
	 * 
	 * @param controller die Controller-Instanz
	 */
	public MKeyPanel(MainController controller) {
		super(controller);
		super.setCrypt(new MCrypt());
		initTextFields();
	}

	/**
	 * Methode die den aktuellen Schluessel zurueckgibt
	 */
	@Override
	public String getKey() {
		return this.keyAsString();
	}

	/**
	 * Wandelt das Array von Schluesselbuchstaben um in ein Schluesselwort
	 * 
	 * @return das Schluesselwort als String
	 */
	private String keyAsString() {
		this.key = "";
		if (keys != null) {
			for (int i = 0; i < keys.length; i++) {
				if (keys[i] != null) {
					this.key += keys[i].getText();
				}
			}
		}
		return this.key;
	}
	
	/**
	 * Initialisiert die TextFelder
	 */
	private void initTextFields() {
		for (int i = 0; i < alphabetSize; i++) {
			keys[i] = new JTextField();
		}
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
			keys[i].setFont(new Font(Font.DIALOG, Font.BOLD, 15));
			keys[i].setColumns(1);
			keys[i].setPreferredSize(new Dimension(19, 30));
			keys[i].setDocument(new LimitedTextfield(maxInput, i, keys));

			// sorgt dafuer dass Text im Textfeld markiert wird beim Klicken
			// benoetigt extra variable j, da i sich veraendert
			int j = i;
			keys[i].addFocusListener(new FocusListener() {
				@Override
				public void focusGained(FocusEvent e) {
					keys[j].selectAll();
				}

				@Override
				public void focusLost(FocusEvent e) {
					// tue nichts
				}
			});

			JPanel nameKeyPanel = new JPanel();
			nameKeyPanel.setLayout(new BoxLayout(nameKeyPanel, BoxLayout.PAGE_AXIS));
			nameKeyPanel.add(names[i]);
			nameKeyPanel.add(keys[i]);
			nameKeyPanel.setPreferredSize(new Dimension(25, 50));
			nameKeyPanels[i] = nameKeyPanel;
		}

		// fuer die Buchstaben und Textfelder
		FlowLayout fLayout = new FlowLayout();
		fLayout.setVgap(25);
		JPanel inputPanel = new JPanel(fLayout);
		for (int i = 0; i < alphabetSize; i++) {
			inputPanel.add(nameKeyPanels[i]);
		}

		// alles zusammenfuegen und Buttons dazu machen
		JPanel total = new JPanel();
		total.setLayout(new BoxLayout(total, BoxLayout.PAGE_AXIS));
		total.add(title);
		title.add(Box.createRigidArea(new Dimension(0, 20)));
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
			this.names[i] = new JLabel(Character.toString(c), JLabel.CENTER);
		}
	}
}
