package pack;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Rectangle2D;
import java.io.IOException;
import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.Timer;

public class PongGame extends JComponent implements ActionListener,KeyListener{
	int framex=1000,framey=900;
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
	static JLabel missLabel1;
	int[] miss={0,0,0,0};
	double predLocation=0,predLocation1=0,predLocation3=0;
	public static JFrame window;
	MessageListener listener;
	int player_number;
	String players;
	String level;
	String pass;
	String message;
	int[] ports;
	int port;
	String[] hostnames;
	MessageTransmitter transmitter;
	
	public void main1(String args[]){
		window = new JFrame("Pong game");
		//PongGame game = new PongGame();
		missLabel1 = new JLabel("1st player:0");
		missLabel1.setBounds(100,10,600,100);
		missLabel1.setFont(new Font("ssssssss",Font.BOLD,20));
		missLabel1.setVisible(true);
		window.add(missLabel1);
		window.add(this);
		window.pack();
		window.setVisible(true);
		Timer t = new Timer(2,this);
		t.start();
		window.addKeyListener(this);
	}
	
	public void missed(int x){
		miss[x]++;
		String s = "1st player : "+miss[0]+", 2nd player :"+miss[1]+", 3rd player :"+miss[2]+
				", 4th player :"+miss[3];
		missLabel1.setText(s);
	}
	
