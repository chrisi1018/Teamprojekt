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
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.Component;

/**
 * Definiert 'createKeyPanel'-Methode fuer Caesar und speichert sein
 * Schluesseltextfeld
 * 
 * @author Julian Singer
 * @version 1.0
 *
 */
public class CKeyPanel extends KeyPanel {

	private JTextField key = new JTextField();
	private JLabel name = new JLabel("A");

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
		// sorgt dafuer dass Text in key markiert wird beim Klicken
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
		description.setFont(new Font(Font.DIALOG, Font.BOLD, 20));
		name.setFont(new Font(Font.DIALOG, Font.BOLD, 15));
		key.setFont(new Font(Font.DIALOG, Font.BOLD, 15));

		// LimitedTextfield bei dem nur ein Character eingegeben darf
		key.setDocument(new LimitedTextfield(1));

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
		key.setMaximumSize(new Dimension(19, 30));
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

}
