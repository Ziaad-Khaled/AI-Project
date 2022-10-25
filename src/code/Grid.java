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
