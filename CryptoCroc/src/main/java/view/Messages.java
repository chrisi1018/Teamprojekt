package view;

import java.awt.Component;
import javax.swing.JOptionPane;

/**
 * Eine Hilfsklasse die Methoden für die Ausgabe von Warn- und Errormitteilungen bereit stellt.
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
	 * @param message Die Fehlermeldung
	 * @param parent Die Componente durch den Warnhinweis blockiert wird.
	 */
	public static void errorMessage(String message, Component parent) {
		JOptionPane.showOptionDialog(parent, "Test", "Fehler", JOptionPane.YES_OPTION,
				JOptionPane.WARNING_MESSAGE, null, new String[]{"A", "B", "C"}, "B");
	}

}
