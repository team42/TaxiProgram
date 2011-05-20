package draw;

import java.awt.*;
import java.util.ArrayList;

import model.*;

/**
 * Class for drawing the taxi menu
 * Draws the triplist menu
 * Colour codes are used as indicater
 * Thick line is indicating which trip is currently selected
 * 
 * @author Nicolai
 *
 */

public class TaxiMenu {

	// Width and heigh of buttons
	int buWidth = 200;
	int buHeight = 55;
	
	// Currently selected trip
	public int currentSelected = 1;
    
	// Triplist
    public ArrayList<TripLockedTime> tripLIST;
    
    /**
     * Constructor
     * 
     * Initialize tripList
     */
    public TaxiMenu() {
    	tripLIST = new ArrayList<TripLockedTime>();
    }

    /**
     * Draws Menu
     * Currenty selected indicated by thick line
     * Trip are drawn.
     * - Green is accepted
     * - Light grey is offered
     * Trip information is drawn
     * Black outline is drawn
     * 
     * @param g
     */
    public void draw(Graphics g) {
    	
    	TripLockedTime curTrip = null;
    	
    	if(tripLIST.size() > 0) {
    		g.fillRect(5, ((currentSelected - 1) * (buHeight+10)), buWidth, buHeight);
    	}
    	
    	g.setColor(Color.LIGHT_GRAY);

    	for (int i = 0; i < tripLIST.size(); i++) {
            curTrip = tripLIST.get(i);
            if(curTrip.getAccepted() == 1) {
            	g.setColor(Color.GREEN);
            	g.fillRect(7, 2 + (i * (buHeight+10)), buWidth-4, buHeight-4);
            	g.setColor(Color.LIGHT_GRAY);
            } else {
            	g.fillRect(7, 2 + (i * (buHeight+10)), buWidth-4, buHeight-4);
            }
        }

        g.setColor(Color.black);

        g.setFont(new Font("Verdana Bold", 0, 12));
        
        for (int i = 0; i < tripLIST.size(); i++) {
            curTrip = tripLIST.get(i);
        	g.drawString("ID: " + curTrip.getTripID(), 15, 20 + (i * (buHeight+10)));
        	g.drawString(curTrip.getCoords(), 15, 40 + (i * (buHeight+10)));
        	g.drawString(curTrip.getTime(), 152, 20 + (i * (buHeight+10)));
        }
        
        for (int i = 0; i < tripLIST.size(); i++) {
            g.drawRect(6, 1 + (i * (buHeight+10)), buWidth-3, buHeight-3);
        }
    }

    /**
     * Go up in menu
     */
    public void Up() {
        currentSelected = currentSelected-1;
        if (currentSelected < 1) {
            currentSelected = tripLIST.size();
        }
    }

    /**
     * Go down in menu
     */
    public void Down() {
        currentSelected = currentSelected+1;
        if (currentSelected > tripLIST.size()) {
            currentSelected = 1;
        }
    }
    
    /**
     * Set new trip list
     * 
     * @param tripList
     */
    public void setTripList(ArrayList<TripLockedTime> tripList) {
    	tripLIST = tripList;
    }
}
