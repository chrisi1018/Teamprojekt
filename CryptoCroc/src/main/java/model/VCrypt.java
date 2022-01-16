package model;

/**
 * Die Klasse stellt die Viginére-Verschluesselung zur Verfuegung.
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
	 * Die Methode verschluesselt einen Text mit der Viginére-Verschluesselung indem mit einem Zaehler
	 * ueber das Schluesselwort gelaufen wird, und dann mit dem Buchstaben des Schluesselworts die
	 * Caesar-Verschluesselung angewandt wird.
	 * @param text Der mit der Viginére-Verschluesselung zu verschluesselde Text
	 * @param key der Verwendete Text
	 * @return der verschluesseltet Text
	 */
	@Override
	public String cryptAll(String text, String key) {
		String ret = "";
		int counter = 0; //Zaehlt ueber das Schluesselwort
		for (int i = 0; i < text.length(); i++) {
			char c = text.charAt(i);
			ret = ret + caesar.cryptChar(c, castKey(key.charAt(counter)));
			if (c >= 'A' && c <= 'z' && (c <= 'Z' || c >= 'a')) {
			//Counter Zaehlt nur weiter wenn auch ein Buchstabe Verschluesselt wird
				counter++;
			}
			if (counter == key.length()) { //ueberprueft ob das Schluesselwort abgelaufen wurde
				counter = 0;
			}
		}
		return ret;
	}
	
	/**
	 * Die Methode entschluesselt einen Text der mit Viginère-Verschluesselung verschluesselt wurden, indem
	 * mit einem Zaehler ueber das Schluesselwort gelaufen wird, und dann mit dem Buchstaben des Schluesselworts
	 * die Entschluesselung des Caesar-Verfahren angewandt wird.
	 */
	@Override
	public String decryptAll(String text, String key) {
		String ret = "";
		int counter = 0; //Zaehlt ueber das Schluesselwort
		for (int i = 0; i < text.length(); i++) {
			char c = text.charAt(i);
			ret = ret + caesar.decryptChar(c, castKey(key.charAt(counter)));
			if (c >= 'A' && c <= 'z' && (c <= 'Z' || c >= 'a')) {
				//Counter Zaehlt nur weiter wenn auch ein Buchstabe Verschluesselt wird
					counter++;
				}
			if (counter == key.length()) { //ueberprueft ob das Schluesselwort abgelaufen wurde
				counter = 0;
			}
		}
		return ret;
	}
	
	/**
	 * Die Methode ueberprueft ob der Schluessel das richtige Format hat, ein String aus arabischen Buchstaben
	 * @param key der Verwedete Schluessel
	 * @return Ein boolscher Wahrheitswert ob das Format des Schluessels passt
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
	 * Die Methode wandelt einen Charakter in einen Integer fuer die Verschluesselung um
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
