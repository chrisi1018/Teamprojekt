package view;

import javax.swing.JOptionPane;

/**
 * Eine Hilfsklasse, die Methoden fuer die Ausgabe von Pop-Up Mitteilungen bereit
 * stellt.
 * 
 * @author Julian Sturm
 * @version 1.0
 */
public final class Messages {

	/**
	 * Privater Konstruktor fuer Hilfsklasse
	 */
	private Messages() {
	}

	/**
	 * Erzeugt ein Pop-up Fenster mit einer Fehlermeldung
	 * 
	 * @param message die Fehlermeldung
	 */
	public static void errorMessage(String message) {
		JOptionPane.showOptionDialog(null, message, "Achtung", JOptionPane.OK_OPTION, JOptionPane.ERROR_MESSAGE, null,
				new String[] { "OK" }, "OK");
	}

	/**
	 * Erzeugt ein Pop-Up Fenster mit einem Warnhinweis.
	 * 
	 * @param message Die Warnnachricht, die ausgegeben wird.
	 */
	public static void warningMessage(String message) {
		JOptionPane.showOptionDialog(null, message, "Achtung", JOptionPane.OK_OPTION, JOptionPane.WARNING_MESSAGE, null,
				new String[] { "OK" }, "OK");
	}

	/**
	 * Erzeugt eine Pop-Up Abfrage fuer eine Ja oder Nein Frage
	 * 
	 * @param message die angezeigte Frage
	 * @return ein boolescher Wert, ob die Frage mit Ja beantwortet wurde
	 */
	public static boolean yesNoQuestion(String message) {
		int temp = JOptionPane.showOptionDialog(null, message, "Achtung", JOptionPane.YES_NO_CANCEL_OPTION,
				JOptionPane.WARNING_MESSAGE, null, new String[] { "Ja", "Nein" }, "Ja");
		if (temp == JOptionPane.YES_OPTION) {
			return true;
		}
		return false;
	}

	/**
	 * Implementiert ein Pop-Up-Fenster, das dem Benutzer erlaubt eine von mehreren Optionen
	 * zu waehlen
	 * 
	 * @param message Die Nachricht, die im Pop-Up-Fenster angezeigt wird
	 * @param option  Ein Array, in dem die moeglichen Optionen uebergeben werden
	 * @return Gibt an, welche Position im Array options ausgewaehlt wurde.
	 */
	public static int query(String message, String[] option) {
		return JOptionPane.showOptionDialog(null, message, "Frage", JOptionPane.DEFAULT_OPTION,
				JOptionPane.QUESTION_MESSAGE, null, option, option[0]);
	}
}
