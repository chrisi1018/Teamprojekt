package controller;

import java.awt.BorderLayout;

import javax.swing.JComboBox;
import javax.swing.JPanel;
import javax.swing.border.LineBorder;

import utility.Utility;

import java.awt.event.ActionListener;

/**
 * Beschreibt ein DropdownMenue fuer die Verschluesselungen
 * 
 * @author zes, Julian Sturm
 * @version 1.1
 */
public class Dropdown {
	
	private JComboBox<String> dropDown = new JComboBox<String>();
	private String[] options;

	/**
	 * Konstruktor, der ein DropdownMenue initialisiert
	 * 
	 * @param options ein String mit allen Optionen des Dropdown Menue's
	 * @param change der ActionListener
	 */
	public Dropdown(String[] options, ActionListener change) {
		dropDown.setEditable(false);
		dropDown.setBorder(new LineBorder(Utility.DARK_GREEN) {
			
			private static final long serialVersionUID = 1L;

			@Override
		    public void paintBorder(java.awt.Component c, java.awt.Graphics g, int x, int y, 
		    	    int width, int height) {
		    	        g.drawRoundRect(x, y, width - 1, height - 1, 7, 7);
		    	    }
		});
		this.options = options;
		for (int i = 0; i < this.options.length; i++) {
			dropDown.addItem(options[i]);
		}
		dropDown.addActionListener(change);
	}

	/**
	 * Erstellt ein Panel mit dem DropdownMenue
	 * 
	 * @return ein Panel mit dem DropdownMenue
	 */
	protected JPanel createDropdown() {
		JPanel panel = new JPanel(new BorderLayout());
		panel.add(dropDown);
		return panel;
	}

	/**
	 * Gibt die aktuelle Option aus
	 * 
	 * @return ein String, der die aktuelle Option enthaelt
	 */
	protected String status() {
		return dropDown.getSelectedItem().toString();
	}
	
	/**
	 * Gibt den aktuellen Index zurueck
	 * 
	 * @return der Integer, der dem aktuellen Index entspricht
	 */
	protected int index() {
		return dropDown.getSelectedIndex();
	}
	
	/**
	 * Setzt das DropdownMenue auf den passenden Index
	 * 
	 * @param index der Index, auf den das DropdownMenue gesetzt wird
	 */
	protected void setIndex(int index) {
		dropDown.setSelectedIndex(index);
	}
}
