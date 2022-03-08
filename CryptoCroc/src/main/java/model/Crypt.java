package model;

/**
 * Fuer die Ver- und Entschluesselung der verschiedenen Verfahren
 * 
 * @author chrisi
 * @version 1.1
 */
public abstract class Crypt {
	
	/**
	 * Verschluesselt den Klartext
	 * 
	 * @param plainText der Klartext
	 * @param key der Schluessel
	 * @return der verschluesselte Text
	 */
	public abstract String cryptAll(String plainText, String key);
	
	/**
	 * Entschluesselt den Geheimtext
	 * 
	 * @param cryptoText der Geheimtext
	 * @param key der Schluessel
	 * @return der entschluesselte Text
	 */
	public abstract String decryptAll(String cryptoText, String key);
	
	/**
	 * Ueberprueft, ob der Schluessel das richtige Format hat
	 * 
	 * @param key der Schluessel, der verwendet wird
	 * @return ein Wahrheitswert, ob der verwendete Schluessel richtig ist
	 */
	public abstract boolean checkKey(String key);
}