	public Dimension getPreferredSize(){
		return new Dimension(framex,framey);
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
			missed(0);
		}*/
		paddlecollide(Integer.parseInt(level+players));
		wallcollide();
		if(velx>2||vely>2){
			velx=1;
			vely=1.5;
		}
		if(miss[0]>3){
			window.dispose();
		}
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
			temp=2*(yd-60)*Math.abs(velx/vely);
			break;
		case 2:
			if(vely<0){
				switch(comp){
				case 1:
					temp=(yd-60)*Math.abs(velx/vely)+(bally)*Math.abs(velx/vely);
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
					temp=(yd-bally-40)*Math.abs(velx/vely)+(yd-60)*Math.abs(velx/vely);
			}
			break;
		case 4:
			if(vely<0){
				if(comp==1)
					temp=(yd-60)*Math.abs(velx/vely)+(bally)*Math.abs(velx/vely);
				else
					temp=(bally-20)*Math.abs(velx/vely);
			}
			else{
				if(comp==1)
					temp=(yd-bally-60)*Math.abs(velx/vely);
				else
					temp=(yd-bally-40)*Math.abs(velx/vely)+(yd-60)*Math.abs(velx/vely);
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
					ch = temp-ballxtemp-k*(xd-40);
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
					ch = ballx+temp-k*(xd-40);
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
		switch(player_number) {
			case 1 :
				switch(keycode){
					case KeyEvent.VK_UP:
						paddle4vy=-padvel;
						message = "18";
						transmitter = new MessageTransmitter(message,hostnames,ports);
					    transmitter.start();// TODO add your handling code here:
						break;
					case KeyEvent.VK_DOWN:
						paddle4vy = padvel;
						message = "12";
						transmitter = new MessageTransmitter(message,hostnames,ports);
					    transmitter.start();// TODO add your handling code here:
						break;
				}
				break;
			case 2 :
				switch(keycode){
				case KeyEvent.VK_UP:
					paddle2vy=-padvel;
					message = "28";
					transmitter = new MessageTransmitter(message,hostnames,ports);
				    transmitter.start();// TODO add your handling code here:
					break;
				case KeyEvent.VK_DOWN:
					paddle2vy = padvel;
					message = "22";
					transmitter = new MessageTransmitter(message,hostnames,ports);
				    transmitter.start();// TODO add your handling code here:
					break;
				}
				break;
			case 3 :
				switch(keycode){
				case KeyEvent.VK_LEFT:
					paddle3vx=-padvel;
					message = "34";
					transmitter = new MessageTransmitter(message,hostnames,ports);
				    transmitter.start();// TODO add your handling code here:
					break;
				case KeyEvent.VK_RIGHT:
					paddle3vx = padvel;
					message = "36";
					transmitter = new MessageTransmitter(message,hostnames,ports);
				    transmitter.start();// TODO add your handling code here:
					break;
				}
				break;
			case 4 :
				switch(keycode){
				case KeyEvent.VK_LEFT:
					paddle1vx=-padvel;
					message = "44";
					transmitter = new MessageTransmitter(message,hostnames,ports);
				    transmitter.start();// TODO add your handling code here:
					break;
				case KeyEvent.VK_RIGHT:
					paddle1vx = padvel;
					message = "46";
					transmitter = new MessageTransmitter(message,hostnames,ports);
				    transmitter.start();// TODO add your handling code here:
					break;
				}
				break;
			}
	}

	@Override
	public void keyReleased(KeyEvent e) {
		int keycode=e.getKeyCode();
		switch(player_number) { 
		case 1 :
			switch(keycode){
				case KeyEvent.VK_UP:
					message = "10";
					transmitter = new MessageTransmitter(message,hostnames,ports);
				    transmitter.start();// TODO add your handling code here:
				    paddle4vy= 0;
					break;
				case KeyEvent.VK_DOWN:
					message = "10";
					transmitter = new MessageTransmitter(message,hostnames,ports);
				    transmitter.start();// TODO add your handling code here:
				    paddle4vy= 0;
					break;
			}
			break;
		case 2 :
			switch(keycode){
			case KeyEvent.VK_UP:
				message = "20";
				transmitter = new MessageTransmitter(message,hostnames,ports);
			    transmitter.start();// TODO add your handling code here:
			    paddle2vy= 0;
				break;
			case KeyEvent.VK_DOWN:
				message = "20";
				transmitter = new MessageTransmitter(message,hostnames,ports);
			    transmitter.start();// TODO add your handling code here:
			    paddle2vy= 0;
				break;
			}
			break;
		case 3 :
			switch(keycode){
			case KeyEvent.VK_LEFT:
				
				message = "30";
				transmitter = new MessageTransmitter(message,hostnames,ports);
			    transmitter.start();// TODO add your handling code here:
			    paddle3vx= 0;
				break;
			case KeyEvent.VK_RIGHT:
				message = "30";
				transmitter = new MessageTransmitter(message,hostnames,ports);
			    transmitter.start();// TODO add your handling code here:
			    paddle3vx= 0;
				break;
			}
			break;
		case 4 :
			switch(keycode){
			case KeyEvent.VK_LEFT:
				message = "40";
				transmitter = new MessageTransmitter(message,hostnames,ports);
			    transmitter.start();// TODO add your handling code here:
			    paddle1vx= 0;
				break;
			case KeyEvent.VK_RIGHT:
				message = "40";
				transmitter = new MessageTransmitter(message,hostnames,ports);
			    transmitter.start();// TODO add your handling code here:
			    paddle1vx= 0;
				break;
			}
			break;
		}
	}

	@Override
	public void keyTyped(KeyEvent e) {
		
	}
	
	public void wallcollide(){
		if(ballx <0){
			velx = -velx;
			missed(0);
		}
		if(ballx>xd-40){
			velx=-velx;
			missed(3);
		}
		if(bally <0){
			vely = -vely;
			missed(1);
		}
		if(bally >yd-40){
			vely=-vely;
			missed(2);
		}
	}
	
	public void paddlecollide(int difplay){
		if(ballx>=paddle1x-40&&ballx<=paddle1x+100&&bally<=paddle1y+13.5&&bally>=paddle1y-43.5){
			vely=-vely;
			velx=velx+(ballx-paddle1x-40)*deviation;
			switch(difplay){
			case 21:
			case 23:
			case 22:
			case 33:
				predLocation1=predict(1,1);
				break;
			case 31:
			case 32:
				predLocation1=predict(1,1);
				predLocation3=predict(3,3);
				break;
			}
		}
		if(ballx>=paddle1x-43.5&&ballx<=paddle1x+103.5&&bally<=paddle1y+10&&bally>=paddle1y-40){
			velx=-velx+(ballx-paddle1x-40)*deviation;			
			switch(difplay){
			case 21:
			case 23:
			case 22:
			case 33:
				predLocation1=predict(1,1);
				break;
			case 31:
			case 32:
				predLocation1=predict(1,1);
				predLocation3=predict(3,3);
				break;
			}
		}
		if(ballx>=paddle3x-40&&ballx<=paddle3x+100&&bally<=paddle3y+13.5&&bally>=paddle3y-43.5){
			vely=-vely;
			velx=velx+(ballx-paddle3x-40)*deviation;
			switch(difplay){
			case 21:
			case 22:
			case 23:
			case 33:
				predLocation1=predict(3,1);
				break;
			case 31:
			case 32:
				predLocation1=predict(3,1);
				predLocation3=predict(1,3);
			}
		}
		if(ballx>=paddle3x-43.5&&ballx<=paddle3x+103.5&&bally<=paddle3y+10&&bally>=paddle3y-40){
			velx=-velx+(ballx-paddle3x-40)*deviation;
			switch(difplay){
			case 21:
			case 22:
			case 23:
			case 33:
				predLocation1=predict(3,1);
				break;
			case 31:
			case 32:
				predLocation1=predict(3,1);
				predLocation3=predict(1,3);
			}
		}
		if(bally>=paddle2y-40&&bally<=paddle2y+100&&ballx<=paddle2x+13.5&&ballx>=paddle2x-43.5){
			velx=-velx;
			vely=vely+(bally-paddle2y-40)*deviation;
			switch(difplay){
			case 21:
			case 22:
			case 23:
			case 33:
				predLocation1=predict(2,1);
				break;
			case 31:
			case 32:
				predLocation1=predict(2,1);
				predLocation3=predict(4,3);
				break;
			}
		}
		if(bally>=paddle2y-43.5&&bally<=paddle2y+103.5&&ballx<=paddle2x+10&&ballx>=paddle2x-40){
			vely=-vely+(bally-paddle2y-40)*deviation;
			switch(difplay){
			case 21:
			case 22:
			case 23:
			case 33:
				predLocation1=predict(2,1);
				break;
			case 31:
			case 32:
				predLocation1=predict(2,1);
				predLocation3=predict(4,3);
				break;
			}
		}
		if(bally>=paddle4y-40&&bally<=paddle4y+100&&ballx<=paddle4x+13.5&&ballx>=paddle4x-43.5){
			velx=-velx;
			vely=vely+(bally-paddle4y-40)*deviation;
			switch(difplay){
			case 21:
			case 22:
			case 23:
			case 33:
				predLocation1=predict(4,1);
				break;
			case 31:
			case 32:
				predLocation1=predict(4,1);
				predLocation3=predict(2,3);
				break;
			}
		}
		if(bally>=paddle4y-43.5&&bally<=paddle4y+103.5&&ballx<=paddle4x+10&&ballx>=paddle4x-40){
			vely=-vely+(bally-paddle4y-40)*deviation;
			switch(difplay){
			case 21:
			case 22:
			case 23:
			case 33:
				predLocation1=predict(4,1);
				break;
			case 31:
			case 32:
				predLocation1=predict(4,1);
				predLocation3=predict(2,3);
				break;
			}
		}
		if(predLocation1!=0){
			Random r = new Random();
			int random=r.nextInt(20)-10;
			if(predLocation1-40+random>paddle1x){
				paddle1vx=padvel;
			}
			else{
				paddle1vx=-padvel;
			}
		}
		if(predLocation3!=0){
			Random r = new Random();
			int random=r.nextInt(20)-10;
			if(predLocation3-40+random>paddle3x){
				paddle3vx=padvel;
			}
			else{
				paddle3vx=-padvel;
			}
		}
		switch(difplay){
		case 11:
			follow(1);follow(2);follow(3);break;
		case 12:
			follow(1);follow(3);break;
		case 13:
			follow(1);break;
		case 21:
			follow(2);follow(3);break;
		case 22:
			follow(3);break;
		case 31:
			follow(2);break;
		}
	}
	
	public void prematch(int port,int player_number,String players,String level,String[] hostnames,int[] ports){
	 	this.player_number = player_number;
	 	this.players = players ;
	 	this.hostnames = hostnames;
	 	this.ports = ports;
	 	this.port=port;
	 	this.level = level;
	 	if(!players.equals("1")){
		 try {
	            listener = new MessageListener(this,port);
	            listener.start();
	        } catch (IOException ex) {
	            Logger.getLogger(PongGame.class.getName()).log(Level.SEVERE, null, ex);
	        }
	 	}
		 main1(null);
	}
	
	public void modify(String s){
		int i=Integer.parseInt(s);
		switch(i){
		case 18:
			paddle4vy=-padvel;
			break;
		case 12:
			paddle4vy=padvel;
			break;
		case 28:
			paddle2vy=-padvel;
			break;
		case 22:
			paddle2vy=padvel;
			break;
		case 34:
			paddle3vx=-padvel;
			break;
		case 36:
			paddle3vx=padvel;
			break;
		case 44:
			paddle1vx=-padvel;
			break;
		case 46:
			paddle1vx=padvel;
			break;
		case 10:
			paddle4vy=0;
			break;
		case 20:
			paddle2vy=0;
			break;
		case 30:
			paddle3vx=0;
			break;
		case 40:
			paddle1vx=0;
			break;
		}
		actionPerformed(null);
	}
}
