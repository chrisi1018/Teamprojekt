package view;

import java.awt.Dimension;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JScrollPane;

/**
 * Definiert ein neues Fenster, welches die Erlaeuterungen zur
 * Vigenere-Verschluesselung beinhaltet
 * 
 * @author Julian Singer, chrisi
 * @version 1.1
 *
 */
@SuppressWarnings("serial")
public class VExplanationFrame extends JFrame {

	private JLabel vExplain;

	/**
	 * Konstruktor, der den Aufbau, Inhalt und die Funktion des Fensters definiert,
	 * das die Erklaerung fuer die Vigenere-Verschluesselung beinhalten soll
	 */
	public VExplanationFrame() {
		super();
		ImageIcon png = new ImageIcon(this.getClass().getResource("/view/VigenereErklaerung.jpg"));
		vExplain = new JLabel(png);
		setTitle("Vigen\u00E8re-Verschl\u00fcsselung");
		vExplain.setVisible(true);
		JScrollPane scroll = new JScrollPane(vExplain, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,
				JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
		scroll.setVisible(true);
		this.add(scroll);
		this.setSize(new Dimension(1200, 600));
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

		// Setze das Icon im Fenster
		String os = System.getProperty("os.name").toLowerCase();
		if (!os.equals("mac os x")) { // Nicht Betriebssystem Apple
			ImageIcon icon = new ImageIcon(this.getClass().getResource(("/controller/croc.png")));
			this.setIconImage(icon.getImage());
		}
	}
}
