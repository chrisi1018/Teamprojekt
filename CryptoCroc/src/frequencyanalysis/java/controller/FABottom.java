package controller;

import javax.swing.JLabel;
import javax.swing.JFrame; //TODO zum TEsten
import javax.swing.JPanel;
import javax.swing.JTextField;

import model.Crypt;
import model.CCrypt;
import model.VCrypt;
import model.MCrypt;


/**
 * Die Klasse erzeugt den unteren Teil der Haufigkeitsanalyse, und dient als Schnittstelle zum Hauptprogramm
 * 
 * @author Julian
 * @version 1.0
 */
public class FABottom {
	
	private KeyPanel key;
	private FATable[] tables;
	private JFrame frame = new JFrame(); //TODO zum testen
	private JPanel bottomPanel = new JPanel(); //TODO zum testen
	private JTextField[] keyText;
	private JLabel cryptoText;
	private String keyString = "";
	private Crypt crypt;
	private boolean mono;
	private String cryptString;
	
	/**
	 * Konstruktor von FABottom
	 * @param key Das KeyPanel des Hauptfeldes
	 * @param tables gibt die FATable weiter
	 */
	FABottom(KeyPanel key, FATable[] tables) {
		this.key = key;
		this.tables = tables;
		this.mono = false;
		this.crypt = this.key.getCrypt();
		initCryptoText();
		initKeyText();
		this.cryptString = this.cryptoText.getText();
		//TODO AB hier zum Testen
		createMainPanel();
		this.frame.setSize(800, 800);
		this.frame.setVisible(true);
	}
	private void createMainPanel() {
		bottomPanel = new JPanel();
		for (int i = 0; i < this.keyText.length; i++) {
			bottomPanel.add(this.keyText[i]);
			this.keyText[i].setVisible(true);
		}
		bottomPanel.add(this.cryptoText);
		this.cryptoText.setVisible(true);
		bottomPanel.setVisible(true);
		frame.add(bottomPanel);
		bottomPanel.setVisible(true);
	}
	private void initKeyText() {
		if (this.mono) {
			this.keyText = new JTextField[0];
		} else {
			this.keyText = new JTextField[this.tables.length];
		}
		if (!key.getKey().isEmpty()) {
			this.keyString = key.getKey();
		}
		for (int i = 0; i < this.keyText.length; i++) {
			this.keyText[i] = new JTextField();
			this.keyText[i].setDocument(new LimitedTextfield(1));
			if (i < keyString.length()) {
				this.keyText[i].setText(keyString.substring(i, i + 1));
				JTextField[] temp = this.tables[i].getTextFields();
				char keyChar = this.keyString.charAt(i);
				for (int j = 0; j < temp.length; j++) {
					this.tables[i].disableListener(j);
					temp[j].setText(Character.toString(keyChar));
					if (keyChar < 'Z') {
						keyChar = (char) (keyChar + 1);
					} else {
						keyChar = 'A';
					}
					this.tables[i].enableListener(j);
				}
				this.tables[i].setTextFields(temp);
			} else {
				this.keyText[i].setText("A");
			}
		}
		this.frame.remove(bottomPanel);
		createMainPanel();
		this.frame.add(bottomPanel);
		this.bottomPanel.setVisible(true);
		this.frame.revalidate();
		this.frame.repaint();
	}
	
	/**
	 * Initalisiert das Label KryptoText
	 */
	private void initCryptoText() {
		this.cryptoText = new JLabel();
		this.cryptoText.setText(key.getController().getCryptoText());
	}
	
	/**
	 * Setzt die FATabels passend
	 * 
	 * @param tables die FATabels
	 */
	public void updateKeyText(FATable[] tables) {
		this.tables = tables;
		for (int i = 0; i < this.tables.length; i++) {
			this.tables[i].setBottom(this);
		}
		initKeyText();
		updateCryptoText();
	}
	
	/**
	 * Aktualisiert das Label anhand des Schlüssels
	 */
	public void updateCryptoText() {
		updateKeyString();
		this.cryptoText.setText(this.crypt.decryptAll(this.cryptString, keyString));
	}
	
	/**
	 * Wechselt ob die Monoalphabetische Verschlüsselung verwendet wird.
	 */
	public void switchMono() {
		this.mono = !this.mono;
	}
	
	/**
	 * Updated den Aktuellen keyString
	 */
	private void updateKeyString() {
		this.keyString = "";
		if (this.mono) {
			JTextField[] temp = tables[0].getTextFields();
			for (int i = 0; i < temp.length; i++) {
				this.keyString = this.keyString + temp[i].getText();
			}
		} else {
			for (int i = 0; i < tables.length; i++) {
				this.keyString = this.keyString + this.tables[i].getTextFields()[0].getText();
			}
		}
	}
	
	/**
	 * Setter-Methode für Crypt
	 */
	public void setCrypt(Crypt crypt) {
		this.crypt = crypt;
	}
	
	/**
	 * Getter-Methide für Crypt
	 */
	public Crypt getCrypt() {
		return this.crypt;
	}
}
