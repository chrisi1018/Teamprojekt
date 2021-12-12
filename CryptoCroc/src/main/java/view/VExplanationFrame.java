package view;

import java.awt.Color;
import java.awt.Cursor;
import java.awt.Desktop;
import java.awt.FlowLayout;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;

import javax.swing.JFrame;
import javax.swing.JLabel;

/**
 * Definiert ein neues Fenster, welches die Erläuterungen zur Vigenère-Verschlüsselung beinhaltet
 * 
 * @author Julian Singer
 * @version 1.0
 *
 */
@SuppressWarnings("serial")
public class VExplanationFrame extends JFrame {
	
	private JLabel hyperlink = new JLabel("https://de.serlo.org/informatik/48392/vigen%C3%A8re-verschl%C3%BCsselung");
	
	/**
	 * Konstruktor, der den Aufbau, Inhalt und die Funktion des Fensters definiert, 
	 * das die Erklärung für die Vigenere-Verschlüsselung beinhalten soll
	 */
	public VExplanationFrame() {
		super();
		setTitle("Vigen\u00E8re-Verschl\u00fcsselung");
		hyperlink.setForeground(Color.BLUE.darker());
		hyperlink.setCursor(new Cursor(Cursor.HAND_CURSOR));
		hyperlink.addMouseListener(new MouseAdapter() {
			
			//Versucht den entsprechenden Link zu öffnen, wenn draufgeklickt wird
			@Override
			public void mouseClicked(MouseEvent e) {
				try {
					Desktop.getDesktop().browse(new URI("https://de.serlo.org/informatik/48392/vigen%C3%A8re-verschl%C3%BCsselung"));
				} catch (IOException | URISyntaxException e1) {
					e1.printStackTrace();
				}
			}
		});
		
		//legt Aufbau und Funktion des Fensters fest
		setLayout(new FlowLayout());
		getContentPane().add(hyperlink);
		setSize(600, 200);
		setLocationRelativeTo(null);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
	}
}
