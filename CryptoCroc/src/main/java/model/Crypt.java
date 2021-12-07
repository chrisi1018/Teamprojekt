package model;

/**
 * Fï¿½r die Ver- und Entschlï¿½sselung der verschiedenen Verfahren
 * 
 * @author chrisi
 * @version 1.1
 */
public abstract class Crypt {
	
	/**
	 * Verschlï¿½sselt den Klartext
	 * 
	 * @param plainText der Klartext
	 * @param key der Schlüssel
	 * @return der verschlüsselte Text
	 */
	public abstract String cryptAll(String plainText, String key);
	
	/**
	 * Entschlï¿½sselt den Geheimtext
	 * 
	 * @param cryptoText der Geheimtext
	 * @param key der Schlüssel
	 * @return der entschlüsselte Text
	 */
	public abstract String decryptAll(String cryptoText, String key);
}
