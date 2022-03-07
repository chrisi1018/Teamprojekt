package view;

import java.awt.Dimension;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JScrollPane;

/**
 * Definiert ein neues Fenster, welches die Erlaeuterungen zur
 * Caesar-Verschluesselung beinhaltet
 * 
 * @author Julian Singer, chrisi
 * @version 1.1
 *
 */
@SuppressWarnings("serial")
public class CExplanationFrame extends JFrame {

	private JLabel cExplain;

	/**
	 * Konstruktor, der den Aufbau, Inhalt und die Funktion des Fensters definiert,
	 * das die Erklaerung fuer die Caesar-Verschluesselung beinhalten soll
	 */
	public CExplanationFrame() {
		super();
		setTitle("C\u00e4sar-Verschl\u00fcsselung");
		ImageIcon png = new ImageIcon(this.getClass().getResource("/view/CaesarErklaerung.jpg"));
		cExplain = new JLabel(png);
		cExplain.setVisible(true);
		JScrollPane scroll = new JScrollPane(cExplain, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,
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
