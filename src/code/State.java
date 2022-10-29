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

    public int getNumberOfPassengersOnCG() {
        return numberOfPassengersOnCG;
    }

    public void setNumberOfPassengersOnCG(int numberOfPassengersOnCG) {
        this.numberOfPassengersOnCG = numberOfPassengersOnCG;
    }

    private int numberOfPassengersOnCG = 0;

    private int pathCost;

    public State(Coordinates coastGuardLocation, HashMap<Coordinates,Integer> passengersInCoordinates,
                 HashMap<Coordinates,Integer> blackBoxCounterInCoordinates){
        this.coastGuardLocation = coastGuardLocation;
        this.passengersInCoordinates = passengersInCoordinates;
        this.blackBoxCounterInCoordinates = blackBoxCounterInCoordinates;
    }
    public Coordinates getCoastGuardLocation() {
        return coastGuardLocation;
    }

    public void setCoastGuardLocation(Coordinates coastGuardLocation) {
        this.coastGuardLocation = coastGuardLocation;
    }

    public HashMap<Coordinates, Integer> getNumberOfPassngersInCoordinates ()
    {
        return passengersInCoordinates;
    }
    public HashMap<Coordinates, Integer> getblackBoxCountInCoordinates ()
    {
        return blackBoxCounterInCoordinates;
    }

    public void preformATimeStep(){
        //iterate over the number of passengers on every ship
        for (HashMap.Entry<Coordinates, Integer> set : passengersInCoordinates.entrySet()) {
            // if the passengers greater than zero then decrement
            // else increment the black box by one
            if(set.getValue() > 0)
                set.setValue(set.getValue()- 1);
            else
            {
                int oldValue =  blackBoxCounterInCoordinates.get(set.getKey());

                /// we should add a ship class to know
                // if black box counter is greater than 100 we should not increment it again
                blackBoxCounterInCoordinates.replace(set.getKey(), oldValue + 1);
            }
        }
    }
}
