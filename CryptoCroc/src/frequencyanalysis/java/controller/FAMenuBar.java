package controller;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;

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
		menuBar.getMenu(barIndex).addMouseListener(new MouseListener() {
			// Setzt das uebergebene Erklaerungsfenster in den Vordergrund und macht es sichtbar
			@Override
			public void mouseClicked(MouseEvent e) {
				newFrame.toFront();
				newFrame.setVisible(true);
			}
			@Override
			public void mousePressed(MouseEvent e) {
				// tue nichts
			}
			@Override
			public void mouseReleased(MouseEvent e) {
				// tue nichts
			}
			@Override
			public void mouseEntered(MouseEvent e) {
				// tue nichts
			}
			@Override
			public void mouseExited(MouseEvent e) {
				// tue nichts
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