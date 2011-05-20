package model;

/**
 * Object model of a trip with a "time since ordered" field
 * 
 * @author Nicolai
 *
 */
public class TripLockedTime {

	private String tripID;
	private String coords;
	private String time;
	private int accepted;
	
	/**
	 * Constructor
	 * 
	 * Set Trip ID, Accepted flag, Coordinates and "time since ordered"
	 * 
	 * @param tripID
	 * @param accepted
	 * @param time
	 * @param coords
	 */
	public TripLockedTime(String tripID, int accepted, String time, String coords) {
		this.tripID = tripID;
		this.accepted = accepted;
		this.coords = coords;
		this.time = time;
	}
	
	/**
	 * Returns Trip ID
	 * 
	 * @return
	 */
	public String getTripID() {
		return tripID;
	}
	
	/**
	 * Return accepted flag
	 * 
	 * @return
	 */
	public int getAccepted() {
		return accepted;
	}
	
	/**
	 * Return coordinates
	 * 
	 * @return
	 */
	public String getCoords() {
		return coords;
	}
	
	/**
	 * Return "time since ordered"
	 * 
	 * @return
	 */
	public String getTime() {
		return time;
	}
}
