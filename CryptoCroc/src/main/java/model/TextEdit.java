package model;

/**
 * Eine Hilfklasse die Methoden zur Bearbeitung des Tesxtes zur verfügung stellt.
 * @author Julian
 * @version 1.0
 */
public final class TextEdit {

	/**
	 * Privater Kunstrukto fuer Hilfsklasse
	 */
	private TextEdit() { }
	
	/**
	 * Eine Methode die folgende Satzzeichen ändert: ä --> ae, Ä --> Ae, ö --> oe, Ö --> Oe,
	 * ü --> ue, Ü --> Ue, ß --> ss.
	 * @param c das zu überprüfende Satzzeichen.
	 * @return die Ausgabe als String.
	 */
	public static String changeUmlauts(char c) {
		int i = c;
		switch(i) {
		case 196: //Ä(Ae)
			return "Ae";
		case 214: //Ö(Oe)
			return "Oe";
		case 220: //Ü(Ue)
			return "Ue";
		case 223: //ß(ss)
			return "ss";
		case 228: //ä(ae)
			return "ae";
		case 246: //ö(oe)
			return "oe";
		case 252: //ü(ue)
			return "ue";
		default:
			return "" + c;
		}
	}
	/**
	 * Eine Methode die Kleinbuchstaben in Großbuchstaben umwandelt
	 * @param c der Buchstabe der Umgewandelt werden soll
	 * @return der Umgewandelte Buchstabe
	 */
	public static char changeToCapitalLetter(char c) {
		char ret = c;
		if (c >= 'a' && c <= 'z') {
			ret = (char) (c - ('a' - 'A'));
		}
		return ret;
	}
	
	/**
	 * Eine Methode die in einem String alle BUchstaben Großschreibt
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
	 * Eine HilfMethode die sowohl Umlaute umwandel, als auch alle Buchstaben in Großbuchstaben umwandelt.
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
