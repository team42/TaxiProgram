package start;

import java.util.TooManyListenersException;
import irProtocol.*;

/**
 * Start Taxi Module
 * 
 * @author Nicolai
 *
 */
public class startTaxiModule {

	public static void main(String[] args) throws TooManyListenersException, InterruptedException {
		@SuppressWarnings("unused")
		PhysicalLayer phy = new PhysicalLayer("/dev/ttyUSB0");
		System.out.println("Receiver Started!");
	}
}
