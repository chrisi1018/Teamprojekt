package view;

import java.awt.Color;
import java.awt.Cursor;
import java.awt.Desktop;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;

/**
 * Definiert ein neues Fenster, welches die Erlaeuterungen zur Caesar-Verschluesselung beinhaltet
 * 
 * @author Julian Singer, chrisi
 * @version 1.1
 *
 */
@SuppressWarnings("serial")
public class CExplanationFrame extends JFrame {
	
	private JLabel hyperlink = new JLabel("https://de.serlo.org/informatik/48121/caesar-verschl%C3%BCsselung");
	
	/**
	 * Konstruktor, der den Aufbau, Inhalt und die Funktion des Fensters definiert, 
	 * das die Erklaerung fuer die Caesar-Verschluesselung beinhalten soll
	 */
	public CExplanationFrame() {
		super();
		setTitle("C\u00e4sar-Verschl\u00fcsselung");
		hyperlink.setFont(new Font(Font.DIALOG, Font.PLAIN, 15));
		hyperlink.setForeground(Color.BLUE.darker());
		hyperlink.setCursor(new Cursor(Cursor.HAND_CURSOR));
		hyperlink.addMouseListener(new MouseAdapter() {
			
			//Versucht den entsprechenden Link zu oeffnen, wenn draufgeklickt wird
			@Override
			public void mouseClicked(MouseEvent e) {
				try {
					Desktop.getDesktop()
							.browse(new URI("https://de.serlo.org/informatik/48121/caesar-verschl%C3%BCsselung"));
				} catch (IOException | URISyntaxException e1) {
					e1.printStackTrace();
				}
			}
		});
		
		//legt Aufbau und Funktion des Fensters fest
		setLayout(new FlowLayout());
		getContentPane().add(hyperlink);
		setSize(800, 300);
		setLocationRelativeTo(null);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		
		//Setze das Icon im Fenster
		String os = System.getProperty("os.name").toLowerCase();
		if (!os.equals("mac os x")) { //Nicht Betriebssystem Apple
			ImageIcon icon = new ImageIcon(this.getClass().getResource(("/controller/croc.png")));
			this.setIconImage(icon.getImage());
		}
	}
}
