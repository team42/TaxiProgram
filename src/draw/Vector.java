package draw;

public class Vector {
	public double x, y;

	public Vector(double x, double y) {
		this.x = x;
		this.y = y;
	}

	public Vector add(Vector v) {
		return new Vector(x + v.x, y + v.y);
	}

}
