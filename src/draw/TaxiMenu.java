package draw;

import java.awt.*;
import java.util.ArrayList;

import model.*;

// @author Nicolai

public class TaxiMenu {

	int buWidth = 200;
	int buHeight = 55;
	
	public int currentSelected = 1;
    
    public ArrayList<TripLockedTime> tripLIST;
    
    public TaxiMenu() {
    	tripLIST = new ArrayList<TripLockedTime>();
    }

    public void draw(Graphics g) {
    	
    	TripLockedTime curTrip = null;
    	
    	g.fillRect(5, ((currentSelected - 1) * (buHeight+10)), buWidth, buHeight);
    	
    	g.setColor(Color.LIGHT_GRAY);
    	for (int i = 0; i < tripLIST.size(); i++) {
            curTrip = tripLIST.get(i);
            if(curTrip.getAccepted() == 1) {
            	g.setColor(Color.GREEN);
            	g.fillRect(5, (i * (buHeight+10)), buWidth, buHeight);
            	g.setColor(Color.LIGHT_GRAY);
            } else {
            	g.fillRect(5, (i * (buHeight+10)), buWidth, buHeight);
            }
        }

        g.setColor(Color.black);

        g.setFont(new Font("Verdana Bold", 0, 12));
        
        for (int i = 0; i < tripLIST.size(); i++) {
            curTrip = tripLIST.get(i);
        	g.drawString("ID: " + curTrip.getTripID(), 20, 30 + (i * (buHeight+10)));
        	g.drawString(curTrip.getCoords(), 20, 50 + (i * (buHeight+10)));
        	g.drawString(curTrip.getTime(), 152, 30 + (i * (buHeight+10)));
        }
        
        for (int i = 0; i < tripLIST.size(); i++) {
            g.drawRect(10, 10 + (i * (buHeight+10)), buWidth, buHeight);
        }
    }

    public void Up() {
        currentSelected = currentSelected-1;
        if (currentSelected < 1) {
            currentSelected = tripLIST.size();
        }
    }

    public void Down() {
        currentSelected = currentSelected+1;
        if (currentSelected > tripLIST.size()) {
            currentSelected = 1;
        }
    }
    
    public void setTripList(ArrayList<TripLockedTime> tripList) {
    	tripLIST = tripList;
    }
}
