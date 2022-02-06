package controller;

import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.Insets;
import java.awt.Font;
import java.io.IOException;

import javax.swing.JButton;
import view.FAGui;
import view.FAExplanationFrame;
import view.Messages;
import model.FAData;
import model.TableData;

import javax.swing.text.AttributeSet;
import javax.swing.text.BadLocationException;
import javax.swing.text.PlainDocument;

/**
 * Die Klasse stellt den Hauptcontroller der Haufigkeitsanalyse dar
 * 
 * @author Julian Sturm, zes, Julian Singer, chrisi
 * @version 1.1
 */
public class FAController {

	private KeyPanel key;
	private JLabel lengthLabel;
	private JTextField lengthTextField;
	private int length = 1;
	private JCheckBox monoCheckBox;
	private JComboBox<String> language;
	private float[] languageData = new float[26]; // 26 fuer die Groesse des Alphabets
	private JComboBox<String> keyChar;
	private JButton left;
	private JButton right;
	private FATable[] tables;
	private FAMenuBar menu;
	private TableData[] data;
	private FAGraph graph;
	// gui muss statisch sein, damit update des graphen auch aus FATable aufgerufen
	// werden kann
	private static FAGui gui;
	// speichert die aktuelle Sprache statisch ab, sodass andere Klassen hier auch
	// drauf zugreifen koennen
	private static String currentLanguage;
	private static int max;

