package code;

import java.awt.geom.Point2D;
import java.util.*;

public class Grid {
	private int width;//M
    private int height;//N
    private int passengersMax; //C
    private ArrayList<Coordinates> stationsCoordinatesList;
    Coordinates cgCoordinates;

	private HashMap<Coordinates,Integer> passengersInCoordinates;
	private HashMap<Coordinates,Integer> blackBoxCounterInCoordinates;

	private Coordinates [] grid;



    public int getWidth() {
		return width;
	}

	public void setWidth(int width) {
		this.width = width;
	}

	public int getHeight() {
		return height;
	}

	public void setHeight(int height) {
		this.height = height;
	}

	public int getPassengersMax() {
		return passengersMax;
	}

	public void setPassengersMax(int passengersMax) {
		this.passengersMax = passengersMax;
	}

	public ArrayList<Coordinates> getStationsCoordinatesList() {
		return stationsCoordinatesList;
	}

	public void setStationsCoordinatesList(ArrayList<Coordinates> stationsCoordinatesList) {
		this.stationsCoordinatesList = stationsCoordinatesList;
	}

	public Coordinates getCgCoordinates() {
		return cgCoordinates;
	}

	public void setCgCoordinates(Coordinates cgCoordinates) {
		this.cgCoordinates = cgCoordinates;
	}

	public Coordinates[] getGrid() {
		return grid;
	}

	public void setGrid(Coordinates[] grid) {
		this.grid = grid;
	}



    public Grid(int m, int n, int C, Coordinates cgCoordinates ,ArrayList<Coordinates> stationsCoordinatesList, HashMap<Coordinates,Integer> passengersInCoordinates,
				HashMap<Coordinates,Integer> blackBoxCounterInCoordinates)
    {
        width = m; height= n;
        passengersMax = C;
        this.stationsCoordinatesList = stationsCoordinatesList;
        this.passengersInCoordinates = passengersInCoordinates;
		this.blackBoxCounterInCoordinates = blackBoxCounterInCoordinates;
		this.cgCoordinates = cgCoordinates;
        
    }

    public Point2D getCoastGuardLocation() {
        return cgCoordinates;
    }

    public boolean moveCoastGuard(int x, int y) {
        //implement a method to change coast guard position
        //remember to check first that after moving it will not exceed the grid height and width
        //if exceeded, return false (did not move)
        return true;
    }

	public static void main(String[] args) {
		System.out.print("hi");
		Set<Coordinates> xx=new HashSet<>();
		xx.add(new Coordinates(1,1));
		xx.add(new Coordinates(1,1));
		xx.add(new Coordinates(2,3));
		ArrayList<Coordinates> arr = new ArrayList<>(xx);
		for(Coordinates element:arr)
		{
		System.out.println(element.getX()+"   "+element.getY());
		}


	}
}
