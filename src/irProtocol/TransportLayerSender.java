package irProtocol;

import java.util.TooManyListenersException;

/**
 * Transport Layer - Sender
 * 
 * Will send the data and retransmit if an acknowledgement
 * haven't been received in a calculated time. 
 * 
 * @author Nicolai Sonne
 *
 */

public class TransportLayerSender {

	// Neighbor layers
	ApplicationLayer application = null;
	NetworkLayer network = null;
	
	// "Last acked"
	public String ACK = "1";
	
	// Variables for time measurements and timeout calculation
	long ts = 0;
	long timeout = 400;
	
	// Current state
	int senderState = 0;
	
	// Ack status
	boolean msgAcked = false;
	
	// Constructor
	// Create new networklayer and set application layer to the initializer for this object
	public TransportLayerSender(String port, ApplicationLayer appL) throws TooManyListenersException {
		application = appL;
		network = new NetworkLayer(port, this);
	}
	
	/**
	 * Sender
	 * 
	 * Sends data to the network layer and waits for acknowledgement
	 * 
	 * @param data
	 */
	public void Sender(String data) {
		do {
			switch(senderState) {
				case 0:
					// Send data with sequense number 0
					network.Sender("0" + data);
					
					// Start timer for timeout
					ts = System.currentTimeMillis();
					
					// Set message acked false
					msgAcked = false;

					// Go to state 1
					senderState = 1;
					break;
				case 1:
					// If timer exceeds timeout:
					// Retransmit and restart timer
					if ((System.currentTimeMillis()-ts) > timeout) {
						network.Sender("0" + data);
						ts = System.currentTimeMillis();
						msgAcked = false;
						senderState = 1;
					
					// If message have been acked:
					} else if (ACK.equals("0")) {
						msgAcked = true;
						senderState = 2;
					}
					break;
				case 2:
					// Send data with sequense number 0
					network.Sender("1" + data);
					
					// Start timer for timeout and
					ts = System.currentTimeMillis();
					
					// Set message acked false
					msgAcked = false;
					
					// Go to state 3
					senderState = 3;
					break;
				case 3:
					// If timer exceeds timeout:
					// Retransmit and restart timer
					if ((System.currentTimeMillis()-ts) > timeout) {
						network.Sender("1" + data);
						ts = System.currentTimeMillis();
						msgAcked = false;
						senderState = 3;
					
					// If message have been acked:
					} else if (ACK.equals("1")) {
						msgAcked = true;
						senderState = 0;
					}
					break;
			}
		} while(!msgAcked);
	}
	
	/**
	 * Receiver
	 * 
	 * Set "last acked" if acks are received 
	 * 
	 * @param data - Incoming data
	 */
	public void receiver(String data) {
		if (data.equals("ACK1")){
			ACK = "1";
		} else if (data.equals("ACK0")) {
			ACK = "0";
		}
		System.out.println("Last ACK: " + ACK);
	}
}
