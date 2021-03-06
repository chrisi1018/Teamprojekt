package controller;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.io.IOException;

import javax.swing.BoxLayout;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import model.TableData;
import utility.Utility;

/**
 * Diese Klasse modelliert die Zuordnungstabelle der Buchstaben fuer die
 * Haeufigkeitsanalyse und ihr zugehoeriges Balkendiagramm
 * 
 * @author Julian Singer, zes, Julian Sturm
 * @version 1.2
 */
public class FATable {

	private TableData data;
	private JTextField[] textFields = new JTextField[Utility.ALPHABET_SIZE];
	private JLabel[] textLabels = new JLabel[Utility.ALPHABET_SIZE];
	private JPanel tablePanel;
	private FAGraph graph;
	private float[] language;
	// fuer alle Textfelder einen TextChangeListener; noetig, um diese beim
	// Vertauschen der Buchstaben zu entfernen

	private FABottom bottom;
	private TextChangeListener[] tcl = new TextChangeListener[Utility.ALPHABET_SIZE];

	/**
	 * Konstruktor, der die Haeufigkeiten der Sprache und des Geheimtextes uebergibt
	 * 
	 * @param tableData    Daten der Haeufigkeitsanalyse
	 * @param languageData Haeufigkeit der Zeichen in spezieller Sprache
	 * @param language     die aktuelle Sprache
	 * @param max          der groesste Haeufigkeitsbalken; wird fuer die Erstellung
	 *                     der Graphen gebraucht
	 */
	public FATable(TableData tableData, float[] languageData, String language, int max) {
		this.data = tableData;
		this.data.initTextFieldChar();
		initTextFields();
		initTextLabels();
		createTablePanel();
		try {
			this.graph = new FAGraph(languageData, this.data.getFrequencyPercentage(), language, max);
		} catch (IOException e) {
			e.printStackTrace();
		}
		this.language = languageData;
	}

	/**
	 * Erzeugt das TablePanel fuer die Zuordnung der Buchstaben und gibt dieses
	 * zurueck
	 */
	public void createTablePanel() {
		int maxInput = 1;
		FlowLayout fLayout = new FlowLayout();
		JPanel tablePanel = new JPanel(fLayout);
		for (int i = 0; i < Utility.ALPHABET_SIZE; i++) {
			// erstellt JPanel mit JLabel als Titel
			JPanel title = new JPanel();
			JLabel letter = this.textLabels[i];
			title.setLayout(new BoxLayout(title, BoxLayout.PAGE_AXIS));
			letter.setAlignmentX(Component.CENTER_ALIGNMENT);
			this.textLabels[i].setFont(Utility.LABEL_FONT);
			this.textLabels[i].setForeground(Utility.DARK_GREEN);
			title.add(letter, BorderLayout.SOUTH);

			// initialisiert Eintraege der Textfelder und setzt den Textstil
			this.textFields[i].setFont(Utility.TEXT_FONT);
			this.textFields[i].setBorder(Utility.TEXTFIELD_BORDER);
			this.textFields[i].setHorizontalAlignment(JTextField.CENTER);
			// dieser Aufruf loescht den aktuellen Inhalt der Textfelder (liegt am Setzen
			// des Documents) :
			this.textFields[i].setDocument(new LimitedTextfield(maxInput));
			// markiert beim Klicken den Text im Textfeld
			int j = i;
			this.textFields[i].addFocusListener(new FocusListener() {
				@Override
				public void focusGained(FocusEvent e) {
					textFields[j].selectAll();
					textFields[j].setBorder(Utility.FOCUS_TEXTFIELD_BORDER);
				}

				@Override
				public void focusLost(FocusEvent e) {
					textFields[j].setBorder(Utility.TEXTFIELD_BORDER);
				}
			});

			// fuegt alles in einem JPanel zusammen
			JPanel letterPanel = new JPanel();
			letterPanel.setLayout(new BoxLayout(letterPanel, BoxLayout.PAGE_AXIS));
			letterPanel.add(this.textLabels[i]);
			letterPanel.add(this.textFields[i]);
			this.textFields[i].setAlignmentX(Component.CENTER_ALIGNMENT);
			// Setzt die Breite und die Hoehe des Textfelds
			this.textFields[i].setMaximumSize(
					new Dimension(Utility.WIDTH_TEXTFIELD_ONE_LETTER_IN_FA, Utility.HEIGHT_TEXTFIELD_IN_FA));
			// Wird benoetigt, um die Hoehe zu setzen
			this.textFields[i].setPreferredSize(
					new Dimension(Utility.WIDTH_TEXTFIELD_ONE_LETTER_IN_FA, Utility.HEIGHT_TEXTFIELD_IN_FA));
			tablePanel.add(letterPanel);
		}

		// wegen setDocument-Aufruf nochmals initialisieren, sodass der Text angezeigt
		// wird
		char firstLetter = 'A';
		for (int i = 0; i < this.textFields.length; i++) {
			this.textFields[i].setText("" + (char) (i + firstLetter));
			this.textFields[i].setEnabled(false);
			this.textFields[i].setEditable(false);
			tcl[i] = new TextChangeListener(this, i);
			this.textFields[i].getDocument().addDocumentListener(tcl[i]);
		}
		this.tablePanel = tablePanel;
	}

