package utility;

import java.awt.Color;

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
	
	public static final Color FA_GREEN = new Color(74, 115, 14); // gruene Farbe fuer den Graph
	public static final Color FA_ORANGE = new Color(185, 137, 0); // orangene Farbe fuer den Graph
	
}
