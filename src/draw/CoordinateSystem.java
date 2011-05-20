package draw;

import java.awt.*;

/**
 * 
 * Class used for adjust coordinate system used for drawing
 * Makes it possible to scale coordinates system and move origo
 * Also flips y-axis, since draw method increase downward,
 * while "normal" coordinate system increase upwards.
 * 
 * @author Kenni, Anders, Nicolai
 *
 */
public class CoordinateSystem {
	Matrix F;
	Matrix S;
	Matrix T;
	Vector O;

	/**
	 * Constructor
	 * 
	 * Set scale and origo.
	 * Create tranformation vector
	 * 
	 * @param sx
	 * @param sy
	 * @param Ox
	 * @param Oy
	 */
	public CoordinateSystem(double sx, double sy, double Ox, double Oy) {
		F = new Matrix(1, 0, 0, -1);
		S = new Matrix(sx, 0, 0, sy);
		T = F.multiply(S);
		O = new Vector(Ox, Oy);
	}

	/**
	 * Use origo and transformation vector to convert
	 * coordinates to this system
	 * 
	 * @param p
	 * @return
	 */
	private Vector transform(Vector p) {
		return T.multiply(p).add(O);
	}

	/**
	 * Draws the taxi, by coordinates and size
	 * Taxi dot is yellow with a black outline
	 * 
	 * @param g
	 * @param p
	 * @param size
	 */
	public void drawSelf(Graphics g, Vector p, int size) {
		g.setColor(Color.YELLOW);
		Vector px = transform(p);
		g.fillOval((int) px.x - (size / 2), (int) px.y - (size / 2), size, size);
		g.setColor(Color.BLACK);
		g.drawOval((int) px.x - (size / 2), (int) px.y - (size / 2), size, size);
	}

	/**
	 * Draws an intersection by a dark gray circle with a black outline
	 * X and Y coordinates are used.
	 * 
	 * @param g
	 * @param X
	 * @param Y
	 */
	void drawIntersection(Graphics g, int X, int Y) {
		Vector p = new Vector(X, Y);
		Vector px = transform(p);
		g.setColor(Color.DARK_GRAY);
		g.fillOval((int) (px.x - 1.5), (int) (px.y - 1.5), 3, 3);
		g.setColor(Color.BLACK);
		g.drawOval((int) (px.x - 1.5), (int) (px.y - 1.5), 3, 3);
	}

	/**
	 * 
	 * Draws a road in light gray.
	 * Two x,y sets are used
	 * 
	 * @param g
	 * @param aX
	 * @param aY
	 * @param bX
	 * @param bY
	 */
	void drawRoad(Graphics g, int aX, int aY, int bX, int bY) {
		Vector p1 = new Vector(aX, aY);
		Vector p2 = new Vector(bX, bY);
		Vector p1x = transform(p1);
		Vector p2x = transform(p2);

		g.setColor(Color.LIGHT_GRAY);

		g.drawLine((int) p1x.x, (int) p1x.y, (int) p2x.x, (int) p2x.y);
	}

	/**
	 * Draw a route line.
	 * A thicker line is used for this compared to drawRoad()
	 * Color is set outside method.
	 * 
	 * @param g
	 * @param aX
	 * @param aY
	 * @param bX
	 * @param bY
	 */
	void drawRouteLine(Graphics g, int aX, int aY, int bX, int bY) {
		Vector p1 = new Vector(aX, aY);
		Vector p2 = new Vector(bX, bY);
		Vector p1x = transform(p1);
		Vector p2x = transform(p2);

		Graphics2D g2 = (Graphics2D) g;
		g2.setStroke(new BasicStroke(3));

		g2.drawLine((int) p1x.x, (int) p1x.y, (int) p2x.x, (int) p2x.y);

		g2.setStroke(new BasicStroke(1));
	}
}
