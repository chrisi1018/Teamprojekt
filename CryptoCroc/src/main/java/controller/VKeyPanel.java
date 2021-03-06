package controller;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.Dimension;
import java.util.Random;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import model.VCrypt;
import utility.Utility;

/**
 * Definiert 'createKeyPanel'-Methode fuer Vigenere und speichert sein
 * SchluesselTextfeld
 * 
 * @author Julian Singer, chrisi
 * @version 1.2
 *
 */
public class VKeyPanel extends KeyPanel {

	private JTextField key = new JTextField();
	private JLabel name = new JLabel("Schl\u00fcsselwort");
	private final int serialnumber = 3;

	/**
	 * Die aktuelle MainController-Instanz wird gesichert
	 * 
	 * @param controller die MainController-Instanz
	 */
	public VKeyPanel(MainController controller) {
		super(controller);
		super.setCrypt(new VCrypt());
	}

	/**
	 * Ueberschreibt die Methode 'createKeyPanel'
	 */
	@Override
	public JPanel createKeyPanel() {
		
		// initialisiert Ueberschrift und setzt den Stil der Elemente
		JLabel description = new JLabel("Schl\u00fcssel");
		description.setFont(Utility.HEADLINE_LABEL_FONT);
		description.setForeground(Utility.DARK_GREEN);
		name.setFont(Utility.LABEL_FONT);
		name.setForeground(Utility.DARK_GREEN);
		key.setFont(Utility.TEXT_FONT);
		key.setBorder(Utility.TEXTFIELD_BORDER);
		key.setHorizontalAlignment(JTextField.CENTER);
		
		key.setDocument(new LimitedTextfield(Utility.MAXIMUM_KEY_LENGTH));

		// definiert ein Panel fuer die SchluesselEingabe
		JPanel textPanel = new JPanel();
		textPanel.setLayout(new BoxLayout(textPanel, BoxLayout.PAGE_AXIS));
		textPanel.add(description);
		description.setAlignmentX(Component.CENTER_ALIGNMENT);
		textPanel.add(Box.createRigidArea(new Dimension(0, 20)));
		textPanel.add(name);
		name.setAlignmentX(Component.CENTER_ALIGNMENT);
		textPanel.add(key);
		key.setAlignmentX(Component.CENTER_ALIGNMENT);

		// setzt die Breite und die Hoehe des Textfelds
		key.setMaximumSize(new Dimension(Utility.WIDTH_TEXTFIELD, Utility.HEIGHT_TEXTFIELD));
		// wird benoetigt, um die Hoehe zu setzen
		key.setPreferredSize(new Dimension(Utility.WIDTH_TEXTFIELD, Utility.HEIGHT_TEXTFIELD));
		
		// fuegt das Text- und ButtonPanel zu einem Panel zusammen
		JPanel keyPanel = new JPanel(new BorderLayout());
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
	 * Die Methode erzeugt einen zufaelligen Schluessel
	 */
	@Override
	public void randomKey() {
		int leftLimit = 1;
		int rightLimit = Utility.MAXIMUM_KEY_LENGTH;
		Random random = new Random();
		int targetStringLength = leftLimit + (int) (random.nextFloat() * (rightLimit - leftLimit + 1));
		random = new Random();
		leftLimit = 65;
		rightLimit = 90;
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
	 * @return 3;
	 */
	public int getSerialnumber() {
		return this.serialnumber;
	}

}
