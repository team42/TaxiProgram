package taxiGUI;

import javax.swing.*;
import javax.swing.GroupLayout.*;
import companyComm.TaxiSide;
import config.Configuration;
import draw.TaxiMapCanvas;
import draw.TaxiMenuCanvas;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

/**
 * JFrame for showing Taxi map and taxi menu
 * Also shows taxi coordinate and a JButton for changing these
 * 
 * @author Nicolai
 */
@SuppressWarnings("serial")
public class TaxiModuleGUI extends JFrame {

	private JButton buttonUpdateCoords;
	private JLabel gpsLabel;
	
	public TaxiMenuCanvas menuCanvas;
	public TaxiMapCanvas mapCanvas;
	public TaxiSide taxiSide;

	Configuration config;
	
	/**
	 * Set up configuration
	 * Set resizable false
	 * Initialize components
	 * set Coordinates label
	 * Initialize company communication object
	 */
	public TaxiModuleGUI() {
		config = Configuration.getConfiguration();
		setResizable(false);
		initComponents();
		
		gpsLabel.setText(config.getTaxiCoord());
		
		this.setVisible(true);
		taxiSide = new TaxiSide(this);
	}

	/**
	 * Set up layout for components
	 */
	private void initComponents() {

		gpsLabel = new JLabel();
		buttonUpdateCoords = new JButton();
		menuCanvas = new TaxiMenuCanvas();
		mapCanvas = new TaxiMapCanvas();
		
		buttonUpdateCoords.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				String gpsCoords = "";
				
				while(gpsCoords.length() != 9) {
					gpsCoords = JOptionPane.showInputDialog("Insert gps coordinates\nIn format xxxx,yyyy");
					if(gpsCoords == null) gpsCoords = "";
				}
				
				if(gpsCoords != null) {
					config.setTaxiCoord(gpsCoords);
					gpsLabel.setText(gpsCoords);
					mapCanvas.repaint();
				}
			}
		});
		
		setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

		mapCanvas.setBackground(new java.awt.Color(255, 255, 255));
		mapCanvas.setBorder(javax.swing.BorderFactory.createEtchedBorder(
				javax.swing.border.EtchedBorder.RAISED,
				java.awt.Color.lightGray, java.awt.Color.darkGray));

		javax.swing.GroupLayout gl_mapCanvas = new javax.swing.GroupLayout(
				mapCanvas);
		mapCanvas.setLayout(gl_mapCanvas);
		gl_mapCanvas.setHorizontalGroup(gl_mapCanvas.createParallelGroup(
				javax.swing.GroupLayout.Alignment.LEADING).addGap(0, 701,
				Short.MAX_VALUE));
		gl_mapCanvas.setVerticalGroup(gl_mapCanvas.createParallelGroup(
				javax.swing.GroupLayout.Alignment.LEADING).addGap(0, 701,
				Short.MAX_VALUE));

		gpsLabel.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
		gpsLabel.setText("GPS osv");

		buttonUpdateCoords.setText("Change GPS Coordinates");

		javax.swing.GroupLayout gl_menuCanvas = new javax.swing.GroupLayout(
				menuCanvas);
		menuCanvas.setLayout(gl_menuCanvas);
		gl_menuCanvas.setHorizontalGroup(gl_menuCanvas.createParallelGroup(
				javax.swing.GroupLayout.Alignment.LEADING).addGap(0, 200,
				Short.MAX_VALUE));
		gl_menuCanvas.setVerticalGroup(gl_menuCanvas.createParallelGroup(
				javax.swing.GroupLayout.Alignment.LEADING).addGap(0, 200,
				Short.MAX_VALUE));

		javax.swing.GroupLayout layout = new javax.swing.GroupLayout(
				getContentPane());
		layout.setHorizontalGroup(
			layout.createParallelGroup(Alignment.LEADING)
				.addGroup(layout.createSequentialGroup()
					.addComponent(mapCanvas, GroupLayout.DEFAULT_SIZE, 701, Short.MAX_VALUE)
					.addGap(10)
					.addGroup(layout.createParallelGroup(Alignment.LEADING)
						.addComponent(gpsLabel, GroupLayout.DEFAULT_SIZE, 200, Short.MAX_VALUE)
						.addComponent(buttonUpdateCoords, GroupLayout.DEFAULT_SIZE, 200, Short.MAX_VALUE)
						.addComponent(menuCanvas, GroupLayout.DEFAULT_SIZE, 200, Short.MAX_VALUE))
					.addGap(10))
		);
		layout.setVerticalGroup(
			layout.createParallelGroup(Alignment.LEADING)
				.addGroup(layout.createSequentialGroup()
					.addGap(10)
					.addComponent(gpsLabel, GroupLayout.PREFERRED_SIZE, 20, GroupLayout.PREFERRED_SIZE)
					.addGap(10)
					.addComponent(buttonUpdateCoords, GroupLayout.PREFERRED_SIZE, 30, GroupLayout.PREFERRED_SIZE)
					.addGap(10)
					.addComponent(menuCanvas, GroupLayout.DEFAULT_SIZE, 620, Short.MAX_VALUE))
				.addComponent(mapCanvas, GroupLayout.DEFAULT_SIZE, 701, Short.MAX_VALUE)
		);
		getContentPane().setLayout(layout);

		pack();
	}

	/**
	 * Accept Method
	 * 
	 * Get trip ID of currently selected trip
	 * if trip is accepted
	 * - Send finish command by company communication
	 * else
	 * - Send accept command by company communication
	 * 
	 */
	public void Accept() {
		String tripID = menuCanvas.taxiMenu.tripLIST.get(menuCanvas.taxiMenu.currentSelected - 1).getTripID();
		if (menuCanvas.taxiMenu.tripLIST.get(menuCanvas.taxiMenu.currentSelected - 1).getAccepted() < 1) {
			taxiSide.Answer('1', tripID);
		} else {
			taxiSide.Answer('2', tripID);
		}
	}

	/**
	 * Decline Method
	 * 
	 * Get trip ID of currently selected trip
	 * Send decline command by company communication
	 * 
	 */
	public void Decline() {
		String tripID = menuCanvas.taxiMenu.tripLIST.get(
				menuCanvas.taxiMenu.currentSelected - 1).getTripID();
		taxiSide.Answer('3', tripID);
	}
}
