package controller;

import javax.swing.JPanel;

import view.Gui;

/**
 * Klasse die fÃ¼r das Erzeugen der GUI-Elemente zustÃ¤ndig ist
 * 
 * @author zes
 * @version 1.1
 */
public class MainController {

	private TextField plainText = new TextField("Klartext");
	private TextField cryptoText = new TextField("Geheimtext");
	private Key key;
	private Gui gui;
	private Menu menuBar;

	/**
	 * Konstruktor, der die GUI-Elemente erzeugt
	 * 
	 */
	public MainController() {
		this.menuBar = new Menu(new String[]{"Speichern", "Laden"}); //definiert eine neue Menueleiste mit Menue
		this.menuBar.addMenu("Erklärungen", new String[]{"Caesar", "Monoalphabetisch"}); //fuegt ein neues Menue hinzu
		this.gui = new Gui(
				this.menuBar.getJMenuBar(), //Menueleiste
				plainText.createTextfieldPanel(), //Klartextpanel
				cryptoText.createTextfieldPanel(), //Cryptotextpanel
				new JPanel() //keyPanel
				//key.createKeyPanel()
				//kann erst hinzugefügt werden, wenn zuvor key festgelegt wurde also mit DropDown Menü
				);
		//key.initKey(); //kann erst hinzugefügt werden, wenn zuvor key festgelegt wurde also erst mit DopDown Menï¿½
	}

}
