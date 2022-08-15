/*
* Ball.java
* Holds the coordinates  of points
*/
public class Ball {
	double xcoordinate,ycoordinate;
int name;
	Ball(double x, double y,int n){
		this.xcoordinate = x;
		this.ycoordinate = y;
		this.name = n;
	}
	
	public double GetX() {
		return xcoordinate;
	}
	
	public double GetY() {
		return ycoordinate;
	}
	
}
