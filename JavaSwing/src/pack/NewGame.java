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
import javax.swing.JFrame;
import javax.swing.JTextField;
import javax.swing.JLabel;
import javax.swing.SwingConstants;

public class NewGame extends JPanel {
	private JTextField textField;
	private JTextField textField_1;
	private JTextField textField_2;
	private JTextField textField_3;
	private JTextField textField_4;
	private JTextField textField_5;
	private JTextField textField_6;
	private JTextField textField_7;
	private JTextField textField_8;
	private JTextField textField_9;
	private JTextField textField_10;
	private JTextField textField_11;
	private JTextField textField_12;
	private JTextField textField_13;
	private JTextField textField_14;
	/**
	 * Create the panel.
	 */
	@Override
	  protected void paintComponent(Graphics g) {
		BufferedImage image = null;
		try {
			image = ImageIO.read(new File("C:/Users/Akram Khan/Desktop/hqdefault.jpg"));
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
		comboBox.setBounds(306, 198, 216, 22);
		add(comboBox);
		
		JLabel lblDifficulty = new JLabel("Difficulty");
		lblDifficulty.setHorizontalAlignment(SwingConstants.CENTER);
		lblDifficulty.setForeground(Color.RED);
		lblDifficulty.setFont(new Font("Snap ITC", Font.BOLD, 18));
		lblDifficulty.setBounds(138, 189, 156, 40);
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
		
		textField_4 = new JTextField();
		textField_4.setFont(new Font("Snap ITC", Font.ITALIC, 18));
		textField_4.setColumns(10);
		textField_4.setBackground(Color.ORANGE);
		textField_4.setBounds(607, 438, 216, 22);
		add(textField_4);
		
		textField_5 = new JTextField();
		textField_5.setFont(new Font("Snap ITC", Font.ITALIC, 18));
		textField_5.setColumns(10);
		textField_5.setBackground(Color.ORANGE);
		textField_5.setBounds(607, 504, 216, 22);
		add(textField_5);
		
		textField_6 = new JTextField();
		textField_6.setFont(new Font("Snap ITC", Font.ITALIC, 18));
		textField_6.setColumns(10);
		textField_6.setBackground(Color.ORANGE);
		textField_6.setBounds(607, 569, 216, 22);
		add(textField_6);
		
		textField_7 = new JTextField();
		textField_7.setFont(new Font("Snap ITC", Font.ITALIC, 18));
		textField_7.setColumns(10);
		textField_7.setBackground(Color.ORANGE);
		textField_7.setBounds(607, 638, 216, 22);
		add(textField_7);
		
		textField_8 = new JTextField();
		textField_8.setFont(new Font("Snap ITC", Font.ITALIC, 18));
		textField_8.setColumns(10);
		textField_8.setBackground(Color.ORANGE);
		textField_8.setBounds(906, 438, 216, 22);
		add(textField_8);
		
		textField_9 = new JTextField();
		textField_9.setFont(new Font("Snap ITC", Font.ITALIC, 18));
		textField_9.setColumns(10);
		textField_9.setBackground(Color.ORANGE);
		textField_9.setBounds(906, 504, 216, 22);
		add(textField_9);
		
		textField_10 = new JTextField();
		textField_10.setFont(new Font("Snap ITC", Font.ITALIC, 18));
		textField_10.setColumns(10);
		textField_10.setBackground(Color.ORANGE);
		textField_10.setBounds(906, 569, 216, 22);
		add(textField_10);
		
		textField_11 = new JTextField();
		textField_11.setFont(new Font("Snap ITC", Font.ITALIC, 18));
		textField_11.setColumns(10);
		textField_11.setBackground(Color.ORANGE);
		textField_11.setBounds(906, 638, 216, 22);
		add(textField_11);
		
		JLabel lblPort = new JLabel("Port");
		lblPort.setHorizontalAlignment(SwingConstants.CENTER);
		lblPort.setForeground(Color.RED);
		lblPort.setFont(new Font("Showcard Gothic", Font.BOLD, 18));
		lblPort.setBounds(633, 373, 156, 40);
		add(lblPort);
		
		JLabel lblIp = new JLabel("IP");
		lblIp.setHorizontalAlignment(SwingConstants.CENTER);
		lblIp.setForeground(Color.RED);
		lblIp.setFont(new Font("Showcard Gothic", Font.BOLD, 18));
		lblIp.setBounds(932, 373, 156, 40);
		add(lblIp);
		
		textField_12 = new JTextField();
		textField_12.setFont(new Font("Snap ITC", Font.ITALIC, 18));
		textField_12.setColumns(10);
		textField_12.setBackground(Color.ORANGE);
		textField_12.setBounds(607, 272, 216, 22);
		add(textField_12);
		
		textField_13 = new JTextField();
		textField_13.setFont(new Font("Snap ITC", Font.ITALIC, 18));
		textField_13.setColumns(10);
		textField_13.setBackground(Color.ORANGE);
		textField_13.setBounds(906, 272, 216, 22);
		add(textField_13);
		
		textField_14 = new JTextField();
		textField_14.setFont(new Font("Snap ITC", Font.ITALIC, 18));
		textField_14.setColumns(10);
		textField_14.setBackground(Color.ORANGE);
		textField_14.setBounds(306, 274, 216, 22);
		add(textField_14);
		
		JLabel lblName = new JLabel("Your Name");
		lblName.setHorizontalAlignment(SwingConstants.CENTER);
		lblName.setForeground(Color.RED);
		lblName.setFont(new Font("Snap ITC", Font.BOLD, 18));
		lblName.setBounds(138, 263, 156, 40);
		add(lblName);
		
		JLabel lblYourPort = new JLabel("Your Port");
		lblYourPort.setHorizontalAlignment(SwingConstants.CENTER);
		lblYourPort.setForeground(Color.RED);
		lblYourPort.setFont(new Font("Showcard Gothic", Font.BOLD, 18));
		lblYourPort.setBounds(633, 203, 156, 40);
		add(lblYourPort);
		
		JLabel lblYourPort_1 = new JLabel("Your IP");
		lblYourPort_1.setHorizontalAlignment(SwingConstants.CENTER);
		lblYourPort_1.setForeground(Color.RED);
		lblYourPort_1.setFont(new Font("Showcard Gothic", Font.BOLD, 18));
		lblYourPort_1.setBounds(932, 203, 156, 40);
		add(lblYourPort_1);
		
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
