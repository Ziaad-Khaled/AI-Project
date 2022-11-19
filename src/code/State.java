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

    private int numberOfPassengersOnCG;


    private int deaths;
    private int retrieved;

//    private int pathCost;



    public State(Coordinates coastGuardLocation, HashMap<Coordinates,Integer> passengersInCoordinates,
                 HashMap<Coordinates,Integer> blackBoxCounterInCoordinates, int numberOfPassengersOnCG,
                 int deaths, int retrieved){
        this.coastGuardLocation = coastGuardLocation;
        this.passengersInCoordinates = passengersInCoordinates;
        this.blackBoxCounterInCoordinates = blackBoxCounterInCoordinates;
        this.numberOfPassengersOnCG = numberOfPassengersOnCG;
        this.deaths = deaths;
        this.retrieved=retrieved;
    }

    public int getDeaths() {
        return deaths;
    }

    public void setDeaths(int deaths) {
        this.deaths = deaths;
    }

    public int getRetrieved() {
        return retrieved;
    }

    public void setRetrieved(int retrieved) {
        this.retrieved = retrieved;
    }


    public HashMap<Coordinates, Integer> getPassengersInCoordinates() {
        return passengersInCoordinates;
    }

    public void setPassengersInCoordinates(HashMap<Coordinates, Integer> passengersInCoordinates) {
        this.passengersInCoordinates = passengersInCoordinates;
    }


    public int getNumberOfPassengersOnCG() {
        return numberOfPassengersOnCG;
    }

    public void setNumberOfPassengersOnCG(int numberOfPassengersOnCG) {
        this.numberOfPassengersOnCG = numberOfPassengersOnCG;
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

    public int preformATimeStep(){

        int cost = 0; //the cost represents the number of passengers who died as we are performing the action
        //and the number of ships whose black boxes are no longer retrievable

        //iterate over the number of passengers on every ship
        for (HashMap.Entry<Coordinates, Integer> set : passengersInCoordinates.entrySet()) {
            // if the passengers greater than zero then decrement
            // else increment the black box by one
            if(set.getValue() > 0)
            {
                set.setValue(set.getValue()- 1);
                //increment the cost
                cost++;
                //increment the number of deaths till this state
                setDeaths(getDeaths()+1);
            }
            else
            {
                int oldValue =  blackBoxCounterInCoordinates.get(set.getKey());

                /// we should add a ship class to know
                // if black box counter is greater than 20 we should not increment it again
                if(oldValue<20)
                {
                    blackBoxCounterInCoordinates.replace(set.getKey(), oldValue + 1);

                    //if the black box became non-retrievable in this step, increase cost
                    //remove the ship from both hashmaps
                    if(oldValue+1 == 20)
                    {
                        cost++;
                        //it will be difficult to remove the keys inside the loop (will result in errors)
                        //so assign to a specific value=1000 and after the loops we filter on this value and remove it
                        blackBoxCounterInCoordinates.put(set.getKey(),1000);
                        passengersInCoordinates.put(set.getKey(),1000);
                    }
                }

            }
        }
        //remove the ships that have no passnegers or black boxes based on the filter
        blackBoxCounterInCoordinates.values().removeIf(value -> value == 1000);
        passengersInCoordinates.values().removeIf(value -> value == 1000);

        return cost;
    }

    public String toString()
    {
        return "getPassengersInCoordinates" + this.getPassengersInCoordinates() +
                "getblackBoxCountInCoordinates" + this.getblackBoxCountInCoordinates() +
                "getCoastGuardLocation " + getCoastGuardLocation();
    }
}
