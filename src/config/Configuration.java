package config;

import java.util.ArrayList;

import javax.swing.JOptionPane;

import database.MapDAO;
import draw.Algorithm;
import model.Intersection;

/**
 * <code>Configuration</code> is a singleton design pattern class (no direct
 * instantiation) used for holding configuration information.
 * 
 * Code for this found here:<br />
 * <li>http://www.javacoffeebreak.com/articles/designpatterns/index.html</li>
 * <li>http://www.javabeginner.com/learn-java/java-singleton-design-pattern</li>
 * 
 * 
 * @author Lasse
 */
public class Configuration {

	private ArrayList<Intersection> mapList;
	private MapDAO mapDAO = new MapDAO();
	private Algorithm algorithm = new Algorithm();

	private String taxiID, taxiCoord, company;

	private int taxiPos;

	private static Configuration configurationObject;

	/**
	 * Make default constructor private to make sure no other class can
	 * instantiate.
	 * 
	 * Map information is read from the database
	 * taxi id, taxi coordinates and company address
	 * is entered and set
	 * 
	 */
	private Configuration() {
		mapList = mapDAO.getMap();

		String taxiID = "";
		String taxiCoordinate = "";

		while (taxiID.length() != 6) {
			taxiID = JOptionPane
					.showInputDialog("Insert Taxi ID\nMust be 6 characters");
		}

		while (taxiCoordinate.length() != 9) {
			taxiCoordinate = JOptionPane
					.showInputDialog("Insert Taxi Coordinate\nin format xxxx,yyyy");
		}
		
		company = JOptionPane.showInputDialog("Enter Company IP:");

		setCompany(company);
		setTaxiID(taxiID);
		setTaxiCoord(taxiCoordinate);
	}

	/**
	 * When first called, the <code>getConfiguration</code>method creates a
	 * singleton instance, assigns it to a member variable, and returns the
	 * singleton. Subsequent calls will return the same singleton, and all is
	 * well with the world.
	 * 
	 * Method is declared syncronized to prevent two threads from calling the
	 * <code>getConfiguration</code> method at the same time.
	 * 
	 * @return configurationObject holds static instance of Configuration
	 *         object.
	 */
	public static synchronized Configuration getConfiguration() {
		if (configurationObject == null) {
			configurationObject = new Configuration();
		}
		return configurationObject;
	}

	/**
	 * We override the inherited superclass implementation of clone() to prevent
	 * cloning of the singleton.
	 * 
	 * For now the only superclass is Object, in which clone() is protected, but
	 * later on we might inherit from another class that implements clone()
	 * 
	 * @return nothing, throws exception
	 * @throws CloneNotSupportedException
	 */
	@Override
	public Object clone() throws CloneNotSupportedException {
		throw new CloneNotSupportedException();
	}

	/**
	 * Get the value of peers
	 * 
	 * @return value of peers
	 */
	public ArrayList<Intersection> getMap() {
		return mapList;
	}

	/**
	 * Set the taxi ID
	 * 
	 * @param taxiID
	 */
	public void setTaxiID(String taxiID) {
		this.taxiID = taxiID;
	}

	/**
	 * Returns the taxi ID
	 * 
	 * @return
	 */
	public String getTaxiID() {
		return taxiID;
	}

	/**
	 * Set the taxi coordinates
	 * 
	 * @param taxiCoord
	 */
	public void setTaxiCoord(String taxiCoord) {
		this.taxiCoord = taxiCoord;
		setTaxiPosition(taxiCoord);
	}

	/**
	 * returns the taxi coordinates
	 * 
	 * @return
	 */
	public String getTaxiCoord() {
		return taxiCoord;
	}

	/**
	 * Finds the closest intersection ID and set for taxi
	 * 
	 * @param taxiPos
	 */
	private void setTaxiPosition(String taxiPos) {
		this.taxiPos = algorithm.findClosestPoint(taxiPos);
	}

	/**
	 * Returns closest intersection ID
	 * 
	 * @return
	 */
	public int getTaxiPosition() {
		return taxiPos;
	}
	
	/**
	 * Set company address
	 * 
	 * @param company
	 */
	public void setCompany(String company) {
		this.company = company;
	}
	
	/**
	 * Return company address
	 * 
	 * @return
	 */
	public String getCompany() {
		return company;
	}
}