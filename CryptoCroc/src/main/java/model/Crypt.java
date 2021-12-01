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
	abstract public void cryptAll(String plainText);
	
	/**
	 * Entschl�sselt den Geheimtext
	 * 
	 * @param cryptoText der Geheimtext
	 */
	abstract public void decryptAll(String cryptoText);
}
