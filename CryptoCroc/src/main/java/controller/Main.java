package controller;

import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import com.formdev.flatlaf.FlatLightLaf;

/**
 * Main Klasse fuer CryptoCroc, welches MainController kreiert
 * 
 * @author zes, chrisi
 * @version 1.1
 */
public class Main {

	/**
	 * main methode, die den MainController kreiert
	 * 
	 * @param args Eingabe String
	 */
	public static void main(String[] args) {
		// falls Mac benutze die mac menu bar
		if (System.getProperty("os.name").toLowerCase().equals("mac os x")) {
			System.setProperty("apple.laf.useScreenMenuBar", "true");
			System.setProperty("apple.awt.application.name", "CryptoCroc");
		}
		
		try {
			UIManager.setLookAndFeel(new FlatLightLaf()); // Windows LookAndFeel
															// "com.sun.java.swing.plaf.windows.WindowsLookAndFeel"
			new MainController();
		} catch (UnsupportedLookAndFeelException e) {
			e.printStackTrace();
		}
	}

}
