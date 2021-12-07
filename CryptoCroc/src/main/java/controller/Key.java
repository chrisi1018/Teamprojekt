package controller;

import java.awt.FlowLayout;
import java.awt.Dimension;

import javax.swing.JButton;
import javax.swing.JPanel;

import model.Crypt;

/**
 * Beschreibt Aufbau der Spalte für die Buttons und die Schlüssel in CryptoCroc
 * 
 * @author chrisi
 * @version 1.1
 */
public abstract class Key {
	private JButton encrypt = new JButton("verschlüsseln");
	private JButton decrypt = new JButton("entschlüsseln");
	private Crypt crypt;
	
	/**
	 * Initialisiert die Buttons durch die Action Listener
	 */
	public void initKey() {
		encrypt.addActionListener(e -> crypt.cryptAll("Klartext","key")); //TODO Hier muss noch der Klartext übergeben werden 
		decrypt.addActionListener(e -> crypt.decryptAll("Geheimtext","Key")); 
		// TODO Hier muss noch der Geheimtext übergeben werden
	}
	/**
	 * Erzeugt die Buttons und die Schlüssel für das JPanel
	 * 
	 * @return ein JPanel mit Buttons und dem Schlüssel
	 */
	public abstract JPanel createKeyPanel(); //Bei der Implementierung createButtonPanel hinzufügen
	
	/**
	 * Erzeugt die Buttons in einem JPanel
	 * 
	 * @return ein JPanel mit die Buttons "verschlüsseln" und "entschlüsseln"
	 */
	protected JPanel createButtonPanel() {
		JPanel panel = new JPanel(new FlowLayout()); //FlowLayout wichtig damit Button passende Grï¿½ï¿½e haben
		panel.add(createGapPanel());
		panel.add(encrypt);
		panel.add(createGapPanel());
		panel.add(decrypt);
		return panel;
	}
	
	/**
	 * Erzeugt ein JPanel, welcher ein Abstand erzeugt
	 * 
	 * @return ein JPanel ohne Inhalt 
	 */
	private JPanel createGapPanel() {
		JPanel panel = new JPanel();
		panel.setPreferredSize(new Dimension(0, 50));
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
}
