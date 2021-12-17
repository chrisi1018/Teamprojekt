package controller;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;

/**
 * Definiert 'createKeyPanel'-Methode fuer monoalphabetische Verschluesselung
 * 
 * @author zes
 * @version 1.0
 */
public class MKeyPanel extends KeyPanel {

	private final int alphabetSize = 26;
	private JTextField[] keys = new JTextField[alphabetSize];
	private JLabel[] names = new JLabel[alphabetSize];
	private JPanel[] nameKeyPanels = new JPanel[alphabetSize];

	/**
	 * Konstruktor, der der neuen Instanz eine monoalphabetische Verschluesselung
	 * zuordnet
	 * 
	 */
	public MKeyPanel(MainController controller) {
		super(controller);
		// super.setCrypt(new MCrypt());
		// TODO
	}

	@Override
	public JPanel createKeyPanel() {
		// Alphabet in Array schreiben
		this.initNames();

		// erstellt Panel mit JLabel als titel
		JPanel title = new JPanel();
		JLabel description = new JLabel("Schl\u00fcssel");
		title.setLayout(new BoxLayout(title, BoxLayout.PAGE_AXIS));
		description.setAlignmentX(Component.CENTER_ALIGNMENT);
		description.setFont(new Font(Font.DIALOG, Font.BOLD, 20));
		title.add(description, BorderLayout.SOUTH);
		// title.add(Box.createRigidArea(new Dimension(0, 20)));

		// intialisiert Eintraege von keys und setzt Textstil
		for (int i = 0; i < alphabetSize; i++) {
			names[i].setFont(new Font(Font.DIALOG, Font.BOLD, 15));
			keys[i] = new JTextField();
			keys[i].setFont(new Font(Font.DIALOG, Font.BOLD, 15));
			keys[i].setColumns(1);
			keys[i].setPreferredSize(new Dimension(19, 30));

			JPanel nameKeyPanel = new JPanel();
			nameKeyPanel.setLayout(new BoxLayout(nameKeyPanel, BoxLayout.PAGE_AXIS));
			nameKeyPanel.add(names[i]);
			nameKeyPanel.add(keys[i]);
			nameKeyPanels[i] = nameKeyPanel;
		}

		// fuer die Buchstaben und Textfelder
		FlowLayout fLayout = new FlowLayout();
		fLayout.setVgap(25);
		JPanel inputPanel = new JPanel(fLayout);
		for (int i = 0; i < alphabetSize; i++) {
			inputPanel.add(nameKeyPanels[i]);
		}

		// alles zusammenfuegen und Buttons dazu machen
		JPanel total = new JPanel();
		total.setLayout(new BoxLayout(total, BoxLayout.PAGE_AXIS));
		total.add(title);
		title.add(Box.createRigidArea(new Dimension(0, 20)));
		total.add(inputPanel);
		total.add(this.createButtonPanel());

		return total;
	}
	
	/**
	 * 
	 */
	@Override
	public String getKey() {
		String key = "";
		for(int i = 0; i < this.alphabetSize; i++) {
			key += this.keys[i].getText();
		}
		return key;
	}

	/**
	 * Methode zum Initialisieren der Namen der Textfelder (Alphabet)
	 */
	private void initNames() {
		for (int i = 0; i < alphabetSize; i++) {
			int val = 65 + i;
			char c = (char) val;
			this.names[i] = new JLabel(Character.toString(c), JLabel.CENTER);
		}
	}
}
