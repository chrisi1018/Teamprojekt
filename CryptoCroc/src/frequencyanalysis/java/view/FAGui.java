package view;

import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenuBar;
import javax.swing.JPanel;
import javax.swing.JTextField;

import controller.FATable;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;

/**
 * Erstellt den Frame und legt das Layout der Haeufigkeitsanalyse fest
 * 
 * @author zes
 * @version 1.0
 */
public class FAGui {

	private JFrame frame;
	private JPanel graphPanel;
	private FATable[] table;
	private JMenuBar menu;
	private JPanel mainPanel;
	private JLabel mainLabel;
	private JButton left;
	private JButton right;
	private JComboBox<String> language;
	private JComboBox<String> keyChar;
	private JLabel lengthLabel;
	private JTextField lengthTextField;
	private JCheckBox monoCheckBox;

	/**
	 * Konstruktor der den Frame fuer die Haeufigkeitsanalyse erstellt und das
	 * Layout festlegt
	 * 
	 * @param menu            die Menue-Bar
	 * @param graphPanel      das Panel das den Graph enthaelt
	 * @param table           das Panel das die Reihe an Buchstaben enthaelt
	 * @param left            den linken button
	 * @param right           den rechten button
	 * @param language        eine ComboBox mit allen moeglichen Sprachen
	 * @param keyChar         eine ComboBox mit der Anzahl der Buchstaben
	 * @param lengthLabel     das Label fuer "Schluessellaenge"
	 * @param lengthTextField das Textfeld fuer die Eingabe der Schluessellaenge
	 * @param monoCheckBox    eine Checkbox fuer die monoalphabetische
	 *                        Verschluesselung
	 */
	public FAGui(JMenuBar menu, JPanel graphPanel, FATable[] table, JButton left, JButton right,
			JComboBox<String> language, JComboBox<String> keyChar, JLabel lengthLabel, JTextField lengthTextField,
			JCheckBox monoCheckBox) {
		// erstellt den Frame
		this.frame = new JFrame("H\u00e4ufigkeitsanalyse");
		this.frame.setSize(1280, 650);
		this.frame.setResizable(false);
		this.frame.setVisible(true);
		this.frame.setLayout(new GridBagLayout());
		this.mainPanel = new JPanel(new BorderLayout(4, 4));

		// alles sichtbar machen und initialisieren
		this.mainLabel = new JLabel("H\u00e4ufigkeitsanalyse");
		this.mainLabel.setVisible(true);
		this.mainLabel.setFont(new Font(Font.DIALOG, Font.BOLD, 20));
		this.menu = menu;
		this.menu.setVisible(true);
		this.graphPanel = graphPanel;
		this.graphPanel.setVisible(true);
		this.left = left;
		this.left.setVisible(true);
		this.right = right;
		this.right.setVisible(true);
		this.language = language;
		this.language.setVisible(true);
		this.keyChar = keyChar;
		this.keyChar.setVisible(true);
		this.lengthLabel = lengthLabel;
		this.lengthLabel.setVisible(true);
		this.lengthTextField = lengthTextField;
		this.lengthTextField.setVisible(true);
		this.monoCheckBox = monoCheckBox;
		this.monoCheckBox.setVisible(true);

		this.table = table;

		// Menu leiste einstellen
		this.frame.setJMenuBar(menu);

		// Groesse der Comboboxen einheitlich machen nach der maximalen Anzahl
		// Buchstaben fuer Vigenere
		keyChar.setPrototypeDisplayValue("999. Buchstabe");
		language.setPrototypeDisplayValue("999. Buchstabe");

		// alles platzieren
		initMainPanel();
		initFrameBorders();
		this.frame.setVisible(true);
	}

