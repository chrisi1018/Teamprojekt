package controller;

import java.awt.FlowLayout;


import javax.swing.JButton;
import javax.swing.JPanel;

import model.Crypt;

/**
 * Beschreibt Aufbau der Spalte f�r die Buttons und die Schl�ssel in CryptoCroc
 * 
 * @author chrisi
 * @version 1.1
 */
public abstract class KeyPanel {
	private JButton encrypt = new JButton("verschl\u00fcsseln");
	private JButton decrypt = new JButton("entschl\u00fcsseln");
	private Crypt crypt;
	
	/**
	 * Initialisiert die Buttons durch die Action Listener
	 */
	public void initKey() {
		encrypt.addActionListener(e -> crypt.cryptAll("Klartext", "key"));
		//TODO Hier muss noch der Klartext �bergeben werden 
		decrypt.addActionListener(e -> crypt.decryptAll("Geheimtext", "Key"));
		// TODO Hier muss noch der Geheimtext �bergeben werden
	}
	/**
	 * Erzeugt die Buttons und die Schl�ssel f�r das JPanel
	 * 
	 * @return ein JPanel mit Buttons und dem Schl�ssel
	 */
	public abstract JPanel createKeyPanel(); //Bei der Implementierung createButtonPanel hinzuf�gen
	
	/**
	 * Erzeugt die Buttons in einem JPanel
	 * 
	 * @return ein JPanel mit die Buttons "verschl�sseln" und "entschl�sseln"
	 */
	protected JPanel createButtonPanel() {
		JPanel panel = new JPanel(new FlowLayout()); //FlowLayout wichtig damit Button passende Gr��e haben
		panel.add(encrypt);
		panel.add(decrypt);
		return panel;
	}
	
	/**
	 * Gib den Button f�r die Verschl�sselung zur�ck
	 * 
	 * @return den Button "verschl�sseln"
	 */
	public JButton getEncrypt() {
		return encrypt;
	}
	
	/**
	 * Gibt den Button f�r die Entschl�sselung zur�ck
	 * 
	 * @return den Button "entschl�sseln"
	 */
	public JButton getDecrypt() {
		return decrypt;
	}
	
	/**
	 * Gibt das Verfahren f�r die Verschl�sselung zur�ck
	 * 
	 * @return das Verschl�sselungs-Verfahren
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