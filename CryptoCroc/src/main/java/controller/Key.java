package controller;

import javax.swing.JButton;
import javax.swing.JPanel;

/**
 * 
 * @author chris
 *
 */
public abstract class Key {
	JButton encrypt;
	
	/**
	 * 
	 */
	public Key() {
		
	}
	
	/**
	 * 
	 * @return
	 */
	abstract public JPanel createKeyPanel();
	
	/**
	 * 
	 * @return
	 */
	public JButton getEncrypt() {
		return null;
	}
}