	/**
	 * Entfernt den DocumentListener an einer gegebenen Stelle, um zu verhindern,
	 * dass dieser getriggert wird
	 * 
	 * @param loc die Stelle des Textfeldes, an dem der Listener entfernt werden
	 *            muss
	 */
	public void disableListener(int loc) {
		this.textFields[loc].getDocument().removeDocumentListener(tcl[loc]);
	}

	/**
	 * Fuegt den DocumentListener an einer gegebenen Stelle wieder ein
	 * 
	 * @param loc die Stelle des Textfeldes, an dem der Listener zugefuegt wird
	 */
	public void enableListener(int loc) {
		this.textFields[loc].getDocument().addDocumentListener(tcl[loc]);
	}

	/**
	 * Schiebt den Inhalt der Textfelder in ihre rechten Nachbarfelder und den
	 * Inhalt des letzten Textfeldes in das erste Textfeld. Ausserdem verschieben
	 * sich die Balken der Geheimtextbuchstaben ihrer neuen Zuordnung entsprechend
	 * nach rechts
	 */
	public void shiftRight() {
		for (int i = 0; i < this.textFields.length; i++) {
			// alle Listener werden entfernt, da diese sonst beim Setzen des Textes
			// getriggert werden
			this.disableListener(i);
			if (i < Utility.ALPHABET_SIZE - 1) {
				this.textFields[i + 1].setText(Character.toString(this.data.getTextFieldChar(i)));
			} else {
				this.textFields[0].setText(Character.toString(this.data.getTextFieldChar(i)));
			}
		}

		// alle Listener werden wieder aktiviert und die Textfelder in data werden
		// gesetzt
		for (int i = 0; i < textFields.length; i++) {
			this.data.setTextFieldChar(this.textFields[i].getText().charAt(0), i);
			this.enableListener(i);
		}

		try {
			this.graph = new FAGraph(this.language, this.data.getForGraph(), FAController.getCurrentLanguage(),
					FAController.getMax());
		} catch (IOException e1) {
			e1.printStackTrace();
		}

		bottom.updateCryptoText();
	}

	/**
	 * Schiebt den Inhalt der Textfelder in ihre linken Nachbarfelder und den Inhalt
	 * des ersten Textfeldes in das letzte Textfeld. Ausserdem verschieben sich die
	 * Balken der Geheimtextbuchstaben ihrer neuen Zuordnung entsprechend nach links
	 */
	public void shiftLeft() {
		for (int i = 0; i < this.textFields.length; i++) {
			// alle Listener werden entfernt, da diese sonst beim Setzen des Textes
			// getriggert werden
			this.disableListener(i);
			if (i < Utility.ALPHABET_SIZE - 1) {
				this.textFields[i].setText(Character.toString(this.data.getTextFieldChar(i + 1)));
			} else {
				this.textFields[i].setText(Character.toString(this.data.getTextFieldChar(0)));
			}
		}

		// alle Listener werden wieder aktiviert und die Textfelder in data werden
		// gesetzt
		for (int i = 0; i < textFields.length; i++) {
			this.data.setTextFieldChar(this.textFields[i].getText().charAt(0), i);
			this.enableListener(i);
		}

		try {
			this.graph = new FAGraph(this.language, this.data.getForGraph(), FAController.getCurrentLanguage(),
					FAController.getMax());
		} catch (IOException e1) {
			e1.printStackTrace();
		}

		bottom.updateCryptoText();
	}

