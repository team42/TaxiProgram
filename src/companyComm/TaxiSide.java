package companyComm;

import java.io.*;
import java.net.*;
import java.util.*;

import config.Configuration;
import model.*;
import taxiGUI.*;

/**
 * Class responsible for handling communication
 * with the company peer
 * 
 * @author Nicolai
 *
 */
public class TaxiSide {

	// Variables
	private InetAddress host;
	private final int PORT = 50004;
	private DatagramSocket datagramSocket;
	private DatagramPacket inPacket, outPacket;
	private byte[] buffer;
	
	ArrayList<TripLockedTime> tripList;
	
	Configuration config = Configuration.getConfiguration();
	
	Timer timer;
	
	String taxiID = config.getTaxiID();
	String coords = config.getTaxiCoord();
	
	TaxiModuleGUI tmGUI = null; 
	
	/**
	 * Constructor
	 * 
	 * Sets the InetAddress for the company
	 * Initialize the trip list
	 * Create taxi module GUI object with reference
	 * to the object that initialize this object.
	 * Start timer for sending update taxi coordinates
	 * 
	 * @param tmGUI
	 */
	public TaxiSide(TaxiModuleGUI tmGUI) {
		
		try {
			host = InetAddress.getByName(config.getCompany());
		} catch (UnknownHostException e) {
			e.printStackTrace();
		}
		
		tripList = new ArrayList<TripLockedTime>();
		
		this.tmGUI = tmGUI;
		
		timer = new Timer();
		timer.schedule(new updateTable(), 1000, 5000);
	}
	
	/**
	 * 
	 * Sends taxi coordinates to company
	 * Piggyback an answer for a trip
	 * Update trip table
	 * Restart timer
	 * 
	 * @param answer
	 * @param tripID
	 */
	public void Answer(char answer, String tripID){
		
		timer.cancel();
		
		taxiID = config.getTaxiID();
		coords = config.getTaxiCoord();
		
		try {
			
			datagramSocket = new DatagramSocket();
			
			String message = taxiID + coords + answer + tripID;
			
			outPacket = new DatagramPacket(message.getBytes(), message.length(), host, PORT);
			datagramSocket.send(outPacket);
			
			String response;
			
			buffer = new byte[512];
			inPacket = new DatagramPacket(buffer, buffer.length);
			datagramSocket.receive(inPacket);
			response = new String(inPacket.getData(),0,inPacket.getLength());
			
			int accepted;
			String time, coords;
			
			TripLockedTime curTrip = null;
			
			tripList.clear();
			
			String[] trips = response.split("%");
			
			if(trips.length > 0 && trips[0].length() > 15) {
				for(int i=0; i<trips.length; i++) {
					tripID = trips[i].substring(0, 10);
					accepted = Integer.parseInt(trips[i].substring(10, 11));
					time = trips[i].substring(11, 16);
					coords = trips[i].substring(16);
					
					curTrip = new TripLockedTime(tripID, accepted, time, coords);
					
					tripList.add(curTrip);
				}
			}
			
			tmGUI.menuCanvas.setTripList(tripList);
			tmGUI.mapCanvas.setTripList(tripList);
			
		} catch(IOException ioEx) {
			ioEx.printStackTrace();
		} finally {
			datagramSocket.close();
		}
		
		timer = new Timer();
		timer.schedule(new updateTable(), 2000, 5000);
	}
	
	/**
	 * Sends an update of taxi coordinates
	 * Set the trip table specified by the response
	 * 
	 */
	public void getTable() {
		
		taxiID = config.getTaxiID();
		coords = config.getTaxiCoord();
		
		try {
			datagramSocket = new DatagramSocket();
			
			String message = taxiID + coords + "0";
			
			outPacket = new DatagramPacket(message.getBytes(), message.length(), host, PORT);
			datagramSocket.send(outPacket);
			
			String response;
			
			buffer = new byte[512];
			inPacket = new DatagramPacket(buffer, buffer.length);
			datagramSocket.receive(inPacket);
			response = new String(inPacket.getData(),0,inPacket.getLength());
			
			int accepted;
			String tripID, time, coords;
			
			TripLockedTime curTrip = null;
			
			tripList.clear();
			
			String[] trips = response.split("%");
			
			if(trips.length > 0 && trips[0].length() > 15) {
				for(int i=0; i<trips.length; i++) {
					tripID = trips[i].substring(0, 10);
					accepted = Integer.parseInt(trips[i].substring(10, 11));
					time = trips[i].substring(11, 16);
					coords = trips[i].substring(16);
					
					curTrip = new TripLockedTime(tripID, accepted, time, coords);
					
					tripList.add(curTrip);
				}
			}
			
			tmGUI.menuCanvas.setTripList(tripList);
			tmGUI.mapCanvas.setTripList(tripList);
			
		} catch(IOException ioEx) {
			ioEx.printStackTrace();
		} finally {
			datagramSocket.close();
		}
	}
	
	/**
	 * Timertask that call update taxi coordinates <getTable>
	 * 
	 * @author Nicolai
	 *
	 */
	class updateTable extends TimerTask  {
	    public void run (  )   {
	    	getTable();
	    }
	}
}