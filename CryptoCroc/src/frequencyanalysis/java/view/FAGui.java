package view;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenuBar;
import javax.swing.JPanel;

import java.awt.BorderLayout;
import java.awt.Font;

/**
 * Erstellt den Frame und legt das Layout der Haeufigkeitsanalyse fest
 * 
 * @author zes
 * @version 1.0
 */
public class FAGui {

	private JFrame frame;
	private JPanel graphPanel;
	private JPanel table;
	private JMenuBar menu;
	private JPanel mainPanel;
	// TODO Titel
	private JLabel mainLabel;

	/**
	 * Konstruktor der den Frame fuer die Haeufigkeitsanalyse erstellt und das
	 * Layout festlegt
	 * 
	 * @param menu       die Menue-Bar
	 * @param graphPanel das Panel das den Graph enthaelt
	 * @param table      das Panel das die Reihe an Buchstaben enthaelt
	 */
	public FAGui(JMenuBar menu, JPanel graphPanel, JPanel table) {
		// erstellt den Frame
		this.frame = new JFrame("H\u00e4ufigkeitsanalyse");
		this.frame.setSize(1280, 800);
		this.frame.setVisible(true);
		this.frame.setLayout(new BorderLayout(0, 0));
		this.mainPanel = new JPanel(new BorderLayout(4, 4));
		this.frame.setJMenuBar(menu);
		
		// alles sichtbar machen
		this.mainLabel = new JLabel("H\u00e4ufigkeitsanalyse");
		this.mainLabel.setVisible(true);
		this.mainLabel.setFont(new Font(Font.DIALOG, Font.BOLD, 20));
		this.menu = menu;
		this.menu.setVisible(true);
		this.graphPanel = graphPanel;
		this.graphPanel.setVisible(true);
		this.table = table;
		this.table.setVisible(true);
		
		initMainPanel();
		initBorder();
		
		this.frame.setVisible(true);
	}

	private void initBorder() {
		this.frame.add(this.mainPanel, BorderLayout.CENTER);
		this.frame.add(new JPanel(), BorderLayout.NORTH);
		this.frame.add(new JPanel(), BorderLayout.SOUTH);
		this.frame.add(new JPanel(), BorderLayout.EAST);
		this.frame.add(new JPanel(), BorderLayout.WEST);
	}

	private void initMainPanel() {
		this.mainPanel.add(this.graphPanel, BorderLayout.CENTER);
		this.mainPanel.add(mainLabel, BorderLayout.NORTH);
	}

	/**
	 * Dummy-Methode TODO muss noch implementiert werden
	 */
	public void repaint() {
		this.mainLabel.revalidate();
		this.mainLabel.repaint();
	}
}
