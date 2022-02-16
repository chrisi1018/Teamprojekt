package controller;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;
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
 * Schluesseltextfeld
 * 
 * @author Julian Singer
 * @version 1.0
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
		key.setDocument(new LimitedTextfield(Utility.MAXIMUMKEYLENGTH));
		BorderLayout layout = new BorderLayout();
		JLabel description = new JLabel("Schl\u00fcssel");
		description.setFont(new Font(Font.DIALOG, Font.BOLD, 20));
		name.setFont(new Font(Font.DIALOG, Font.BOLD, 15));
		key.setFont(new Font(Font.DIALOG, Font.BOLD, 15));

		// definiert ein Panel fuer die Schluesseleingabe
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
		key.setAlignmentX(Component.CENTER_ALIGNMENT);

		// fuegt das Text- und Buttonpanel zu einem Panel zusammen
		JPanel keyPanel = new JPanel(layout);
		keyPanel.add(textPanel, BorderLayout.PAGE_START);
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
		int rightLimit = Utility.MAXIMUMKEYLENGTH;
		Random random = new Random();
		int targetStringLength = leftLimit + (int) (random.nextFloat() * (rightLimit - leftLimit + 1));
		random = new Random();
		leftLimit = 65;
		rightLimit = 90;
		StringBuilder buffer = new StringBuilder(targetStringLength);
		for (int i = 0; i < targetStringLength; i++) { //Schleife fuer Stringbuilder des Random key
			int randomLimitedInt = leftLimit + (int) (random.nextFloat() * (rightLimit - leftLimit + 1));
			buffer.append((char) randomLimitedInt);
		}
		String keyString = buffer.toString();
		this.setKey(keyString);
	}
	
	/**
	 * Die Methode gibt die Seriennumer des KeyPanels zurück
	 * CKeypanel = 1
	 * MKeypanel = 2
	 * VKeypanel = 3
	 * 
	 * @param return 3;
	 */
	public int getSerialnumber() {
		return this.serialnumber;
	}

}
