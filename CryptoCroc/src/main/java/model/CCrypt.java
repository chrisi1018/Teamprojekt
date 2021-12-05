package model;

/**
 * 
 * @author Julian Sturm
 * @version 1.0
 */
public class CCrypt extends Crypt {

	/**
	 * Eine Methode die einen Buchstaben mit dem C�sar-Verfahren verschl�sselt
	 * 
	 * @param c der zu verschl�sselde Buchstabe
	 * @param key der Schl�ssel mit dem verschl�sselt wird
	 * @return der verschl�sselde Buchstabe
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
	 * Eine Methode die einen Buchstaben mit dem C�sar-Verfahren entschl�sselt
	 * 
	 * @param c der zu entschl�sselde Buchstabe
	 * @param key der Schl�ssel mit dem entschl�sselt wird
	 * @return der entschl�sselte Buchstabe
	 */
	public char decryptChar(char c, int key) {
		return cryptChar(c, 26 - key);
	}
	
	/**
	 * Methode die einen String mit dem C�sar-Verfahren verschl�sselt.
	 * 
	 * @param text der zu verschl�sselde Text
	 * @param stringKey der verwendete Schl�ssel
	 * @return der verschl�sselde Text
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
	 *  Methode die einen String mit dem C�sar-Verfahren entschl�sselt.
	 *  
	 *  @param text der zu entschl�sselde Text
	 *  @param stringKey der verwendete Schl�ssel
	 *  @return der entschl�sselte Text
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
	 * Eine Methode die �berfr�ft ob der Schl�ssel das richtige Format hat.
	 * 
	 * @param string Der Schl�ssel der verwendet wird.
	 * @return ein Wahrheitswer ob der Verwendete schl�ssel richtig ist.
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
	 * Bei ung�ltiger Eingabe wird 0 als Schl�ssel zur�ckgegeben.
	 * 
	 * @param stringKey der Schl�ssel in Stringform
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
