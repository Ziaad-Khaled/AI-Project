package code;

import java.awt.geom.Point2D;
import java.util.ArrayList;

public class Grid {
	private int width;//M
    private int height;//N
    private int passengersMax; //C
    private ArrayList<Coordinates> stationsCoordinatesList;
    Coordinates cgCoordinates;
    private State [] states;
    private State currentState;
    ArrayList<Ship> ships;
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

	public State[] getStates() {
		return states;
	}

	public void setStates(State[] states) {
		this.states = states;
	}

	public State getCurrentState() {
		return currentState;
	}

	public void setCurrentState(State currentState) {
		this.currentState = currentState;
	}

	public ArrayList<Ship> getShips() {
		return ships;
	}

	public void setShips(ArrayList<Ship> ships) {
		this.ships = ships;
	}

	public Coordinates[] getGrid() {
		return grid;
	}

	public void setGrid(Coordinates[] grid) {
		this.grid = grid;
	}



    public Grid(int m, int n, int C, Coordinates cgCoordinates ,ArrayList<Coordinates> stationsCoordinatesList, ArrayList<Ship> ships)
    {
        width = m; height= n;
        passengersMax = C;
        this.stationsCoordinatesList = stationsCoordinatesList;
        this.ships = ships;
        this.cgCoordinates = cgCoordinates;
        
    }

    public Point2D getCoastGuard() {
        return currentState.getCoastGuard();
    }

    public boolean moveCoastGuard(int x, int y) {
        //implement a method to change coast guard position
        //remember to check first that after moving it will not exceed the grid height and width
        //if exceeded, return false (did not move)
        return true;
    }
}
