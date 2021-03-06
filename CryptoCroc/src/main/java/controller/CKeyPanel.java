package controller;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import model.CCrypt;
import utility.Utility;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.util.Random;
import java.awt.Component;

/**
 * Definiert 'createKeyPanel'-Methode fuer Caesar und speichert sein
 * Schluesseltextfeld
 * 
 * @author Julian Singer, chrisi
 * @version 1.2
 *
 */
public class CKeyPanel extends KeyPanel {

	private JTextField key = new JTextField();
	private JLabel name = new JLabel("A");
	private final int serialnumber = 1;

	/**
	 * Konstruktor, der der neuen Instanz die Caesar-Verschluesselung zuordnet
	 * 
	 * @param controller die Controller-Instanz
	 */
	public CKeyPanel(MainController controller) {
		super(controller);
		super.setCrypt(new CCrypt());
	}

	/**
	 * Ueberschreibt die Methode 'createKeyPanel'
	 */
	@Override
	public JPanel createKeyPanel() {
		// sorgt dafuer, dass beim Klicken Text in key markiert wird
		key.addFocusListener(new FocusListener() {
			@Override
			public void focusGained(FocusEvent e) {
				key.selectAll();
			}

			@Override
			public void focusLost(FocusEvent e) {
				// tue nichts
			}
		});
		
		BorderLayout layout = new BorderLayout();
		JLabel description = new JLabel("Schl\u00fcssel");
		description.setFont(Utility.HEADLINE_LABEL_FONT);
		description.setForeground(Utility.DARK_GREEN);
		name.setFont(Utility.LABEL_FONT);
		name.setForeground(Utility.DARK_GREEN);
		key.setFont(Utility.TEXT_FONT);
		key.setBorder(Utility.TEXTFIELD_BORDER);
		key.setHorizontalAlignment(JTextField.CENTER);

		// LimitedTextfield bei dem nur ein Character eingegeben darf
		key.setDocument(new LimitedTextfield(Utility.KEY_LENGTH_FOR_SINGLE_SHIFT));

		// definiert ein Panel fuer die Schluesseleingabe
		JPanel textPanel = new JPanel();
		textPanel.setLayout(new BoxLayout(textPanel, BoxLayout.PAGE_AXIS));
		textPanel.add(description);
		description.setAlignmentX(Component.CENTER_ALIGNMENT);
		textPanel.add(Box.createRigidArea(new Dimension(0, 20)));
		textPanel.add(name);
		name.setAlignmentX(Component.CENTER_ALIGNMENT);
		textPanel.add(key);
		key.setAlignmentX(Component.CENTER_ALIGNMENT);
		
		// Setzt die Breite und die Hoehe des Textfelds
		key.setMaximumSize(new Dimension(Utility.WIDTH_TEXTFIELD_ONE_LETTER, Utility.HEIGHT_TEXTFIELD));
		// Wird benoetigt, um die Hoehe zu setzen
		key.setPreferredSize(new Dimension(Utility.WIDTH_TEXTFIELD_ONE_LETTER, Utility.HEIGHT_TEXTFIELD));

		// fuegt das Text- und Buttonpanel zu einem Panel zusammen
		JPanel keyPanel = new JPanel(layout);
		keyPanel.add(textPanel, BorderLayout.PAGE_START);
		textPanel.add(Box.createRigidArea(new Dimension(0, 20)));
		keyPanel.add(this.createButtonPanel(), BorderLayout.CENTER);

		return keyPanel;
	}

	/**
	 * Gibt den Schluessel als String zurueck
	 */
	@Override
	public String getKey() {
		return this.key.getText();
	}
	
	/**
	 * Setzt einen Schluessel in das TextFeld
	 * 
	 * @param key der zu setzende Schluessel
	 */
	@Override
	public void setKey(String key) {
		this.key.setText(key);
	}
	
	/**
	 * Erzeugt einen zufaelligen Schluessel
	 */
	@Override
	public void randomKey() {
		int leftLimit = 65;
		int rightLimit = 90;
		int targetStringLength = 1;
		Random random = new Random();
		StringBuilder buffer = new StringBuilder(targetStringLength);
		for (int i = 0; i < targetStringLength; i++) { // Schleife fuer StringBuilder des Random key
			int randomLimitedInt = leftLimit + (int) (random.nextFloat() * (rightLimit - leftLimit + 1));
			buffer.append((char) randomLimitedInt);
		}
		String keyString = buffer.toString();
		this.setKey(keyString);
		
	}
	
	/**
	 * Die Methode gibt die Seriennummer des KeyPanels zurueck
	 * CKeypanel = 1
	 * MKeypanel = 2
	 * VKeypanel = 3
	 * 
	 * @return 1;
	 */
	public int getSerialnumber() {
		return this.serialnumber;
	}
}
