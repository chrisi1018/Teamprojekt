package controller;

import javax.swing.JMenuItem;
import javax.swing.JPopupMenu;
import javax.swing.JTextArea;
import javax.swing.UIManager;

import utility.Utility;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class MousePopupMenu {
	private JPopupMenu menu = new JPopupMenu();
	private JMenuItem entry = null;

	public MousePopupMenu(JTextArea textArea) {
		ActionListener al = new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (textArea.getToolkit().getSystemClipboard() != null) {
					if (e.getActionCommand().equals("Ausschneiden")) {
						textArea.cut();
					} else if (e.getActionCommand().equals("Kopieren")) {
						textArea.copy();
					} else if (e.getActionCommand().equals("Einf\u00fcgen")) {
						textArea.paste();
					} else if (e.getActionCommand().equals("Alles markieren")) {
						textArea.selectAll();
					}
				}
			}
		};

		textArea.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseReleased(MouseEvent me) {
				// Spezialfall fuer macOS
				if (UIManager.getSystemLookAndFeelClassName().equals("com.apple.laf.AquaLookAndFeel")) {
					if (me.getButton() == Utility.MACOSMOUSECLICK) {
						menu.show(me.getComponent(), me.getX(), me.getY());
					}
				}
				if (me.isPopupTrigger()) {
					menu.show(me.getComponent(), me.getX(), me.getY());
				}
			}

			@Override
			public void mouseClicked(java.awt.event.MouseEvent e) {
				// tue nichts
			}

			@Override
			public void mousePressed(java.awt.event.MouseEvent e) {
				// tue nichts
			}

			@Override
			public void mouseEntered(java.awt.event.MouseEvent e) {
				// tue nichts
			}

			@Override
			public void mouseExited(java.awt.event.MouseEvent e) {
				// tue nichts
			}
		});

		entry = new JMenuItem("Ausschneiden");
		menu.add(entry);
		entry.addActionListener(al);

		entry = new JMenuItem("Kopieren");
		menu.add(entry);
		entry.addActionListener(al);

		entry = new JMenuItem("Einf\u00fcgen");
		menu.add(entry);
		entry.addActionListener(al);

		menu.addSeparator();

		entry = new JMenuItem("Alles markieren");
		menu.add(entry);
		entry.addActionListener(al);

	}
}
