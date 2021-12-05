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
	 */
	public abstract String cryptAll(String plainText, String key);
	
	/**
	 * Entschl�sselt den Geheimtext
	 * 
	 * @param cryptoText der Geheimtext
	 */
	public abstract String decryptAll(String cryptoText, String key);
}
