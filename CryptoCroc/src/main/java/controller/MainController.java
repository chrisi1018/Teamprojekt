package controller;

import view.Gui;
import javax.swing.JPanel;
/**
 * Klasse die f√ºr das Erzeugen der GUI-Elemente zust√§ndig ist
 * 
 * @author zes
 * @version 1.1
 */
public class MainController {

	private TextField plainText = new TextField("Klartext");
	private TextField cryptoText = new TextField("Geheimtext");
	private Gui gui;
	private Menu menuBar;

	/**
	 * Konstruktor, der die GUI-Elemente erzeugt
	 * 
	 */
	public MainController() {
<<<<<<< HEAD
		this.menubar = new Menu({"Speichern", "Laden"});
		this.menuBar.addMenu("Erkl‰rungen", {"Caesar", "Monoalphabetisch"});
		this.gui = new Gui(
				this.menuBar.getJMenuBar();
=======
		this.menuBar = new Menu(new String[]{"Speichern", "Laden"}); //definiert eine neue Menueleiste mit Menue
		this.menuBar.addMenu("Erkl‰rungen", new String[]{"Caesar", "Monoalphabetisch"}); //fuegt ein neues Menue hinzu
		this.gui = new Gui(
				this.menuBar.getJMenuBar(), //Menueleiste
>>>>>>> 0ce12d4b403597884ff9056e7542214a0563b176
				plainText.createTextfieldPanel(), //Klartextpanel
				cryptoText.createTextfieldPanel(), //Cryptotextpanel
				new JPanel()  //keyPanel
				);
	}

}
