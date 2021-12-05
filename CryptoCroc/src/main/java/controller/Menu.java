package controller;

import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;

/**
 * Beschreibt Aufbau und Funktion der Menueleiste in CryptoCroc
 * 
 * @author Julian Singer
 * @version 1.2
 */
public class Menu {
	
	private JMenuBar menuBar;
	
	/**
	 * Konstruktor, der Menuepunkte entgegennimmt und diese der Menueleiste hinzufuegt
	 * 
	 * @param menus zu hinzufuegende Menuepunkte
	 */
	
	public Menu(String[] menus) {
		menuBar = new JMenuBar();
		JMenu menu = new JMenu("Menu");
		menuBar.add(menu);
		for (int i = 0; i < menus.length; i++) {
			JMenuItem item = new JMenuItem(menus[i]);
			menu.add(item);
		}
	}
	
	/**
	 * Fuegt der Menueleiste ein neues Menue mit uebergebenen Unterpunkten hinzu
	 * 
	 * @param name Name des neuen Menues
	 * @param menus zugehoerige Menuepunkte
	 */
	public void addMenu(String name, String[] menus) {
		JMenu newMenu = new JMenu(name);
		menuBar.add(newMenu);
		for (int i = 0; i < menus.length; i++) {
			JMenuItem item = new JMenuItem(menus[i]);
			newMenu.add(item);
		}
	}
	
	/**
	 * Gibt die Menueleiste der aktuellen Instanz zurueck
	 * 
	 * @return this.menuBar Menueleiste
	 */
	public JMenuBar getJMenuBar() {
		return this.menuBar;
	}
}
