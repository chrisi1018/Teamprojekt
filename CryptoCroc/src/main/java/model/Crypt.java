package model;

/**
 * F�r die Ver- und Entschl�sselung der verschiedenen Verfahren
 * 
 * @author chrisi
 * @version 1.1
 */
public abstract class Crypt {
	
	/**
	 * Verschl�sselt den Klartext
	 * 
	 * @param plainText der Klartext
	 * @param key der Schl�ssel
	 * @return der verschl�sselte Text
	 */
	public abstract String cryptAll(String plainText, String key);
	
	/**
	 * Entschl�sselt den Geheimtext
	 * 
	 * @param cryptoText der Geheimtext
	 * @param key der Schl�ssel
	 * @return der entschl�sselte Text
	 */
	public abstract String decryptAll(String cryptoText, String key);
}
