package pack;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.geom.Ellipse2D;

import javax.swing.JPanel;
import javax.swing.Timer;

public class GraphicTrial extends JPanel implements ActionListener,KeyListener{
	
	Timer t = new Timer(5,this);
	double x=0,y=0,velx=0,vely=0;;
	
	public GraphicTrial(){
		t.start();
		addKeyListener(this);
		setFocusable(true);
		setFocusTraversalKeysEnabled(false);
	}
	
	public void paintComponent(Graphics g){
		super.paintComponent(g);
		Graphics2D g2 = (Graphics2D) g;
		Ellipse2D circle = new Ellipse2D.Double(x,y,40,40);
		g2.fill(circle);
	}
	
	@Override
	public void actionPerformed(ActionEvent arg0) {
		x = x+velx;
		y= y+vely;
		repaint();
	}

	@Override
	public void keyPressed(KeyEvent e) {

		int code = e.getKeyCode();
		switch(code){
		case KeyEvent.VK_UP:
			vely = -1.5;
			velx =0;
			break;
		case KeyEvent.VK_DOWN:
			vely = 1.5;
			velx =0;
			break;
		case KeyEvent.VK_LEFT:
			velx = -1.5;
			vely =0;
			break;
		case KeyEvent.VK_RIGHT:
			velx = 1.5;
			vely =0;
			break;
		}		
	}

	@Override
	public void keyReleased(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void keyTyped(KeyEvent e) {
		
	}
}
