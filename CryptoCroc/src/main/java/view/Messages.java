package view;

import javax.swing.JOptionPane;

/**
 * Eine Hilfsklasse die Methoden fuer die Ausgabe von Pop-Up Mitteilungen bereit
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
	 * @param message Die Warnnachricht die Ausgegeben wird.
	 */
	public static void warningMessage(String message) {
		JOptionPane.showOptionDialog(null, message, "Achtung", JOptionPane.OK_OPTION, JOptionPane.WARNING_MESSAGE, null,
				new String[] { "OK" }, "OK");
	}

	/**
	 * Erzeugt eine Pop-Up Abfrage fuer eine Ja oder Nein Frage
	 * 
	 * @param message die angezeigte Frage
	 * @return ein boolscher Wert ob die Frage mit Ja beantwortet wurde
	 */
	public static boolean yesNoQuestion(String message) {
		int temp = JOptionPane.showOptionDialog(null, message, "Achtung", JOptionPane.YES_NO_CANCEL_OPTION,
				JOptionPane.WARNING_MESSAGE, null, new String[] { "JA", "Nein" }, "Nein");
		if (temp == JOptionPane.YES_OPTION) {
			return true;
		}
		return false;
	}

	/**
	 * Implementiert ein Pop-Up-Fenster, dass dem Benutzer erlaubt eine Option von
	 * mehreren zu waehlen
	 * 
	 * @param message Die Nachricht die im Pop-Up-Fenster Angezeigt wird
	 * @param option  Ein Array indem die moeglichen optionen uebergeben werden
	 * @return Gibt an welches Position im Arry options ausgewaehlt wurde.
	 */
	public static int query(String message, String[] option) {
		return JOptionPane.showOptionDialog(null, message, "Frage", JOptionPane.DEFAULT_OPTION,
				JOptionPane.QUESTION_MESSAGE, null, option, option[0]);
	}
}
