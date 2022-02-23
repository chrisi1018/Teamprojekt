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
 * @author zes, Julian Singer, Julian Sturm
 * @version 1.5
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
			int opt = dropDown.index() + 1;
			setKeyPanel(opt);
		}
	};

	/**
	 * Konstruktor, der die GUI-Elemente erzeugt
	 * 
	 */
	public MainController() {
		this.setUI();
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
		// initialisiert die Menueitems
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
	 * Definiert Farben und Schriftstil in der Menuleiste
	 */
	private void setUI() {
		UIManager.put("Menu.font", new FontUIResource(Utility.MENU_FONT));
		UIManager.put("MenuItem.font", new FontUIResource(Utility.FONT));
		UIManager.put("MenuItem.selectionBackground", new ColorUIResource(Utility.DARK_GREEN));
		if (System.getProperty("os.name").toLowerCase().equals("mac os x")) {
			UIManager.put("MenuBar.hoverBackground", new ColorUIResource(Utility.LIGHT_GREEN));
			UIManager.put("Menu.selectionBackground", new ColorUIResource(Utility.LIGHT_GREEN));
		} else {
			UIManager.put("MenuBar.hoverBackground", new ColorUIResource(Utility.DARK_GREEN));
			UIManager.put("Menu.selectionBackground", new ColorUIResource(Utility.DARK_GREEN));
		}

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

	/**
	 * Methode die das Key Panel austauscht
	 * 
	 * @param index die SerienNummer des keypanels
	 * @return das Erzeugte KeyPanel
	 */
	public KeyPanel setKeyPanel(int index) {
		switch (index) {
		case 1:
			this.key = new CKeyPanel(controller);
			break;
		case 2:
			this.key = new MKeyPanel(controller);
			break;
		case 3:
			this.key = new VKeyPanel(controller);
		default:
			this.key = new VKeyPanel(controller);
		}
		this.gui.setKeyPanel(this.key.createKeyPanel());
		// Wichtig: da ansonsten die Buttons nicht mehr funktionieren
		key.initKey();
		return this.key;
	}
}
