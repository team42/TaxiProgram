package taxiGUI;
import javax.swing.*;

//http://www.particle.kth.se/~lindsey/JavaCourse/Book/Part1/Java/Chapter12/catchingKeystrokes.html

import java.awt.event.*;
import java.awt.Color;
import java.awt.Dimension;
import java.util.TooManyListenersException;

@SuppressWarnings("serial")

public class SWsimulation extends JFrame {

	public SWsimulation() throws TooManyListenersException {
		
		final TaxiModuleGUI tmGUI = new TaxiModuleGUI();
		
		JPanel canvas = new JPanel ();
		add (canvas, "Center");
		canvas.setBackground (Color.DARK_GRAY);
		
		canvas.addKeyListener(new KeyAdapter() {
			public void keyPressed(KeyEvent e) {
				// Up
				if(e.getKeyCode() == 38) {
					tmGUI.menuCanvas.UP();
					tmGUI.mapCanvas.UP();
				}
				// Down
				if(e.getKeyCode() == 40) {
					tmGUI.menuCanvas.DW();
					tmGUI.mapCanvas.DW();
				}
				// Enter / Accept / Finish
				if(e.getKeyCode() == 10) {
					tmGUI.Accept();
				}
				// Backspace / Decline / Request new Taxi
				if(e.getKeyCode() == 8) {
					tmGUI.Decline();
				}
				// Alt / Zoom In
				if(e.getKeyCode() == 16) {
					tmGUI.mapCanvas.zoomIn();
				}
				// Ctrl / Zoom Out
				if(e.getKeyCode() == 17) {
					tmGUI.mapCanvas.zoomOut();
				}
				
			} 
		});
		
		canvas.setFocusable (true);
    }

    public static void main(String[] args) throws TooManyListenersException {

    	SWsimulation SWS = new SWsimulation();
    	SWS.setVisible(true);
    	SWS.setPreferredSize(new Dimension(500,500));
    	SWS.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }
}