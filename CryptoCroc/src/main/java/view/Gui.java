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
	
	/**
	 * Konstruktor erzeugt den Frame und legt das Layout fest.
	 * 
	 * @param menu Die Menu-Bar
	 * @param clearText enthält das Textfeld für den Klartext
	 * @param cryptotext enthält das Textfeld für den Cryptotext
	 * @param KeyPanel enthält die Buttons verschlüsseln und die Textfelder für den Schlüssel
	 */
	public Gui(JMenuBar menu, JPanel clearText, JPanel cryptoText, JPanel KeyPanel) {
		
		this.frame = new JFrame("CryptoCroc");
		this.frame.setSize(1280,800);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setLayout(new BorderLayout(0,0));
		
		JPanel panel = new JPanel(new BorderLayout(4,4));
		this.clearText = clearText;
		this.clearText.setVisible(true);
		this.cryptoText = cryptoText;
		this.cryptoText.setVisible(true);
		this.keyPanel = KeyPanel;
		this.keyPanel.setVisible(true);
		
		this.frame.setJMenuBar(menu);
		panel.add(this.clearText, BorderLayout.WEST);
		this.clearText.setPreferredSize(new Dimension(500,740));
		panel.add(this.cryptoText, BorderLayout.EAST);
		this.cryptoText.setPreferredSize(new Dimension(500,740));
		panel.add(this.keyPanel, BorderLayout.CENTER);
		this.keyPanel.setPreferredSize(new Dimension(200,740));
		
		this.frame.add(panel, BorderLayout.CENTER);
		this.frame.add(new JPanel(), BorderLayout.NORTH);
		this.frame.add(new JPanel(), BorderLayout.SOUTH);
		this.frame.add(new JPanel(), BorderLayout.WEST);
		this.frame.add(new JPanel(), BorderLayout.EAST);
		this.frame.setResizable(false);
		this.frame.setVisible(true);
		
		
	}
	
	/**
	 * Eine Setter-Methode für das KeyPanel-Panel
	 * @param das KeyPanel panel, das eingesetzt wird
	 */
	public void setKeyPanel(JPanel keyPanel) {
		this.keyPanel = keyPanel;
		this.keyPanel.add(this.keyPanel, BorderLayout.CENTER);
		this.keyPanel.setPreferredSize(new Dimension(200,740));
	}
}
