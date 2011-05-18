package draw;

public class Matrix {
	double a, b;
	double c, d;

	public Matrix(double a, double b, double c, double d) {
		this.a = a;
		this.b = b;
		this.c = c;
		this.d = d;
	}

	public Vector multiply(Vector v) {
		return new Vector(a * v.x + b * v.y, c * v.x + d * v.y);
	}

	public Matrix multiply(Matrix m) {
		return new Matrix(a * m.a + b * m.c, a * m.b + b * m.d, c * m.a + d
				* m.c, c * m.b + d * m.d);
	}
}
