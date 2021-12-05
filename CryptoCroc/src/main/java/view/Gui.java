package view;

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

	/**
	 * Konstruktor erzeugt den Frame und legt das Layout fest.
	 * 
	 * @param menu       Die Menu-Bar
	 * @param clearText  enth�lt das Textfeld f�r den Klartext
	 * @param cryptotext enth�lt das Textfeld f�r den Cryptotext
	 * @param key        enth�lt die Buttons verschl�sseln und die Textfelder f�r
	 *                   den Schl�ssel
	 * @param dropDown   enthält das Dropdown-menü
	 */
	public Gui(JMenuBar menu, JPanel clearText, JPanel cryptoText, JPanel keyPanel, JPanel dropDown) {

		// clearText.setBackground(Color.GREEN);
		// cryptoText.setBackground(Color.BLUE);
		// key.setBackground(Color.RED);

		this.frame = new JFrame("CryptoCroc");
		this.frame.setSize(1280, 800);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setLayout(new BorderLayout(0, 0));

		JPanel panel = new JPanel(new BorderLayout(4, 4));
		this.clearText = clearText;
		this.clearText.setVisible(true);
		this.cryptoText = cryptoText;
		this.cryptoText.setVisible(true);
		this.dropDown = dropDown;
		this.dropDown.setVisible(true);
		this.keyPanel = keyPanel;
		this.keyPanel.setVisible(true);

		this.frame.setJMenuBar(menu);
		panel.add(this.clearText, BorderLayout.WEST);
		this.clearText.setPreferredSize(new Dimension(500, 740));
		panel.add(this.cryptoText, BorderLayout.EAST);
		this.cryptoText.setPreferredSize(new Dimension(500, 740));
		panel.add(this.keyPanel, BorderLayout.CENTER);
		this.keyPanel.setPreferredSize(new Dimension(200, 740));

		// zur Seite schieben des Dropdown Menue's
		BorderLayout bSpace = new BorderLayout();
		JPanel space = new JPanel(bSpace);
		space.add(this.dropDown, BorderLayout.WEST);
		space.add(new JPanel(), BorderLayout.EAST);
		bSpace.setHgap(30);

		panel.add(space, BorderLayout.NORTH);

		this.frame.add(panel, BorderLayout.CENTER);
		this.frame.add(new JPanel(), BorderLayout.NORTH);
		this.frame.add(new JPanel(), BorderLayout.SOUTH);
		this.frame.add(new JPanel(), BorderLayout.WEST);
		this.frame.add(new JPanel(), BorderLayout.EAST);
		this.frame.setResizable(false);
		this.frame.setVisible(true);

	}

	/**
	 * Eine Setter-Methode f�r das KeyPanel-Panel
	 * 
	 * @param das KeyPanel panel, das eingesetzt wird
	 */
	public void setKeyPanel(JPanel keyPanel) {
		this.keyPanel = keyPanel;
		this.keyPanel.add(this.keyPanel, BorderLayout.CENTER);
		this.keyPanel.setPreferredSize(new Dimension(200, 740));
	}
}
