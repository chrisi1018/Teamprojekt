package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;



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
	private KeyPanel key = new CKeyPanel();
	private Gui gui;
	private Menu menuBar;
	private Dropdown dropDown;
	private String[] encryptOpt = { "C\u00e4sar", "Monoalphabetisch", "Vigenere" };
	private ActionListener change = new ActionListener() {
		@Override
		public void actionPerformed(ActionEvent e) {
			String opt = dropDown.status();
			if (opt.equals(encryptOpt[0])) {
				key = new CKeyPanel();
			} else if (opt.equals(encryptOpt[1])) {
				// TODO für einen Monoalphabetischen Schlüssel
				key = new CKeyPanel();
			} else if (opt.equals(encryptOpt[2])) {
				key = new VKeyPanel();
			}
			gui.setKeyPanel(key.createKeyPanel());
		}
	};

	/**
	 * Konstruktor, der die GUI-Elemente erzeugt
	 * 
	 */
	public MainController() {
		this.menuBar = new Menu(new String[] { "Speichern", "Laden" }); // definiert eine neue Menueleiste mit Menue
		this.menuBar.addMenu("Erkl\u00e4rungen", encryptOpt); // fuegt ein neues Menue hinzu
		this.dropDown = new Dropdown(encryptOpt, change);
		this.gui = new Gui(this.menuBar.getJMenuBar(), // Menueleiste
				plainText.createTextfieldPanel(), // Klartextpanel
				cryptoText.createTextfieldPanel(), // Cryptotextpanel
				key.createKeyPanel(), dropDown.createDropdown() // für das Dropdownmenü
		);
		this.key.initKey();
		// kann erst hinzugef�gt werden, wenn zuvor key festgelegt
		// wurde also erst mit DropDown Men�
	}
}
