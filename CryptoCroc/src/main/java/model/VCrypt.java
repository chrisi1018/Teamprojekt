package model;

/**
 * Die Klasse stellt die Vigin�re-Verschl�sselung zur Verf�gung.
 * 
 * @author Julian
 * @version 1.0
 */
public class VCrypt extends Crypt {
	
	/**
	 * Die C�sar-Verschl�sselung wird verwendet
	 */
	CCrypt caesar = new CCrypt();
	
	/**
	 * Die Methode verschl�sselt einen Text mit der Vigin�re-Verschl�sselung indem mit einem Z�hler
	 * �ber das Schl�sselwort gelaufen wird, und dann mit dem Buchstaben des Schl�sselworts die
	 * C�sar-Verschl�sselung angewandt wird.
	 * @param text Der mit der Vigin�re-Verschl�sselung zu verschl�sselde Text
	 * @param key der Verwendete Text
	 * @return der verschl�sseltet Text
	 */
	@Override
	public String cryptAll(String text, String key) {
		String ret = "";
		int counter = 0; //Z�hlt �ber das Schl�sselwort
		for (int i = 0; i < text.length(); i++) {
			char c = text.charAt(i);
			ret = ret + caesar.cryptChar(c, castKey(key.charAt(counter)));
			if (c >= 'A' && c <= 'z' && (c <= 'Z' || c >= 'a')) {
			//Counter Z�hlt nur weiter wenn auch ein Buchstabe Verschl�sselt wird
				counter++;
			}
			if (counter == key.length()) { //�berpr�ft ob das Schl�sselwort abgelaufen wurde
				counter = 0;
			}
		}
		return ret;
	}
	
	/**
	 * Die Methode entschl�sselt einen Text der mit Vigin�re-Verschl�sselung verschl�sselt wurden, indem
	 * mit einem Z�hler �ber das Schl�sselwort gelaufen wird, und dann mit dem Buchstaben des Schl�sselworts
	 * die Entschl�sselung des C�sar-Verfahren angewandt wird.
	 */
	@Override
	public String decryptAll(String text, String key) {
		String ret = "";
		int counter = 0; //Z�hlt �ber das Schl�sselwort
		for (int i = 0; i < text.length(); i++) {
			char c = text.charAt(i);
			ret = ret + caesar.decryptChar(c, castKey(key.charAt(counter)));
			if (c >= 'A' && c <= 'z' && (c <= 'Z' || c >= 'a')) {
				//Counter Z�hlt nur weiter wenn auch ein Buchstabe Verschl�sselt wird
					counter++;
				}
			if (counter == key.length()) { //�berpr�ft ob das Schl�sselwort abgelaufen wurde
				counter = 0;
			}
		}
		return ret;
	}
	
	/**
	 * Die Methode �berpr�ft ob der Schl�ssel das richtige Format hat, ein String aus arabischen Buchstaben
	 * @param key der Verwedete Schl�ssel
	 * @return Ein boolscher Wahrheitswert ob das Format des Schl�ssels passt
	 */
	public boolean checkKey(String key) {
		if (key.equals("")) {
			return false;
		}
		for (int i = 0; i < key.length(); i++) {
			char c = key.charAt(i);
			if (c < 'A' || c > 'z') {
				return false;
			}
			if (c > 'Z' && c < 'a') {
				return false;
			}
		}
		return true;
	}
	
	/**
	 * Die Methode wandelt einen Charakter in einen Integer f�r die Verschl�sselung um
	 * @param c
	 * @return
	 */
	private int castKey(char c) {
		if (c >= 'a' && c <= 'z') {
			return c - 'a';
		}
		if (c >= 'A' && c <= 'Z') {
			return c - 'A';
		}
		return 0;
	}
	

}
