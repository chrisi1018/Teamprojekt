package controller;

import java.awt.FlowLayout;


import javax.swing.JButton;
import javax.swing.JPanel;

import model.Crypt;
import view.Messages;

/**
 * Beschreibt Aufbau der Spalte für die Buttons und die Schlüssel in CryptoCroc
 * 
 * @author chrisi
 * @version 1.1
 */
public abstract class KeyPanel {
	private JButton encrypt = new JButton("verschl\u00fcsseln");
	private JButton decrypt = new JButton("entschl\u00fcsseln");
	private Crypt crypt;
	private MainController controller; 
	
	/**
	 * 
	 * 
	 * @param controller
	 */
	public KeyPanel(MainController controller) {
		this.controller = controller;
	}
	
	/**
	 * Initialisiert die Buttons durch die Action Listener
	 */
	public void initKey() {
		encrypt.addActionListener(e -> this.clickButtonEncrypt());
		decrypt.addActionListener(e -> this.clickButtonDecrypt());
	}
	
	/**
	 * Erzeugt die Buttons und die Schlüssel für das JPanel
	 * 
	 * @return ein JPanel mit Buttons und dem Schlüssel
	 */
	public abstract JPanel createKeyPanel(); //Bei der Implementierung createButtonPanel hinzufügen
	
	/**
	 * Gibt den aktuellen Key zurück
	 * 
	 * @return den aktuellen Key als String
	 */
	public abstract String getKey();
	
	/**
	 * Erzeugt die Buttons in einem JPanel
	 * 
	 * @return ein JPanel mit die Buttons "verschlüsseln" und "entschlüsseln"
	 */
	protected JPanel createButtonPanel() {
		JPanel panel = new JPanel(new FlowLayout()); //FlowLayout wichtig damit Button passende Größe haben
		panel.add(encrypt);
		panel.add(decrypt);
		return panel;
	}
	
	/**
	 * Gib den Button für die Verschlüsselung zurück
	 * 
	 * @return den Button "verschlüsseln"
	 */
	public JButton getEncrypt() {
		return encrypt;
	}
	
	/**
	 * Gibt den Button für die Entschlüsselung zurück
	 * 
	 * @return den Button "entschlüsseln"
	 */
	public JButton getDecrypt() {
		return decrypt;
	}
	
	/**
	 * Gibt das Verfahren für die Verschlüsselung zurück
	 * 
	 * @return das Verschlüsselungs-Verfahren
	 */
	public Crypt getCrypt() {
		return crypt;
	}
	
	/**
	 * Setzt die Crypt-Instanz der aktuellen Instanz auf eine neue Crypt-Instanz
	 * 
	 * @param newCrypt neue Crypt-Instanz
	 */
	public void setCrypt(Crypt newCrypt) {
		this.crypt = newCrypt;
	}
	
	/**
	 * 
	 */
	public void clickButtonEncrypt() {
		String plainText = this.controller.getPlainText();
		String cryptoText = this.controller.getCryptoText();
		String key = getKey();
		
		if (plainText.isEmpty()) {
			Messages.errorMessage("Zum verschl\u00fcsseln muss im Klartextfeld ein Text eingegeben werden.");
		} else {
			if (this.crypt.checkKey(key)) {
				if (cryptoText.isEmpty() || Messages.yesNoQuestion("Darf der Geheimtext im Geheimtextfeld überschrieben werden?")) {
					cryptoText = this.crypt.cryptAll(plainText, key);
					this.controller.setCryptoText(cryptoText);
				}
			} else {
				Messages.errorMessage("Der eingegebene Schl\u00fcssel ist nicht korrekt.");
			}
		}
	}
	
	/**
	 * 
	 */
	public void clickButtonDecrypt() {
		String plainText = this.controller.getPlainText();
		String cryptoText = this.controller.getCryptoText();
		String key = getKey();
		
		if (cryptoText.isEmpty()) {
			Messages.errorMessage("Zum entschl\u00fcsseln muss im Geheimtestfeld ein Text eingegeben werden.");
		} else {
			if (this.crypt.checkKey(key)) {
				if (plainText.isEmpty() || Messages.yesNoQuestion("Darf der Klartext im Klartextfeld überschrieben werden?")) {
					plainText = this.crypt.decryptAll(cryptoText, key);
					this.controller.setPlainText(plainText);
				}
			} else {
				Messages.errorMessage("Der eingegebene Schl\u00fcssel ist nicht korrekt.");
			}
		}
	}
}
