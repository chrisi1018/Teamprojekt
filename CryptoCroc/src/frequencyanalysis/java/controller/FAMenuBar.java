package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;

/**
 * Beschreibt Aufbau und Funktion der Menueleiste im Fenster der Haeufigkeitsanalyse
 * 
 * @author Julian Singer
 * @version 1.0
 */
public class FAMenuBar {
	
	private final String[] options = new String[] { "Text neu laden", "Erkl\u00e4rung" };
	
	private JMenuBar menuBar;
	
	/**
	 * Konstruktor, der die im Array "options" festgelegten Menuepunkte der Menueleiste hinzufuegt
	 */
	public FAMenuBar() {
		this.menuBar = new JMenuBar();
		for (int i = 0; i < options.length; i++) {
			JMenu menu = new JMenu(options[i]);
			this.menuBar.add(menu);
		}
	}
	
	/**
	 * Oeffnet einen uebergebenen Frame, sobald das Menueitem an uebergebener Stelle angeklickt wird
	 * 
	 * @param barIndex Index des Menues in der Menueleiste
	 * @param menuIndex Index des Items im Menue
	 * @param newFrame zu erstellendes Fenster
	 */
	public void initExplanationItem(int barIndex, int menuIndex, JFrame newFrame) {
		menuBar.getMenu(barIndex).getItem(menuIndex).addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				newFrame.setVisible(true);
			}
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