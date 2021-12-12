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
 * Definiert ein neues Fenster, welches die Erl�uterungen zur Caesar-Verschl�sselung beinhaltet
 * 
 * @author Julian Singer
 * @version 1.0
 *
 */
@SuppressWarnings("serial")
public class CExplanationFrame extends JFrame {
	
	private JLabel hyperlink = new JLabel("https://de.serlo.org/informatik/48121/caesar-verschl%C3%BCsselung");
	
	public CExplanationFrame() {
		super();
		setTitle("C�sar-Verschl�sselung");
		hyperlink.setForeground(Color.BLUE.darker());
		hyperlink.setCursor(new Cursor(Cursor.HAND_CURSOR));
		hyperlink.addMouseListener(new MouseAdapter() {
			
			@Override
			public void mouseClicked(MouseEvent e) {
				try {
					Desktop.getDesktop().browse(new URI("https://de.serlo.org/informatik/48121/caesar-verschl%C3%BCsselung"));
				} catch (IOException | URISyntaxException e1) {
					e1.printStackTrace();
				}
			}
		});
		
		setLayout(new FlowLayout());
		getContentPane().add(hyperlink);
		setSize(500, 200);
		setLocationRelativeTo(null);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
	}
}
