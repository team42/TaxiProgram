package draw;

import config.Configuration;
import javax.swing.*;
import java.awt.*;
import java.util.*;
import model.*;

@SuppressWarnings("serial")
public class TaxiMap {

	ArrayList<TripLockedTime> tripList = new ArrayList<TripLockedTime>();
	Algorithm algorithm = new Algorithm();
	
	double scale = 0.348;
	
	Configuration config = Configuration.getConfiguration();

	ArrayList<Intersection> mapList = config.getMap();
	ArrayList<Integer> route = new ArrayList<Integer>();

	public TaxiMap() {
		mapList = config.getMap();
	}

	public void draw(Graphics g) {
		CoordinateSystem S2 = new CoordinateSystem(scale, scale, 0, scale * 2000);

		int xLength = 2000;
		int yLength = 2000;

		S2.drawLine(g, new Vector(0, 0), new Vector(0, yLength));
		S2.drawLine(g, new Vector(0, yLength), new Vector(xLength, yLength));
		S2.drawLine(g, new Vector(xLength, yLength), new Vector(xLength, 0));
		S2.drawLine(g, new Vector(xLength, 0), new Vector(0, 0));

		g.setColor(Color.BLACK);

		for (int t = 0; t < mapList.size(); t++) {
			for (int i = mapList.get(t).getLinks(); i > 0; i--) {
				int ax = mapList.get(t).getXCoord();
				int ay = mapList.get(t).getYCoord();

				int neighbor = mapList.get(t).getNn(i);

				int bx = mapList.get(neighbor).getXCoord();
				int by = mapList.get(neighbor).getYCoord();

				S2.drawStationLine(g, ax, ay, bx, by, false);
			}
		}
		
		for (int i = 0; i < mapList.size(); i++) {
			S2.drawStation(g, mapList.get(i).getXCoord(), mapList.get(i).getYCoord());
		}

		int start = config.getTaxiPosition();
		int tempId;
		
		int ownX = Integer.parseInt(config.getTaxiCoord().substring(0, 4));
		int ownY = Integer.parseInt(config.getTaxiCoord().substring(5, 9));
		
		S2.drawPoint(g, new Vector(ownX, ownY), 20);
		
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
}
