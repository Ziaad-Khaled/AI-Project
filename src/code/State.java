package code;

import java.awt.geom.Point2D;
import java.util.ArrayList;
import java.util.HashMap;

public class State{
//    private Point2D[] ship;
//    private int[] passengersNum;

    //state in the world is specified by the coastGuard location, the number of passengers in each ship, black box counter
    //in each ship
    private Coordinates coastGuardLocation;
    private HashMap<Coordinates,Integer> passengersInCoordinates;
    private HashMap<Coordinates,Integer> blackBoxCounterInCoordinates;

    private int pathCost;

    public State(Coordinates coastGuardLocation, HashMap<Coordinates,Integer> passengersInCoordinates,
                 HashMap<Coordinates,Integer> blackBoxCounterInCoordinates, int pathCost){
        this.coastGuardLocation = coastGuardLocation;
        this.passengersInCoordinates = passengersInCoordinates;
        this.blackBoxCounterInCoordinates = blackBoxCounterInCoordinates;
    }
    public Coordinates getCoastGuardLocation() {
        return coastGuardLocation;
    }

    public int getNumberOfPassngersInCoordinates (Coordinates c)
    {
        return passengersInCoordinates.get(c);
    }
    public int getblackBoxCountInCoordinates (Coordinates c)
    {
        return blackBoxCounterInCoordinates.get(c);
    }
}