	/**
	 * Setzt den vorigen/alten Character des Textfeldes an der uebergebenen Stelle
	 * in das Textfeld, in dem der neue Character zuvor allein stand
	 * 
	 * @param swapIndex Index des Textfeldes, in dem der alte Character durch einen
	 *                  neuen ersetzt wurde
	 */
	public void swapChar(int swapIndex) {
		char oldChar = this.data.getTextFieldChar(swapIndex);
		char newChar = this.textFields[swapIndex].getText().charAt(0);
		int newIndex = 0;
		for (int i = 0; i < this.textFields.length; i++) {

			if (this.data.getTextFieldChar(i) == newChar) {
				newIndex = i;
			}
		}
		// Listener werden entfernt, sonst triggert das Aendern des Textes auch den
		// Listener
		this.textFields[newIndex].getDocument().removeDocumentListener(tcl[newIndex]);

		this.textFields[newIndex].setText(String.valueOf(oldChar));
		this.data.setTextFieldChar(newChar, swapIndex);
		this.data.setTextFieldChar(oldChar, newIndex);
		try {
			this.graph = new FAGraph(this.language, this.data.getForGraph(), FAController.getCurrentLanguage(),
					FAController.getMax());
		} catch (IOException e1) {
			e1.printStackTrace();
		}

		// Listener werden wieder hinzugefuegt und Aenderung des Graphen wird aufgerufen
		this.textFields[newIndex].getDocument().addDocumentListener(tcl[newIndex]);
		bottom.updateCryptoText();
		FAController.updateGraph(this.graph);
	}

	/**
	 * Initialisiert die 26 nicht-editierbaren, grauen Textfelder und setzt in diese
	 * die Buchstaben von 'A' bis 'Z'
	 */
	private void initTextFields() {
		char firstLetter = 'A';
		for (int i = 0; i < this.textFields.length; i++) {
			this.textFields[i] = new JTextField();
			this.textFields[i].setText("" + (char) (i + firstLetter));
			this.textFields[i].setEnabled(false);
			this.textFields[i].setEditable(false);
		}
	}

	/**
	 * Initialisiert die 26 Labels und setzt in diese die Buchstaben von 'A' bis 'Z'
	 */
	private void initTextLabels() {
		char firstLetter = 'A';
		for (int i = 0; i < this.textLabels.length; i++) {
			this.textLabels[i] = new JLabel("" + (char) (i + firstLetter), JLabel.CENTER);
		}
	}

	/**
	 * Veraendert die Aktivierung der Textfelder und die Editierbarkeit
	 * 
	 * @param enable true, falls die Textfelder aktiviert werden sollen und false,
	 *               falls sie deaktiviert werden sollen
	 */
	public void enableTextFields(boolean enable) {
		for (int i = 0; i < this.textFields.length; i++) {
			this.textFields[i].setEnabled(enable);
			this.textFields[i].setEditable(enable);
		}
	}

	/**
	 * Getter fuer den zugehoerigen Graphen
	 * 
	 * @return aktueller Graph
	 */
	public FAGraph getGraph() {
		return this.graph;
	}

	/**
	 * Getter fuer das zugehoerige TablePanel
	 * 
	 * @return aktuelles TablePanel
	 */
	public JPanel getTablePanel() {
		return this.tablePanel;
	}

	/**
	 * Getter fuer textFields
	 * 
	 * @return gibt die TextFelder zurueck
	 */
	public JTextField[] getTextFields() {
		return this.textFields;
	}

	/**
	 * Setter Methode fuer die textFields; updated auch FAData
	 * 
	 * @param textFields die TextFelder
	 */
	public void setTextFields(JTextField[] textFields) {
		this.textFields = textFields;
		for (int i = 0; i < this.textFields.length; i++) {
			this.data.setTextFieldChar(this.textFields[i].getText().charAt(0), i);
		}
	}

	/**
	 * Setter Methode fuer bottom
	 * 
	 * @param bottom Ein Object FABottom
	 */
	public void setBottom(FABottom bottom) {
		this.bottom = bottom;
	}
}