	/**
	 * Der Konstruktor fuer die Klasse FaControlle siehe init-Methoden fuer mehr
	 * info
	 * 
	 * @param key das keyPanel, entweder V, M oder C, des Main-Frames
	 */
	public FAController(KeyPanel key) {
		this.key = key;
		initLength();
		initMonoCheckBox();
		initLanguage();
		initKeyChar(length);
		initLeftRight();
		initTableData();
		currentLanguage = language.getSelectedItem().toString();
		max = calcMax();
		initFATable();
		initFAMenuBar();
		try {
			graph = new FAGraph(languageData, data[0].getFrequencyPercentage(), currentLanguage, max);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		initFAGui();

	}

	/**
	 * Berechnet die groesste vorkommende Zahl um den Graphen daran anzupassen
	 * 
	 * @return die groesste vorkommende Zahl
	 */
	private int calcMax() {
		int max = 0;
		for (int i = 0; i < data.length; i++) {
			float[] freq = data[i].getFrequencyPercentage();
			for (int j = 0; j < freq.length; j++) {
				if (freq[j] > max) {
					max = (int) Math.ceil(Double.parseDouble(Float.toString(freq[j])));
				}
			}
		}
		return max;
	}

	/**
	 * Groesste Zahl der Haeufigkeitenverteilung um den Graph daran anzupassen
	 * 
	 * @return max
	 */
	public static int getMax() {
		return max;
	}

	/**
	 * Damit FATable Zugang hat zur aktuell ausgewaehlten Sprache
	 * 
	 * @return die aktuelle Sprache
	 */
	public static String getCurrentLanguage() {
		return currentLanguage;
	}

	/**
	 * Erstellt das JLabel fuer die Laenge, erstellt das JTextFeld fuer die Laenge,
	 * es koennen nur Zahlen eingegebn werden.
	 */
	private void initLength() {
		this.lengthLabel = new JLabel("Schl\u00fcssell\u00e4nge");
		this.lengthLabel.setVisible(true);

		this.lengthTextField = new JTextField(10);
		this.lengthTextField.setDocument(new PlainDocument() {

			private static final long serialVersionUID = 6389795108727999785L;
			private int limit = 3;

			// Hier wird festgelegt das nur Zahlen eingeben werden koennen
			@Override
			public void insertString(int offset, String str, AttributeSet att) throws BadLocationException {
				boolean insert = true;
				boolean leadingZero = false;
				// Ueberprueft das nur Zahlen eingegeben werden
				for (int i = 0; i < str.length(); i++) {
					if (str.charAt(0) < '0' || str.charAt(i) > '9') {
						insert = false;
					}
				}
				// Ueberprueft auf eine fuehrende Null
				if (offset == 0) {
					if (str.charAt(0) == '0') {
						leadingZero = true;
					}
				}
				if (insert && !leadingZero) {

					if (this.getLength() + str.length() <= limit) {
						super.insertString(offset, str, att);
						// trigger textboxlength
					} else {
						Messages.errorMessage("Die L\u00e4nge des Schl\u00fcssel darf 999 nicht \u00fcberschreiten!");
					}
				}
			}
		});
		this.lengthTextField.setText("1");
		this.lengthTextField.getDocument().addDocumentListener(new DocumentListener() {

			@Override
			public void insertUpdate(DocumentEvent e) {
				int count = Integer.parseInt(lengthTextField.getText().toString());
				initKeyChar(count);
				gui.updateKeyChar(keyChar);
			}

			@Override
			public void removeUpdate(DocumentEvent e) {
				// nichts tun
			}

			@Override
			public void changedUpdate(DocumentEvent e) {
				// wird nicht aufgerufen
			}

		});

		this.lengthTextField.setVisible(true);

	}

	/**
	 * Erstellt die CheckBox die festlegt ob die Textfelder beschreibbar sind.
	 */
	private void initMonoCheckBox() {
		this.monoCheckBox = new JCheckBox("Monoalphabetische Verschl\u00fcsselung");
		this.monoCheckBox.setVisible(true);
		this.monoCheckBox.addActionListener(e -> this.checkCheckbox());
	}

	/**
	 * Bei aktivierter Checkbox wird die Schreibsperre der Textfleder aufgehoben.
	 * Bei deaktivierter Checkbox wird die Schreibspeere der Textfelder gesetzt.
	 */
	private void checkCheckbox() {
		for (int i = 0; i < this.tables.length; i++) {
			this.tables[i].enableTextFields(this.monoCheckBox.isSelected());
		}
	}

	/**
	 * Erstellt das DropDownMenue mit dem man die Sprache auswaehlt
	 */
	private void initLanguage() {
		String[] languages = { "Deutsch" };
		this.languageData = FAData.GERMAN;
		this.language = new JComboBox<String>(languages);
		this.language.setVisible(true);
	}

	/**
	 * Erstellt das DropDownMenue fuer die Auswahl des Buchstabens und legt die
	 * Aktion beim Klick auf eine der moeglichen Optionen fest
	 * 
	 * @param count Anzahl der Auswahlmoeglichkeiten
	 */
	private void initKeyChar(int count) {
		String[] number = new String[count];
		for (int i = 0; i < count; i++) {
			number[i] = (i + 1) + ". Buchstabe";
		}
		this.keyChar = new JComboBox<String>(number);
		this.keyChar.setVisible(true);
		// bei auswahl der aktuellen option
		this.keyChar.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				int letterIndex = Integer.parseInt(keyChar.getSelectedItem().toString().substring(0, 1)) - 1;
				gui.setCurrentTable(letterIndex);
				gui.setTablePanel();
				gui.repaint();
			}
		});
	}

	/**
	 * Erstellt die Buttons "left" und "right"
	 */
	private void initLeftRight() {
		this.left = new JButton("\u00ab");
		this.left.setMargin(new Insets(0, 0, 0, 0));
		this.left.setFont(new Font("Arial", Font.PLAIN, 22));
		this.left.addActionListener(e ->
		this.tables[this.keyChar.getSelectedIndex()].shiftLeft());
		
		this.left.setVisible(true);
		this.right = new JButton("\u00bb");
		this.right.setMargin(new Insets(0, 0, 0, 0));
		this.right.setFont(new Font("Arial", Font.PLAIN, 22));
		this.right.addActionListener(e ->
		this.tables[this.keyChar.getSelectedIndex()].shiftRight());
		
		this.right.setVisible(true);

	}

	/**
	 * Initialisiert die FATable-Instanz fuer jeden Buchstaben im Schluessel
	 */
	private void initFATable() {
		int keyLength;
		if (this.lengthTextField.getText().isEmpty()) {
			keyLength = 0;
		} else {
			keyLength = Integer.valueOf(this.lengthTextField.getText());
			this.tables = new FATable[keyLength];
		}
		for (int i = 0; i < keyLength; i++) {
			this.tables[i] = new FATable(this.data[i], this.languageData, currentLanguage, max);
		}
	}

	/**
	 * Initialisiert die Menueleiste und fuegt den Menues ActionListener hinzu
	 */
	private void initFAMenuBar() {
		this.menu = new FAMenuBar();
		this.menu.initExplanationItem(1, new FAExplanationFrame());
		// initialisiert bei "Text neu laden" FATabel und TableData neu
		// und passt das Fenster an neuen Text an
		this.menu.getMenuBar().getMenu(0).addMouseListener(new MouseListener() {
			@Override
			public void mouseClicked(MouseEvent e) {
				initTableData();
				max = calcMax();
				initFATable();
				checkCheckbox();
				gui.setTable(tables);
				gui.setTablePanel();
				gui.repaint();
			}

			@Override
			public void mousePressed(MouseEvent e) {
				// tue nichts
			}

			@Override
			public void mouseReleased(MouseEvent e) {
				// tue nichts
			}

			@Override
			public void mouseEntered(MouseEvent e) {
				// tue nichts
			}

			@Override
			public void mouseExited(MouseEvent e) {
				// tue nichts
			}
		});
	}

	/**
	 * Initialisiert data[] mit den jeweiligen Haeufigkeiten
	 */
	private void initTableData() {
		int keyLength;
		if (this.lengthTextField.getText().isEmpty()) {
			keyLength = 0;
		} else {
			keyLength = Integer.valueOf(this.lengthTextField.getText());
			this.data = new TableData[keyLength];
		}
		float[][] allFrequencies = FAData.analyse(this.key.getController().getCryptoText(), keyLength);

		// 2D-Array wird in 1D-Array umgewandelt und im Konstruktor uebergeben
		for (int i = 0; i < keyLength; i++) {
			float[] oneFrequencies = new float[allFrequencies[i].length];
			for (int j = 0; j < allFrequencies[i].length; j++) {
				oneFrequencies[j] = allFrequencies[i][j];
			}
			this.data[i] = new TableData(oneFrequencies);
		}
	}

	/**
	 * Aktualisiert den Graphen der GUI; statische Methode damit diese auch aus
	 * FATable aufgerufen werden kann ohne eine konkrete Instanz der GUI zu brauchen
	 * 
	 * @param graph den neuen Graphen
	 */
	public static void updateGraph(FAGraph graph) {
		gui.updateGraph(graph);
	}

	/**
	 * Erstellt die GUI fuer die Haeufigkeitsanalyse
	 */
	private void initFAGui() {
		gui = new FAGui(menu.getMenuBar(), graph, tables, left, right, language, keyChar, lengthLabel, lengthTextField,
				monoCheckBox);
	}
}
