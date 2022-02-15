package view;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JMenuBar;
import javax.swing.JPanel;
import java.awt.BorderLayout;
import java.awt.Dimension;

/**
 * Erzeugt den Frame und legt dessen Layout fest.
 * 
 * @author Julian Sturm
 * @version 1.2
 */
public class Gui {

	private JFrame frame;
	private JPanel clearText;
	private JPanel cryptoText;
	private JPanel keyPanel;
	private JPanel dropDown;
	private JPanel mainPanel;

	/**
	 * Konstruktor erzeugt den Frame und legt das Layout fest.
	 * 
	 * @param menu       Die Menu-Bar
	 * @param clearText  enthaelt das Textfeld fuer den Klartext
	 * @param cryptoText enthaelt das Textfeld fuer den Cryptotext
	 * @param keyPanel        enthaelt die Buttons verschluesseln und die Textfelder fuer
	 *                   den Schluessel
	 * @param dropDown   enthaelt das Dropdown-menue
	 */
	public Gui(JMenuBar menu, JPanel clearText, JPanel cryptoText, JPanel keyPanel, JPanel dropDown) {
		//Erstellt den Frame
		this.frame = new JFrame("CryptoCroc");
		this.frame.setSize(1280, 800);
		this.frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.frame.setLayout(new BorderLayout(0, 0));
		this.mainPanel = new JPanel(new BorderLayout(4, 4));
		this.frame.setJMenuBar(menu);
		
		//Setze das Icon im Fenster
		ImageIcon icon = new ImageIcon(this.getClass().getResource(("..\\controller\\croc.png")));
		this.frame.setIconImage(icon.getImage());
		//Speichert die einzelnen Gui-Elemente als Attribute ab
		this.clearText = clearText;
		this.clearText.setVisible(true);
		this.cryptoText = cryptoText;
		this.cryptoText.setVisible(true);
		this.dropDown = dropDown;
		this.dropDown.setVisible(true);
		this.keyPanel = keyPanel;
		this.keyPanel.setVisible(true);
		
		initMainPanel(); //Plaziert die JPanels im MainPanel
		initBorder(); //Erzeugt den aeusseren Rahmen
		
		this.frame.setResizable(false);
		this.frame.setVisible(true);
	}

	/**
	 * Tauscht das KeyPanel aus
	 * 
	 * @param keyPanel das KeyPanel panel, das eingesetzt wird
	 */
	public void setKeyPanel(JPanel keyPanel) {
		this.mainPanel.remove(this.keyPanel);
		this.keyPanel = keyPanel;
		this.mainPanel.add(this.keyPanel, BorderLayout.CENTER);
		this.keyPanel.setPreferredSize(new Dimension(200, 740));
		this.repaintFrame();
	}
	
	/**
	 * Methode die den Frame repainted, muss nach Veraenderungen der Gui aufgerufen werden
	 * 
	 */
	public void repaintFrame() {
		this.mainPanel.revalidate();
		this.mainPanel.repaint();
	}
	
	/**
	 * Die Methode initialisert das MainPanel
	 */
	private void initMainPanel() {
		this.mainPanel.add(this.clearText, BorderLayout.WEST);
		this.clearText.setPreferredSize(new Dimension(500, 740));
		this.mainPanel.add(this.cryptoText, BorderLayout.EAST);
		this.cryptoText.setPreferredSize(new Dimension(500, 740));
		this.mainPanel.add(this.keyPanel, BorderLayout.CENTER);
		this.keyPanel.setPreferredSize(new Dimension(200, 740));

		// zur Seite schieben des Dropdown Menue's
		BorderLayout bSpace = new BorderLayout();
		JPanel space = new JPanel(bSpace);
		space.add(this.dropDown, BorderLayout.WEST);
		space.add(new JPanel(), BorderLayout.EAST);
		bSpace.setHgap(30);

		this.mainPanel.add(space, BorderLayout.NORTH);
	}
	
	/**
	 * DIe Methode erzeugt des Rhamen
	 */
	private void initBorder() {
		this.frame.add(this.mainPanel, BorderLayout.CENTER);
		this.frame.add(new JPanel(), BorderLayout.NORTH);
		this.frame.add(new JPanel(), BorderLayout.SOUTH);
		this.frame.add(new JPanel(), BorderLayout.WEST);
		this.frame.add(new JPanel(), BorderLayout.EAST);
		
	}
}
