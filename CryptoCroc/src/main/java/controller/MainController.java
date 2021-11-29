package controller;

import view.Gui;
import javax.swing.JPanel;
import javax.swing.JMenuBar;

/**
 * Klasse die für das Erzeugen der GUI-Elemente zuständig ist
 * 
 * @author zes
 * @version 1.1
 */
public class MainController {

	private TextField plainText = new TextField("Klartext");
	private TextField cryptoText = new TextField("Geheimtext");
	private Gui gui;

	/**
	 * Konstruktor, der die GUI-Elemente erzeugt
	 * 
	 */
	public MainController() {
		this.gui = new Gui(
				new JMenuBar(),
				plainText.createTextfieldPanel(), //Klartextpanel
				cryptoText.createTextfieldPanel(), //Cryptotextpanel
				new JPanel()  //keyPanel
				);
	}

}
