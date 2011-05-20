package irProtocol;
import java.util.TooManyListenersException;

/**
 * NetworkLayer
 * 
 * Handles echo by the use of adressing
 * 
 * @author Nicolai Sonne
 *
 */

public class NetworkLayer {

	// Neighbor Layers
	LinkLayer link = null;
	TransportLayerSender transportSe = null;
	TransportLayerReceiver transportRe = null;
	
	// boolean indicating if this is a sender or receiver initialization
	boolean sender = false;
	
	String identifier = "";
	
	/**
	 * Constructor - Sender mode
	 * 
	 * Set transportSe layer to the initializing layer
	 * Create new link layer with the given port
	 * 
	 * @param port - Port transfered to link layer
	 * @param transp - Transport layer of the initializer
	 * @throws TooManyListenersException
	 */
	public NetworkLayer(String port, TransportLayerSender transp) throws TooManyListenersException {
		transportSe = transp;
		link = new LinkLayer(port, this);
		sender = true;
		identifier = "1";
	}
	
	/**
	 * Constructor - Receiver mode
	 * 
	 * Set link layer to the initializing layer
	 * Create new transport receiver layer
	 * 
	 * @param linkL - link layer of the initializer
	 * @throws TooManyListenersException
	 */
	public NetworkLayer(LinkLayer linkL) throws TooManyListenersException {
		link = linkL;
		transportRe = new TransportLayerReceiver(this);
		sender = false;
		identifier = "0";
	}
	
	/**
	 * Sender
	 * 
	 * Add an identifier to the data
	 * 
	 * @param data - Data you wish to send
	 */
	public void Sender(String data) {
		link.Sender(identifier + data);
	}
	
	/**
	 * Receiver
	 * 
	 * Compare identifier to the its own
	 * If they match, it discard the data
	 * else it's send to the transport layer
	 * 
	 * @param data - Data received
	 */
	public void receiver(String data) {
		
		String receiveIdentifier = data.substring(0, 1);
		
		if(!identifier.equals(receiveIdentifier)) {
			if(sender) {
				transportSe.receiver(data.substring(1));
			} else {
				transportRe.receiver(data.substring(1));
			}
		}
	}
}