package controller;

import javax.swing.JButton;

import utility.Utility;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.Color;
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
	
	/**
	 * serial UID
	 */
	private static final long serialVersionUID = 7651642719382287262L;

	/**
	 * Button wird mit den Standardwerten initialisiert.
	 */
	public GradientButton() {
		super();
		super.setForeground(Utility.WHITE);
		super.setFont(Utility.FONT);
		this.color1 = Utility.DARK_GREEN;
		this.color2 = Utility.LIGHT_GREEN;
		this.borderColor = Utility.DARK_GREEN;
		addMouseListener(this);
	}

	/**
	 * Button wird mit den Standardwerten initialisiert und der Text wird gesetzt.
	 * 
	 * @param text ButtonText
	 */
	public GradientButton(String text) {
		super(text);
		super.setForeground(Utility.WHITE);
		super.setFont(Utility.FONT);
		this.color1 = Utility.DARK_GREEN;
		this.color2 = Utility.LIGHT_GREEN;
		this.borderColor = Utility.DARK_GREEN;
		addMouseListener(this);
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
	public void mouseClicked(MouseEvent e) {
		// tue nichts
	}

	@Override
	public void mousePressed(MouseEvent e) {
		this.mouseAction = 1;

	}

	@Override
	public void mouseReleased(MouseEvent e) {
		// tue nichts
	}

	@Override
	public void mouseEntered(MouseEvent e) {
		this.borderColor = Utility.ORANGE;
	}

	@Override
	public void mouseExited(MouseEvent e) {
		this.borderColor = Utility.DARK_GREEN;
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
