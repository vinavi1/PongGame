package pack;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JSlider;
import javax.swing.JScrollBar;
import javax.swing.JTree;
import javax.swing.JComboBox;
import javax.swing.JTextField;
import javax.swing.JLabel;
import javax.swing.SwingConstants;

public class NewGame extends JPanel {
	private JTextField textField;
	private JTextField textField_1;
	private JTextField textField_2;
	private JTextField textField_3;
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
	
	public NewGame() {
		setLayout(null);
		
		String levels[] = {"Easy","Medium","Hard"};
		JComboBox comboBox = new JComboBox(levels);
		comboBox.setBackground(Color.ORANGE);
		comboBox.setSelectedIndex(0);
		comboBox.setFont(new Font("Snap ITC", Font.PLAIN, 18));
		comboBox.setBounds(306, 270, 216, 22);
		add(comboBox);
		
		JLabel lblDifficulty = new JLabel("Difficulty");
		lblDifficulty.setHorizontalAlignment(SwingConstants.CENTER);
		lblDifficulty.setForeground(Color.RED);
		lblDifficulty.setFont(new Font("Snap ITC", Font.BOLD, 18));
		lblDifficulty.setBounds(138, 261, 156, 40);
		add(lblDifficulty);
		
		JLabel lblPlayers = new JLabel("Players");
		lblPlayers.setHorizontalAlignment(SwingConstants.CENTER);
		lblPlayers.setForeground(Color.RED);
		lblPlayers.setFont(new Font("Snap ITC", Font.BOLD, 18));
		lblPlayers.setBounds(138, 359, 156, 40);
		add(lblPlayers);
		
		JComboBox comboBox_1 = new JComboBox(new Integer[]{1,2,3,4});
		comboBox_1.setSelectedIndex(3);
		comboBox_1.setFont(new Font("Snap ITC", Font.PLAIN, 18));
		comboBox_1.setBackground(Color.ORANGE);
		comboBox_1.setBounds(306, 370, 216, 22);
		add(comboBox_1);
		
		JButton btnNewButton = new JButton("START");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				PongGame pong = new PongGame();
				pong.main(null);
			}
		});
		btnNewButton.setForeground(Color.RED);
		btnNewButton.setFont(new Font("Snap ITC", Font.PLAIN, 22));
		btnNewButton.setBounds(186, 817, 336, 99);
		add(btnNewButton);
		
		JButton btnBack = new JButton("Back");
		btnBack.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
					cardlayout.card.show(cardlayout.container,"2");
			}
		});
		btnBack.setForeground(Color.RED);
		btnBack.setFont(new Font("Stencil", Font.ITALIC, 16));
		btnBack.setBounds(138, 13, 97, 25);
		add(btnBack);
		
		JLabel lblPlayer = new JLabel("Player1");
		lblPlayer.setHorizontalAlignment(SwingConstants.CENTER);
		lblPlayer.setForeground(Color.RED);
		lblPlayer.setFont(new Font("Snap ITC", Font.BOLD, 18));
		lblPlayer.setBounds(138, 427, 156, 40);
		add(lblPlayer);
		
		JLabel lblPlayer_1 = new JLabel("Player2");
		lblPlayer_1.setHorizontalAlignment(SwingConstants.CENTER);
		lblPlayer_1.setForeground(Color.RED);
		lblPlayer_1.setFont(new Font("Snap ITC", Font.BOLD, 18));
		lblPlayer_1.setBounds(138, 493, 156, 40);
		add(lblPlayer_1);
		
		JLabel lblPlayer_2 = new JLabel("Player3");
		lblPlayer_2.setHorizontalAlignment(SwingConstants.CENTER);
		lblPlayer_2.setForeground(Color.RED);
		lblPlayer_2.setFont(new Font("Snap ITC", Font.BOLD, 18));
		lblPlayer_2.setBounds(138, 558, 156, 40);
		add(lblPlayer_2);
		
		JLabel lblPlayer_3 = new JLabel("Player4");
		lblPlayer_3.setHorizontalAlignment(SwingConstants.CENTER);
		lblPlayer_3.setForeground(Color.RED);
		lblPlayer_3.setFont(new Font("Snap ITC", Font.BOLD, 18));
		lblPlayer_3.setBounds(138, 627, 156, 40);
		add(lblPlayer_3);
		
		textField = new JTextField();
		textField.setBackground(Color.ORANGE);
		textField.setFont(new Font("Snap ITC", Font.ITALIC, 18));
		textField.setBounds(306, 438, 216, 22);
		add(textField);
		textField.setColumns(10);
		
		textField_1 = new JTextField();
		textField_1.setBackground(Color.ORANGE);
		textField_1.setFont(new Font("Snap ITC", Font.ITALIC, 18));
		textField_1.setColumns(10);
		textField_1.setBounds(306, 504, 216, 22);
		add(textField_1);
		
		textField_2 = new JTextField();
		textField_2.setBackground(Color.ORANGE);
		textField_2.setFont(new Font("Snap ITC", Font.ITALIC, 18));
		textField_2.setColumns(10);
		textField_2.setBounds(306, 569, 216, 22);
		add(textField_2);
		
		textField_3 = new JTextField();
		textField_3.setBackground(Color.ORANGE);
		textField_3.setFont(new Font("Snap ITC", Font.ITALIC, 18));
		textField_3.setColumns(10);
		textField_3.setBounds(306, 638, 216, 22);
		add(textField_3);
		
		comboBox_1.addItemListener(new ItemListener() {
			@Override
			public void itemStateChanged(ItemEvent e) {
				String players = e.getItem().toString();
				switch(players){
				case "1":
					lblPlayer_1.setVisible(false);
					textField_1.setVisible(false);
					lblPlayer_2.setVisible(false);
					textField_2.setVisible(false);
					lblPlayer_3.setVisible(false);
					textField_3.setVisible(false);
					break;
				case "2":
					lblPlayer_1.setVisible(true);
					textField_1.setVisible(true);
					lblPlayer_2.setVisible(false);
					textField_2.setVisible(false);
					lblPlayer_3.setVisible(false);
					textField_3.setVisible(false);
					break;
				case "3":
					lblPlayer_1.setVisible(true);
					textField_1.setVisible(true);
					lblPlayer_2.setVisible(true);
					textField_2.setVisible(true);
					lblPlayer_3.setVisible(false);
					textField_3.setVisible(false);
					break;
				case "4":
					lblPlayer_1.setVisible(true);
					textField_1.setVisible(true);
					lblPlayer_2.setVisible(true);
					textField_2.setVisible(true);
					lblPlayer_3.setVisible(true);
					textField_3.setVisible(true);
					break;
				}
			}
		});	
	}
}
