package utility;

import java.awt.Color;
import java.awt.Font;

import javax.swing.BorderFactory;
import javax.swing.border.Border;

/**
 * Hilfsklasse, die alle benutzten Konstanten zur Verfuegung stellt
 * 
 * @author Julian Singer, chrisi, zes
 * @version 1.3
 *
 */
public final class Utility {
	/** Groesse des Alphabets */
	public static final int ALPHABET_SIZE = 26;
	/** maximale Schluessellaenge */
	public static final int MAXIMUM_KEY_LENGTH = 15;
	/** Schluessellaenge kann maximal zweistellig sein */
	public static final int KEY_LENGTH_DIGITS = 2;
	/** Schluessellaenge fuer Caesar und Monoalphabetisch */
	public static final int KEY_LENGTH_FOR_SINGLE_SHIFT = 1;

	/** erster Buchstabe des Alphabets */
	public static final char FIRST_LETTER = 'A';

	/** gruene Farbe fuer Graph und Farbverlauf */
	public static final Color DARK_GREEN = new Color(75, 115, 14);
	/** orangene Farbe fuer Graph, Menue und Buttons */
	public static final Color ORANGE = new Color(185, 137, 0);
	/** hellgruene Farbe fuer den Farbverlauf */
	public static final Color LIGHT_GREEN = new Color(191, 205, 169);
	/** weisse Farbe fuer die Texte bei Farbverlauf */
	public static final Color WHITE = new Color(255, 255, 255);

	/** Standard-Schriftzug fuer die Buttons */
	public static final Font FONT = new Font("Arial", Font.BOLD, 14);
	/** Standard-Schriftzug fuer das Menue */
	public static final Font MENU_FONT = new Font("Arial", Font.BOLD, 14);
	/** Standard-Schriftzug fuer Text in Textfeld */
	public static final Font TEXT_FONT = new Font("Arial", Font.PLAIN, 14);
	/** Standard-Schriftzug fuer Ueberschriften Labels */
	public static final Font HEADLINE_LABEL_FONT = new Font("Arial", Font.BOLD, 20);
	/** Standard-Schriftzug fuer Labels */
	public static final Font LABEL_FONT = new Font("Arial", Font.BOLD, 16);

	/** Standard-Umrandung fuer die Textfelder */
	public static final Border TEXTFIELD_BORDER = BorderFactory.createMatteBorder(1, 1, 1, 1, Utility.DARK_GREEN);
	/** Umrandung ausgewaeltes Textfeld */
	public static final Border FOCUS_TEXTFIELD_BORDER = BorderFactory.createMatteBorder(1, 1, 1, 1, Utility.ORANGE);

	/** Maximale Anzahl wie oft Fehlermeldungen angezeigt werden */
	public static final int MAX_ERROR_MESSAGES = 4;

	// privater Konstruktor
	private Utility() {

	}
}
