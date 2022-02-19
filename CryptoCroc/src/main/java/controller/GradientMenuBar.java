package controller;

import java.awt.Color;
import java.awt.GradientPaint;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;

import javax.swing.JMenuBar;

import utility.Utility;

public class GradientMenuBar extends JMenuBar {
	
	private static final long serialVersionUID = -2852521973904866625L;
	
	private Color color1;
	private Color color2;
	private Color borderColor;

	private int mouseAction = 0;
	private Graphics2D g2 = null;
	
	public GradientMenuBar() {
		super();
		super.setForeground(Utility.WHITE);
		super.setFont(Utility.FONT);
		this.color1 = Utility.DARK_GREEN;
		this.color2 = Utility.LIGHT_GREEN;
		this.borderColor = Utility.DARK_GREEN;
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
	protected void paintComponent(Graphics g) {
		Graphics scratchGraphics = g.create();
		g2 = (Graphics2D) scratchGraphics;

		if (this.mouseAction == 0) {
			g2.setPaint(new GradientPaint(new Point(0, 0), this.color2, new Point(0, getHeight()), this.color1));
		} else {
			g2.setPaint(new GradientPaint(new Point(0, 0), this.color1, new Point(0, getHeight()), this.color2));
		}
		g2.fillRect(0, 0, getWidth(), getHeight());
		g2.setPaint(this.borderColor);
		g2.drawRect(0, 0, getWidth() - 1, getHeight() - 1);
		try {
			ui.update(g2, this);
		} finally {
			scratchGraphics.dispose();
			g2.dispose();
		}
	}

}
