package utility;

import java.awt.Color;
import java.awt.Font;

/**
 * Hilfsklasse, die alle benutzten Konstanten zur Verfuegung stellt
 * 
 * @author Julian Singer
 * @version 1.2
 *
 */
public class Utility {
	
	public static final int ALPHABET_SIZE = 26;     // Groesse des Alphabets
	public static final int MAXIMUM_KEY_LENGTH = 15; // maximale Schluessellaenge
	public static final int KEY_LENGTH_DIGITS = 2;   // Schluessellaenge kann maximal dreistellig sein
	public static final int KEY_LENGTH_FOR_SINGLE_SHIFT = 1; // Schluessellaenge fuer Caesar und Monoalphabetisch
	
	public static final char FIRST_LETTER = 'A';    // erster Buchstabe des Alphabets
	
	public static final Color DARK_GREEN = new Color(75, 115, 14); // gruene Farbe fuer Graph und Farbverlauf
	public static final Color ORANGE = new Color(185, 137, 0); // orangene Farbe fuer Graph, Menue und Buttons
	public static final Color LIGHT_GREEN = new Color(191, 205, 169); // hellgruene Farbe fuer den Farbverlauf
	public static final Color WHITE = new Color(255, 255, 255); // weisse Farbe fuer die Texte bei Farbverlauf
	
	public static final Font FONT = new Font("Arial", Font.BOLD, 12); // Standard-Schriftzug fuer Menue und Buttons
	
}
