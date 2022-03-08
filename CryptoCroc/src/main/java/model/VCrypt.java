package model;

/**
 * Die Klasse stellt die Vigenere-Verschluesselung zur Verfuegung.
 * 
 * @author Julian
 * @version 1.0
 */
public class VCrypt extends Crypt {

	/**
	 * Die Caesar-Verschluesselung wird verwendet
	 */
	CCrypt caesar = new CCrypt();

	/**
	 * Die Methode verschluesselt einen Text mit der Vigenere-Verschluesselung, indem
	 * mit einem Zaehler ueber das SchluesselWort gelaufen und dann mit dem
	 * Buchstaben des SchluesselWortes die Caesar-Verschluesselung angewendet wird.
	 * 
	 * @param text Der mit der Vigenere-Verschluesselung zu verschluesselnde Text
	 * @param key  der verwendete Text
	 * @return der verschluesselte Text
	 */
	@Override
	public String cryptAll(String text, String key) {
		String ret = "";
		int counter = 0; // Zaehlt ueber das SchluesselWort
		for (int i = 0; i < text.length(); i++) {
			char c = text.charAt(i);
			ret = ret + caesar.cryptChar(c, castKey(key.charAt(counter)));
			if (c >= 'A' && c <= 'z' && (c <= 'Z' || c >= 'a')) {
				// Counter zaehlt nur weiter, wenn auch ein Buchstabe verschluesselt wird
				counter++;
			}
			if (counter == key.length()) { // ueberprueft, ob das SchluesselWort abgelaufen wurde
				counter = 0;
			}
		}
		return ret;
	}

	/**
	 * Die Methode entschluesselt einen Text, der mit Vigenere-Verschluesselung
	 * verschluesselt wurden, indem mit einem Zaehler ueber das SchluesselWort
	 * gelaufen und dann mit dem Buchstaben des SchluesselWortes die
	 * Entschluesselung des Caesar-Verfahren angewendet wird.
	 */
	@Override
	public String decryptAll(String text, String key) {
		String ret = "";
		int counter = 0; // Zaehlt ueber das SchluesselWort
		for (int i = 0; i < text.length(); i++) {
			char c = text.charAt(i);
			ret = ret + caesar.decryptChar(c, castKey(key.charAt(counter)));
			if (c >= 'A' && c <= 'z' && (c <= 'Z' || c >= 'a')) {
				// Counter zaehlt nur weiter, wenn auch ein Buchstabe verschluesselt wird
				counter++;
			}
			if (counter == key.length()) { // ueberprueft, ob das SchluesselWort abgelaufen wurde
				counter = 0;
			}
		}
		return ret;
	}

	/**
	 * Die Methode ueberprueft, ob der Schluessel das richtige Format hat, ein String
	 * aus arabischen Buchstaben
	 * 
	 * @param key der verwendete Schluessel
	 * @return Ein boolescher Wahrheitswert, ob das Format des Schluessels passt
	 */
	public boolean checkKey(String key) {
		if (key.equals("")) {
			return false;
		}
		for (int i = 0; i < key.length(); i++) {
			char c = key.charAt(i);
			if (c < 'A' || c > 'z') {
				return false;
			}
			if (c > 'Z' && c < 'a') {
				return false;
			}
		}
		return true;
	}

	/**
	 * Die Methode wandelt einen Charakter in einen Integer fuer die
	 * Verschluesselung um
	 * 
	 * @param c
	 * @return
	 */
	private int castKey(char c) {
		if (c >= 'a' && c <= 'z') {
			return c - 'a';
		}
		if (c >= 'A' && c <= 'Z') {
			return c - 'A';
		}
		return 0;
	}

}
