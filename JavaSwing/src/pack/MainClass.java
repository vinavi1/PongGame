package pack;

import javax.swing.JFrame;

public class MainClass {
	public static void main(String args[]){
		GraphicTrial graphicTrial = new GraphicTrial();
		JFrame frame = new JFrame();
		frame.add(graphicTrial);
		frame.setVisible(true);;
		frame.setSize(600, 400);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setTitle("Moving Ball");
	}
}
