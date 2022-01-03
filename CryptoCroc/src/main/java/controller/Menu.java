package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.Scanner;

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
 * @version 1.3
 */
public class Menu {
	
	private final String[] options = new String[] { "Klartext", "Geheimtext" };
	
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
	
	/**
	 * Fuegt dem uebergebenen Text alle 40 Zeichen einen Zeilenumbruch hinzu und entfernt alle 
	 * Whitespace-Chars an der Stelle des Zeilenumbruchs
	 * 
	 * @param text uebergebener Text
	 * @return Text mit regelmaeßigen Zeilenumbruechen
	 */
	public String createFileString(String text) {
		String newText = "";
		String lineSeparator = System.lineSeparator();
		int lastIndex = 0;
		//geht nur bei über 50 Chars in Verzweigung, da sonst keine Zeilenumbrueche gebraucht werden
		if (text.length() > 50) {
			for (int i = 0; i < text.length() - 50; i = i + 50) {
				int nextIndex = i + 50;
				newText = newText + text.substring(i, nextIndex) + lineSeparator;
				//soll (falls vorhanden) Whitespace an der nächsten Stelle ignorieren
				if (Character.isWhitespace(text.charAt(nextIndex))) {
					lastIndex = nextIndex + 1;
				} else {
					lastIndex = nextIndex;
				}
			}
		}
		newText = newText + text.substring(lastIndex);
		return newText;
	}
	
	/**
	 * Oeffnet ein Speichern-Dialogfenster, in dem man Speicherort und Name der zu speichernden Textdatei
	 * selbst festlegen kann und erzeugt eine Textdatei mit ausgesuchtem Inhalt
	 * 
	 * @param barIndex Index des Menues in der Menueleiste
	 * @param menuIndex Index des Items im Menue
	 * @param plainText übergebenes Klartextfeld
	 * @param cryptoText übergebenes Geheimtextfeld
	 */
	public void initSaveItem(int barIndex, int menuIndex, TextField plainText, TextField cryptoText) {
		final String fileType = "txt";
		JFileChooser fileChooser = new JFileChooser();
		//legt fest, was beim Anklicken des Menueitems passiert
		menuBar.getMenu(barIndex).getItem(menuIndex).addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				TextField chosenTextField;
				FileNameExtensionFilter filter = new FileNameExtensionFilter("*." + fileType, fileType);
				int textNumber = Messages.query("Welchen Text willst du speichern?", options);
				//ueberprueft, ob im PopUp-Fenster ein Textfeld gewaehlt wurde und waehlt dieses
				if (textNumber != JOptionPane.CLOSED_OPTION) {
					if (textNumber == 1) {
						chosenTextField = cryptoText;
					} else {
						chosenTextField = plainText;
					}
					//erzeugt eine Textdatei mit eingetipptem Namen, wenn auf 'Speichern' geklickt wird
					fileChooser.addChoosableFileFilter(filter);
					fileChooser.removeChoosableFileFilter(fileChooser.getAcceptAllFileFilter());
					int response = fileChooser.showSaveDialog(null);
					if (response == JFileChooser.APPROVE_OPTION) {
						String fileName = fileChooser.getSelectedFile().getAbsolutePath();
						File file;
						if (fileName.endsWith("." + fileType)) {
							file = new File(fileName);
						} else {
							file = new File(fileName + "." + fileType);
						}
						//schreibt den Text im ausgewählten Textfeld in die erzeugte Datei
						PrintWriter writer = null;
						try {
							writer = new PrintWriter(file);
							writer.println(createFileString(chosenTextField.getTextArea().getText()));
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
	
	public void initOpenItem(int barIndex, int menuIndex, TextField plainText, TextField cryptoText) {
		menuBar.getMenu(barIndex).getItem(menuIndex).addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				JFileChooser fileChooser = new JFileChooser();
				FileNameExtensionFilter filter = new FileNameExtensionFilter("*.txt", "txt");
				fileChooser.setFileFilter(filter);
				int textNumber = Messages.query("In welches Textfeld willst du den Text einsetzen?", options);
				//ueberprueft, ob im PopUp-Fenster ein Textfeld gewaehlt wurde
				if (textNumber != JOptionPane.CLOSED_OPTION) {
					int response = fileChooser.showOpenDialog(null);
					if (response == JFileChooser.APPROVE_OPTION) {
						File file = new File(fileChooser.getSelectedFile().getAbsolutePath());
						Scanner fileScanner = null;
						try {
							fileScanner = new Scanner(file);
							if (file.isFile()) {
								String text = "";
								while (fileScanner.hasNextLine()) {
									text = text + fileScanner.nextLine() + " ";
								}
								if (textNumber == 1) {
									cryptoText.getTextArea().setText(text);
								} else {
									plainText.getTextArea().setText(text);
								}
							}
						} catch (FileNotFoundException e1) {
							e1.printStackTrace();
						} finally {
							fileScanner.close();
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
