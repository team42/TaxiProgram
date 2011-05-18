package draw;

import java.awt.*;

public class CoordinateSystem {
	Matrix F;
	Matrix S;
	Matrix T;
	Vector O;

	public CoordinateSystem(int sx, int sy, int Ox, int Oy) {
		F = new Matrix(1, 0, 0, -1);
		S = new Matrix(sx, 0, 0, sy);
		T = F.multiply(S);
		O = new Vector(Ox, Oy);
	}

	public CoordinateSystem(double sx, double sy, double Ox, double Oy) {
		F = new Matrix(1, 0, 0, -1);
		S = new Matrix(sx, 0, 0, sy);
		T = F.multiply(S);
		O = new Vector(Ox, Oy);
	}

	public Vector transform(Vector p) {
		return T.multiply(p).add(O);
	}

	public void drawPoint(Graphics g, Vector p, int size) {
		g.setColor(Color.YELLOW);
		Vector px = transform(p);
		g.fillOval((int) px.x - (size / 2), (int) px.y - (size / 2), size, size);
		g.setColor(Color.BLACK);
		g.drawOval((int) px.x - (size / 2), (int) px.y - (size / 2), size, size);
	}

	public void drawLine(Graphics g, Vector p1, Vector p2) {
		Vector px1 = transform(p1);
		Vector px2 = transform(p2);
		g.drawLine((int) px1.x, (int) px1.y, (int) px2.x, (int) px2.y);

	}

	void drawString(Graphics g, String str, Vector position, int offSetX,
			int offSetY) {
		Vector px = transform(position);
		g.drawString(str, (int) px.x + offSetX, (int) px.y + offSetY);
	}

	void drawString(Graphics g, String str, int X, int Y) {
		Vector position = new Vector(X, Y);
		Vector px = transform(position);
		g.drawString(str, (int) px.x, (int) px.y);
	}

	void drawString(Graphics g, String str, Vector position) {
		Vector px = transform(position);
		g.drawString(str, (int) px.x, (int) px.y);
	}

	void drawIntersection(Graphics g, int X, int Y) {
		Vector p = new Vector(X, Y);
		Vector px = transform(p);
		g.setColor(Color.DARK_GRAY);
		g.fillOval((int) (px.x - 1.5), (int) (px.y - 1.5), 3, 3);
	}

	void drawRoad(Graphics g, int aX, int aY, int bX, int bY) {
		Vector p1 = new Vector(aX, aY);
		Vector p2 = new Vector(bX, bY);
		Vector p1x = transform(p1);
		Vector p2x = transform(p2);

		g.setColor(Color.LIGHT_GRAY);

		g.drawLine((int) p1x.x, (int) p1x.y, (int) p2x.x, (int) p2x.y);
	}

	void drawRouteLine(Graphics g, int aX, int aY, int bX, int bY) {
		Vector p1 = new Vector(aX, aY);
		Vector p2 = new Vector(bX, bY);
		Vector p1x = transform(p1);
		Vector p2x = transform(p2);

		Graphics2D g2 = (Graphics2D) g;
		g2.setStroke(new BasicStroke(3));

		g2.setColor(Color.RED);
		g2.drawLine((int) p1x.x, (int) p1x.y, (int) p2x.x, (int) p2x.y);

		g2.setStroke(new BasicStroke(1));
	}
}
