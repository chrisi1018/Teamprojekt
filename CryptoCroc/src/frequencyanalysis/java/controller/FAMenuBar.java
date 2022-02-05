package controller;

import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.event.MenuEvent;
import javax.swing.event.MenuListener;

/**
 * Beschreibt Aufbau und Funktion der Menueleiste im Fenster der
 * Haeufigkeitsanalyse
 * 
 * @author Julian Singer
 * @version 1.1
 */
public class FAMenuBar {

	private final String[] options = new String[] { "Text neu laden", "Erkl\u00e4rung" };

	private JMenuBar menuBar;

	/**
	 * Konstruktor, der die im Array "options" festgelegten Menuepunkte der
	 * Menueleiste hinzufuegt
	 */
	public FAMenuBar() {
		this.menuBar = new JMenuBar();
		for (int i = 0; i < options.length; i++) {
			JMenu menu = new JMenu(options[i]);
			this.menuBar.add(menu);
		}
	}

	/**
	 * Oeffnet einen uebergebenen Frame, sobald das Menueitem an uebergebener Stelle
	 * angeklickt wird
	 * 
	 * @param barIndex Index des Menues in der Menueleiste
	 * @param newFrame zu erstellendes Fenster
	 */
	public void initExplanationItem(int barIndex, JFrame newFrame) {
		menuBar.getMenu(barIndex).addMenuListener(new MenuListener() {
			//Setzt das uebergebene Erklaerungsfenster in den Vordergrund und macht es sichtbar
			@Override
	        public void menuSelected(MenuEvent e) {
				newFrame.toFront();
				newFrame.setAlwaysOnTop(true);
				newFrame.setVisible(true);
	        }
			//ueberschriebene Methoden brauchen keine konkrete Implementierung
			@Override
			public void menuDeselected(MenuEvent e) { }
			@Override
			public void menuCanceled(MenuEvent e) { }
		});
	}

	/**
	 * Gibt die Menueleiste der aktuellen Instanz zurueck
	 * 
	 * @return this.menuBar Menueleiste
	 */
	public JMenuBar getMenuBar() {
		return this.menuBar;
	}

}