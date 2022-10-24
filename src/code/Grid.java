package code;

import java.awt.geom.Point2D;

public class Grid {

    private int width;//M
    private int height;//N
    private int passengersMax; //C
    private Point2D[] station;
    private State [] states;
    private State currentState;



    public Grid()
    {
        //generate a random grid
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
