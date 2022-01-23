package view;

import javax.swing.JFrame;
import javax.swing.JLabel;
import java.awt.FlowLayout;

public class FAGui {
	
	
	private JFrame frame;
	
	/**
	 * TODO zum testen später auf private
	 */
	public JLabel mainLabel;
	
	/**
	 * TODO zum Tesen späte löschen
	 */
	public FAGui() {
		this.frame = new JFrame("H\u00e4ufigkeitsanalyse");
		this.frame.setSize(1280, 800);
		this.frame.setVisible(true);
		this.mainLabel = new JLabel();
		this.mainLabel.setLayout(new FlowLayout());
		this.frame.add(mainLabel);
		this.mainLabel.setVisible(true);
	}
	/**
	 * Dummy-Methode
	 * TODO muss noch implementiert werden
	 */
	public void repaint() {
		this.mainLabel.revalidate();
		this.mainLabel.repaint();
	}
}
