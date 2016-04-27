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
	public static JPanel home = new JPanel();
	public static JPanel settings = new JPanel();
	public static JPanel nop = new JPanel();
	
	private static JButton j[] = new JButton[4];
	
	public cardlayout() {
		container.setLayout(card);
		home.setLayout(null);
		settings.setLayout(null);
		nop.setLayout(null);
		
		for(int i=0;i<j.length;i++){
			j[i]=new JButton(i+"");
			j[i].setBounds(100,150, 100, 40);
			j[i].addActionListener(l);
		}
		
		home.add(j[0]);
		settings.add(j[1]);
		nop.add(j[2]);
		
		home.setBackground(Color.BLUE);
		settings.setBackground(Color.red);
		nop.setBackground(Color.cyan);
		
		setSize(getMaximumSize());
		setLocationRelativeTo(null);
		setDefaultCloseOperation(getDefaultCloseOperation());
		
		container.add(home,"2");
		container.add(settings,"3");
		container.add(nop,"4");
		
		card.show(container, "8");
	}
	
	public static void main(String args[]){
		
		cardlayout c = new cardlayout();
		
		c.add(container);
		c.setVisible(true);
	}
	
	ActionListener l = new ActionListener() {
		@Override
		public void actionPerformed(ActionEvent e) {
			if(e.getSource()==j[0]){
				card.show(container, "3");
			}
			
			if(e.getSource()==j[1]){
				card.show(container, "4");
			}
			
			if(e.getSource()==j[2]){
				card.show(container, "2");
			}
		}
	};
}
