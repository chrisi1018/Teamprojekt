package controller;

import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;

/**
 * Beschreibt Aufbau und Funktion der Menueleiste in CryptoCroc
 * 
 * @author Julian Singer
 * @version 1.0
 */
public class Menu {
	
	private JMenuBar menuBar;
	
	/**
	 * Fügt der Menueleiste des aktuellen Objekts alle übergebenen Menuepunkte zur Auswahl hinzu
	 * 
	 * @param menus auswählbare Menuepunkte
	 * @return neue Menueleiste
	 */
	public JMenuBar createMenuBar(String[] menus) {
		JMenu menu = new JMenu("Menu");
		menuBar.add(menu);
		for (int i=0; i<=menus.length; i++) {
			JMenuItem item = new JMenuItem(menus[i]);
			menu.add(item);
		}
		return menuBar;
	}
	
	/**
	 * Fügt der Menueleiste einen einzelnen neuen Menuepunkt hinzu
	 * @param newMenuItem neuer Menuepunkt
	 */
	public void addMenuItem(String newMenuItem) {
		menuBar.add(new JMenuItem(newMenuItem));
	}

}
