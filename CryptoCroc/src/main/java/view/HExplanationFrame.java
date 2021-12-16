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

import javax.swing.JFrame;
import javax.swing.JLabel;

/**
 * Definiert ein neues Fenster, welches die Erl�uterungen zur Haeufigkeitsanalyse beinhaltet
 * 
 * @author Julian Singer
 * @version 1.0
 *
 */
@SuppressWarnings("serial")
public class HExplanationFrame extends JFrame {
	
	private JLabel hyperlink = new JLabel("https://kryptografie.de/kryptografie/kryptoanalyse"
			+ "/2-3-2-brechen-von-vigenere-mittels-schluesselrekonstruktion.htm");
	
	/**
	 * Konstruktor, der den Aufbau, Inhalt und die Funktion des Fensters definiert, 
	 * das die Erkl�rung f�r die Haeufigkeitsanalyse beinhalten soll
	 */
	public HExplanationFrame() {
		super();
		setTitle("H\u00e4ufigkeitsanalyse");
		hyperlink.setFont(new Font(Font.DIALOG, Font.PLAIN, 15));
		hyperlink.setForeground(Color.BLUE.darker());
		hyperlink.setCursor(new Cursor(Cursor.HAND_CURSOR));
		hyperlink.addMouseListener(new MouseAdapter() {
			
			//Versucht den entsprechenden Link zu �ffnen, wenn draufgeklickt wird
			@Override
			public void mouseClicked(MouseEvent e) {
				try {
					Desktop.getDesktop().browse(new URI("https://kryptografie.de/kryptografie/kryptoanalyse"
							+ "/2-3-2-brechen-von-vigenere-mittels-schluesselrekonstruktion.htm"));
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
	}
}