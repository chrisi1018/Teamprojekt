package controller;

import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.border.LineBorder;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.Insets;
import java.awt.Dimension;
import java.awt.Font;
import java.io.IOException;

import javax.swing.JButton;
import view.FAGui;
import view.FAExplanationFrame;
import view.Messages;
import model.FAData;
import model.TableData;
import model.VCrypt;
import model.CCrypt;
import model.MCrypt;
import utility.Utility;

import javax.swing.text.AttributeSet;
import javax.swing.text.BadLocationException;
import javax.swing.text.PlainDocument;

/**
 * Die Klasse stellt den Hauptcontroller der Haeufigkeitsanalyse dar
 * 
 * @author Julian Sturm, zes, Julian Singer, chrisi
 * @version 1.4
 */
public class FAController {

	private KeyPanel key;
	private JLabel lengthLabel;
	private JTextField lengthTextField;
	private int length = 1;
	private JCheckBox monoCheckBox;
	private JComboBox<String> language;
	private String[] languages = { "Deutsch", "Englisch" };
	private float[] languageData = new float[Utility.ALPHABET_SIZE];
	private JComboBox<String> keyChar;
	private JButton left;
	private JButton right;
	private FATable[] tables;
	private FAMenuBar menu;
	private TableData[] data;
	private FAGraph graph;
	// speichert die zweite Ziffer der Schluessellaenge, um bei einem Wechsel einer
	// Zahl zwischen
	// 11 und 15 zu einer Zahl zwischen 16 und 19 zu ihrer vorherigen im validen
	// Bereich zurueckzukehren
	private String previousSecondNumber;
	// gui muss statisch sein, damit Update des Graphen auch aus FATable aufgerufen
	// werden kann
	private static FAGui gui;
	// speichert die aktuelle Sprache statisch ab, sodass andere Klassen hier auch
	// darauf zugreifen koennen
	private static String currentLanguage;
	private static int max;
	private FABottom bottom;
	private int invalidLengthError = 1;

	/**
	 * Der Konstruktor fuer die Klasse FaController, siehe init-Methoden fuer mehr
	 * Information
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
		initFABottom();
		try {
			graph = new FAGraph(languageData, data[0].getFrequencyPercentage(), currentLanguage, max);
		} catch (IOException e) {
			e.printStackTrace();
		}
		initFAGui();
	}

	/**
	 * Berechnet die groesste vorkommende Zahl, um den Graphen daran anzupassen
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
	 * Gibt die groesste Zahl der Haeufigkeitsverteilung zurueck, um den Graph daran
	 * anzupassen
	 * 
	 * @return max
	 */
	public static int getMax() {
		return max;
	}

	/**
	 * Damit FATable Zugang zur aktuell ausgewaehlten Sprache hat
	 * 
	 * @return die aktuelle Sprache
	 */
	public static String getCurrentLanguage() {
		return currentLanguage;
	}

