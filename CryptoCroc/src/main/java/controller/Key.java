package controller;

import java.awt.BorderLayout;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

/**
 * 
 * @author chrisi
 *
 */
public class Key {
	private JButton encrypt;
	
	/**
	 * 
	 */
	public Key() {
		encrypt = new JButton("verschluesseln");
	}
	
	/**
	 * 
	 * @return
	 */
	public JPanel createKeyPanel() {
		JPanel panel = new JPanel(new BorderLayout());
		panel.add(encrypt, BorderLayout.CENTER);
		return panel;
	}
	
	/**
	 * 
	 * @return
	 */
	public JButton getEncrypt() {
		return encrypt;
	}
}
