package taxiGUI;
import javax.swing.*;
import irProtocol.*;

//http://www.particle.kth.se/~lindsey/JavaCourse/Book/Part1/Java/Chapter12/catchingKeystrokes.html

import java.awt.event.*;
import java.awt.Color;
import java.awt.Dimension;
import java.util.TooManyListenersException;

@SuppressWarnings("serial")

public class SteeringWheelGUI extends JFrame {

	public SteeringWheelGUI() throws TooManyListenersException {
		
		final ApplicationLayer appLayer = new ApplicationLayer("/dev/ttyUSB0");
		
		JPanel canvas = new JPanel ();
		add (canvas, "Center");
		canvas.setBackground (Color.BLUE);
		canvas.setPreferredSize(new Dimension(350, 125));
		
		canvas.addKeyListener(new KeyAdapter() {
			public void keyPressed(KeyEvent e) {
				// Up
				if(e.getKeyCode() == 38) {
					appLayer.Sender("UP");
				}
				// Down
				if(e.getKeyCode() == 40) {
					appLayer.Sender("DW");
				}
				// Enter / Accept / Finish
				if(e.getKeyCode() == 10) {
					appLayer.Sender("AC");
				}
				// Backspace / Decline / Request new Taxi
				if(e.getKeyCode() == 8) {
					appLayer.Sender("DC");
				}
				// Alt / Zoom In
				if(e.getKeyCode() == 16) {
					appLayer.Sender("ZI");
				}
				// Ctrl / Zoom Out
				if(e.getKeyCode() == 17) {
					appLayer.Sender("ZO");
				}
			} 
		});
		
		canvas.setFocusable (true);
    }

    public static void main(String[] args) throws TooManyListenersException {

    	SteeringWheelGUI SWG = new SteeringWheelGUI();
    	SWG.setVisible(true);
    	SWG.setPreferredSize(new Dimension(500,500));
    	SWG.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }
}
