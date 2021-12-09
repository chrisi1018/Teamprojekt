package controller;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.Font;
import java.awt.GridLayout;

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
		JLabel description = new JLabel("Schl\u00fcssel");
		JPanel text = new JPanel();
		text.setLayout(new BoxLayout(text, BoxLayout.PAGE_AXIS));
		description.setAlignmentX(Component.CENTER_ALIGNMENT);
		description.setFont(new Font(Font.DIALOG, Font.BOLD, 20));
		text.add(description, BorderLayout.NORTH);

		for (int i = 0; i < alphabetSize; i++) {
			names[i].setFont(new Font(Font.DIALOG, Font.BOLD, 15));
			keys[i] = new JTextField();
			keys[i].setFont(new Font(Font.DIALOG, Font.BOLD, 15));
		}

		GridLayout gLayout = new GridLayout(10, 6, 5, 5);

		JPanel inputPanel = new JPanel(gLayout);
		for (int i = 0; i < alphabetSize; i++) {
			inputPanel.add(names[i]);
			keys[i].setColumns(1);
			inputPanel.add(keys[i]);
		}
		JPanel keyPanel = new JPanel(new GridLayout(3, 1));
		JPanel filler = new JPanel();
		filler.setSize(5, 5);
		keyPanel.add(text, BorderLayout.PAGE_START);
		//keyPanel.add(filler);
		keyPanel.add(inputPanel, BorderLayout.CENTER);
	//	keyPanel.add(filler);
		keyPanel.add(this.createButtonPanel());

		return keyPanel;
	}

	/**
	 * Methode zum Initialisieren der Namen der Textfelder
	 */
	private void initNames() {
		for (int i = 0; i < alphabetSize; i++) {
			int val = 65 + i;
			char c = (char) val;
			this.names[i] = new JLabel(Character.toString(c));
		}
	}
}
