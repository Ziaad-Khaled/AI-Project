package code;

import java.awt.geom.Point2D;

public class Coordinates extends Point2D {
    public double x;
    public double y;
    public Coordinates(double x, double y){
        setLocation(x,y);
    }
	@Override
	public double getX() {
		// TODO Auto-generated method stub
		return x;
	}
	@Override
	public double getY() {
		// TODO Auto-generated method stub
		return y;
	}
	@Override
	public void setLocation(double x, double y) {
		this.x=x;
		this.y=y;
		
	}
	



}
