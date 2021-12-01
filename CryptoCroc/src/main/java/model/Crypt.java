package model;

/**
 * Für die Ver- und Entschlüsselung der verschiedenen Verfahren
 * 
 * @author chrisi
 * @version 1.1
 */
public abstract class Crypt {
	
	/**
	 * Verschlüsselt den Klartext
	 * 
	 * @param plainText der Klartext
	 */
	abstract public void cryptAll(String plainText);
	
	/**
	 * Entschlüsselt den Geheimtext
	 * 
	 * @param cryptoText der Geheimtext
	 */
	abstract public void decryptAll(String cryptoText);
}
