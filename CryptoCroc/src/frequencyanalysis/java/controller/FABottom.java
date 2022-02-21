package controller;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.JScrollPane;
import javax.swing.JComponent;
import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.FlowLayout;

import model.Crypt;
import utility.Utility;


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
	private GradientButton button;
	private FAController faController;
	
	/**
	 * Konstruktor von FABottom
	 * @param key Das KeyPanel des Hauptfeldes
	 * @param tables gibt die FATable weiter
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
	 * Erzeugt das Panel f�r den Unteren Teil der H�ufigkeitsanalyse
	 * 
	 * @return das erzeugte Panel
	 */
	public JPanel createBottomPanel() {
		JPanel bottomPanel = new JPanel();
		bottomPanel.setLayout(new BorderLayout());
		//Erzeugt den Linken Teil des Unteren Panels mit dem GeheimText
		JPanel leftPanel = new JPanel(new BorderLayout());
		JLabel crypto = new JLabel("   Geheimtext");
		crypto.setFont(Utility.LABEL_FONT);
		crypto.setForeground(Utility.DARK_GREEN);
		crypto.setVisible(true);
		JScrollPane scroll = new JScrollPane(this.cryptoText, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,
				JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
		this.cryptoText.setVisible(true);
		scroll.setPreferredSize(new Dimension(500, 60));
		scroll.setBorder(Utility.TEXTFIELD_BORDER);
		leftPanel.add(crypto, BorderLayout.NORTH);
		leftPanel.add(scroll, BorderLayout.CENTER);
		leftPanel.add(new JPanel(), BorderLayout.WEST);
		bottomPanel.add(leftPanel, BorderLayout.WEST);
		
		//Erzeugt den mittleren Teil des unteren Panels mit den KeyText feldern
		JPanel middlePanel = new JPanel(new GridLayout(2, 1, 0, 0));
		JLabel key = new JLabel("    Schl\u00fcssel");
		key.setFont(Utility.HEADLINE_LABEL_FONT);
		key.setForeground(Utility.DARK_GREEN);
		key.setVisible(true);
		JLabel keyText = new JLabel("     Gebe hier deinen Schl\u00fcssel ein:");
		keyText.setFont(Utility.LABEL_FONT);
		keyText.setForeground(Utility.DARK_GREEN);
		keyText.setVisible(true);
		JPanel textPanel = new JPanel(new GridLayout(2, 1));
		textPanel.add(key);
		textPanel.add(keyText);
		middlePanel.add(textPanel);
		JPanel keyPanel = new JPanel(new FlowLayout(FlowLayout.LEADING));
		keyPanel.add(new JPanel());
		for (int i = 0; i < this.keyText.length; i++) {
			keyPanel.add(this.keyText[i]);
		}
		middlePanel.add(keyPanel);
		bottomPanel.add(middlePanel, BorderLayout.CENTER);
		//ERzeugt den Rechten Teil des Unteren Panels den Button
		JPanel rightPanel = new JPanel(new GridLayout(2, 1));
		JPanel buttonPanel = new JPanel();
		buttonPanel.add(button);
		rightPanel.add(new JPanel());
		rightPanel.add(buttonPanel);
		bottomPanel.add(rightPanel, BorderLayout.EAST);
		this.button.setVisible(true);
		bottomPanel.setVisible(true);
		bottomPanel.setVisible(true);
		return bottomPanel;
	}
	
	private void initKeyText() {
		for (int i = 0; i < this.keyText.length; i++) {
			this.keyText[i] = new JTextField();
			this.keyText[i].setDocument(new LimitedTextfield(1));
			this.keyText[i].setBorder(Utility.TEXTFIELD_BORDER);
			this.keyText[i].setForeground(Utility.DARK_GREEN);
			this.keyText[i].setFont(Utility.TEXT_FONT);
			this.keyText[i].setHorizontalAlignment(JTextField.CENTER);
			this.keyText[i].setPreferredSize(new Dimension(30, 30));
			this.keyText[i].setText("A");
		}
	}
	/**
	 * Initalisiert das Label KryptoText
	 */
	private void initCryptoText() {
		this.cryptoText = new JLabel();
		this.cryptoText.setFont(Utility.TEXT_FONT);
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
	 * Aktualisiert das Label anhand des Schl�ssels
	 */
	public void updateCryptoText() {
		updateKeyString();
		this.cryptoText.setText("<html><p align=\"justify\" style=\"width:370px\">"
				+ this.crypt.decryptAll(cryptString, keyString)
				+ "</p></html>");
	}

	/**
	 * Wechselt ob die Monoalphabetische Verschl�sselung verwendet wird.
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
	 * Setter-Methode f�r Crypt
	 */
	public void setCrypt(Crypt crypt) {
		this.crypt = crypt;
	}
	
	/**
	 * Getter-Methode f�r mono
	 * @return der boolean Wert von mono
	 */
	public boolean getMono() {
		return mono;
	}
	
	/**
	 * Getter-Methide f�r Crypt
	 */
	public Crypt getCrypt() {
		return this.crypt;
	}
	
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
