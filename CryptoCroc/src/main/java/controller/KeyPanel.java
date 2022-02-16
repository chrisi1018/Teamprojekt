package controller;

import java.awt.FlowLayout;
import java.awt.Image;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

import model.Crypt;
import model.TextEdit;
import view.Messages;

/**
 * Beschreibt Aufbau der Spalte fuer die Buttons und die Schluessel in
 * CryptoCroc
 * 
 * @author chrisi, zes
 * @version 1.2
 */
public abstract class KeyPanel {
	private JButton encrypt = new GradientButton("verschl\u00fcsseln");
	private JButton decrypt = new GradientButton("entschl\u00fcsseln");
	private JButton freqAna = new GradientButton("H\u00e4ufigkeitsanalyse");
	private JButton randomKey = new GradientButton("Zufallsschl\u00fcssel");
	private Crypt crypt;
	private MainController controller;
	private FAController fa;
	private static boolean faIsOpen = false;

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
		randomKey.addActionListener(e -> this.clickButtonRandomKey());
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
	 * Setzt einen Schluessel in die SchluesselTextfelder
	 * 
	 * @param key der Schluessel der in die Textfelder Gesetzt wird
	 */
	public abstract void setKey(String key);
	
	/**
	 * Fuegt in das Schluesselfeld einen zufaelligen Schluessel ein
	 */
	public abstract void randomKey();
	
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
		panel.add(randomKey);
		panel.add(this.createCroc());
		return panel;
	}
	
	/**
	 * Erzeugt ein Label, dass das Bild des Krokodils beinhaltet, dies wird sofort
	 * auf die passende groesse skaliert.
	 * 
	 * @return ein JLabel mit dem Bild des Krokodils
	 */
	private JLabel createCroc() {
		ImageIcon imageIcon = new ImageIcon(this.getClass().getResource(("croc.png")));
		Image image = imageIcon.getImage()
				.getScaledInstance((int) (imageIcon.getIconWidth() / 5.5) + 1, (int) (imageIcon.getIconHeight() / 5.5),
						Image.SCALE_SMOOTH);
		JLabel croc = new JLabel(new ImageIcon(image));
		return croc;
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
			Messages.errorMessage("Zum Verschl\u00fcsseln muss im Klartextfeld ein Text eingegeben werden.");
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
			Messages.errorMessage("Zum Entschl\u00fcsseln muss im Geheimtextfeld ein Text eingegeben werden.");
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
	 * Wechselt den Zustand von faIsOpen; benutzt um zu ueberpruefen ob die
	 * Haeufigkeitsanalyse bereits geoeffnet ist
	 */
	public static void faSwitchOpen() {
		if (faIsOpen) {
			faIsOpen = false;
		} else {
			faIsOpen = true;
		}
	}

	/**
	 * Oeffnet den Controller fuer die Haeufigkeitsanalyse, falls diese nicht offen
	 * ist und gibt eine Warnmeldung falls doch
	 */
	public void clickButtonFreqAna() {
		if (!faIsOpen) {
			fa = new FAController(this);
			faIsOpen = true;
		} else {
			fa.focus();
		}
	}
	
	/**
	 * Ruft die Methoden auf die einen zufaelligen Schuessel erzeugen
	 */
	public void clickButtonRandomKey() {
		this.randomKey();
	}

}
