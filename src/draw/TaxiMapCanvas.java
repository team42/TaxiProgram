package draw;
/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

import java.awt.Graphics;
import java.util.ArrayList;
import javax.swing.JPanel;

import model.TripLockedTime;


//* @author Nicolai
 
@SuppressWarnings("serial")
public class TaxiMapCanvas extends JPanel {

    public TaxiMap taxiMap = new TaxiMap();

    public TaxiMapCanvas() {
        initComponents();
    }

    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        taxiMap.draw(g);
    }

    private void initComponents() {

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 200, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 500, Short.MAX_VALUE)
        );
    }
    
    public void setTripList(ArrayList<TripLockedTime> tripList) {
    	taxiMap.setTripList(tripList);
    	repaint();
    }
    
    public void zoomIn() {
    	taxiMap.zoomIn();
		repaint();
	}
	
	public void zoomOut() {
		taxiMap.zoomOut();
		repaint();
	}
}
