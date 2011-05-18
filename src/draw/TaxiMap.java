package draw;

import config.Configuration;
import java.awt.*;
import java.util.*;
import model.*;

public class TaxiMap {

	ArrayList<TripLockedTime> tripList = new ArrayList<TripLockedTime>();
	Algorithm algorithm = new Algorithm();
	
	double scale = 0.35;
	double scaleZoom = 0.15;
	
	Configuration config = Configuration.getConfiguration();

	ArrayList<Intersection> mapList = config.getMap();
	ArrayList<Integer> route = new ArrayList<Integer>();

	public TaxiMap() {
		mapList = config.getMap();
	}

	public void draw(Graphics g) {
		
		int taxiX = Integer.parseInt(config.getTaxiCoord().substring(0, 4));
		int taxiY = Integer.parseInt(config.getTaxiCoord().substring(5, 9));
		
		CoordinateSystem S2 = new CoordinateSystem(scale, scale, (scale * (1000-taxiX)) - ((scale-0.35) * 1000), scale * (1000+taxiY) - ((scale-0.35) * 1000));

		int xLength = 2000;
		int yLength = 2000;

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
		
		int ownX = Integer.parseInt(config.getTaxiCoord().substring(0, 4));
		int ownY = Integer.parseInt(config.getTaxiCoord().substring(5, 9));
		
		S2.drawPoint(g, new Vector(ownX, ownY), 10);
		
		for(int i=0; i<tripList.size(); i++) {
			route = algorithm.Route(start, tripList.get(i).getCoords());
			int j=0;
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
	}
	
	public void setTripList(ArrayList<TripLockedTime> tripList) {
		this.tripList = tripList;
	}
	
	public void zoomIn() {
		if(scale < 1.1) {
			scale += scaleZoom;
		} else {
			scale = 1.1;
		}
	}
	
	public void zoomOut() {
		if(scale > 0.35) {
			scale -= scaleZoom;
		} else {
			scale = 0.35;
		}
	}
}
