package draw;

/**
 * Model used for representing a 2x2 matrix.
 * Also used for calculating matrix formulas.
 * 
 * @author Anders, Kenni, Nicolai
 *
 */
public class Matrix {
	double a, b;
	double c, d;

	/**
	 * Constructor
	 * 
	 * Set all four field of the matrix
	 * 
	 * @param a
	 * @param b
	 * @param c
	 * @param d
	 */
	public Matrix(double a, double b, double c, double d) {
		this.a = a;
		this.b = b;
		this.c = c;
		this.d = d;
	}

	/**
	 * Multiply the matrix with a vector
	 * 
	 * @param v
	 * @return
	 */
	public Vector multiply(Vector v) {
		return new Vector(a * v.x + b * v.y, c * v.x + d * v.y);
	}

	/**
	 * Multiply the matrix by another matrix
	 * 
	 * @param m
	 * @return
	 */
	public Matrix multiply(Matrix m) {
		return new Matrix(a * m.a + b * m.c, a * m.b + b * m.d, c * m.a + d
				* m.c, c * m.b + d * m.d);
	}
}
