package controller;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.Font;

/**
 * Definiert 'createKeyPanel'-Methode fuer monoalphabetische Verschluesselung
 * 
 * @author zes
 * @version 1.0
 */
public class MKeyPanel extends KeyPanel {

	private int alphabetSize = 26;
	private JTextField[] keys = new JTextField[alphabetSize];
	private JLabel[] names = new JLabel[alphabetSize];

	/**
	 * Konstruktor, der der neuen Instanz eine monoalphabetische Verschlüsselung
	 * zuordnet
	 * 
	 */
	public MKeyPanel() {
		// super.setCrypt(new MCrypt());
		// TODO
	}

	@Override
	public JPanel createKeyPanel() {
		this.initNames();
		BorderLayout bLayout = new BorderLayout();
		JPanel keyPanel = new JPanel(bLayout);
		JLabel description = new JLabel("Schl\\u00fcssel");
		description.setFont(new Font(Font.DIALOG, Font.BOLD, 20));
		for (int i = 0; i < alphabetSize; i++) {
			names[i].setFont(new Font(Font.DIALOG, Font.BOLD, 15));
			keys[i] = new JTextField();
			keys[i].setFont(new Font(Font.DIALOG, Font.BOLD, 15));
		}

		JPanel[] keyName = new JPanel[alphabetSize];
		FlowLayout fLayout = new FlowLayout();
		fLayout.setHgap(10);
		JPanel inputPanel = new JPanel(fLayout);
		for (int i = 0; i < 2; i++) {
			keyName[i] = new JPanel(bLayout);
			keyName[i].setVisible(true);
			keyName[i].add(names[i], BorderLayout.NORTH);
			keyName[i].add(keys[i], BorderLayout.SOUTH);
			inputPanel.add(keyName[i]);
		}

		return inputPanel;
	}

	/**
	 * Methode zum Initialisieren der Namen der Textfelder s
	 */
	private void initNames() {
		for (int i = 0; i < alphabetSize; i++) {
			int val = 65 + i;
			char c = (char) val;
			this.names[i] = new JLabel(Character.toString(c));
		}
	}
}
