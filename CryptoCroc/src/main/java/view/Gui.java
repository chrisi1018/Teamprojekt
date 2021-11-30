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
	 * @param clearText enth�lt das Textfeld f�r den Klartext
	 * @param cryptotext enth�lt das Textfeld f�r den Cryptotext
	 * @param key enth�lt die Buttons verschl�sseln und die Textfelder f�r den Schl�ssel
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
