package controller;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import model.MCrypt;

import utility.Utility;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.util.Random;

/**
 * Definiert 'createKeyPanel'-Methode fuer monoalphabetische Verschluesselung
 * 
 * @author zes, Julian Sturm, chrisi
 * @version 1.5
 */
public class MKeyPanel extends KeyPanel {

	private String key = this.keyAsString();

	private final int serialnumber = 2;
	private JTextField[] keys = new JTextField[Utility.ALPHABET_SIZE];
	private JLabel[] names = new JLabel[Utility.ALPHABET_SIZE];
	private JPanel[] nameKeyPanels = new JPanel[Utility.ALPHABET_SIZE];
	private int maxInput = Utility.KEY_LENGTH_FOR_SINGLE_SHIFT;

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
		for (int i = 0; i < Utility.ALPHABET_SIZE; i++) {
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
		description.setFont(Utility.HEADLINE_LABEL_FONT);
		description.setForeground(Utility.DARK_GREEN);
		title.add(description, BorderLayout.SOUTH);

		// intialisiert Eintraege von keys und setzt Textstil
		for (int i = 0; i < Utility.ALPHABET_SIZE; i++) {
			names[i].setFont(Utility.LABEL_FONT);
			names[i].setForeground(Utility.DARK_GREEN);
			names[i].setAlignmentX(Component.CENTER_ALIGNMENT);
			keys[i].setFont(Utility.TEXT_FONT);
			keys[i].setBorder(Utility.TEXTFIELD_BORDER);
			keys[i].setHorizontalAlignment(JTextField.CENTER);
			// keys[i].setColumns(1);
			keys[i].setDocument(new LimitedTextfield(maxInput, i, keys));

			// sorgt dafuer dass Text im Textfeld markiert wird beim Klicken
			// benoetigt extra variable j, da i sich veraendert
			int j = i;
			keys[i].addFocusListener(new FocusListener() {
				@Override
				public void focusGained(FocusEvent e) {
					keys[j].selectAll();
					keys[j].setBorder(Utility.FOCUS_TEXTFIELD_BORDER);
				}

				@Override
				public void focusLost(FocusEvent e) {
					keys[j].setBorder(Utility.TEXTFIELD_BORDER);
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
		for (int i = 0; i < Utility.ALPHABET_SIZE; i++) {
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
	 * Methode die einen Zufaelligen Schluessel erzeugt
	 */
	@Override
	public void randomKey() {
		int leftLimit = 0;
		int rightLimit = 25;
		String[] keyString = new String[Utility.ALPHABET_SIZE];
		for (int i = 0; i < Utility.ALPHABET_SIZE; i++) {
			keyString[i] = Character.toString((char) ('A' + i));
		}
		Random random = new Random();
		// Random Shuffel des Array keyString erzeugt eienn Random Schluessel
		for (int i = 0; i < Utility.ALPHABET_SIZE; i++) {
			int randomLimitedInt = leftLimit + (int) (random.nextFloat() * (rightLimit - leftLimit + 1));
			String temp = keyString[i];
			keyString[i] = keyString[randomLimitedInt];
			keyString[randomLimitedInt] = temp;
		}
		String key = "";
		for (int i = 0; i < Utility.ALPHABET_SIZE; i++) {
			key = key + keyString[i];
		}
		this.setKey(key);
	}

	/**
	 * Eine Methode die einen Schluessel in die Schluesselfelder Schreibt
	 * 
	 * @param key der Schluessel der in die Schluesselfelder Geschrieben wird
	 */
	@Override
	public void setKey(String key) {
		for (int i = 0; i < Utility.ALPHABET_SIZE; i++) {
			this.keys[i].setText(key.substring(i, i + 1));
		}
	}

	/**
	 * Methode zum Initialisieren der Namen der Textfelder (Alphabet)
	 */
	private void initNames() {
		for (int i = 0; i < Utility.ALPHABET_SIZE; i++) {
			int val = 65 + i;
			char c = (char) val;
			this.names[i] = new JLabel(Character.toString(c), JLabel.CENTER);
		}
	}
	
	/**
	 * Die Methode gibt die Seriennumer des KeyPanels zurück
	 * CKeypanel = 1
	 * MKeypanel = 2
	 * VKeypanel = 3
	 * 
	 * @param return 2;
	 */
	public int getSerialnumber() {
		return this.serialnumber;
	}
}
