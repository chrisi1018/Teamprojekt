package controller;

import javax.swing.JPanel;

import view.Gui;

/**
 * Klasse die für das Erzeugen der GUI-Elemente zuständig ist
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
		this.menuBar.addMenu("Erkl�rungen", new String[]{"Caesar", "Monoalphabetisch"}); //fuegt ein neues Menue hinzu
		
		this.dropDown = new Dropdown(new String[] {"Caesar", "Monoalphabetisch"});
		this.gui = new Gui(
				this.menuBar.getJMenuBar(), //Menueleiste
				plainText.createTextfieldPanel(), //Klartextpanel
				cryptoText.createTextfieldPanel(), //Cryptotextpanel
<<<<<<< HEAD
				new JPanel(), //keyPanel
				//key.createKeyPanel() //kann erst hinzugef�gt werden, wenn zuvor key festgelegt wurde
				dropDown.createDropdown() // für das Dropdownmenü
				);
		//key.getEncrypt().addActionListener(e -> key.getCrypt().cryptAll("Klartext")); //TODO Hier muss noch der Klartext �bergeben werden 
		//key.getDecrypt().addActionListener(e -> key.getCrypt().decryptAll("Geheimtext")); // TODO Hier muss noch der Geheimtext �bergeben werden
=======
				new JPanel() //keyPanel
				//key.createKeyPanel() //kann erst hinzugef�gt werden, wenn zuvor key festgelegt wurde also mit DropDown Men�
				);
		//key.initKey(); //kann erst hinzugef�gt werden, wenn zuvor key festgelegt wurde also erst mit DopDown Men�
>>>>>>> main
	}

}