	/**
	 * Erstellt das JLabel fuer die Laenge und das JTextField, in das nur Zahlen
	 * eingegeben werden koennen
	 */
	private void initLength() {
		this.lengthLabel = new JLabel("Schl\u00fcssell\u00e4nge");
		this.lengthLabel.setFont(Utility.LABEL_FONT);
		this.lengthLabel.setForeground(Utility.DARK_GREEN);

		this.lengthTextField = new JTextField(10);
		this.lengthTextField.setFont(Utility.TEXT_FONT);
		this.lengthTextField.setBorder(Utility.TEXTFIELD_BORDER);
		this.lengthTextField.setHorizontalAlignment(JTextField.CENTER);
		
		// Setzt die Breite und die Hoehe des Textfeldes
		this.lengthTextField.setMaximumSize(new Dimension(Utility.WIDTH_TEXTFIELD, Utility.HEIGHT_TEXTFIELD));
		// Wird benoetigt um die Hoehe zu setzen
		this.lengthTextField.setPreferredSize(new Dimension(Utility.WIDTH_TEXTFIELD, Utility.HEIGHT_TEXTFIELD));
		
		this.lengthTextField.setDocument(new PlainDocument() {

			private static final long serialVersionUID = 6389795108727999785L;

			// Hier wird festgelegt, dass nur Zahlen eingegeben werden koennen
			@Override
			public void insertString(int offset, String str, AttributeSet att) throws BadLocationException {
				boolean insert = true;
				boolean leadingZero = false;
				// Ueberprueft, ob nur Zahlen eingegeben werden
				for (int i = 0; i < str.length(); i++) {
					if (str.charAt(0) < '0' || str.charAt(i) > '9') {
						insert = false;
					}
				}
				// Prueft auf eine fuehrende Null
				if (offset == 0) {
					if (str.charAt(0) == '0') {
						leadingZero = true;
					}
				}
				if (insert && !leadingZero) {
					if (this.getLength() + str.length() <= Utility.KEY_LENGTH_DIGITS
							&& isLessThanSixteen(offset, str)) {
						super.insertString(offset, str, att);
						previousSecondNumber = lengthTextField.getText(1, 1).trim();
					} else {
						if (this.getLength() + str.length() == Utility.KEY_LENGTH_DIGITS
								&& lengthTextField.getText(0, 1).equals("1")) {
							super.insertString(1, previousSecondNumber, att);
						}
						if (invalidLengthError % Utility.MAX_ERROR_MESSAGES == 1) {
							Messages.errorMessage("Die L\u00e4nge des Schl\u00fcssels darf "
									+ Utility.MAXIMUM_KEY_LENGTH + " nicht \u00fcberschreiten!");
						}
						invalidLengthError++;
					}
				}
			}
		});
		this.lengthTextField.setText(Integer.toString(length));
		// DocumentListener, um Aktualisierungen des Textfeldes an die ComboBox mit
		// der Schluessellaenge weiterzuleiten
		this.lengthTextField.getDocument().addDocumentListener(new DocumentListener() {
			@Override
			public void insertUpdate(DocumentEvent e) {
				// neue Laenge holen und ComboBox und GUI daran anpassen
				int count = Integer.parseInt(lengthTextField.getText().toString());
				initKeyChar(count);
				initTableData();
				max = calcMax();
				initFATable();
				if (bottom.getMono()) {
					bottom.setCrypt(new MCrypt());
				} else if (count > 1) {
					bottom.setCrypt(new VCrypt());
				} else {
					bottom.setCrypt(new CCrypt());
				}
				bottom.updateKeyText(tables);
				if (gui != null) {
					gui.setTable(tables);
					gui.setTablePanel();
					gui.updateKeyChar(keyChar);
					tables[keyChar.getSelectedIndex()].shiftRight();
					tables[keyChar.getSelectedIndex()].shiftLeft();
				}
			}

			@Override
			public void removeUpdate(DocumentEvent e) {
				// nichts tun
			}

			@Override
			public void changedUpdate(DocumentEvent e) {
				// nichts tun
			}

		});
	}

	/**
	 * Erstellt die CheckBox, die festlegt, ob die Textfelder beschreibbar sind.
	 */
	private void initMonoCheckBox() {
		this.monoCheckBox = new JCheckBox("Monoalphabetische Verschl\u00fcsselung");
		this.monoCheckBox.setFont(Utility.LABEL_FONT);
		this.monoCheckBox.setForeground(Utility.DARK_GREEN);
		this.monoCheckBox.addActionListener(e -> this.checkCheckbox());
	}

	/**
	 * Bei aktivierter Checkbox wird die Schreibsperre der Textfelder aufgehoben.
	 * Bei deaktivierter Checkbox wird die Schreibsperre der Textfelder gesetzt.
	 */
	private void checkCheckbox() {
		this.bottom.switchMono();
		this.lengthTextField.setText("1");
		for (int i = 0; i < this.tables.length; i++) {
			this.tables[i].enableTextFields(this.monoCheckBox.isSelected());
		}
		this.lengthTextField.setEnabled(!this.monoCheckBox.isSelected());
	}

