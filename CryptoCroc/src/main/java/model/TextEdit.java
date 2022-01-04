package model;

/**
 * Eine Hilfklasse die Methoden zur Bearbeitung des Tesxtes zur verfuegung stellt.
 * @author Julian
 * @version 1.0
 */
public final class TextEdit {

	/**
	 * Privater Kunstrukto fuer Hilfsklasse
	 */
	private TextEdit() { }
	
	/**
	 * Eine Methode die folgende Satzzeichen aendert: � --> ae, � --> Ae, � --> oe, � --> Oe,
	 * � --> ue, � --> Ue, � --> ss.
	 * @param c das zu ueberpruefende Satzzeichen.
	 * @return die Ausgabe als String.
	 */
	public static String changeUmlauts(char c) {
		int i = c;
		switch(i) {
		case 196: //�(Ae)
			return "Ae";
		case 214: //�(Oe)
			return "Oe";
		case 220: //�(Ue)
			return "Ue";
		case 223: //�(ss)
			return "ss";
		case 228: //�(ae)
			return "ae";
		case 246: //�(oe)
			return "oe";
		case 252: //�(ue)
			return "ue";
		default:
			return "" + c;
		}
	}
	/**
	 * Eine Methode die Kleinbuchstaben in Grossbuchstaben umwandelt
	 * @param c der Buchstabe der Umgewandelt werden soll
	 * @return der Umgewandelte Buchstabe
	 */
	public static char changeToCapitalLetter(char c) {
		char ret = c;
		if (c >= 'a' && c <= 'z') {
			ret = (char) (c - ('a' - 'A'));
			return ret;
		} else {
			int i = c;
			switch(i) {
			case 228: //�(ae)
				return '\u00c4';
			case 246: //�(oe)
				return '\u00d6';
			case 252: //�(ue)
				return '\u00dc';
			default:
				return ret;
			}
		}
	}
	
	/**
	 * Eine Methode die in einem String alle Buchstaben gross schreibt
	 * @param text der String der umgewandelt werden soll
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
	 * Eine HilfMethode die sowohl Umlaute umwandel, als auch alle Buchstaben in Grossbuchstaben umwandelt.
	 * @param text der Text der umgewandelt werden soll.
	 * @return der Umgewandelte Text.
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
