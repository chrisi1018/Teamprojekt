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
		try {
			UIManager.setLookAndFeel("com.sun.java.swing.plaf.windows.WindowsLookAndFeel"); //new FlatLightLaf()
			new MainController();
		} catch (UnsupportedLookAndFeelException | ClassNotFoundException | InstantiationException | IllegalAccessException e) {
			e.printStackTrace();
		}
	}

}
