package controller;

import javax.swing.JButton;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.Color;
import java.awt.Font;

import java.awt.Graphics2D;
import java.awt.Graphics;
import java.awt.GradientPaint;
import java.awt.Point;

/**
 * Es wird ein Button mit einem Farbverlauf und gerundeten Ecken erzeugt.
 * 
 * @author chrisi
 * @version 1.0
 */
public class GradientButton extends JButton implements MouseListener {

	private Color color1;
	private Color color2;
	private Color borderColor;

	private int mouseAction = 0;
	private Graphics2D g2 = null;

	/** Die dunkel gruene CryptoCroc Farbe */
	public static final Color DARK_GREEN = new Color(75, 115, 14);
	/** Die orangene CryptoCroc Farbe */
	public static final Color ORANGE = new Color(185, 137, 0);
	/** Hell gruener Verlauf */
	public static final Color LIGHT_GREEN = new Color(191, 205, 169);
	/** Die Farbe weiss fuer den Text */
	public static final Color WHITE = new Color(255, 255, 255);
	/** standard Schriftzug fuer die Buttons */
	public static final Font FONT = new Font("Arial", Font.BOLD, 12);
	
	/**
	 * serial UID
	 */
	private static final long serialVersionUID = 7651642719382287262L;

	/**
	 * Button wird mit den Standartwerten initialisiert.
	 */
	public GradientButton() {
		super();
		super.setForeground(WHITE);
		super.setFont(FONT);
		this.color1 = DARK_GREEN;
		this.color2 = LIGHT_GREEN;
		this.borderColor = DARK_GREEN;
		addMouseListener(this);
	}

	/**
	 * Button wird mit den Standartwerten initialisiert und der Text wird gesetzt.
	 * 
	 * @param text Buttontext
	 */
	public GradientButton(String text) {
		super(text);
		super.setForeground(WHITE);
		super.setFont(FONT);
		this.color1 = DARK_GREEN;
		this.color2 = LIGHT_GREEN;
		this.borderColor = DARK_GREEN;
		addMouseListener(this);
	}

	/**
	 * Setzt den Farbverlauf auf die uebergebene Farben.
	 * 
	 * @param color1 Grundfarbe fuer den Farbverlauf
	 * @param color2 Abschlussfarbe fuer den Farbverlauf
	 */
	public void setGradient(Color color1, Color color2) {
		this.color1 = color1;
		this.color2 = color2;
	}

	@Override
	public void mouseClicked(MouseEvent e) {
	}

	@Override
	public void mousePressed(MouseEvent e) {
		this.mouseAction = 1;

	}

	@Override
	public void mouseReleased(MouseEvent e) {
	}

	@Override
	public void mouseEntered(MouseEvent e) {
		this.borderColor = ORANGE;
	}

	@Override
	public void mouseExited(MouseEvent e) {
		this.borderColor = DARK_GREEN;
		this.mouseAction = 0;
	}

	@Override
	protected void paintComponent(Graphics g) {
		setContentAreaFilled(false);
		Graphics scratchGraphics = g.create();
		g2 = (Graphics2D) scratchGraphics;

		if (this.mouseAction == 0) {
			g2.setPaint(new GradientPaint(new Point(0, 0), this.color2, new Point(0, getHeight()), this.color1));
		} else {
			g2.setPaint(new GradientPaint(new Point(0, 0), this.color1, new Point(0, getHeight()), this.color2));
		}
		g2.fillRoundRect(0, 0, getWidth(), getHeight(), 20, 20);
		g2.setPaint(this.borderColor);
		g2.drawRoundRect(0, 0, getWidth() - 1, getHeight() - 1, 19, 19);
		try {
			ui.update(g2, this);
		} finally {
			scratchGraphics.dispose();
			g2.dispose();
		}
	}

}