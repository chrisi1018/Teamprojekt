package model;

/**
 * Die Klasse stellt die Viginére-Verschlüsselung zur Verfügung.
 * 
 * @author Julian
 * @version 1.0
 */
public class VCrypt extends Crypt {
	
	/**
	 * Die Cäsar-Verschlüsselung wird verwendet
	 */
	CCrypt caesar = new CCrypt();
	
	/**
	 * Die Methode verschlüsselt einen Text mit der Viginére-Verschlüsselung indem mit einem Zähler
	 * über das Schlüsselwort gelaufen wird, und dann mit dem Buchstaben des Schlüsselworts die
	 * Cäsar-Verschlüsselung angewandt wird.
	 * @param text Der mit der Viginére-Verschlüsselung zu verschlüsselde Text
	 * @param key der Verwendete Text
	 * @return der verschlüsseltet Text
	 */
	@Override
	public String cryptAll(String text, String key) {
		String ret = "";
		int counter = 0; //Zählt über das Schlüsselwort
		for (int i = 0; i < text.length(); i++) {
			char c = text.charAt(i);
			ret = ret + caesar.cryptChar(c, castKey(key.charAt(counter)));
			if (c >= 'A' && c <= 'z' && (c <= 'Z' || c >= 'a')) {
			//Counter Zählt nur weiter wenn auch ein Buchstabe Verschlüsselt wird
				counter++;
			}
			if (counter == key.length()) { //Überprüft ob das Schlüsselwort abgelaufen wurde
				counter = 0;
			}
		}
		return ret;
	}
	
	/**
	 * Die Methode entschlüsselt einen Text der mit Viginère-Verschlüsselung verschlüsselt wurden, indem
	 * mit einem Zähler über das Schlüsselwort gelaufen wird, und dann mit dem Buchstaben des Schlüsselworts
	 * die Entschlüsselung des Cäsar-Verfahren angewandt wird.
	 */
	@Override
	public String decryptAll(String text, String key) {
		String ret = "";
		int counter = 0; //Zählt über das Schlüsselwort
		for (int i = 0; i < text.length(); i++) {
			char c = text.charAt(i);
			ret = ret + caesar.decryptChar(c, castKey(key.charAt(counter)));
			if (c >= 'A' && c <= 'z' && (c <= 'Z' || c >= 'a')) {
				//Counter Zählt nur weiter wenn auch ein Buchstabe Verschlüsselt wird
					counter++;
				}
			if (counter == key.length()) { //Überprüft ob das Schlüsselwort abgelaufen wurde
				counter = 0;
			}
		}
		return ret;
	}
	
	/**
	 * Die Methode Überprüft ob der Schlüssel das richtige Format hat, ein String aus arabischen Buchstaben
	 * @param key der Verwedete Schlüssel
	 * @return Ein boolscher Wahrheitswert ob das Format des Schlüssels passt
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
	 * Die Methode wandelt einen Charakter in einen Integer für die Verschlüsselung um
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
