package view;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenuBar;
import javax.swing.JPanel;
import javax.swing.JTextField;

import controller.FAGraph;
import controller.FATable;
import controller.KeyPanel;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

/**
 * Erstellt den Frame und legt das Layout der Haeufigkeitsanalyse fest
 * 
 * @author zes, Julian Singer
 * @version 1.2
 */
public class FAGui {

	private JFrame frame;
	private FAGraph graph;
	private JPanel graphPanel;
	private JPanel tablePanel;
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
	private int currentTable;

	/**
	 * Konstruktor, der den Frame fuer die Haeufigkeitsanalyse erstellt und das
	 * Layout festlegt
	 * 
	 * @param menu            die Menueleiste
	 * @param graph           der aktuelle Graph
	 * @param table           das Panel, das die Reihe an Buchstaben enthaelt
	 * @param left            der Links-Button
	 * @param right           der Rechts-Button
	 * @param language        eine ComboBox mit allen moeglichen Sprachen
	 * @param keyChar         eine ComboBox mit der Anzahl der Buchstaben
	 * @param lengthLabel     das Label fuer "Schluessellaenge"
	 * @param lengthTextField das Textfeld fuer die Eingabe der Schluessellaenge
	 * @param monoCheckBox    eine Checkbox fuer die monoalphabetische
	 *                        Verschluesselung
	 */
	public FAGui(JMenuBar menu, FAGraph graph, FATable[] table, JButton left, JButton right, JComboBox<String> language,
			JComboBox<String> keyChar, JLabel lengthLabel, JTextField lengthTextField, JCheckBox monoCheckBox) {
		// erstellt den Frame
		this.frame = new JFrame("H\u00e4ufigkeitsanalyse");
		this.frame.setSize(1300, 650);
		this.frame.setResizable(false);
		this.frame.setVisible(true);
		this.frame.setLayout(new GridBagLayout());
		this.mainPanel = new JPanel(new BorderLayout(4, 4));
		
		//Setze das Icon im Fenster
		ImageIcon icon = new ImageIcon("C:\\Users\\chris\\workspace\\Teamprojekt\\CryptoCroc\\src\\main\\resources\\croc.png");
		this.frame.setIconImage(icon.getImage());

		// alles sichtbar machen und initialisieren
		this.mainLabel = new JLabel("H\u00e4ufigkeitsanalyse");
		this.mainLabel.setVisible(true);
		this.mainLabel.setFont(new Font(Font.DIALOG, Font.BOLD, 20));
		this.menu = menu;
		this.menu.setVisible(true);
		this.graph = graph;
		this.graphPanel = graph.getGraphPanel();
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
		this.currentTable = 0;
		this.tablePanel = initTable();
		this.tablePanel.setVisible(true);

		// Menueleiste einstellen
		this.frame.setJMenuBar(menu);

		// Groesse der ComboBoxen einheitlich machen nach der maximalen Anzahl
		// Buchstaben fuer Vigenere
		keyChar.setPrototypeDisplayValue("999. Buchstabe");
		language.setPrototypeDisplayValue("999. Buchstabe");

		// alles platzieren
		initMainPanel();
		initFrameBorders();
		this.frame.setVisible(true);

		this.frame.addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent e) {
				KeyPanel.faSwitchOpen();
			}
		});
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
		// TODO hier ist Platz fuer das Feld mit dem Schluessel; eventuell muss die
		// Groesse des frame's auf (1300, 700) umgestellt werden
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
	 * Initialisiert das linke Panel mit ComboBoxen fuer die Sprache und Buchstaben
	 */
	private JPanel initComboBoxPanel() {
		JPanel left = new JPanel();
		left.add(language, BorderLayout.PAGE_START);
		left.add(keyChar, BorderLayout.PAGE_END);
		left.setPreferredSize(new Dimension(150, 50));
		return left;
	}

	/**
	 * Initialisiert das Panel mit den Buttons und der Buchstabenreihe; hier wird
	 * auch das Panel mit den ComboBoxen eingefuegt
	 */
	private JPanel initTable() {
		JPanel total = new JPanel(new BorderLayout());

		JPanel tableWithButtons = new JPanel(new FlowLayout(FlowLayout.LEADING));
		JPanel filler = new JPanel();
		filler.setPreferredSize(new Dimension(40, 0));
		tableWithButtons.add(filler);

		tableWithButtons.add(left);
		if (this.currentTable >= table.length) {
			this.setCurrentTable(table.length - 1);
			tableWithButtons.add(table[this.currentTable].getTablePanel());
		} else {
			tableWithButtons.add(table[this.currentTable].getTablePanel());
		}
		tableWithButtons.add(right);

		total.add(initComboBoxPanel(), BorderLayout.CENTER);
		total.add(tableWithButtons, BorderLayout.EAST);

		return total;
	}

	/**
	 * Wechselt zur Buchstabenreihe und dem zugehoerigen Graphen vom aktuellen
	 * Schluesselbuchstaben
	 */
	public void setTablePanel() {
		this.mainPanel.remove(this.tablePanel);
		this.mainPanel.remove(this.graphPanel);
		this.tablePanel = initTable();
		this.graph = this.table[this.currentTable].getGraph();
		this.graphPanel = this.graph.getGraphPanel();
		initMainPanel();
		repaint();
	}

	/**
	 * Setter fuer table
	 * 
	 * @param newTable neue FATables
	 */
	public void setTable(FATable[] newTable) {
		this.table = newTable;
	}

	/**
	 * Setter fuer currentTable
	 * 
	 * @param newTable neues currentTable
	 */
	public void setCurrentTable(int newTable) {
		this.currentTable = newTable;
	}

	/**
	 * Fuellt das MainPanel mit dem Graph, sowie der Buchstabenreihe und den Buttons
	 */
	private void initMainPanel() {
		this.mainPanel.add(this.tablePanel, BorderLayout.NORTH);
		this.mainPanel.add(this.graphPanel, BorderLayout.CENTER);
	}

	/**
	 * Aktualisiert den Frame mit einem neuen Graphen, indem erst das Panel entfernt
	 * und dann neu eingesetzt wird
	 * 
	 * @param graph der neue Graph
	 */
	public void updateGraph(FAGraph graph) {
		this.mainPanel.remove(this.graph.getGraphPanel());
		this.graph = graph;
		this.graphPanel = graph.getGraphPanel();
		this.mainPanel.add(this.graphPanel, BorderLayout.CENTER);
		this.repaint();
	}

	/**
	 * Methode, die den Frame repainted (wird nach Veraenderungen der GUI
	 * aufgerufen)
	 */
	public void repaint() {
		this.mainPanel.revalidate();
		this.mainPanel.repaint();
	}

	/**
	 * Aktualisiert die keyChar-ComboBox entsprechend der Laenge des eingegebenen
	 * Schluessels; gibt es mehr keyChars als Panels werden die restlichen zu leeren
	 * FATables
	 * 
	 * @param keyChar die neue ComboBox
	 */
	public void updateKeyChar(JComboBox<String> keyChar) {
		// passe Panel an, indem das alte Panel entfernt und das neue Panel eingesetzt
		// wird
		this.keyChar = keyChar;
		this.keyChar.setPrototypeDisplayValue("999. Buchstabe");
		this.mainPanel.remove(this.tablePanel);
		this.tablePanel = initTable();
		this.graphPanel = this.table[this.currentTable].getGraph().getGraphPanel();
		initMainPanel();
		repaint();

	}
	
	/**
	 * Methode die den Frame fokusiert
	 */
	public void focus() {
		this.frame.toFront();
	}
}
