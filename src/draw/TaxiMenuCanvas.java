package draw;

import java.awt.Graphics;
import java.util.ArrayList;
import javax.swing.JPanel;

import model.TripLockedTime;

/**
 * JPanel canvas used for drawing manue
 * 
 * @author Nicolai
 *
 */
 
@SuppressWarnings("serial")
public class TaxiMenuCanvas extends JPanel {

	public TaxiMenu taxiMenu = new TaxiMenu();

	/**
     * Constructor
     * 
     * Initialize components
     */
    public TaxiMenuCanvas() {
        initComponents();
    }

    /**
     * Draw Manu
     * Redrawn with method <repaint()>
     */
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        taxiMenu.draw(g);
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
     * Call Up in TaxiMenu object
     * repaint
     */
    public void UP() {
        taxiMenu.Up();
        repaint();
    }

    /**
     * Call Dwon in TaxiMenu object
     * repaint
     */
    public void DW() {
        taxiMenu.Down();
        repaint();
    }
    
    /**
     * Set new triplist in TaxiMenu object
     * repaint
     * 
     * @param tripList
     */
    public void setTripList(ArrayList<TripLockedTime> tripList) {
    	taxiMenu.setTripList(tripList);
    	repaint();
    }
}
