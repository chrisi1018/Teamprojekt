package view;

import java.awt.Dimension;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JScrollPane;

/**
 * Definiert ein neues Fenster, welches die Erlaeuterungen zur
 * Haeufigkeitsanalyse beinhaltet
 * 
 * @author Julian Singer, chrisi
 * @version 1.1
 *
 */
@SuppressWarnings("serial")
public class FAExplanationFrame extends JFrame {

	private JLabel fExplain;

	/**
	 * Konstruktor, der den Aufbau, Inhalt und die Funktion des Fensters definiert,
	 * das die Erklaerung fuer die Haeufigkeitsanalyse beinhalten soll
	 */
	public FAExplanationFrame() {
		super();
		setTitle("H\u00e4ufigkeitsanalyse");
		ImageIcon png = new ImageIcon(this.getClass().getResource("/view/HaeufigkeitsanalyseErklaerung.jpg"));
		fExplain = new JLabel(png);
		fExplain.setVisible(true);
		JScrollPane scroll = new JScrollPane(fExplain, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,
				JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
		scroll.setVisible(true);
		this.add(scroll);
		this.setSize(new Dimension(1200, 700));
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

		// Setze das Icon im Fenster
		String os = System.getProperty("os.name").toLowerCase();
		if (!os.equals("mac os x")) { // Nicht Betriebssystem Apple
			ImageIcon icon = new ImageIcon(this.getClass().getResource(("/controller/croc.png")));
			this.setIconImage(icon.getImage());
		}
	}
}
