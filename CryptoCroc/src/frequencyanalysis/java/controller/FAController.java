package controller;

import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JButton;
import view.FAGui;
import view.Messages;
import model.FAData;
import model.TableData;
import javax.swing.text.AttributeSet;
import javax.swing.text.BadLocationException;
import javax.swing.text.PlainDocument;

/**
 * Die Klasse stellt den Hauptcontroller der Haufigkeitsanalyse dar
 * 
 * @author Julian Sturm
 * @version 1.0
 */
public class FAController {
	
	private KeyPanel key;
	private JLabel title;
	private JLabel lengthLabel;
	private JTextField lengthTextField;
	private JCheckBox monoCheckBox;
	private JComboBox<String> language;
	private float[] languageData = new float[26];//26 fuer die Groesse des Alphabets
	private JComboBox<String> keyChar;
	private JButton left;
	private JButton right;
	private FATable[] tables;
	private FAMenuBar menu;
	private TableData data;
	private FAGui gui;
	
	/**
	 * Der Konstruktor für die Klasse FaControlle siehe init-Methoden für mehr info
	 * 
	 * @param key das keyPanel, entweder V, M oder C, des Main-Frames
	 */
	FAController(KeyPanel key) {
		this.key = key;
		initFAGui();
		initTitle();
		initLength();
		initMonoCheckBox();
		initLanguage();
		initKeyChar(1);
		initLeftRight();
		initFAAlgo();
		initFATable();
		initFAMenuBar();
		initTableData();
	}
	
	/**
	 * Erstellt den Titel "Haufigkeitsanalys"
	 * TODO wird vielleicht nicht gebraucht
	 */
	private void initTitle() {
		this.title = new JLabel("H\u00e4ufigkeitsanalyse");
		this.title.setVisible(true);
	}
	
	/**
	 * Erstell das JLabel fuer die Laenge, erstell das JTextFeld fuer die Laenge, es koennen nur Zahlen eingegebn
	 * werden.
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
			public void insertString(int offset, String str, AttributeSet att) throws BadLocationException  {
				boolean insert = true;
				boolean leadingZero = false;
				//Ueberprueft das nur Zahlen eingegeben werden
				for (int i = 0; i < str.length(); i++) {
					if (str.charAt(0) < '0' || str.charAt(i) > '9') {
						insert = false;
					}
				}
				//Ueberprüeft auf eine fuehrende Null
				if (offset == 0) {
					if (str.charAt(0) == '0') {
						leadingZero = true;
					}
				}
				if (insert && !leadingZero) {
					
					if (this.getLength() + str.length() <= limit) {
						super.insertString(offset, str, att);
					} else {
						Messages.errorMessage("Die L\u00e4nge des Schl\u00fcssel darf 999 nicht \u00fcberschreiten!");
					}
				}
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
	}
	
	/**
	 * Erstellt das DropDownMenue mit dem Man die Sprache auswaehlt
	 */
	private void initLanguage() {
		String[] languages = {"Deutsch"};
		this.languageData = FAData.GERMAN;
		this.language = new JComboBox<String>(languages);
		this.language.setVisible(true);
	}
	
	/**
	 * Erstellt das DropDownMenue für die Auswahl des Buchstabens
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
		
	}
	
	/**
	 * Erstellt die Buttons "left" und "right"
	 */
	private void initLeftRight() { 
		this.left = new JButton("<");
		this.left.setVisible(true);
		this.right = new JButton(">");
		this.right.setVisible(true);
		
	}
	
	/**
	 * Dummy- Methode
	 */
	private void initFAAlgo() { }
	
	/**
	 * Dummy Methode
	 */
	private void initFATable() { }
	
	/**
	 * Dummy Methode
	 */
	private void initFAMenuBar() { }
	
	/**
	 * Dummy Methode
	 */
	private void initTableData() { }
	/**
	 * TODO muss noch ausgearbeitet werden, rudimentärnur zum Testen
	 */
	private void initFAGui() {
		this.gui = new FAGui();
	}
}
