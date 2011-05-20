package draw;

import java.awt.Graphics;
import java.util.ArrayList;
import javax.swing.JPanel;

import model.TripLockedTime;

/**
 * JPanel canvas used for drawing map
 * 
 * @author Nicolai
 *
 */
 
@SuppressWarnings("serial")
public class TaxiMapCanvas extends JPanel {

    public TaxiMap taxiMap = new TaxiMap();

    /**
     * Constructor
     * 
     * Initialize components
     */
    public TaxiMapCanvas() {
        initComponents();
    }

    /**
     * Draw map
     * Redrawn with method <repaint()>
     */
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        taxiMap.draw(g);
    }

    /**
     * Set up components
     */
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
    
    /**
     * Set new triplist in TaxiMap object
     * repaint
     * 
     * @param tripList
     */
    public void setTripList(ArrayList<TripLockedTime> tripList) {
    	taxiMap.setTripList(tripList);
    	repaint();
    }
    
    /**
     * Call Up in TaxiMap object
     * repaint
     */
    public void UP() {
        taxiMap.Up();
        repaint();
    }

    /**
     * Call Down in TaxiMap object
     * repaint
     */
    public void DW() {
        taxiMap.Down();
        repaint();
    }
    
    /**
     * Call ZoomIn in TaxiMap object
     * repaint
     */
    public void zoomIn() {
    	taxiMap.zoomIn();
		repaint();
	}
    
    /**
     * Call ZoomOut in TaxiMap object
     * repaint
     */
	public void zoomOut() {
		taxiMap.zoomOut();
		repaint();
	}
}
