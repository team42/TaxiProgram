package draw;

/**
 * Model used for representing a vector with a length of 2.
 * Also used for calculating vector formulas.
 * 
 * @author Anders, Kenni, Nicolai
 *
 */
public class Vector {
	public double x, y;

	/**
	 * Constructor
	 * 
	 * Set both vector fields
	 * 
	 * @param x
	 * @param y
	 */
	public Vector(double x, double y) {
		this.x = x;
		this.y = y;
	}

	/**
	 * Adds two vectors
	 * 
	 * @param v
	 * @return
	 */
	public Vector add(Vector v) {
		return new Vector(x + v.x, y + v.y);
	}

}