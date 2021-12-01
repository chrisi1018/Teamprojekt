package controller;

import view.Gui;
import javax.swing.JPanel;

import model.Crypt;
/**
 * Klasse die für das Erzeugen der GUI-Elemente zuständig ist
 * 
 * @author zes
 * @version 1.1
 */
public class MainController {

	private TextField plainText = new TextField("Klartext");
	private TextField cryptoText = new TextField("Geheimtext");
	private Key key = new Key();
	private Gui gui;
	private Menu menuBar;
	
	private Crypt crypt = new Crypt();

	/**
	 * Konstruktor, der die GUI-Elemente erzeugt
	 * 
	 */
	public MainController() {
		this.menuBar = new Menu(new String[]{"Speichern", "Laden"}); //definiert eine neue Menueleiste mit Menue
		this.menuBar.addMenu("Erkl�rungen", new String[]{"Caesar", "Monoalphabetisch"}); //fuegt ein neues Menue hinzu
		this.gui = new Gui(
				this.menuBar.getJMenuBar(), //Menueleiste
				plainText.createTextfieldPanel(), //Klartextpanel
				cryptoText.createTextfieldPanel(), //Cryptotextpanel
				key.createKeyPanel()  //keyPanel
				);
		key.getEncrypt().addActionListener(e -> crypt.cryptAll("Test"));
	}

}
