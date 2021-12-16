package view;

import javax.swing.JOptionPane;

/**
 * Eine Hilfsklasse die Methoden f�r die Ausgabe von Pop-Up Meitteilungen bereit stellt.
 * 
 * @author Julian
 * @version 1.0
 */
public final class Messages {
	
	/**
	 * Privater Konstruktor f�r Hilfsklasse
	 */
	private Messages() { }
	
	/**
	 * Erzeugt ein Pop-up Fenster mit einer Fehlermeldung
	 * 
	 * @param message die Fehlermeldung
	 */
	public static void errorMessage(String message) {
		JOptionPane.showOptionDialog(null, message, "Achtung", JOptionPane.OK_OPTION,
				JOptionPane.ERROR_MESSAGE, null, new String[] {"OK"}, "OK");
	}
	
	/**
	 * Erzeugt eine Pop-Up Fenster mit einem Wahnhinweis.
	 * 
	 * @param message Die Warnnachricht die AUsgegeben wird.
	 */
	public static void warningMessage(String message) {
		JOptionPane.showOptionDialog(null, message, "Achtung", JOptionPane.OK_OPTION,
				JOptionPane.WARNING_MESSAGE, null, new String[] {"OK"}, "OK");
	}
	
	/**
	 * Erzeugt eine Pop-Up Abfrage f�r eine Ja oder Nein Frage
	 * 
	 *@param message die Angezeigt Frage
	 *@return ein boolscher Wert ob die Frage mit Ja beantwortet wurde
	 */
	public static boolean yesNoQuestion(String message) {
		int temp = JOptionPane.showOptionDialog(null, message, "Achtung",
				JOptionPane.YES_NO_CANCEL_OPTION, JOptionPane.WARNING_MESSAGE,
				null, new String[] {"JA", "Nein"}, "Nein");
		if (temp == JOptionPane.YES_OPTION) {
			return true;
		}
		return false;
	}
}