package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.UIManager;
import javax.swing.plaf.ColorUIResource;
import javax.swing.plaf.FontUIResource;

import utility.Utility;

import view.CExplanationFrame;
import view.Gui;
import view.FAExplanationFrame;
import view.MExplanationFrame;
import view.VExplanationFrame;

/**
 * Klasse die fuer das Erzeugen der GUI-Elemente zustaendig ist
 * 
 * @author zes, Julian Singer
 * @version 1.3
 */
public class MainController {

	private TextField plainText = new TextField("Klartext");
	private TextField cryptoText = new TextField("Geheimtext");
	private KeyPanel key = new CKeyPanel(this);
	private Gui gui;
	private Menu menuBar;
	private Dropdown dropDown;
	private MainController controller = this;
	private String[] encryptOpt = { "C\u00e4sar", "Monoalphabetisch", "Vigen\u00E8re" };
	private String[] explanationOpt = { "C\u00e4sar", "Monoalphabetisch", "Vigen\u00E8re", "H\u00e4ufigkeitsanalyse" };
	private ActionListener change = new ActionListener() {
		@Override
		public void actionPerformed(ActionEvent e) {
			String opt = dropDown.status();
			if (opt.equals(encryptOpt[0])) {
				key = new CKeyPanel(controller);
			} else if (opt.equals(encryptOpt[1])) {
				key = new MKeyPanel(controller);
			} else if (opt.equals(encryptOpt[2])) {
				key = new VKeyPanel(controller);
			}
			gui.setKeyPanel(key.createKeyPanel());
			//Wichtig: da ansonsten die Buttons nicht mehr funktionieren
			key.initKey();
		}
	};

	/**
	 * Konstruktor, der die GUI-Elemente erzeugt
	 * 
	 */
	public MainController() {
		// definiert Farben und Schriftstil in der Menueleiste
		UIManager.put("Menu.font", new FontUIResource(Utility.MENU_FONT));
		UIManager.put("MenuItem.font", new FontUIResource(Utility.FONT));
		UIManager.put("MenuItem.selectionBackground", new ColorUIResource(Utility.DARK_GREEN));
		UIManager.put("MenuBar.hoverBackground", new ColorUIResource(Utility.DARK_GREEN));
		UIManager.put("Menu.selectionBackground", new ColorUIResource(Utility.DARK_GREEN));

		this.menuBar = new Menu(new String[] { "Speichern", "Laden" }); // definiert eine neue Menueleiste mit Menue
		this.menuBar.addMenu("Erkl\u00e4rungen", explanationOpt); // fuegt ein neues Menue hinzu
		this.menuBar.fillRightSide();
		this.dropDown = new Dropdown(encryptOpt, change);
		this.gui = new Gui(this.menuBar.getJMenuBar(), // Menueleiste
				plainText.createTextfieldPanel(), // Klartextpanel
				cryptoText.createTextfieldPanel(), // Cryptotextpanel
				key.createKeyPanel(), // Schluesselpanel
				dropDown.createDropdown() // Dropdownmenue
		);
		
		this.key.initKey();
		//initialisiert die Menueitems
		this.menuBar.initExplanationItem(1, 0, new CExplanationFrame());
		this.menuBar.initExplanationItem(1, 1, new MExplanationFrame());
		this.menuBar.initExplanationItem(1, 2, new VExplanationFrame());
		this.menuBar.initExplanationItem(1, 3, new FAExplanationFrame());
		this.menuBar.initSaveItem(0, 0, this.plainText, this.cryptoText);
		this.menuBar.initOpenItem(0, 1, plainText, cryptoText);
		
		@SuppressWarnings("unused")
		MousePopupMenu popupPlainText = null;
		popupPlainText = new MousePopupMenu(this.plainText.getTextArea());
		@SuppressWarnings("unused")
		MousePopupMenu popupCryptoText = null;
		popupPlainText = new MousePopupMenu(this.cryptoText.getTextArea());
	}
	
	/**
	 * Gibt den Klartext zurueck
	 * 
	 * @return den Klartext als String
	 */
	public String getPlainText() {
		return this.plainText.getText();
	}
	
	/**
	 * Setzt den neuen Klartext
	 * 
	 * @param text den Klartext
	 */
	public void setPlainText(String text) {
		this.plainText.setText(text);
	}
	
	/**
	 * Gibt den Geheimtext zurueck
	 * 
	 * @return den Geheimtext als String
	 */
	public String getCryptoText() {
		return this.cryptoText.getText();
	}
	
	/**
	 * Setzt den neuen Geheimtext
	 * 
	 * @param text den Geheimtext
	 */
	public void setCryptoText(String text) {
		this.cryptoText.setText(text);
	}
}
