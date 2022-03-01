package controller;

import java.awt.event.MouseEvent;

import java.awt.Point;

import javax.swing.JFrame;

import javax.swing.event.MouseInputAdapter;

/**
 * Definiert einen eigenen MouseInputAdapter zur Verschiebung
 * 
 * @author Julian Singer
 * @version 1.0
 */
public class MoveMouseListener extends MouseInputAdapter {
	
	private JFrame frame;
	private Point dragLoc;
	private Point frameLoc;
	
	/**
	 * Konstruktor, der den zu verschiebenden JFrame uebergibt
	 * 
	 * @param currentFrame das zu verschiebende Fenster
	 */
	public MoveMouseListener(JFrame currentFrame) {
		this.frame = currentFrame;
	}
	
	/**
	 * Gibt die aktuelle Position der Maus auf dem Bildschirm zurueck
	 * 
	 * @param e Maus
	 * @return aktuelle Position der Maus
	 */
	private Point getScreenLocation(MouseEvent e) {
		Point cursor = e.getPoint();
		Point frameLoc = this.frame.getLocationOnScreen();
		return new Point((int) (frameLoc.getX() + cursor.getX()), (int) (frameLoc.getY() + cursor.getY()));
	}

	// verschiebt das Fenster beim Ziehen der Maus
	@Override
	public void mouseDragged(MouseEvent e) {
		Point current = this.getScreenLocation(e);
		Point offset = new Point((int) current.getX() - (int) dragLoc.getX(), 
				(int) current.getY() - (int) dragLoc.getY());
		Point newLoc = new Point((int) (frameLoc.getX() + offset.getX()), 
				(int) (frameLoc.getY() + offset.getY()));
		this.frame.setLocation(newLoc);
	}

	@Override
	public void mouseMoved(MouseEvent e) {
		// tue nichts
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		// tue nichts
	}

	// aktualisiert bei gedrueckter Maus wiederholt die Position von Maus und Fenster
	@Override
	public void mousePressed(MouseEvent e) {
		this.dragLoc = this.getScreenLocation(e);
		this.frameLoc = this.frame.getLocation();
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		// tue nichts
	}

	@Override
	public void mouseEntered(MouseEvent e) {
		// tue nichts
	}

	@Override
	public void mouseExited(MouseEvent e) {
		// tue nichts
	}

}
