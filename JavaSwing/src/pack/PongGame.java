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
import java.util.Random;

import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.Timer;
import javax.swing.WindowConstants;

public class PongGame extends JComponent implements ActionListener,KeyListener{
	int xd=700,yd=600;
	double padvel=0.75;
	double paddle1x=400,paddle1y=yd-20;
	double paddle2x=10,paddle2y=225;
	double paddle3x=400,paddle3y=10;
	double paddle4x=xd-20,paddle4y=225;
	double paddle1vx=0;
	double paddle3vx=0;
	double paddle2vy=0;
	double paddle4vy=0;
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

		g.setColor(new Color(110, 62,23));
		Rectangle2D rectangle4 = new Rectangle2D.Double(paddle4x,paddle4y,10,120);
		g2.fill(rectangle4);
		
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
			predLocation=predict(3,3);
		}
		if(ballx>=paddle1x-43.5&&ballx<=paddle1x+103.5&&bally<=paddle1y+10&&bally>=paddle1y-40){
			velx=-velx+(ballx-paddle1x-40)*deviation;
			scored(0);
			predLocation=predict(3,3);
		}
		if(ballx>=paddle3x-40&&ballx<=paddle3x+100&&bally<=paddle3y+13.5&&bally>=paddle3y-43.5){
			vely=-vely;
			velx=velx+(ballx-paddle3x-40)*deviation;
			scored(2);
			predLocation=predict(1,3);
		}
		if(ballx>=paddle3x-43.5&&ballx<=paddle3x+103.5&&bally<=paddle3y+10&&bally>=paddle3y-40){
			velx=-velx+(ballx-paddle3x-40)*deviation;
			scored(2);
			predLocation=predict(1,3);
		}
		if(bally>=paddle2y-40&&bally<=paddle2y+100&&ballx<=paddle2x+13.5&&ballx>=paddle2x-43.5){
			velx=-velx;
			vely=vely+(bally-paddle2y-40)*deviation;
			scored(1);
			predLocation=predict(4,3);
		}
		if(bally>=paddle2y-43.5&&bally<=paddle2y+103.5&&ballx<=paddle2x+10&&ballx>=paddle2x-40){
			vely=-vely+(bally-paddle2y-40)*deviation;
			scored(1);
			predLocation=predict(4,3);
		}
		if(bally>=paddle4y-40&&bally<=paddle4y+100&&ballx<=paddle4x+13.5&&ballx>=paddle4x-43.5){
			velx=-velx;
			vely=vely+(bally-paddle4y-40)*deviation;
			scored(1);
			predLocation=predict(2,3);
		}
		if(bally>=paddle4y-43.5&&bally<=paddle4y+103.5&&ballx<=paddle4x+10&&ballx>=paddle4x-40){
			vely=-vely+(bally-paddle4y-40)*deviation;
			scored(1);
			predLocation=predict(2,3);
		}
		if(ballx <0 || ballx >xd-40){
			velx = -velx;
		}
		if(bally <0 || bally >yd-40){
			vely = -vely;
		}
		if(velx>2||vely>2){
			velx=1;
			vely=1.5;
		}
		if(predLocation!=0){
			if(predLocation-40>paddle3x){
				paddle3vx=padvel;
			}
			else{
				paddle3vx=-padvel;
			}
		}
		follow(1);
		ballx = ballx + velx;
		bally = bally + vely;
		paddle1x = paddle1x + paddle1vx;
		paddle2y=paddle2y + paddle2vy;
		paddle3x = paddle3x + paddle3vx;
		paddle4y=paddle4y+paddle4vy;
		repaint();
	}
	
	public void follow(int i){
		switch(i){
		case 1:
			if(paddle1x+40>ballx)
				paddle1vx=-padvel;
			else
				paddle1vx=padvel;
			break;
		case 2:
			if(paddle2y+40<bally)
				paddle2vy=padvel;
			else
				paddle2vy=-padvel;
			break;
		case 3:
			if(paddle3x+40>ballx)
				paddle3vx = -padvel;
			else
				paddle3vx=padvel;
			break;
		}
	}
	
	public double predict(int opponent,int comp){
		double temp = 0;
		switch(opponent){
		case 3:
			temp=(yd-80)*Math.abs(velx/vely);
			break;
		case 1:
			temp=2*(yd-80)*Math.abs(velx/vely);
			break;
		case 2:
			if(vely<0){
				switch(comp){
				case 1:
					temp=(yd-80)*Math.abs(velx/vely)+(bally-20)*Math.abs(velx/vely);
					break;
				case 3:
					temp=(bally-20)*Math.abs(velx/vely);
					break;
				}	
			}
			else{
				if(comp==1)
					temp=(yd-bally-60)*Math.abs(velx/vely);
				else
					temp=(yd-bally-60)*Math.abs(velx/vely)+(yd-80)*Math.abs(velx/vely);
			}
			break;
		case 4:
			if(vely<0){
				if(comp==1)
					temp=(yd-80)*Math.abs(velx/vely)+(bally-20)*Math.abs(velx/vely);
				else
					temp=(bally-20)*Math.abs(velx/vely);
			}
			else{
				if(comp==1)
					temp=(yd-bally-60)*Math.abs(velx/vely);
				else
					temp=(yd-bally-60)*Math.abs(velx/vely)+(yd-80)*Math.abs(velx/vely);
			}
			break;
		}
		if(velx<0){
			double ch=xd-40+2;
			int k;
			double ballxtemp=ballx;
			if(temp<ballxtemp){
				return (ballxtemp-temp);
			}
			else{
				for(k=0;ch>xd-40;k++){
					ch = temp-ballxtemp-k*(xd-20);
				}
				if(k%2!=0){
					return ch-40;
				}
				else{
					return xd-ch-40-40;
				}
			}
		}
		else{
			double ch=xd+2-40;
			int k;
			if(temp<xd-ballx-40){
				return (ballx+temp);	//
			}
			else{
				for(k=0;ch>xd-40;k++){
					ch = ballx+temp-k*(xd-20);
				}
				if(k%2==0){
					return xd-ch-40-40;
				}
				else{
					return (ch-40);
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
		case KeyEvent.VK_P:
			paddle4vy=-padvel;
			break;
		case KeyEvent.VK_L:
			paddle4vy =padvel;
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
		case KeyEvent.VK_P:
			paddle4vy=0;
			break;
		case KeyEvent.VK_L:
			paddle4vy =0;
			break;
		}
	}

	@Override
	public void keyTyped(KeyEvent e) {
		
	}
	
	public void predictor(String s,int paddleno){
		
	}
}
