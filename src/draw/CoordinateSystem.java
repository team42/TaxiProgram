package draw;




import java.awt.*;

public class CoordinateSystem {
  Matrix F;
  Matrix S;
  Matrix T;
  Vector O;
  
 public CoordinateSystem(int sx, int sy, int Ox, int Oy) {
    F=new Matrix(1, 0,
                   0, -1);
    S=new Matrix(sx,  0,
                   0,   sy);
    T=F.multiply(S);
    O=new Vector(Ox,Oy);
  }
 public CoordinateSystem(double sx, double sy, double Ox, double Oy) {
    F=new Matrix(1, 0,
                   0, -1);
    S=new Matrix(sx,  0,
                   0,   sy);
    T=F.multiply(S);
    O=new Vector(Ox,Oy);
  }
  
 public Vector transform(Vector p){
    return T.multiply(p).add(O);
  }
  
 public void drawPoint(Graphics g, Vector p){
    Vector px=transform(p);
    g.drawOval((int)px.x, (int)px.y, 1, 1);
    }

 public void drawPoint(Graphics g, Vector p, int h){
    Vector px=transform(p);
    g.drawOval((int)px.x, (int)px.y, h, h);
    }
 
 public void drawPoint(Graphics g, Vector p, int h, Color c){
    Vector px=transform(p);
    Color old = g.getColor();
    g.setColor(c);
    g.drawOval((int)px.x, (int)px.y, h, h);
    g.setColor(old);
  }
 public void drawTaxi(Graphics g, int X, int Y)
 {
    g.setColor(Color.RED);
	 int h = 5;
	Vector position = new Vector(X, Y);
	Vector px = transform(position);
	g.drawOval((int)px.x, (int)px.y, h, h);
	g.setColor(Color.BLACK);
 }
public void drawLine(Graphics g, Vector p1, Vector p2){
    Vector px1=transform(p1);
    Vector px2=transform(p2);  
    g.drawLine((int)px1.x, (int)px1.y, (int)px2.x, (int)px2.y);
  
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

void drawStation(Graphics g, int X, int Y ) {
	Vector p = new Vector (X,Y);
	Vector px = transform(p);
	g.setColor(Color.BLACK);
	g.drawOval((int) px.x, (int) px.y, 3, 3);
	//g.setColor(Color.GRAY);
	//drawString(g, (printName ? name : ""), (int) p.x, (int) p.y);
}

//void drawStation(Graphics g,  Vector p) {
//	drawStation(g, p, true);
//}

//void drawStation(Graphics g, int X, int Y) {
//	Vector position = new Vector(X, Y);
//	drawStation(g, position);
//}

//void drawStation(Graphics g, SerializableStation station, boolean printName) {
//	Vector position = new Vector(station.latitude, station.longitude);
//	drawStation(g, (printName ? station.stationName : ""), position);
//}

//void drawStation(Graphics g, SerializableStation station) {
//	drawStation(g, station, true);
//}

void drawStationLine(Graphics g, int aX, int aY, int bX, int bY, Boolean highlight) 
{
	Vector p1 = new Vector(aX, aY);
	Vector p2 = new Vector(bX, bY);
	Vector p1x = transform(p1);
	Vector p2x = transform(p2);

	if (!highlight)
		g.setColor(Color.LIGHT_GRAY);
	else
		g.setColor(Color.GREEN);

	g.drawLine((int) p1x.x, (int) p1x.y, (int) p2x.x, (int) p2x.y);
}

void drawRouteLine(Graphics g, int aX, int aY, int bX, int bY) 
{
	Vector p1 = new Vector(aX, aY);
	Vector p2 = new Vector(bX, bY);
	Vector p1x = transform(p1);
	Vector p2x = transform(p2);

	g.setColor(Color.RED);

	g.drawLine((int) p1x.x, (int) p1x.y, (int) p2x.x, (int) p2x.y);
}

//void drawStationLine(Graphics g, SerializableStation station1, SerializableStation station2) {
//	drawStationLine(g, station1, station2, false);
//}

//Tegner linier. Farve kan vælges.
 public void drawLine(Graphics g, Vector p1, Vector p2, Color c){
    Vector px1=transform(p1);
    Vector px2=transform(p2);
    Color old = g.getColor();
    g.setColor(c);
    g.drawLine((int)px1.x, (int)px1.y, (int)px2.x, (int)px2.y);
    g.setColor(old);
  }


 //Tegner x- og y-aksen
 public void drawAxis(Graphics g){
    drawLine(g, new Vector(0,0), new Vector(1,0));  //1 unit x-akse
    drawLine(g, new Vector(0,0), new Vector(0,1)); //1 unit y-akse

    drawLine(g, new Vector(1,0), new Vector(100,0));
    drawLine(g, new Vector(0,1), new Vector(0,100));
    drawLine(g, new Vector(0,0), new Vector(-100,0));
    drawLine(g, new Vector(0,0), new Vector(0,-100));

    //1 unit indikator - x-akse
    drawLine(g, new Vector(1,0), new Vector(1,0.25));
    drawLine(g, new Vector(1,0), new Vector(1,-0.25));
            //1 unit indikator - x-akse (minus akse)
            drawLine(g, new Vector(-1,0), new Vector(-1,0.25));
            drawLine(g, new Vector(-1,0), new Vector(-1,-0.25));

    //2 unit indikator - x-akse
    drawLine(g, new Vector(2,0), new Vector(2,0.25));
    drawLine(g, new Vector(2,0), new Vector(2,-0.25));
            //2 unit indikator - x-akse (minus akse
            drawLine(g, new Vector(-2,0), new Vector(-2,0.25));
            drawLine(g, new Vector(-2,0), new Vector(-2,-0.25));

    //3 unit indikator - x-akse
    drawLine(g, new Vector(3,0), new Vector(3,0.25));
    drawLine(g, new Vector(3,0), new Vector(3,-0.25));
            //3 unit indikator - x-akse (minus akse)
            drawLine(g, new Vector(-3,0), new Vector(-3,0.25));
            drawLine(g, new Vector(-3,0), new Vector(-3,-0.25));

    //4 unit indikator - x-akse
    drawLine(g, new Vector(4,0), new Vector(4,0.25));
    drawLine(g, new Vector(4,0), new Vector(4,-0.25));
            //4 unit indikator - x-akse (minus akse)
            drawLine(g, new Vector(-4,0), new Vector(-4,0.25));
            drawLine(g, new Vector(-4,0), new Vector(-4,-0.25));

    //1 unit indikator - y-akse
    drawLine(g, new Vector(0,1), new Vector(0.25, 1));
    drawLine(g, new Vector(0,1), new Vector(-0.25,1));
            //1 unit indikator - y-akse (minus akse)
            drawLine(g, new Vector(0,-1), new Vector(0.25, -1));
            drawLine(g, new Vector(0,-1), new Vector(-0.25, -1));

    //2 unit indikator - y-akse
    drawLine(g, new Vector(0,2*1), new Vector(0.25, 2*1));
    drawLine(g, new Vector(0,2*1), new Vector(-0.25, 2*1));
            //2 unit indikator - y-akse (minus akse)
            drawLine(g, new Vector(0,-2*1), new Vector(0.25, -2*1));
            drawLine(g, new Vector(0,-2*1), new Vector(-0.25, -2*1));
   
    //3 unit indikator - y-akse
    drawLine(g, new Vector(0,3*1), new Vector(0.25, 3*1));
    drawLine(g, new Vector(0,3*1), new Vector(-0.25, 3*1));
            //3 unit indikator - y-akse (minus akse)
            drawLine(g, new Vector(0,-3*1), new Vector(0.25, -3*1));
            drawLine(g, new Vector(0,-3*1), new Vector(-0.25, -3*1));

    //4 unit indikator - y-akse
    drawLine(g, new Vector(0,4*1), new Vector(0.25, 4*1));
    drawLine(g, new Vector(0,4*1), new Vector(-0.25, 4*1));
            //4 unit indikator - y-akse (minus akse)
            drawLine(g, new Vector(0,-4*1), new Vector(0.25, -4*1));
            drawLine(g, new Vector(0,-4*1), new Vector(-0.25, -4*1));

  }
}
