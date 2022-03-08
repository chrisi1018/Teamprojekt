package model;

/**
 * Eine Hilfsklasse, die Methoden zur Bearbeitung des Textes zur Verfuegung
 * stellt.
 * 
 * @author Julian
 * @version 1.0
 */
public final class TextEdit {

	/**
	 * Privater Konstruktor fuer die Hilfsklasse
	 */
	private TextEdit() {
	}

	/**
	 * Eine Methode, die folgende Satzzeichen aendert: \u00e4 --> ae, \u00c4 --> Ae,
	 * \u00f6 --> oe, \u00d6 --> Oe, \u00fc --> ue, \u00dc --> Ue, \u00df --> ss.
	 * 
	 * @param c das zu ueberpruefende Satzzeichen.
	 * @return die Ausgabe als String.
	 */
	public static String changeUmlauts(char c) {
		int i = c;
		switch (i) {
		case 196: // (Ae)
			return "Ae";
		case 214: // (Oe)
			return "Oe";
		case 220: // (Ue)
			return "Ue";
		case 223: // (ss)
			return "ss";
		case 228: // (ae)
			return "ae";
		case 246: // (oe)
			return "oe";
		case 252: // (ue)
			return "ue";
		default:
			return "" + c;
		}
	}

	/**
	 * Eine Methode, die KleinBuchstaben in GrossBuchstaben umwandelt
	 * 
	 * @param c der Buchstabe, der umgewandelt werden soll
	 * @return der umgewandelte Buchstabe
	 */
	public static char changeToCapitalLetter(char c) {
		char ret = c;
		if (c >= 'a' && c <= 'z') {
			ret = (char) (c - ('a' - 'A'));
			return ret;
		} else {
			int i = c;
			switch (i) {
			case 228: // (ae)
				return '\u00c4';
			case 246: // (oe)
				return '\u00d6';
			case 252: // (ue)
				return '\u00dc';
			default:
				return ret;
			}
		}
	}

	/**
	 * Eine Methode, die in einem String alle Buchstaben gross schreibt
	 * 
	 * @param text der String, der umgewandelt werden soll
	 * @return Der umgewandelte String
	 */
	public static String changeToCapitalLetter(String text) {
		String ret = "";
		if (text.isEmpty()) {
			return ret;
		}
		for (int i = 0; i < text.length(); i++) {
			ret = ret + changeToCapitalLetter(text.charAt(i));
		}
		return ret;
	}

	/**
	 * Eine HilfsMethode, die sowohl Umlaute umwandelt, als auch alle Buchstaben in
	 * GrossBuchstaben umwandelt.
	 * 
	 * @param text der Text, der umgewandelt werden soll.
	 * @return der umgewandelte Text.
	 */
	public static String editText(String text) {
		String ret = "";
		if (text.isEmpty()) {
			return ret;
		}
		for (int i = 0; i < text.length(); i++) {
			ret = ret + changeToCapitalLetter(changeUmlauts(text.charAt(i)));
		}
		return ret;
	}
}
