package controller;

import javax.swing.JPanel;

/**
 * Klasse die für das Erzeugen der GUI-Elemente zuständig ist
 * 
 * @author zes
 * @version 1.0
 */
public class MainController {

	private JPanel plainTextPanel;
	private String plainLabelName = "Klartext";
	private JPanel cryptoTextPanel;
	private String cryptoLabelName = "Geheimtext";
	private JPanel keyPanel;
	private JPanel menuPanel;

	/**
	 * Konstruktor, der die GUI-Elemente erzeugt
	 * 
	 */
	public MainController() {
		TextField plainText = new TextField(plainLabelName);
		TextField cryptoText = new TextField(cryptoLabelName);
		plainTextPanel = plainText.createTextfieldPanel();
		cryptoTextPanel = cryptoText.createTextfieldPanel();

	}

	/**
	 * Liefert das TextPanel mit dem Klartext
	 * 
	 * @return das TextPanel mit dem Klartext
	 */
	public JPanel getPlainTextPanel() {
		return plainTextPanel;
	}

	/**
	 * Liefert das TextPanel mit dem Geheimtext
	 * 
	 * @return das TextPanel mit dem verschlüsselten Text
	 */
	public JPanel getCryptoTextPanel() {
		return cryptoTextPanel;
	}

}
