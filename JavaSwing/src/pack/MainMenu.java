package pack;

import javax.swing.JPanel;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;

import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.awt.Color;
import org.eclipse.wb.swing.FocusTraversalOnArray;
import java.awt.Component;
import java.awt.BorderLayout;
import java.awt.Canvas;

public class MainMenu extends JPanel {
	
	public JButton btnNewButton;
	JButton btnAbout;
	public JButton btnQuit;
	/**
	 * Create the panel.
	 */
	@Override
	  protected void paintComponent(Graphics g) {
		BufferedImage image = null;
		try {
			image = ImageIO.read(new File("C:/Users/Vineeth Patibandla/Desktop/mess.jpg"));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		Image mimage=image.getScaledInstance(super.getWidth(),super.getHeight(), Image.SCALE_SMOOTH);
	    super.paintComponent(g);
	    g.drawImage(mimage, 0, 0, null);
	}
	
	public MainMenu() {
		setLayout(null);
		
		btnNewButton = new JButton("New Game");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				cardlayout.card.show(cardlayout.container,"3");
			}
		});
		
		btnNewButton.setForeground(Color.RED);
		btnNewButton.setBackground(Color.LIGHT_GRAY);
		btnNewButton.setFont(new Font("Snap ITC", Font.BOLD, 18));
		btnNewButton.setBounds(65, 313, 413, 58);
		add(btnNewButton);
		
		btnAbout = new JButton("About");
		btnAbout.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				cardlayout.card.show(cardlayout.container,"5");
			}
		});
		btnAbout.setForeground(Color.RED);
		btnAbout.setFont(new Font("Snap ITC", Font.BOLD, 18));
		btnAbout.setBackground(Color.LIGHT_GRAY);
		btnAbout.setBounds(65, 456, 413, 58);
		add(btnAbout);
		
		btnQuit = new JButton("Quit");
		btnQuit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.exit(0);
			}
		});
		btnQuit.setForeground(Color.RED);
		btnQuit.setFont(new Font("Snap ITC", Font.BOLD, 18));
		btnQuit.setBackground(Color.LIGHT_GRAY);
		btnQuit.setBounds(65, 598, 413, 58);
		add(btnQuit);
		setFocusTraversalPolicy(new FocusTraversalOnArray(new Component[]{btnNewButton}));
		
	}
}
