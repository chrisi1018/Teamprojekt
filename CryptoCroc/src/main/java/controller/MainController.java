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
	private Dropdown dropDown;

	/**
	 * Konstruktor, der die GUI-Elemente erzeugt
	 * 
	 */
	public MainController() {
		this.menuBar = new Menu(new String[]{"Speichern", "Laden"}); //definiert eine neue Menueleiste mit Menue
		this.menuBar.addMenu("Erklï¿½rungen", new String[]{"Caesar", "Monoalphabetisch"}); //fuegt ein neues Menue hinzu
		
		this.dropDown = new Dropdown(new String[] {"Caesar", "Monoalphabetisch"});
		this.gui = new Gui(
				this.menuBar.getJMenuBar(), //Menueleiste
				plainText.createTextfieldPanel(), //Klartextpanel
				cryptoText.createTextfieldPanel(), //Cryptotextpanel
<<<<<<< HEAD
				new JPanel(), //keyPanel
				//key.createKeyPanel() //kann erst hinzugefï¿½gt werden, wenn zuvor key festgelegt wurde
				dropDown.createDropdown() // fÃ¼r das DropdownmenÃ¼
				);
		//key.getEncrypt().addActionListener(e -> key.getCrypt().cryptAll("Klartext")); //TODO Hier muss noch der Klartext ï¿½bergeben werden 
		//key.getDecrypt().addActionListener(e -> key.getCrypt().decryptAll("Geheimtext")); // TODO Hier muss noch der Geheimtext ï¿½bergeben werden
=======
				new JPanel() //keyPanel
				//key.createKeyPanel() //kann erst hinzugefügt werden, wenn zuvor key festgelegt wurde also mit DropDown Menü
				);
		//key.initKey(); //kann erst hinzugefügt werden, wenn zuvor key festgelegt wurde also erst mit DopDown Menü
>>>>>>> main
	}

}
