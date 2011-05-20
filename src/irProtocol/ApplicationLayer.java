package irProtocol;

import java.util.TooManyListenersException;
import taxiGUI.*;

public class ApplicationLayer {
	
	// Neighbor layer
	TransportLayerSender transportSe = null;
	
	// Taxi Module GUI
	TaxiModuleGUI tmGUI = null;
	
	/**
	 * Constructor - Sender mode
	 * 
	 * Create new transport layer with the given port
	 * 
	 * @param port - Serial port used
	 * @throws TooManyListenersException
	 */
	public ApplicationLayer(String port) throws TooManyListenersException {
		transportSe = new TransportLayerSender(port, this);
	}
	
	/**
	 * Constructor - Receiver mode
	 * 
	 * Initialize Taxi module GUI
	 * 
	 * @throws TooManyListenersException
	 */
	public ApplicationLayer() throws TooManyListenersException {
		tmGUI = new TaxiModuleGUI();
		tmGUI.setVisible(true);
	}
	
	/**
	 * Sender
	 * 
	 * Send data to Transport layer
	 * 
	 * @param data - Data you wish to send
	 */
	public void Sender(String data) {
		transportSe.Sender(data);
	}
	
	/**
	 * Receiver
	 * 
	 * If an accepted command is received the corresponding 
	 * method is called of the taxi module GUI.
	 * 
	 * @param data
	 */
	public void receiver(String data) {
		System.out.println("Data received: " + data);
		if(data.equals("UP")) {
			tmGUI.menuCanvas.UP();
			tmGUI.mapCanvas.UP();
		} else if(data.equals("DW")) {
			tmGUI.menuCanvas.DW();
			tmGUI.mapCanvas.DW();
		} else if(data.equals("AC")) {
			tmGUI.Accept();
		} else if(data.equals("DC")) {
			tmGUI.Decline();
		} else if(data.equals("ZI")) {
			tmGUI.mapCanvas.zoomIn();
		} else if(data.equals("ZO")) {
			tmGUI.mapCanvas.zoomOut();
		} else {
			System.out.println("UNKNOWN COMMAND!!!");
		}
	}	
}
