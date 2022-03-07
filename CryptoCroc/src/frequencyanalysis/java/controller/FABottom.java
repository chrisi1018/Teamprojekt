package controller;

import javax.swing.Box;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.JScrollPane;
import java.awt.Dimension;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.FlowLayout;

import model.Crypt;
import utility.Utility;


/**
 * Die Klasse erzeugt den unteren Teil der Haufigkeitsanalyse, und dient als Schnittstelle zum Hauptprogramm
 * 
 * @author Julian, chrisi
 * @version 1.1
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
	private GradientButton button;
	private FAController faController;
	
	/**
	 * Konstruktor von FABottom
	 * @param key Das KeyPanel des Hauptfeldes
	 * @param tables gibt die FATable weiter
	 * @param faController der FAController der Haeufigkeitsanalyse um den Frame zu schliessen
	 */
	FABottom(KeyPanel key, FATable[] tables, FAController faController) {
		this.key = key;
		this.faController = faController;
		this.tables = tables;
		this.mono = false;
		this.crypt = this.key.getCrypt();
		this.button = new GradientButton("Schl\u00fcssel \u00fcbernehmen");
		this.button.addActionListener(e -> buttonClick());
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
	 * Erzeugt das Panel fuer den Geheimtext, die Schluesselfelder und dem Button.
	 * Und setzt die Anordnung der Elemente und den Stil.
	 * 
	 * @return das Panel fuer den Geheimtext, die Schlusselfelder und dem Button
	 */
	public JPanel createBottomPanel() {
		JPanel bottomPanel = new JPanel(new FlowLayout(0));
		
		//Erzeugt den Linken Teil des Unteren Panels mit dem GeheimText
		JPanel leftPanel = new JPanel(new FlowLayout(0));
		
		//Erstellt die Ueberschift und setzt den Stil
		JLabel crypto = new JLabel("Geheimtext");
		crypto.setFont(Utility.HEADLINE_LABEL_FONT);
		crypto.setForeground(Utility.DARK_GREEN);

		//Erstellt das Textfeld fuer den Geheimtext
		JScrollPane scroll = new JScrollPane(this.cryptoText, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,
				JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
		scroll.setPreferredSize(new Dimension(400, 110));
		scroll.setBorder(Utility.TEXTFIELD_BORDER);
		
		//Fuegt die Elemente dem Panel hinzu
		leftPanel.add(crypto);
		leftPanel.add(scroll);
		leftPanel.setPreferredSize(new Dimension(410, 150));
		
		//Erzeugt den mittleren Teil des unteren Panels mit den Schluessel-Textfeldern
		JPanel middlePanel = new JPanel(new FlowLayout(0));
		
		//Erstellt die Ueberschift und setzt den Stil
		JLabel key = new JLabel("Schl\u00fcssel");
		key.setFont(Utility.HEADLINE_LABEL_FONT);
		key.setForeground(Utility.DARK_GREEN);
		
		//Erstellt den beschreibenden Text und setzt den Stil
		JLabel keyText = new JLabel("Gebe hier deinen Schl\u00fcssel ein:");
		keyText.setFont(Utility.LABEL_FONT);
		keyText.setForeground(Utility.DARK_GREEN);
		
		//Setzt die Texte zusammen in ein Panel 
		JPanel textPanel = new JPanel(new FlowLayout(0));
		textPanel.add(key);
		textPanel.add(keyText);
		textPanel.setPreferredSize(new Dimension(250, 55));

		//Erzeugt alle noetigen Schluesselfelder
		JPanel keyPanel = new JPanel(new FlowLayout(0));
		for (int i = 0; i < this.keyText.length; i++) {
			keyPanel.add(this.keyText[i]);
		}
		
		//Erzeugt ein Panel fuer den Button, damit der Rand gleich bleibt
		JPanel buttonPanel = new JPanel(new FlowLayout(0));
		buttonPanel.add(this.button);
		
		//Fuegt alle Elemente mit Abstanden dem Panel hinzu
		middlePanel.add(textPanel);
		middlePanel.add(Box.createRigidArea(new Dimension(460, 0)));
		middlePanel.add(keyPanel);
		middlePanel.add(Box.createRigidArea(new Dimension(460, 0)));
		middlePanel.add(buttonPanel);
		middlePanel.setPreferredSize(new Dimension(460, 150));
		
		bottomPanel.add(leftPanel);
		bottomPanel.add(middlePanel);
		
		return bottomPanel;
	}
	
	/**
	 * Initialisert die Textfelder fuer die Schuesseleingabe
	 */
	private void initKeyText() {
		for (int i = 0; i < this.keyText.length; i++) {
			this.keyText[i] = new JTextField();
			this.keyText[i].setDocument(new LimitedTextfield(1));
			this.keyText[i].setBorder(Utility.TEXTFIELD_BORDER);
			this.keyText[i].setForeground(Utility.DARK_GREEN);
			this.keyText[i].setFont(Utility.TEXT_FONT);
			this.keyText[i].setHorizontalAlignment(JTextField.CENTER);
			// Setzt die Breite und die Hoehe des Textfelds
			this.keyText[i].setPreferredSize(new Dimension(Utility.WIDTH_TEXTFIELD_ONE_LETTER_IN_FA, Utility.HEIGHT_TEXTFIELD_IN_FA));
			this.keyText[i].setText("A");
			
			// sorgt dafuer dass Text im Textfeld markiert wird beim Klicken
			// benoetigt extra variable j, da i sich veraendert
			int j = i;
			keyText[i].addFocusListener(new FocusListener() {
				@Override
				public void focusGained(FocusEvent e) {
					keyText[j].selectAll();
					keyText[j].setBorder(Utility.FOCUS_TEXTFIELD_BORDER);
				}

				@Override
				public void focusLost(FocusEvent e) {
					keyText[j].setBorder(Utility.TEXTFIELD_BORDER);
				}
			});
		}
	}
	
	/**
	 * Initalisiert das Label KryptoText
	 */
	private void initCryptoText() {
		this.cryptoText = new JLabel();
		this.cryptoText.setFont(Utility.TEXT_FONT);
		this.cryptString = key.getController().getCryptoText();
		//HTML ist notwendig damit das Label Zeilenumbrueche hat
		this.cryptoText.setText("<html><p align=\"justify\" style=\"width:370px\">"
		+ this.cryptString
		+ "</p></html>");
		
	}
	
	/**
	 * Updated die Textfelder in denen der Schluessel eingeben werden kann abhaengig von der 
	 * Schluessellaenge
	 * 
	 * 
	 * @param tables die FATabels
	 */
	public void updateKeyText(FATable[] tables) {
		this.tables = tables;
		for (int i = 0; i < this.tables.length; i++) {
			this.tables[i].setBottom(this);
		}
		if (this.mono) { //Macht die Schluesselfelder unsichtbar
			JTextField[] temp = this.tables[0].getTextFields();
			for (int i = 0; i < temp.length; i++) {
				temp[i].setText(this.monoString.substring(i, i + 1));
			}
			for (int i = 0; i < this.keyText.length; i++) {
				this.keyText[i].setVisible(false);
			}
			this.tables[0].setTextFields(temp);
		} else { // Macht die Passende Anzahl der Schluesselfelder sichtbar
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
	 * Aktualisiert das Label anhand des Schluessels
	 */
	public void updateCryptoText() {
		updateKeyString();
		this.cryptoText.setText("<html><p align=\"justify\" style=\"width:370px\">"
				+ this.crypt.decryptAll(cryptString, keyString)
				+ "</p></html>");
	}

	/**
	 * Wechselt ob die Monoalphabetische Verschluesselung verwendet wird.
	 */
	public void switchMono() {
		this.mono = !this.mono;
	}

	/**
	 * Updated den keyString und monoString
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
	 * Setter-Methode fuer Crypt
	 * 
	 * @param crypt die Verschluesselung die verwendet wird
	 */
	public void setCrypt(Crypt crypt) {
		this.crypt = crypt;
	}
	
	/**
	 * Getter-Methode fuer mono
	 * @return der boolean Wert von mono
	 */
	public boolean getMono() {
		return mono;
	}
	
	/**
	 * Die Methode die ausgefuehrt wird wenn der Button "Schluessel uebernehmen"
	 * angeklickt wird, der Schluessel wird im Hauptfenster ins Schluesseltextfeld
	 * geschrieben und schlie�t den Frame
	 */
	private void buttonClick() {
		MainController controller = this.key.getController();
		int index = 0;
		if (this.mono) {
			index = 2;
		} else {
			if (this.keyString.length() == 1) {
				index = 1;
			} else {
				if (this.keyString.length() > 1) {
					index = 3;
				}
			}
		}
		this.key = controller.setKeyPanel(index);
		if (this.mono) {
			this.key.setKey(monoString);
		} else {
			int length = keyString.length();
			this.keyString = "";
			for (int i = 0; i < length; i++) {
				this.keyString = this.keyString + this.keyText[i].getText();
			}
			this.key.setKey(keyString);
		}
		KeyPanel.faSwitchOpen();
		this.faController.disposeFrame();
		
	}
}
