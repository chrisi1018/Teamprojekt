package controller;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.JScrollPane;
import model.Crypt;
import utility.Utility;
import java.awt.Dimension;

/**
 * Die Klasse erzeugt den unteren Teil der Haufigkeitsanalyse, und dient als Schnittstelle zum Hauptprogramm
 * 
 * @author Julian
 * @version 1.0
 */
public class FABottom {
	
	private KeyPanel key;
	private FATable[] tables;
	private JTextField[] keyText = new JTextField[Utility.MAXIMUM_KEY_LENGTH];
	private JLabel cryptoText;
	private String keyString = "";
	private String monoString = "";
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
		for (int i = 0; i < this.keyText.length; i++) {
			this.keyText[i] = new JTextField();
			this.keyText[i].setDocument(new LimitedTextfield(1));
		}
		this.keyString = this.key.getKey();
		if (this.keyString.length() == Utility.ALPHABET_SIZE) {
			this.monoString = this.keyString;
			this.keyString = "";
		} else {
			for (int i = 0; i < Utility.ALPHABET_SIZE; i++) {
				this.monoString = this.monoString + Character.toString((char) ('A' + i));
			}
		}
		initCryptoText();
		initKeyText();
	}
	
	/**
	 * Erzeugt das Panel für den Unteren Teil der Häufigkeitsanalyse
	 * 
	 * @return das erzeugte Panel
	 */
	public JPanel createBottomPanel() {
		JPanel bottomPanel = new JPanel();
		for (int i = 0; i < this.keyText.length; i++) {
			bottomPanel.add(this.keyText[i]);
		}
		JScrollPane scroll = new JScrollPane(this.cryptoText, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,
				JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
		scroll.setPreferredSize(new Dimension(500, 70));
		bottomPanel.add(scroll);
		this.cryptoText.setVisible(true);
		bottomPanel.setVisible(true);
		bottomPanel.setVisible(true);
		return bottomPanel;
	}
	
	private void initKeyText() {
		for (int i = 0; i < this.keyText.length; i++) {
			this.keyText[i] = new JTextField();
			this.keyText[i].setDocument(new LimitedTextfield(1));
			this.keyText[i].setText("A");
		}
	}
	/**
	 * Initalisiert das Label KryptoText
	 */
	private void initCryptoText() {
		this.cryptoText = new JLabel();
		this.cryptString = key.getController().getCryptoText();
		this.cryptoText.setText("<html><p align=\"justify\" style=\"width:370px\">"
		+ this.cryptString
		+ "</p></html>");
		
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
		if (this.mono) {
			JTextField[] temp = this.tables[0].getTextFields();
			for (int i = 0; i < temp.length; i++) {
				temp[i].setText(this.monoString.substring(i, i + 1));
			}
			for (int i = 0; i < this.keyText.length; i++) {
				this.keyText[i].setVisible(false);
			}
			this.tables[0].setTextFields(temp);
		} else {
			for (int i = 0; i < this.tables.length; i++) {
				JTextField[] temp = this.tables[i].getTextFields();
				char keyChar;
				if (i < this.keyString.length()) {
					keyChar = this.keyString.charAt(i);
				} else {
					keyChar = 'A';
				}
				for (int j = 0; j < temp.length; j++) {
					temp[j].setText(Character.toString(keyChar));
					if (keyChar == 'Z') {
						keyChar = 'A';
					} else {
						keyChar = (char) (keyChar + 1);
					}
				}
				this.keyText[i].setVisible(true);
				this.tables[i].setTextFields(temp);
			}
			for (int i = this.tables.length; i < this.keyText.length; i++) {
				this.keyText[i].setVisible(false);
			}
		}
		updateCryptoText();
	}
	
	/**
	 * Aktualisiert das Label anhand des Schlüssels
	 */
	public void updateCryptoText() {
		updateKeyString();
		this.cryptoText.setText("<html><p align=\"justify\" style=\"width:370px\">"
				+ this.crypt.decryptAll(cryptString, keyString)
				+ "</p></html>");
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
		if (this.mono) {
			this.monoString = "";
			JTextField[] temp = tables[0].getTextFields();
			for (int i = 0; i < temp.length; i++) {
				this.monoString = this.monoString + temp[i].getText();
			}
		} else {
			this.keyString = "";
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
	 * Getter-Methode für mono
	 * @return der boolean Wert von mono
	 */
	public boolean getMono() {
		return mono;
	}
	
	/**
	 * Getter-Methide für Crypt
	 */
	public Crypt getCrypt() {
		return this.crypt;
	}
}
