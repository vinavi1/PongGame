package pack;

import javax.swing.JPanel;
import javax.swing.JButton;
import java.awt.Font;
import java.awt.Color;
import org.eclipse.wb.swing.FocusTraversalOnArray;
import java.awt.Component;
import java.awt.Canvas;

public class MainMenu extends JPanel {

	/**
	 * Create the panel.
	 */
	public MainMenu() {
		setLayout(null);
		
		JButton btnNewButton = new JButton("New Game");
		btnNewButton.setForeground(Color.RED);
		btnNewButton.setBackground(Color.RED);
		btnNewButton.setFont(new Font("Snap ITC", Font.BOLD, 18));
		btnNewButton.setBounds(25, 55, 176, 34);
		add(btnNewButton);
		
		JButton btnSettings = new JButton("Settings");
		btnSettings.setForeground(Color.RED);
		btnSettings.setFont(new Font("Snap ITC", Font.BOLD, 18));
		btnSettings.setBackground(Color.RED);
		btnSettings.setBounds(25, 102, 176, 34);
		add(btnSettings);
		
		JButton btnAbout = new JButton("About");
		btnAbout.setForeground(Color.RED);
		btnAbout.setFont(new Font("Snap ITC", Font.BOLD, 18));
		btnAbout.setBackground(Color.RED);
		btnAbout.setBounds(25, 149, 176, 34);
		add(btnAbout);
		
		JButton btnQuit = new JButton("Quit");
		btnQuit.setForeground(Color.RED);
		btnQuit.setFont(new Font("Snap ITC", Font.BOLD, 18));
		btnQuit.setBackground(Color.RED);
		btnQuit.setBounds(25, 196, 176, 34);
		add(btnQuit);
		setFocusTraversalPolicy(new FocusTraversalOnArray(new Component[]{btnNewButton, btnSettings, btnAbout, btnQuit}));

	}
}