	/**
	 * Erstellt das DropDownMenue mit dem man die Sprache auswaehlt
	 */
	private void initLanguage() {
		this.languageData = FAData.GERMAN;
		this.language = new JComboBox<String>(languages);
		this.language.setBorder(new LineBorder(Utility.DARK_GREEN) {
			
			private static final long serialVersionUID = 1L;

			@Override
		    public void paintBorder(java.awt.Component c, java.awt.Graphics g, int x, int y, 
		    	    int width, int height) {
		    	        g.drawRoundRect(x, y, width - 1, height - 1, 7, 7);
		    	    }
		});
		this.language.setVisible(true);
		this.language.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				if (language.getSelectedItem().equals(languages[0])) {
					currentLanguage = languages[0];
					languageData = FAData.GERMAN;
				} else if (language.getSelectedItem().equals(languages[1])) {
					currentLanguage = languages[1];
					languageData = FAData.ENGLISCH;
				}
				initFATable();
				bottom.updateKeyText(tables);
				gui.setTable(tables);
				gui.setTablePanel();
				tables[keyChar.getSelectedIndex()].shiftRight();
				tables[keyChar.getSelectedIndex()].shiftLeft();
			}

		});
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
		this.keyChar.setBorder(new LineBorder(Utility.DARK_GREEN) {
			
			private static final long serialVersionUID = 1L;

			@Override
		    public void paintBorder(java.awt.Component c, java.awt.Graphics g, int x, int y, 
		    	    int width, int height) {
		    	        g.drawRoundRect(x, y, width - 1, height - 1, 7, 7);
		    	    }
		});
		this.keyChar.setVisible(true);
		// bei Auswahl der aktuellen Option
		this.keyChar.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				String number = keyChar.getSelectedItem().toString();
				String digit = "";
				// maximaler String ist 15, d.h. maximale Laenge muss 2 sein
				for (int i = 0; i < Utility.KEY_LENGTH_DIGITS; i++) {
					if (number.charAt(i) != '.' && number.charAt(i) != ' ') {
						digit = digit + number.charAt(i);
					}
				}
				int letterIndex = Integer.parseInt(digit) - 1;
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
		this.left = new GradientButton("\u00ab");
		this.left.setMargin(new Insets(1, 10, 1, 10));
		this.left.setFont(new Font("Arial", Font.PLAIN, 14));
		this.left.addActionListener(e -> {
			this.tables[this.keyChar.getSelectedIndex()].shiftLeft();
			updateGraph(this.tables[this.keyChar.getSelectedIndex()].getGraph());
		});

		this.right = new GradientButton("\u00bb");
		this.right.setMargin(new Insets(1, 10, 1, 10));
		this.right.setFont(new Font("Arial", Font.PLAIN, 14));
		this.right.addActionListener(e -> {
			this.tables[this.keyChar.getSelectedIndex()].shiftRight();
			updateGraph(this.tables[this.keyChar.getSelectedIndex()].getGraph());
		});
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
		for (int i = keyLength - 1; i >= 0; i--) {
			this.tables[i] = new FATable(this.data[i], this.languageData, currentLanguage, max);
		}
	}

	/**
	 * Initialisiert die Menueleiste und fuegt den Menues ActionListener hinzu
	 */
	private void initFAMenuBar() {
		this.menu = new FAMenuBar();
		this.menu.initExplanationItem(2, new FAExplanationFrame());
		this.menu.fillRightSide();
		// initialisiert bei "Text neu laden" FATabel und TableData neu
		// und passt das Fenster an neuen Text an
		this.menu.getMenuBar().getMenu(1).addMouseListener(new MouseListener() {
			@Override
			public void mouseClicked(MouseEvent e) {
				initTableData();
				max = calcMax();
				initFATable();
				checkCheckbox();
				bottom.updateKeyText(tables);
				bottom.updateCryptoText();
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
		for (int i = keyLength - 1; i >= 0; i--) {
			float[] oneFrequencies = new float[allFrequencies[i].length];
			for (int j = 0; j < allFrequencies[i].length; j++) {
				oneFrequencies[j] = allFrequencies[i][j];
			}
			this.data[i] = new TableData(oneFrequencies);
		}
	}

	/**
	 * Hilfsmethode, die prueft, ob die durch die eingegebenen Parameter neu
	 * entstehende Schluessellaenge maximal 15 betraegt
	 * 
	 * @param offset Position, an der die neuen Zahlen eingefuegt werden
	 * @param str    neue eingegebene Zahlen
	 * @return Ob die neue Schluessellaenge valide ist
	 * @throws BadLocationException Ungueltige Position
	 */
	private boolean isLessThanSixteen(int offset, String str) throws BadLocationException {
		String newLength = "1";
		switch (offset) {
		case 0:
			newLength = str + this.lengthTextField.getText();
			break;
		case 1:
			if (this.length >= 1) {
				newLength = this.lengthTextField.getText(0, 1) + str + this.lengthTextField.getText(1, this.length - 1);
			} else {
				newLength = str;
			}
			break;
		// Checkstyle braucht default option
		default:
		}
		return (Integer.parseInt(newLength) < 16);
	}

	/**
	 * Aktualisiert den Graphen der GUI; statische Methode damit diese auch aus
	 * FATable aufgerufen werden kann ohne eine konkrete Instanz der GUI oder des
	 * FAControllers zu brauchen
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
				monoCheckBox, bottom.createBottomPanel());
	}

	/**
	 * Initialisiert FABottom
	 */
	private void initFABottom() {
		this.bottom = new FABottom(this.key, this.tables, this);
		switch (this.key.getSerialnumber()) {
		case 1:
			this.lengthTextField.setText(Integer.toString(length));
			break;
		case 2:
			this.monoCheckBox.setSelected(true);
			checkCheckbox();
			break;
		case 3:
			String temp = this.key.getKey();
			if (!temp.isEmpty()) {
				this.lengthTextField.setText(Integer.toString(temp.length()));
			}
			break;
		default:
			this.lengthTextField.setText(Integer.toString(length));
			break;
		}
		for (int i = 0; i < this.tables.length; i++) {
			this.tables[i].setBottom(this.bottom);
		}
		this.bottom.updateKeyText(tables);
	}

	/**
	 * Legt den focus auf die Haeufigkeitsanalyse
	 */
	public void focus() {
		gui.focus();
	}

	/**
	 * Schliesst den Frame der Haeufigkeitsanalyse
	 */
	public void disposeFrame() {
		gui.disposeFrame();
	}
}
