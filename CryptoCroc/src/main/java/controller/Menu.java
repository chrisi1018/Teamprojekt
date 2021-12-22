package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;

import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.filechooser.FileNameExtensionFilter;

import view.Messages;

/**
 * Beschreibt Aufbau und Funktion der Menueleiste in CryptoCroc
 * 
 * @author Julian Singer
 * @version 1.2
 */
public class Menu {
	
	private JMenuBar menuBar;
	
	/**
	 * Konstruktor, der Menuepunkte entgegennimmt und diese der Menueleiste hinzufuegt
	 * 
	 * @param menus zu hinzufuegende Menuepunkte
	 */
	
	public Menu(String[] menus) {
		menuBar = new JMenuBar();
		JMenu menu = new JMenu("Men\u00fc");
		menuBar.add(menu);
		for (int i = 0; i < menus.length; i++) {
			JMenuItem item = new JMenuItem(menus[i]);
			menu.add(item);
		}
	}
	
	/**
	 * Fuegt der Menueleiste ein neues Menue mit uebergebenen Unterpunkten hinzu
	 * 
	 * @param name Name des neuen Menues
	 * @param menus zugehoerige Menuepunkte
	 */
	public void addMenu(String name, String[] menus) {
		JMenu newMenu = new JMenu(name);
		menuBar.add(newMenu);
		for (int i = 0; i < menus.length; i++) {
			JMenuItem item = new JMenuItem(menus[i]);
			newMenu.add(item);
		}
	}
	
	/**
	 * Oeffnet einen uebergebenen Frame, sobald das Menueitem an übergebener Stelle angeklickt wird
	 * 
	 * @param barIndex Index des Menues in der Menueleiste
	 * @param menuIndex Index des Items im Menue
	 * @param newFrame zu erstellendes Fenster
	 */
	public void initExplanationItem(int barIndex, int menuIndex, JFrame newFrame) {
		menuBar.getMenu(barIndex).getItem(menuIndex).addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				newFrame.setVisible(true);
			}
		});
	}
	
	public void initSaveItem(int barIndex, int menuIndex, TextField plainText, TextField cryptoText) {
		String[] options = new String[] { "Klartext", "Geheimtext" };
		JFileChooser fileChooser = new JFileChooser();
		menuBar.getMenu(barIndex).getItem(menuIndex).addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				TextField chosenTextField;
				int textNumber = Messages.query("Welchen Text willst du speichern?", options);
				if (textNumber != JOptionPane.CLOSED_OPTION) {
					if (textNumber == 1) {
						chosenTextField = cryptoText;
					} else {
						chosenTextField = plainText;
					}
					int response = fileChooser.showSaveDialog(null);
					if (response == JFileChooser.APPROVE_OPTION) {
						File file = new File(fileChooser.getSelectedFile().getAbsolutePath());
						PrintWriter writer = null;
						try {
							writer = new PrintWriter(file);
							writer.println(chosenTextField.getTextArea().getText());
						} catch (FileNotFoundException e1) {
							e1.printStackTrace();
						} finally {
							writer.close();					
						}
					}
				}
			}
		});
	}
	
	/**
	 * Gibt die Menueleiste der aktuellen Instanz zurueck
	 * 
	 * @return this.menuBar Menueleiste
	 */
	public JMenuBar getJMenuBar() {
		return this.menuBar;
	}
}
