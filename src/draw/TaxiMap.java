package draw;

import config.Configuration;
import java.awt.*;
import java.util.*;
import model.*;

/**
 * Class used for drawing the taxi map
 * all roads and intersections are drawed.
 * The taxi is drawn and routes of current trip
 * trip routes are colour coded.
 * 
 * @author Anders, Kenni, Nicolai
 *
 */
public class TaxiMap {

	// Variables
	ArrayList<TripLockedTime> tripList = new ArrayList<TripLockedTime>();
	Algorithm algorithm = new Algorithm();
	
	public int currentSelected = 1;
	
	double scale = 0.35;
	double scaleZoom = 0.15;
	
	Configuration config = Configuration.getConfiguration();

	ArrayList<Intersection> mapList = config.getMap();
	ArrayList<Integer> route = new ArrayList<Integer>();

	/**
	 * Constructor
	 * 
	 * Loads map
	 * 
	 */
	public TaxiMap() {
		mapList = config.getMap();
	}

	/**
	 * 
	 * Draws the map
	 * 
	 * Draws all roads and intersections
	 * Draw all routes
	 * - Trip offer are blue
	 * - Accepted trips are green
	 * - currently selected trip is yellow
	 * Draws the taxi itself
	 * 
	 * @param g
	 */
	public void draw(Graphics g) {
		
		int taxiX = Integer.parseInt(config.getTaxiCoord().substring(0, 4));
		int taxiY = Integer.parseInt(config.getTaxiCoord().substring(5, 9));
		
		CoordinateSystem S2 = new CoordinateSystem(scale, scale, (scale * (1000-taxiX)) - ((scale-0.35) * 1000), scale * (1000+taxiY) - ((scale-0.35) * 1000));

		g.setColor(Color.BLACK);

		for (int t = 0; t < mapList.size(); t++) {
			for (int i = mapList.get(t).getLinks(); i > 0; i--) {
				int ax = mapList.get(t).getXCoord();
				int ay = mapList.get(t).getYCoord();

				int neighbor = mapList.get(t).getNn(i);

				int bx = mapList.get(neighbor).getXCoord();
				int by = mapList.get(neighbor).getYCoord();

				S2.drawRoad(g, ax, ay, bx, by);
			}
		}
		
		for (int i = 0; i < mapList.size(); i++) {
			S2.drawIntersection(g, mapList.get(i).getXCoord(), mapList.get(i).getYCoord());
		}

		int start = config.getTaxiPosition();
		int tempId;
		
		for(int i=0; i<tripList.size(); i++) {
			route = algorithm.Route(start, tripList.get(i).getCoords());
			int j=0;
			
			if(tripList.get(i).getAccepted() == 1) {
				g.setColor(Color.GREEN);
			} else {
				g.setColor(Color.BLUE);
			}
			
			while (j < route.size() - 1) {
				tempId = route.get(j);
				int Ax = mapList.get(tempId).getXCoord();
				int Ay = mapList.get(tempId).getYCoord();
				j++;
				tempId = route.get(j);
				int Bx = mapList.get(tempId).getXCoord();
				int By = mapList.get(tempId).getYCoord();

				S2.drawRouteLine(g, Ax, Ay, Bx, By);
			}
		}
		
		g.setColor(Color.YELLOW);
		
		if(tripList.size() > 0) {
		int j=0;
			while (j < route.size() - 1) {
				route = algorithm.Route(start, tripList.get(currentSelected-1).getCoords());
				tempId = route.get(j);
				int Ax = mapList.get(tempId).getXCoord();
				int Ay = mapList.get(tempId).getYCoord();
				j++;
				tempId = route.get(j);
				int Bx = mapList.get(tempId).getXCoord();
				int By = mapList.get(tempId).getYCoord();
	
				S2.drawRouteLine(g, Ax, Ay, Bx, By);
			}
		}
		int ownX = Integer.parseInt(config.getTaxiCoord().substring(0, 4));
		int ownY = Integer.parseInt(config.getTaxiCoord().substring(5, 9));
		
		S2.drawSelf(g, new Vector(ownX, ownY), 10);
	}
	
	/**
	 * Set new trip list
	 * 
	 * @param tripList
	 */
	public void setTripList(ArrayList<TripLockedTime> tripList) {
		this.tripList = tripList;
	}
	
	/**
	 * Zoom in on map
	 * 
	 */
	public void zoomIn() {
		if(scale < 1.1) {
			scale += scaleZoom;
		} else {
			scale = 1.1;
		}
	}
	
	/**
	 * Zoom out of map
	 * 
	 */
	public void zoomOut() {
		if(scale > 0.35) {
			scale -= scaleZoom;
		} else {
			scale = 0.35;
		}
	}
	
	/**
	 * Change currently selected trip to the one above
	 */
    public void Up() {
        currentSelected = currentSelected-1;
        if (currentSelected < 1) {
            currentSelected = tripList.size();
        }
    }
    
    /**
	 * Change currently selected trip to the one below
	 */
    public void Down() {
        currentSelected = currentSelected+1;
        if (currentSelected > tripList.size()) {
            currentSelected = 1;
        }
    }
}
