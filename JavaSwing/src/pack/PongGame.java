package pack;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Window;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionListener;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Rectangle2D;

import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.Timer;
import javax.swing.WindowConstants;

public class PongGame extends JComponent implements ActionListener,KeyListener{
	int xd=700,yd=800;
	double padvel=0.75;
	double paddle1x=400,paddle1y=yd-30;
	double paddle2x=30,paddle2y=225;
	double paddle3x=400,paddle3y=10;
	double paddle1vx=0;
	double paddle3vx=0;
	double paddle2vy=0;
	double deviation = 0.01;
	double ballx=440,bally=500;
	double velx=0.5,vely=0.75;
	static JLabel scoreLabel1;
	int[] score={0,0,0,0};
	double predLocation=0;
	
	public static void main(String args[]){
		JFrame window = new JFrame("Pong game");
		PongGame game = new PongGame();
		scoreLabel1 = new JLabel("1st player:0");
		scoreLabel1.setBounds(100,10,600,100);
		scoreLabel1.setFont(new Font("ssssssss",Font.BOLD,20));
		scoreLabel1.setVisible(true);
		window.add(scoreLabel1);
		window.add(game);
		window.pack();
		window.setVisible(true);
		Timer t = new Timer(2,game);
		t.start();
		window.addKeyListener(game);
	}
	
	public void scored(int x){
		score[x]++;
		String s = "1st player : "+score[0]+", 2nd player :"+score[1]+", 3rd player :"+score[2]+
				", 4th player :"+score[3];
		scoreLabel1.setText(s);
	}
	
	public Dimension getPreferredSize(){
		return new Dimension(xd,yd);
	}
	
	@Override
	public void paintComponent(Graphics g){
		Graphics2D g2 = (Graphics2D)g;
		
		g.setColor(new Color(110, 62,23));
		Rectangle2D rectangle1 = new Rectangle2D.Double(paddle1x,paddle1y,120,10);
		g2.fill(rectangle1);
		
		g.setColor(new Color(110, 62,23));
		Rectangle2D rectangle2 = new Rectangle2D.Double(paddle2x,paddle2y,10,120);
		g2.fill(rectangle2);
		
		g.setColor(new Color(110, 62,23));
		Rectangle2D rectangle3 = new Rectangle2D.Double(paddle3x,paddle3y,120,10);
		g2.fill(rectangle3);
		
		g.setColor(new Color(100, 200, 200));
		Ellipse2D ellipse2d = new Ellipse2D.Double(ballx,bally,40,40);
		g2.fill(ellipse2d);
	}

	@Override
	public void actionPerformed(ActionEvent arg0) {
		/*if(ballx>=paddle1x-40&&ballx<=paddle1x+100&&bally<=paddle1y+10&&bally>=paddle1y-40){
			vely=-vely;
			velx=velx+(ballx-paddle1x-40)*deviation;
			scored(0);
		}*/
		if(ballx>=paddle1x-40&&ballx<=paddle1x+100&&bally<=paddle1y+13.5&&bally>=paddle1y-43.5){
			vely=-vely;
			velx=velx+(ballx-paddle1x-40)*deviation;
			scored(0);
			predLocation=predict(0);
			System.out.println(predLocation);
		}
		if(ballx>=paddle1x-43.5&&ballx<=paddle1x+103.5&&bally<=paddle1y+10&&bally>=paddle1y-40){
			velx=-velx+(ballx-paddle1x-40)*deviation;
			scored(0);
			predLocation=predict(0);
			System.out.println(predLocation);
		}
		if(ballx>=paddle3x-40&&ballx<=paddle3x+100&&bally<=paddle3y+13.5&&bally>=paddle3y-43.5){
			vely=-vely;
			velx=velx+(ballx-paddle3x-40)*deviation;
			scored(2);
			predLocation=predict(2);
			System.out.println(predLocation);
		}
		if(ballx>=paddle3x-43.5&&ballx<=paddle3x+103.5&&bally<=paddle3y+10&&bally>=paddle3y-40){
			velx=-velx+(ballx-paddle3x-40)*deviation;
			scored(2);
			predLocation=predict(2);
			System.out.println(predLocation);
		}
		if(bally>=paddle2y-40&&bally<=paddle2y+100&&ballx<=paddle2x+13.5&&ballx>=paddle2x-43.5){
			velx=-velx;
			vely=vely+(bally-paddle2y-40)*deviation;
			scored(1);
		}
		if(bally>=paddle2y-43.5&&bally<=paddle2y+103.5&&ballx<=paddle2x+10&&ballx>=paddle2x-40){
			vely=-vely+(bally-paddle2y-40)*deviation;
			scored(1);
		}
		if(ballx <0 || ballx >xd-40){
			velx = -velx;
		}
		if(bally <0 || bally >yd-40){
			vely = -vely;
		}
		if(velx>5||vely>5){
			velx=1;
			vely=1.5;
		}
		if(paddle1x>xd-120||paddle1x<0){
			paddle1vx=0;
		}
		if(paddle3x>xd-120||paddle3x<0){
			paddle3vx=0;
		}
		if(predLocation!=0){
			if(predLocation>paddle1x){
				paddle1vx=padvel;
			}
			else{
				paddle1vx=-padvel;
			}
		}
		ballx = ballx + velx;
		bally = bally + vely;
		paddle1x = paddle1x + paddle1vx;
		paddle2y=paddle2y + paddle2vy;
		follow();
		paddle3x = paddle3x + paddle3vx;
		repaint();
	}
	
	public void follow(){
		if(paddle3x+40>ballx){
			paddle3vx = -padvel;
		}
		else{
			paddle3vx=padvel;
		}
	}
	
	public double predict(int x){
		double temp = 0;
		switch(x){
		case 2:
			temp=yd*Math.abs(velx/vely);
			break;
		case 0:
			temp=2*yd*Math.abs(velx/vely);
			break;
		}
		if(velx<0){
			double ch=xd+2;
			int k;
			if(temp<ballx){
				return (ballx-temp);
			}
			else{
				for(k=0;ch>xd;k++){
					ch = temp-ballx-k*xd;
				}
				if(k%2!=0){
					return ch-60;
				}
				else{
					return xd-ch-60;
				}
			}
		}
		else{
			double ch=xd+2;
			int k;
			if(temp<xd-ballx){
				return (ballx+temp);
			}
			else{
				for(k=0;ch>xd;k++){
					ch = ballx+temp-k*xd;
					System.out.println(ch+"-"+k);
				}
				if(k%2==0){
					return xd-ch-60;
				}
				else{
					return (ch-60);
				}
			}
		}
	}
	
	@Override
	public void keyPressed(KeyEvent e) {
		int keycode=e.getKeyCode();
		switch(keycode){
		case KeyEvent.VK_LEFT:
			paddle1vx=-padvel;
			break;
		case KeyEvent.VK_RIGHT:
			paddle1vx = padvel;
			break;
		case KeyEvent.VK_S:
			paddle2vy=padvel;
			break;
		case KeyEvent.VK_W:
			paddle2vy =-padvel;
			break;
		}
	}

	@Override
	public void keyReleased(KeyEvent e) {
		int keycode=e.getKeyCode();
		switch(keycode){
		case KeyEvent.VK_LEFT:
			paddle1vx=0;
			break;
		case KeyEvent.VK_RIGHT:
			paddle1vx = 0;
			break;
		case KeyEvent.VK_S:
			paddle2vy=0;
			break;
		case KeyEvent.VK_W:
			paddle2vy = 0;
			break;
		}
	}

	@Override
	public void keyTyped(KeyEvent e) {
		
	}

	
}
