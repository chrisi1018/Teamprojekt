package view;

import javax.swing.JOptionPane;

/**
 * Eine Hilfsklasse die Methoden für die Ausgabe von Pop-Up Meitteilungen bereit stellt.
 * 
 * @author Julian
 * @version 1.0
 */
public final class Messages {
	
	
	/**
	 * Privater Konstruktor für Hilfsklasse
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
	 * Erzeugt eine Pop-Up Abfrage für eine Ja oder Nein Frage
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
	
	/**
	 *  Implementiert ein Pop-Up-Fenster, dass dem Benuter erlaubt eine Option von Mehrern zu wählen
	 *  
	 *  @param message Die Nachricht die im Pop-Up-Fenster Angezeigt wird
	 *  @param option Ein Array indem die möglichen optionen übergeben werden
	 *  @return Gibt an welches Position im Arry options ausgewählt wurde.
	 */
	public static int query(String message, String[] option) {
		return JOptionPane.showOptionDialog(null, message, "Frage",
				JOptionPane.DEFAULT_OPTION, JOptionPane.QUESTION_MESSAGE,
				null, option, option[0]);
	}
}