	/**
	 * Positioniert das oberste Panel das in <code>initTopPanelForFrame()</code>
	 * erstellt wird, das mainPanel mit den Komponenten und fuegt Padding in alle
	 * Richtungen hinzu
	 */
	private void initFrameBorders() {
		GridBagConstraints c = new GridBagConstraints();
		c.gridx = 0;
		c.gridy = 0;
		c.ipadx = 30;
		this.frame.add(initTopPanelForFrame(), c);
		// rechts davon leer
		c.gridx = 1;
		c.ipadx = 0;
		c.fill = GridBagConstraints.HORIZONTAL;
		c.gridwidth = 2;
		this.frame.add(new JPanel(), c);

		// mainPanel mit Graph, Buttons, ComboBoxen, usw
		c.gridx = 0;
		c.gridy = 1;
		c.ipady = 40;
		this.frame.add(mainPanel, c);

		// padding nach unten
		// TODO hier ist platz fuer das Feld mit dem Schluessel; eventuell muss die
		// Groesse des frame's auf (1260, 700) umgestellt werden
		c.ipady = 0;
		c.gridy = 2;
		c.ipadx = 30;
		c.fill = GridBagConstraints.HORIZONTAL;
		this.frame.add(new JPanel(), c);
	}

	/**
	 * Erstellt das oberste Panel, welches aus zwei Panels besteht; toptop enthaelt
	 * das Label mit dem Titel "Haeufigkeitsanalyse" und topbottom enthaelt das
	 * Label mit Schluessellaenge, das Textfeld fuer Buchstabenlaenge und die
	 * MonoCheckBox
	 */
	private JPanel initTopPanelForFrame() {
		GridLayout gl = new GridLayout(1, 2);
		// enthaelt toptop und topbottom
		JPanel top = new JPanel();
		// enthaelt das Label Haeufigkeitsanalyse
		JPanel toptop = new JPanel(gl);
		// enthaelt Schluessellange bis ComboBox
		JPanel topbottom = new JPanel();

		// mainLabel zu Panel hinzuefuegen
		JPanel label = new JPanel();
		label.add(mainLabel, BorderLayout.BEFORE_LINE_BEGINS);

		toptop.add(label, BorderLayout.BEFORE_FIRST_LINE);
		toptop.add(new JPanel(), BorderLayout.PAGE_START);
		// Label am Rand und Schatten von Wort "Haeufigkeitsanalyse" genau gleich wie
		// Schatten von "Schluessellaenge"
		toptop.setPreferredSize(new Dimension(1260 / 2 - 36, 40));
		top.add(toptop, BorderLayout.BEFORE_LINE_BEGINS);

		// Komponenten hinzufuegen
		topbottom.add(lengthLabel, BorderLayout.LINE_START);
		topbottom.add(lengthTextField, BorderLayout.CENTER);
		topbottom.add(monoCheckBox, BorderLayout.LINE_END);
		top.add(topbottom, BorderLayout.PAGE_END);
		top.setPreferredSize(new Dimension(500, 100));
		return top;
	}

	/**
	 * Linkes Panel mit Comboboxen fuer Sprache und Buchstaben
	 */
	private JPanel initComboBoxPanel() {
		JPanel left = new JPanel();
		left.add(language, BorderLayout.PAGE_START);
		left.add(keyChar, BorderLayout.PAGE_END);
		left.setPreferredSize(new Dimension(150, 50));
		return left;
	}

	/**
	 * Panel mit den Buttons und der Buchstabenreihe; hier wird auch das Panel mit
	 * den ComboBoxen eingefuegt
	 */
	private JPanel initTable() {

		JPanel total = new JPanel(new BorderLayout());

		JPanel tableWithButtons = new JPanel(new FlowLayout(FlowLayout.LEADING));
		JPanel filler = new JPanel();
		filler.setPreferredSize(new Dimension(40, 0));
		tableWithButtons.add(filler);

		left.setPreferredSize(new Dimension(30, 30));
		tableWithButtons.add(left);

		tableWithButtons.add(table[0].getTablePanel());

		right.setPreferredSize(new Dimension(30, 30));
		tableWithButtons.add(right);

		total.add(initComboBoxPanel(), BorderLayout.CENTER);
		total.add(tableWithButtons, BorderLayout.EAST);

		return total;
	}

	/**
	 * Fuellt das MainPanel mit dem Graph, sowie der Buchstabenreihe und den Buttons
	 */
	private void initMainPanel() {
		this.mainPanel.add(initTable(), BorderLayout.NORTH);
		this.mainPanel.add(graphPanel, BorderLayout.CENTER);
	}

	/**
	 * Methode die den Frame repainted, muss nach Veraenderungen der Gui aufgerufen
	 * werden
	 */
	public void repaint() {
		this.mainPanel.revalidate();
		this.mainPanel.repaint();
	}
}
