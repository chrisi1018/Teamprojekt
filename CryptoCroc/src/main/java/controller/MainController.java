package controller;

/**
 * Klasse die für das Erzeugen der GUI-Elemente zuständig ist
 * 
 * @author zes
 * @version 1.1
 */
public class MainController {

	private TextField plainText;
	private TextField cryptoText;
	private Menu menuBar;

	/**
	 * Konstruktor, der die GUI-Elemente erzeugt
	 * 
	 */
	public MainController() {
		this.menubar = new Menu({"Speichern", "Laden"});
		this.menuBar.addMenu("Erkl�rungen", {"Caesar", "Monoalphabetisch"});
		this.gui = new Gui(
				this.menuBar.getJMenuBar();
				plainText.createTextfieldPanel(), //Klartextpanel
				cryptoText.createTextfieldPanel(), //Cryptotextpanel
				new JPanel()  //keyPanel
				);
	}

}
