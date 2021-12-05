package model;

/**
 * 
 * @author Julian Sturm
 * @version 1.0
 */
public class CCrypt extends Crypt {

	/**
	 * Eine Methode die einen Buchstaben mit dem Cäsar-Verfahren verschlüsselt
	 * 
	 * @param c der zu verschlüsselde Buchstabe
	 * @param key der Schlüssel mit dem verschlüsselt wird
	 * @return der verschlüsselde Buchstabe
	 */
	public char cryptChar(char c, int key) {
		int temp = c;
		if (c >= 'a' && c <= 'z') {
			temp = (((c - 'a') + key) % 26) + 'a';
		} else if (c >= 'A' && c <= 'Z') {
			temp = (((c - 'A') + key) % 26) + 'A';
		}
		return (char) temp;
	}
	
	/**
	 * Eine Methode die einen Buchstaben mit dem Cäsar-Verfahren entschlüsselt
	 * 
	 * @param c der zu entschlüsselde Buchstabe
	 * @param key der Schlüssel mit dem entschlüsselt wird
	 * @return der entschlüsselte Buchstabe
	 */
	public char decryptChar(char c, int key) {
		return cryptChar(c, 26 - key);
	}
	
	/**
	 * Methode die einen String mit dem Cäsar-Verfahren verschlüsselt.
	 * 
	 * @param text der zu verschlüsselde Text
	 * @param stringKey der verwendete Schlüssel
	 * @return der verschlüsselde Text
	 */
	public String cryptAll(String text, String stringKey) {
		int key = castKey(stringKey);
		String ret = "";
		for (int i = 0; i < text.length(); i++) {
			ret = ret + cryptChar(text.charAt(i), key);
		}
		return ret;
	}
	
	/**
	 *  Methode die einen String mit dem Cäsar-Verfahren entschlüsselt.
	 *  
	 *  @param text der zu entschlüsselde Text
	 *  @param stringKey der verwendete Schlüssel
	 *  @return der entschlüsselte Text
	 */
	public String decryptAll(String text, String stringKey) {
		int key = castKey(stringKey);
		String ret = "";
		for (int i = 0; i < text.length(); i++) {
			ret = ret + decryptChar(text.charAt(i), key);
		}
		return ret;
	}
	
	/**
	 * Eine Methode die Überfrüft ob der Schlüssel das richtige Format hat.
	 * 
	 * @param string Der Schlüssel der verwendet wird.
	 * @return ein Wahrheitswer ob der Verwendete schlüssel richtig ist.
	 */
	public boolean checkKey(String key) {
		if (key.length() != 1) {
			return false;
		}
		char c = key.charAt(0);
		if (c >= 'a' && c <= 'z') {
			return true;
		}
		if (c >= 'A' && c <= 'Z') {
			return true;
		}
		return false;
	}
	
	
	/**
	 * Eine Methode die den keyString in eine IntegerVariable umwandelt.
	 * Bei ungültiger Eingabe wird 0 als Schlüssel zurückgegeben.
	 * 
	 * @param stringKey der Schlüssel in Stringform
	 * @param die Position 
	 */
	private int castKey(String stringKey) {
		char c = stringKey.charAt(0);
		if (c >= 'a' && c <= 'z') {
			return c - 'a';
		}
		if (c >= 'A' && c <= 'Z') {
			return c - 'A';
		}
		return 0;
		
	}
}
