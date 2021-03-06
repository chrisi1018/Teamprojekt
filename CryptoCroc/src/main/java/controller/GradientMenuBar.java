package controller;

import java.awt.Color;
import java.awt.GradientPaint;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;

import javax.swing.JMenuBar;

import utility.Utility;

/**
 * Klasse, die den Farbverlauf fuer die MenueLeiste realisiert
 * 
 * @author Julian Singer, zes
 * @version 1.2
 *
 */
public class GradientMenuBar extends JMenuBar {

	private static final long serialVersionUID = -2852521973904866625L;

	private Color color1;
	private Color color2;

	private Graphics2D g2 = null;

	/**
	 * Konstruktor, der die Farben fuer Farbverlauf und Schrift festlegt
	 */
	public GradientMenuBar() {
		super();
		if (System.getProperty("os.name").toLowerCase().equals("mac os x")) {
			super.setForeground(Utility.WHITE);
			super.setBackground(Utility.DARK_GREEN);
			this.color1 = Utility.WHITE;
			this.color2 = Utility.WHITE;
		} else {
			super.setForeground(Utility.WHITE);
			this.color1 = Utility.DARK_GREEN;
			this.color2 = Utility.LIGHT_GREEN;
		}
	}

	/**
	 * Setzt den Farbverlauf auf die uebergebenen Farben.
	 * 
	 * @param color1 Grundfarbe fuer den Farbverlauf
	 * @param color2 Abschlussfarbe fuer den Farbverlauf
	 */
	public void setGradient(Color color1, Color color2) {
		this.color1 = color1;
		this.color2 = color2;
	}

	@Override
	protected void paintComponent(Graphics g) {
		Graphics scratchGraphics = g.create();
		g2 = (Graphics2D) scratchGraphics;

		g2.setPaint(new GradientPaint(new Point(0, 0), this.color2, new Point(0, getHeight()), this.color1));
		g2.fillRoundRect(0, 0, getWidth(), getHeight(), 20, 20);
		g2.drawRoundRect(0, 0, getWidth() - 1, getHeight() - 1, 19, 19);
		try {
			ui.update(g2, this);
		} finally {
			scratchGraphics.dispose();
			g2.dispose();
		}
	}

}
