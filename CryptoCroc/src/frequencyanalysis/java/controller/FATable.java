package controller;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.io.IOException;

import javax.swing.BoxLayout;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import model.TableData;

/**
 * Diese Klasse modelliert die Zuordnungstabelle der Buchstaben fuer die
 * Haeufigkeitsanalyse und ihr zugehoeriges Balkendiagramm
 * 
 * @author Julian Singer, zes
 * @version 1.0
 */
public class FATable {

	private final int alphabetSize = 26;

	private TableData data;
	private JTextField[] textFields = new JTextField[alphabetSize];
	private JLabel[] textLabels = new JLabel[alphabetSize];
	private JPanel tablePanel;
	private FAGraph graph;
	private float[] language;
	// fuer alle textfelder einen Textchangelistener; noetig um diese beim
	// vertauschen der buchstaben zu entfernen
	private TextChangeListener[] tcl = new TextChangeListener[alphabetSize];

	/**
	 * Konstruktor, der die Haeufigkeiten der Sprache und des Geheimtextes uebergibt
	 * 
	 * @param tableData Daten der Haeufigkeitsanalyse
	 * @param languageData  Haeufigkeit der Zeichen in spezieller Sprache
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
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		this.language = languageData;
	}

	/**
	 * Erzeugt das TablePanel fuer die Zuordnung der Buchstaben und gibt dieses
	 * zurueck
	 * 
	 */
	public void createTablePanel() {
		int maxInput = 1;
		FlowLayout fLayout = new FlowLayout();
		fLayout.setVgap(25);
		JPanel tablePanel = new JPanel(fLayout);
		for (int i = 0; i < alphabetSize; i++) {
			// erstellt JPanel mit JLabel als Titel
			JPanel title = new JPanel();
			JLabel letter = this.textLabels[i];
			title.setLayout(new BoxLayout(title, BoxLayout.PAGE_AXIS));
			letter.setAlignmentX(Component.CENTER_ALIGNMENT);
			this.textLabels[i].setFont(new Font(Font.DIALOG, Font.BOLD, 19));
			title.add(letter, BorderLayout.SOUTH);

			// initialisiert Eintraege der Textfelder und setzt den Textstil
			this.textFields[i].setFont(new Font(Font.DIALOG, Font.BOLD, 15));
			this.textFields[i].setColumns(1);
			this.textFields[i].setPreferredSize(new Dimension(19, 30));
			// DIESER AUFRUF LOESCHT DEN AKTUELLEN INHALT DER TEXTFELDER (liegt am setzen
			// des Documents):
			this.textFields[i].setDocument(new LimitedTextfield(maxInput));
			// markiert beim Klicken den Text im Textfeld
			int j = i;
			this.textFields[i].addFocusListener(new FocusListener() {
				@Override
				public void focusGained(FocusEvent e) {
					textFields[j].selectAll();
				}

				@Override
				public void focusLost(FocusEvent e) {
					// tue nichts
				}
			});

			// fuegt alles in einem JPanel zusammen
			JPanel letterPanel = new JPanel();
			letterPanel.setLayout(new BoxLayout(letterPanel, BoxLayout.PAGE_AXIS));
			letterPanel.add(this.textLabels[i]);
			letterPanel.add(this.textFields[i]);
			letterPanel.setPreferredSize(new Dimension(30, 50));
			tablePanel.add(letterPanel);
		}

		// wegen setDocument-Aufruf nochmals initialisieren sodass text angezeigt wird
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
	 * Entfernt den Document listener an einer gegebenen Stelle um zu verhindern
	 * dass dieser getriggert wird
	 * 
	 * @param loc die Stelle des Textfields an dem der Listener entfernt werden muss
	 */
	private void disableListener(int loc) {
		this.textFields[loc].getDocument().removeDocumentListener(tcl[loc]);
	}

	/**
	 * Fuegt den Documentlistener an einer gegebenen Stelle wieder ein
	 * 
	 * @param loc die Stelle des Textfields an dem der Listener zugefuegt wird
	 */
	private void enableListener(int loc) {
		this.textFields[loc].getDocument().addDocumentListener(tcl[loc]);
	}

	/**
	 * Schiebt den Inhalt der Textfelder in ihre rechten Nachbarfelder und den
	 * Inhalt des letzten Textfeldes in das erste Textfeld. Ausserdem verschieben
	 * sich die Balken der Geheimtextbuchstaben ihrer neuen Zuordnung entsprechend
	 * nach rechts
	 */
	public void shiftRight() {
		String lastChar = this.textFields[25].getText();
		for (int i = this.textFields.length - 1; i < 0; i--) {
			// alle listener entfernen, da diese sonst beim setzen des texts getriggert
			// werden
			this.disableListener(i);
			this.textFields[i].setText(this.textFields[(i + 25) % alphabetSize].getText());
			this.data.setTextFieldChar(this.data.getTextFieldChar((i + 25) % alphabetSize), i);
		}
		this.textFields[0].setText(lastChar);
		this.data.setTextFieldChar(this.data.getTextFieldChar(lastChar.charAt(0)), 0);
		try {
			this.graph = new FAGraph(this.language, this.data.getForGraph(), FAController.getCurrentLanguage(),
					FAController.getMax());
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}

		// alle listener wieder aktiviern
		for (int i = 0; i < textFields.length; i++) {
			this.enableListener(i);
		}
		FAController.updateGraph(this.graph);
	}

	/**
	 * Schiebt den Inhalt der Textfelder in ihre linken Nachbarfelder und den Inhalt
	 * des ersten Textfeldes in das letzte Textfeld. Ausserdem verschieben sich die
	 * Balken der Geheimtextbuchstaben ihrer neuen Zuordnung entsprechend nach links
	 */
	public void shiftLeft() {
		String firstChar = this.textFields[0].getText();
		for (int i = 0; i < this.textFields.length - 1; i++) {
			// auch hier wie bei shiftLeft alle listener deaktivieren
			this.disableListener(i);
			this.textFields[i].setText(this.textFields[(i + 1) % alphabetSize].getText());
			this.data.setTextFieldChar(this.data.getTextFieldChar((i + 1) % alphabetSize), i);
		}
		this.textFields[this.textFields.length - 1].setText(firstChar);
		this.data.setTextFieldChar(this.data.getTextFieldChar(firstChar.charAt(0)), alphabetSize - 1);
		try {
			this.graph = new FAGraph(this.language, this.data.getForGraph(), FAController.getCurrentLanguage(),
					FAController.getMax());
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}

		// listener wieder aktiviern
		for (int i = 0; i < textFields.length; i++) {
			this.enableListener(i);
		}
		FAController.updateGraph(this.graph);
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
		// listener entfernen, sonst triggerd das aendern des texts auch den listener
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

		// listener wieder hinzufuegen und aenderung des Graphen aufrufen
		this.textFields[newIndex].getDocument().addDocumentListener(tcl[newIndex]);
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
	 * @param enable true falls die Textfelder aktiviert werden soll, false falls
	 *               sie deaktiviert werden soll
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
}
