package pack;

import java.awt.CardLayout;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class cardlayout extends JFrame{
	
	public static CardLayout card = new CardLayout();
	
	public static JPanel container = new JPanel();
	public static MainMenu home = new MainMenu();
	public static NewGame newgame = new NewGame();
	public static JPanel nop = new JPanel();
	
	private static JButton j[] = new JButton[4];
	
	public cardlayout() {
		container.setLayout(card);
		newgame.setLayout(null);
		nop.setLayout(null);
		
		nop.setBackground(Color.cyan);
		
		setSize(getMaximumSize());
		setLocationRelativeTo(null);
		setDefaultCloseOperation(getDefaultCloseOperation());
		
		container.add(home,"2");
		container.add(newgame,"3");
		container.add(nop,"4");
		
		card.show(container, "8");
	}
	
	public static void main(String args[]){
		
		cardlayout c = new cardlayout();
		
		c.add(container);
		c.setVisible(true);
	}
}


