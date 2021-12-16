package controller;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import model.CCrypt;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Component;

/**
 * Definiert 'createKeyPanel'-Methode f�r Caesar und speichert sein Schluesseltextfeld
 * 
 * @author Julian Singer
 * @version 1.0
 *
 */
public class CKeyPanel extends KeyPanel {

	private JTextField key = new JTextField();
	private JLabel name = new JLabel("A");
	
	
	/**
	 * Konstruktor, der der neuen Instanz die Caesar-Verschl�sselung zuordnet
	 */
	public CKeyPanel() {
		super.setCrypt(new CCrypt());
	}
	
	/**
	 * Ueberschreibt die Methode 'createKeyPanel'
	 */
	@Override
	public JPanel createKeyPanel() {
		BorderLayout layout = new BorderLayout();
		JLabel description = new JLabel("Schl\u00fcssel");
		description.setFont(new Font(Font.DIALOG, Font.BOLD, 20));
		name.setFont(new Font(Font.DIALOG, Font.BOLD, 15));
		key.setFont(new Font(Font.DIALOG, Font.BOLD, 15));
		
		//definiert ein Panel fuer die Schluesseleingabe
		JPanel textPanel = new JPanel();
		textPanel.setLayout(new BoxLayout(textPanel, BoxLayout.PAGE_AXIS));
		textPanel.add(description);
		description.setAlignmentX(Component.CENTER_ALIGNMENT);
		textPanel.add(Box.createRigidArea(new Dimension(0, 20)));
		textPanel.add(name);
		name.setAlignmentX(Component.CENTER_ALIGNMENT);
		textPanel.add(Box.createRigidArea(new Dimension(0, 5)));
		textPanel.add(key);
		key.setHorizontalAlignment(JTextField.CENTER);
		key.setMaximumSize(new Dimension(19, 30));
		key.setAlignmentX(Component.CENTER_ALIGNMENT);
		
		//fuegt das Text- und Buttonpanel zu einem Panel zusammen
		JPanel keyPanel = new JPanel(layout);
		keyPanel.add(textPanel, BorderLayout.PAGE_START);
		keyPanel.add(this.createButtonPanel(), BorderLayout.CENTER);
		
		return keyPanel;
	}

}