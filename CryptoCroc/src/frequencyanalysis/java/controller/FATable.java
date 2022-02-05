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
 * @author Julian Singer
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

	/**
	 * Konstruktor, der die Haeufigkeiten der Sprache und des Geheimtextes uebergibt
	 * 
	 * @param tableData Daten der Haeufigkeitsanalyse
	 * @param language  Haeufigkeit der Zeichen in spezieller Sprache
	 */
	public FATable(TableData tableData, float[] language) {
		this.data = tableData;
		this.data.initTextFieldChar();
		initTextFields();
		initTextLabels();
		createTablePanel();
		try {
			this.graph = new FAGraph(language, this.data.getFrequencyPercentage(), FAController.getCurrentLanguage(),
					FAController.getMax());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		this.language = language;
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
			this.textFields[i].setDocument(new LimitedTextfield(maxInput, i, this.textFields));
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
			this.textFields[i].setHorizontalAlignment(JTextField.CENTER);
			this.textFields[i].setText("" + (char) (i + firstLetter));
			this.textFields[i].setEnabled(false);
			this.textFields[i].setEditable(false);
		}
		this.tablePanel = tablePanel;
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
			this.textFields[i].setText(this.textFields[(i + 25) % alphabetSize].getText());
			this.data.setTextFieldChar(this.data.getTextFieldChar((i + 25) % alphabetSize), i);
		}
		this.textFields[0].setText(lastChar);
		this.data.setTextFieldChar(this.data.getTextFieldChar(lastChar.charAt(0)), 0);
		try {
			this.graph.setGraphPanel(new FAGraph(this.language, this.data.getForGraph(),
					FAController.getCurrentLanguage(), FAController.getMax()).getGraphPanel());
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
	}

	/**
	 * Schiebt den Inhalt der Textfelder in ihre linken Nachbarfelder und den Inhalt
	 * des ersten Textfeldes in das letzte Textfeld. Ausserdem verschieben sich die
	 * Balken der Geheimtextbuchstaben ihrer neuen Zuordnung entsprechend nach links
	 */
	public void shiftLeft() {
		String firstChar = this.textFields[0].getText();
		for (int i = 0; i < this.textFields.length - 1; i++) {
			this.textFields[i].setText(this.textFields[(i + 1) % alphabetSize].getText());
			this.data.setTextFieldChar(this.data.getTextFieldChar((i + 1) % alphabetSize), i);
		}
		this.textFields[this.textFields.length - 1].setText(firstChar);
		this.data.setTextFieldChar(this.data.getTextFieldChar(firstChar.charAt(0)), alphabetSize - 1);
		try {
			this.graph.setGraphPanel(new FAGraph(this.language, this.data.getForGraph(),
					FAController.getCurrentLanguage(), FAController.getMax()).getGraphPanel());
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
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
		this.textFields[newIndex].setText(String.valueOf(oldChar));
		this.data.setTextFieldChar(newChar, swapIndex);
		this.data.setTextFieldChar(oldChar, newIndex);
		try {
			this.graph.setGraphPanel(new FAGraph(this.language, this.data.getForGraph(),
					FAController.getCurrentLanguage(), FAController.getMax()).getGraphPanel());
		} catch (IOException e1) {
			e1.printStackTrace();
		}
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
	 * Aktiviert die Textfelder und macht sie editierbar
	 */
	public void enableTextFields() {
		for (int i = 0; i < this.textFields.length; i++) {
			this.textFields[i].setEnabled(true);
			this.textFields[i].setEditable(true);
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