package controller;

import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;

/**
 * Beschreibt Aufbau und Funktion der Menueleiste in CryptoCroc
 * 
 * @author Julian Singer
 * @version 1.1
 */
public class Menu {
	
	private JMenuBar menuBar;
	
	/**
	 * Konstruktor, der Menuepunkte entgegennimmt und diese der Menueleiste hinzuf�gt
	 * 
	 * @param menus zu hinzuf�gende Menuepunkte
	 */
	
	public Menu(String[] menus) {
		JMenu menu = new JMenu("Menu");
		menuBar.add(menu);
		for (int i=0; i<=menus.length; i++) {
			JMenuItem item = new JMenuItem(menus[i]);
			menu.add(item);
		}
	}
	
	/**
	 * F�gt der Menueleiste ein neues Menue mit �bergebenen Unterpunkten hinzu
	 * 
	 * @param name Nem des neuen Menues
	 * @param menus zugeh�rige Menuepunkte
	 */
	public void addMenu(String name, String[] menus) {
		JMenu newMenu = new JMenu(name);
		menuBar.add(newMenu);
		for (int i=0; i<=menus.length; i++) {
			JMenuItem item = new JMenuItem(menus[i]);
			newMenu.add(item);
		}
	}
	
	/**
	 * Gibt die Menueleiste der aktuellen Instanz zur�ck
	 * @return this.menuBar Menueleiste
	 */
	public JMenuBar getJMenuBar() {
		return this.menuBar;
	}
}
