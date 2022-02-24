package controller;

import java.awt.Font;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.Box;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;

import utility.Utility;

/**
 * Beschreibt Aufbau und Funktion der Menueleiste im Fenster der
 * Haeufigkeitsanalyse
 * 
 * @author Julian Singer
 * @version 1.1
 */
public class FAMenuBar {

	private final String[] options = new String[] { "Text neu laden", "Erkl\u00e4rung" };

	private GradientMenuBar menuBar;

	/**
	 * Konstruktor, der die im Array "options" festgelegten Menuepunkte der
	 * Menueleiste hinzufuegt
	 */
	public FAMenuBar() {
		this.menuBar = new GradientMenuBar();
		this.menuBar.add(Box.createHorizontalStrut(10)); // Abstand zum abgerundeten Rand
		for (int i = 0; i < options.length; i++) {
			JMenu menu = new JMenu(options[i]);
			menu.setFont(new Font("Arial", Font.BOLD, 14));
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
	 * Fuellt die rechte Seite des Menues, um diese später 
	 * auch einfaerben zu koennen
	 */
	public void fillRightSide() {
		JLabel title = new JLabel("H\u00e4ufigkeitsanalyse");
		title.setFont(Utility.HEADLINE_LABEL_FONT);
		title.setForeground(Utility.WHITE);
		menuBar.add(Box.createHorizontalStrut(290)); // zur mittigen Platzierung des Labels
		menuBar.add(title);
		menuBar.add(Box.createHorizontalGlue());
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