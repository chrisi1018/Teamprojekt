package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.Scanner;

import javax.swing.Box;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.filechooser.FileNameExtensionFilter;

import org.apache.commons.io.FilenameUtils;

import utility.Utility;
import view.Messages;

/**
 * Beschreibt Aufbau und Funktion der Menueleiste in CryptoCroc
 * 
 * @author Julian Singer
 * @version 1.6
 */
public class Menu {

	private final String[] options = new String[] { "Klartext", "Geheimtext" };
	private final String txtType = "txt"; // Dateityp zum Speichern und Laden

	private GradientMenuBar menuBar;

	/**
	 * Konstruktor, der Menuepunkte entgegennimmt und diese der Menueleiste
	 * hinzufuegt
	 * 
	 * @param menus zu hinzufuegende Menuepunkte
	 */
	public Menu(String[] menus) {
		menuBar = new GradientMenuBar();
		menuBar.add(Box.createHorizontalStrut(10)); // Abstand zum abgerundeten Rand
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
	 * @param name  Name des neuen Menues
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
	 * Oeffnet einen uebergebenen Frame, sobald das Menueitem an uebergebener Stelle
	 * angeklickt wird
	 * 
	 * @param barIndex  Index des Menues in der Menueleiste
	 * @param menuIndex Index des Items im Menue
	 * @param newFrame  zu erstellendes Fenster
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
	 * Fuegt dem uebergebenen Text alle 45 Zeichen einen Zeilenumbruch hinzu und
	 * entfernt alle Whitespace-Chars an der Stelle des Zeilenumbruchs
	 * 
	 * @param text uebergebener Text
	 * @return Text mit regelmaessigen Zeilenumbruechen
	 */
	public String createFileString(String text) {
		final int charsUntilWrap = 45; // Anzahl Zeichen bis Zeilenumbruch
		String newText = "";
		String lineSeparator = System.lineSeparator();
		if (text.length() <= charsUntilWrap) {
			return text;
		}
		int indexForWordWrap = text.indexOf(" ", charsUntilWrap);
		int nextWrapIndex = text.indexOf(" ", indexForWordWrap + charsUntilWrap);
		if (indexForWordWrap == -1) {
			return text;
			// ersetzt jedes erste Leerzeichen nach 45 Zeichen durch einen Zeilenumbruch
		} else {
			newText = newText + text.substring(0, indexForWordWrap) + lineSeparator;
			while (nextWrapIndex < text.length() && nextWrapIndex != -1) {
				newText = newText + text.substring(indexForWordWrap + 1, nextWrapIndex) + lineSeparator;
				indexForWordWrap = nextWrapIndex;
				nextWrapIndex = text.indexOf(" ", indexForWordWrap + charsUntilWrap);
			}
			newText = newText + text.substring(indexForWordWrap + 1);
		}
		return newText;
	}

	/**
	 * Oeffnet ein Speichern-Dialogfenster, in dem man Speicherort und Name der zu
	 * speichernden Textdatei selbst festlegen kann und erzeugt eine Textdatei mit
	 * ausgesuchtem Inhalt
	 * 
	 * @param barIndex   Index des Menues in der Menueleiste
	 * @param menuIndex  Index des Items im Menue
	 * @param plainText  uebergebenes Klartextfeld
	 * @param cryptoText uebergebenes Geheimtextfeld
	 */
	public void initSaveItem(int barIndex, int menuIndex, TextField plainText, TextField cryptoText) {
		JFileChooser fileChooser = new JFileChooser();
		// legt fest, was beim Anklicken des Menueitems passiert
		menuBar.getMenu(barIndex).getItem(menuIndex).addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				TextField chosenTextField;
				FileNameExtensionFilter filter = new FileNameExtensionFilter("*." + txtType, txtType);
				int textNumber = Messages.query("Welchen Text willst du speichern?", options);
				// ueberprueft, ob im PopUp-Fenster ein Textfeld gewaehlt wurde und waehlt
				// dieses
				if (textNumber != JOptionPane.CLOSED_OPTION) {
					if (textNumber == 1) {
						chosenTextField = cryptoText;
					} else {
						chosenTextField = plainText;
					}
					// erzeugt eine Textdatei mit eingetipptem Namen, wenn auf 'Speichern' geklickt
					// wird
					fileChooser.addChoosableFileFilter(filter);
					fileChooser.removeChoosableFileFilter(fileChooser.getAcceptAllFileFilter());
					int response = fileChooser.showSaveDialog(null);
					if (response == JFileChooser.APPROVE_OPTION) {
						String fileName = fileChooser.getSelectedFile().getAbsolutePath();
						File file;
						if (fileName.endsWith("." + txtType)) {
							file = new File(fileName);
						} else {
							file = new File(fileName + "." + txtType);
						}
						// prueft, ob die Datei bereits existiert
						if (file.exists()) {
							boolean replace = Messages.yesNoQuestion("Es existiert dort bereits eine Datei mit "
									+ "dem Namen " + file.getName() + ". Willst du diese Datei wirklich ersetzen?");
							if (replace) {
								// schreibt den Text im ausgewaehlten Textfeld in die erzeugte Datei
								PrintWriter writer = null;
								try {
									writer = new PrintWriter(file);
									writer.println(createFileString(chosenTextField.getText()));
								} catch (FileNotFoundException e1) {
									e1.printStackTrace();
								} finally {
									writer.close();
								}
							}
						} else {
							// schreibt den Text im ausgewaehlten Textfeld in die erzeugte Datei
							PrintWriter writer = null;
							try {
								writer = new PrintWriter(file);
								writer.println(createFileString(chosenTextField.getText()));
							} catch (FileNotFoundException e1) {
								e1.printStackTrace();
							} finally {
								writer.close();
							}
						}
					}
				}
			}
		});
	}

	/**
	 * Oeffnet ein Oeffnen-Dialogfenster, in dem man die zu ladende Textdatei unter
	 * allen Textdateien selbst auswaehlen kann und ueberschreibt den Text im
	 * ausgewaehlten Textfeld mit dem Inhalt der gewaehlten Textdatei
	 * 
	 * @param barIndex   Index des Menues in der Menueleiste
	 * @param menuIndex  Index des Items im Menue
	 * @param plainText  uebergebenes Klartextfeld
	 * @param cryptoText uebergebenes Geheimtextfeld
	 */
	public void initOpenItem(int barIndex, int menuIndex, TextField plainText, TextField cryptoText) {
		menuBar.getMenu(barIndex).getItem(menuIndex).addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				JFileChooser fileChooser = new JFileChooser();
				FileNameExtensionFilter filter = new FileNameExtensionFilter("*." + txtType, txtType);
				fileChooser.setFileFilter(filter);
				int textNumber = Messages.query("In welches Textfeld willst du den Text einsetzen?", options);
				// ueberprueft, ob im PopUp-Fenster ein Textfeld gewaehlt wurde
				if (textNumber != JOptionPane.CLOSED_OPTION) {
					int response = fileChooser.showOpenDialog(null);
					if (response == JFileChooser.APPROVE_OPTION) {
						File file = new File(fileChooser.getSelectedFile().getAbsolutePath());
						Scanner fileScanner = null;
						if (!file.getAbsolutePath().endsWith("." + txtType)) {
							Messages.errorMessage("Das Format ." + FilenameUtils.getExtension(file.getName())
									+ " wird nicht unterst\u00fctzt");
						} else {
							// versucht, die gewaehlte Datei auszulesen und ersetzt alle Zeilenumbrueche
							// durch Leerzeichen
							try {
								fileScanner = new Scanner(file);
								if (file.isFile()) {
									String text = "";
									if (fileScanner.hasNextLine()) {
										text = text + fileScanner.nextLine();
									}
									while (fileScanner.hasNextLine()) {
										text = text + " " + fileScanner.nextLine();
									}
									// setzt den Text in das ausgewaehlte Textfeld, falls es leer ist oder
									// ueberschrieben werden soll
									if (textNumber == 1) {
										if (cryptoText.isEmpty()) {
											cryptoText.setText(text);
										} else {
											boolean replace = Messages.yesNoQuestion("Im Geheimtextfeld "
													+ "existiert bereits ein Text. Willst du diesen wirklich "
													+ "ersetzen?");
											if (replace) {
												cryptoText.setText(text);
											}
										}
									} else {
										if (plainText.isEmpty()) {
											plainText.setText(text);
										} else {
											boolean replace = Messages.yesNoQuestion("Im Klartextfeld "
													+ "existiert bereits ein Text. Willst du diesen wirklich "
													+ "ersetzen?");
											if (replace) {
												plainText.setText(text);
											}
										}
									}
								}
							} catch (FileNotFoundException e1) {
								Messages.errorMessage("Die Datei " + file.getName() + " konnte nicht gefunden "
										+ "oder ausgelesen werden");
								e1.printStackTrace();
							} finally {
								fileScanner.close();
							}
						}
					}
				}
			}
		});
	}
	
	/**
	 * Fuellt die rechte Seite des Menues, um diese spaeter 
	 * auch einfaerben zu koennen
	 */
	public void fillRightSide() {
		JLabel title = new JLabel("CryptCroc");
		title.setFont(Utility.HEADLINE_LABEL_FONT);
		title.setForeground(Utility.WHITE);
		menuBar.add(Box.createHorizontalStrut(385)); // zur mittigen Platzierung des Labels
		menuBar.add(title);
		menuBar.add(Box.createHorizontalGlue());
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
