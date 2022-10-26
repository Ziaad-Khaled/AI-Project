package code;

import java.awt.geom.Point2D;
import java.util.*;

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
   public static Grid GenGrid()
   {
	   Random rand = new Random(); //instance of random class
	   int width = rand.nextInt();
	   int height=rand.nextInt();
	   int passengersMax=rand.nextInt();

	   // random number of stations to be created
	   int stationsNumber=rand.nextInt(width*height);
	   Set<Coordinates> stationsCoordinatesSet=new HashSet<>();
	   for(int i=0;i<stationsNumber;i++)
	   {
		   stationsCoordinatesSet.add(new Coordinates(rand.nextInt(height),rand.nextInt(width)));
	   }


	   Coordinates cgCoordinates=new Coordinates(rand.nextInt(height),rand.nextInt(width));
      //generate number of ships which cannot exceed my grid size and has upper limit of grid size minus stations
	   int shipsNumber=rand.nextInt(width*height-stationsCoordinatesSet.size());
	   int shipPassengers;
	   Set<Ship> ships=new HashSet<>();
       int x;
	   int y;
	   for(int i=0;i<shipsNumber;i++)
	   {   shipPassengers=rand.nextInt();
		   x=rand.nextInt(height);
		   y=rand.nextInt(width);
		   while (stationsCoordinatesSet.contains(new Coordinates(x,y)))
		   {
			   x=rand.nextInt(height);
			   y=rand.nextInt(width);
		   }
		   ships.add(new Ship(new Coordinates(x,y) ,shipPassengers));
	   }
	   //converting sets to arraylist
	   ArrayList<Ship> shipss = new ArrayList<>(ships);
	   ArrayList<Coordinates> stationsCoordinatesList = new ArrayList<>(stationsCoordinatesSet);


	   Grid myGrid=new Grid(width,height,passengersMax,cgCoordinates ,stationsCoordinatesList,shipss);
	   return myGrid;

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
