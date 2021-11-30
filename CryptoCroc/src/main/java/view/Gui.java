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
 * @version 1.1
 */
public class Gui {
	
	private JFrame frame;
	private JPanel clearText;
	private JPanel cryptoText;
	private JPanel key;
	private BorderLayout layout = new BorderLayout();
	/**
	 * Konstruktor erzeugt den Frame und legt das Layout fest.
	 * 
	 * @param menu Die Menu-Bar
	 * @param clearText enthält das Textfeld für den Klartext
	 * @param cryptotext enthält das Textfeld für den Cryptotext
	 * @param key enthält die Buttons verschlüsseln und die Textfelder für den Schlüssel
	 */
	public Gui(JMenuBar menu, JPanel clearText, JPanel cryptoText, JPanel key) {
		
		//clearText.setBackground(Color.GREEN);
		//cryptoText.setBackground(Color.BLUE);
		//key.setBackground(Color.RED);
		
		this.frame = new JFrame("CryptoCroc");
		this.frame.setSize(1280,800);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setLayout(layout);
		
		this.clearText = clearText;
		this.clearText.setVisible(true);
		this.cryptoText = cryptoText;
		this.cryptoText.setVisible(true);
		this.key = key;
		this.key.setVisible(true);
		
		this.frame.setJMenuBar(menu);
		this.frame.add(this.clearText, BorderLayout.WEST);
		this.clearText.setPreferredSize(new Dimension(500,800));
		this.frame.add(this.cryptoText, BorderLayout.EAST);
		this.cryptoText.setPreferredSize(new Dimension(500,800));
		this.frame.add(this.key, BorderLayout.CENTER);
		this.key.setPreferredSize(new Dimension(280,800));
		
		this.frame.setResizable(false);
		this.frame.setVisible(true);
		
		
	}
}
