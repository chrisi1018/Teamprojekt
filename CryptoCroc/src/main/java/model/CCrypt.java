package model;

/**
 * Stellt die Caesar Verschuesselung zur Verfuegung.
 * @author Julian Sturm
 * @version 1.0
 */
public class CCrypt extends Crypt {

	/**
	 * Eine Methode die einen Buchstaben mit dem Caesar-Verfahren verschluesselt
	 * 
	 * @param c der zu verschluesselnde Buchstabe
	 * @param key der Schluessel mit dem verschluesselt wird
	 * @return der verschluesselnde Buchstabe
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
	 * Eine Methode die einen Buchstaben mit dem Caesar-Verfahren entschluesselt
	 * 
	 * @param c der zu entschluesselnde Buchstabe
	 * @param key der Schluessel mit dem entschluesselt wird
	 * @return der entschluesselte Buchstabe
	 */
	public char decryptChar(char c, int key) {
		return cryptChar(c, 26 - key);
	}
	
	/**
	 * Methode die einen String mit dem Caesar-Verfahren verschluesselt.
	 * 
	 * @param text der zu verschluesselnde Text
	 * @param stringKey der verwendete Schluessel
	 * @return der verschluesselde Text
	 */
	@Override
	public String cryptAll(String text, String stringKey) {
		int key = castKey(stringKey);
		String ret = "";
		for (int i = 0; i < text.length(); i++) {
			ret = ret + cryptChar(text.charAt(i), key);
		}
		return ret;
	}
	
	/**
	 *  Methode die einen String mit dem Caesar-Verfahren entschluesselt.
	 *  
	 *  @param text der zu entschluesselnde Text
	 *  @param stringKey der verwendete Schluessel
	 *  @return der entschluesselte Text
	 */
	@Override
	public String decryptAll(String text, String stringKey) {
		int key = castKey(stringKey);
		String ret = "";
		for (int i = 0; i < text.length(); i++) {
			ret = ret + decryptChar(text.charAt(i), key);
		}
		return ret;
	}
	
	/**
	 * Eine Methode die Ueberprueft ob der Schluessel das richtige Format hat.
	 */
	@Override
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
	 * Bei ungueltiger Eingabe wird 0 als Schluessel zurueckgegeben.
	 * 
	 * @param stringKey der Schluessel in Stringform
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
