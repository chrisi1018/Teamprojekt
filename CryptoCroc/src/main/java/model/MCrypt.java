package model;

/**
 * Fuer eine monoalphabetische Verschluesselung
 * 
 * @author zes
 * @version 1.1
 */
public class MCrypt extends Crypt {

	/**
	 * Ueberprueft ob ein Schluessel gueltig ist
	 * 
	 * @param keyString Der Schluessel der verwendet wird.
	 * @return ob der Schluessel gueltig ist
	 */
	@Override
	public boolean checkKey(String keyString) {
		if (keyString.length() != 26) {
			return false;
		}

		boolean allGood = true;
		// checkt ob jedes Zeichen nur einmal vorkommt
		String[] key = this.convertKeyToArray(keyString);
		for (int i = 0; i < key.length; i++) {
			char c = key[i].charAt(0);
			if (!(c >= 'a' && c <= 'z') && !(c >= 'A' && c <= 'Z')) {
				return false;
			}
			for (int j = 0; j < key.length; j++) {
				if (key[i].equalsIgnoreCase(key[j]) && (i != j)) {
					allGood = false;
				}
			}
		}
		if (allGood) {
			return true;
		}
		return false;
	}

	/**
	 * Macht aus einem Schluesselwort ein Array
	 * 
	 * @param key das Schluesselwort als String
	 * @return ein Array mit in jedem Slot ein Buchstabe
	 */
	public String[] convertKeyToArray(String key) {
		String[] keyArray = new String[key.length()];
		for (int i = 0; i < keyArray.length; i++) {
			keyArray[i] = Character.toString(key.charAt(i));
		}
		return keyArray;
	}

	@Override
	public String cryptAll(String plainText, String key) {
		String[] keyArray = this.convertKeyToArray(key);
		String cryptoText = "";
		for (int i = 0; i < plainText.length(); i++) {
			cryptoText += this.cryptChar(plainText.charAt(i), keyArray);
		}
		return cryptoText;
	}

	/**
	 * Verschluesselt einen Buchstaben mit einem gegebenen Schluessel, indem der
	 * Wert an der Stelle 'letter' aus dem Schluessel-Array abgelesen wird
	 * 
	 * @param letter der aktuelle Buchstabe, der verschluesselt werden soll
	 * @param key    ein Array mit dem aktuellen Schluessel
	 * @return der verschluesselte Buchstabe als String
	 */
	private String cryptChar(char letter, String[] key) {
		String val = Character.toString(letter);
		if (letter >= 'a' && letter <= 'z') {
			val = key[(letter) % 'a'];
		} else if (letter >= 'A' && letter <= 'Z') {
			val = key[(letter) % 'A'];
		}
		val = val.toUpperCase();
		return val;
	}

	@Override
	public String decryptAll(String cryptoText, String key) {
		String[] keyArray = this.convertKeyToArray(key);
		String plainText = "";
		for (int i = 0; i < cryptoText.length(); i++) {
			plainText += this.decryptChar(cryptoText.charAt(i), keyArray);
		}
		return plainText;
	}

	/**
	 * Verschluesselt einen Buchstaben indem es schaut an welcher Stelle der
	 * Buchstabe im Schluessel-Array steht und dies dann in einen Grossbuchstaben
	 * umwandelt
	 * 
	 * @param letter
	 * @param key
	 * @return String
	 */
	private String decryptChar(char letter, String[] key) {
		String val = Character.toString(letter);
		for (int i = 0; i < key.length; i++) {
			if (val.equalsIgnoreCase(key[i])) {
				char c = (char) ('A' + i);
				val = Character.toString(c);
				return val;
			}
		}
		return val;
	}

}
