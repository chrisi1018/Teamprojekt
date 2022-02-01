package controller;

import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JPanel;

import model.Crypt;
import model.TextEdit;
import view.Messages;

/**
 * Beschreibt Aufbau der Spalte fuer die Buttons und die Schluessel in
 * CryptoCroc
 * 
 * @author chrisi
 * @version 1.1
 */
public abstract class KeyPanel {
	private JButton encrypt = new JButton("verschl\u00fcsseln");
	private JButton decrypt = new JButton("entschl\u00fcsseln");
	private JButton freqAna = new JButton("H\u00e4ufigkeitsanalyse");
	private Crypt crypt;
	private MainController controller;

	/**
	 * Die aktuelle MainController-Instanz wird gesichert
	 * 
	 * @param controller die MainController-Instanz
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
		freqAna.addActionListener(e -> this.clickButtonFreqAna());
	}

	/**
	 * 
	 * Erzeugt die Buttons und die Schluessel fuer das JPanel
	 * 
	 * @return ein JPanel mit Buttons und dem Schluessel
	 */
	public abstract JPanel createKeyPanel(); // Bei der Implementierung createButtonPanel hinzufuegen

	/**
	 * Gibt den aktuellen Key zurueck
	 * 
	 * @return den aktuellen Key als String
	 */
	public abstract String getKey();

	/**
	 * Erzeugt die Buttons in einem JPanel
	 * 
	 * @return ein JPanel mit die Buttons "verschluesseln" und "entschluesseln"
	 */
	protected JPanel createButtonPanel() {
		JPanel panel = new JPanel(new FlowLayout()); // FlowLayout wichtig damit Button passende Groesse haben
		panel.add(encrypt);
		panel.add(decrypt);
		panel.add(freqAna);
		return panel;
	}

	/**
	 * Gib den Button fuer die Verschluesselung zurueck
	 * 
	 * @return den Button "verschluesseln"
	 */
	public JButton getEncrypt() {
		return encrypt;
	}

	/**
	 * Gibt den Button fuer die Entschluesselung zurueck
	 * 
	 * @return den Button "entschluesseln"
	 */
	public JButton getDecrypt() {
		return decrypt;
	}

	/**
	 * Gibt das Verfahren fuer die Verschluesselung zurueck
	 * 
	 * @return das Verschluesselungs-Verfahren
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
	 * Gibt die MainController Instanz zurueck
	 * 
	 * @return die MainController-Instanz
	 */
	public MainController getController() {
		return this.controller;
	}

	/**
	 * Der Klartext wird verschluesselt und im Geheimtextfeld ausgegeben.
	 * Ausfuehrung bei clicken auf den Button "verschluesseln".
	 */
	public void clickButtonEncrypt() {
		String plainText = this.controller.getPlainText();
		String cryptoText = this.controller.getCryptoText();
		String key = getKey();

		if (plainText.isEmpty()) {
			Messages.errorMessage("Zum verschl\u00fcsseln muss im Klartextfeld ein Text eingegeben werden.");
		} else { // Wird ausgefuert nur wenn ein Klartext gegeben ist
			if (this.crypt.checkKey(key)) {
				if (cryptoText.isEmpty()
						|| Messages.yesNoQuestion("Darf der Geheimtext im Geheimtextfeld \u00fcberschrieben werden?")) {
					plainText = TextEdit.editText(plainText);
					cryptoText = this.crypt.cryptAll(plainText, key); // Der verschluesselte Text wird erzeugt und
					this.controller.setCryptoText(cryptoText); // im Geheimtextfeld ausgegeben
				}
			} else {
				Messages.errorMessage("Der eingegebene Schl\u00fcssel ist nicht korrekt.");
			}
		}
	}

	/**
	 * Der Geheimtext wird entschluesselt und im Klartestfeld ausgegeben.
	 * Ausfuehrung bei clicken auf den Button "entschluesseln".
	 */
	public void clickButtonDecrypt() {
		String plainText = this.controller.getPlainText();
		String cryptoText = this.controller.getCryptoText();
		String key = getKey();

		if (cryptoText.isEmpty()) {
			Messages.errorMessage("Zum entschl\u00fcsseln muss im Geheimtextfeld ein Text eingegeben werden.");
		} else { // Wird ausgefuert nur wenn ein Geheimtext gegeben ist
			if (this.crypt.checkKey(key)) {
				if (plainText.isEmpty()
						|| Messages.yesNoQuestion("Darf der Klartext im Klartextfeld \u00fcberschrieben werden?")) {
					cryptoText = TextEdit.editText(cryptoText);
					plainText = this.crypt.decryptAll(cryptoText, key); // Der entschluesselte Text wird erzeugt und
					this.controller.setPlainText(plainText); // im Klartextfeld ausgegeben
				}
			} else {
				Messages.errorMessage("Der eingegebene Schl\u00fcssel ist nicht korrekt.");
			}
		}
	}

	/**
	 * Oeffnet den Controller fuer die Haeufigkeitsanalyse
	 */
	public void clickButtonFreqAna() {
		new FAController(this);
	}

}
