package code;

import java.sql.SQLOutput;
import java.util.*;

public class SearchTreeNode {

    private SearchTreeNode parent;//map every action to a new node

    public ArrayList<String> getActionsSequence() {
        return actionsSequence;
    }

    private ArrayList<String> actionsSequence;//sequence of actions from the root till this node
    private int pathCost;//the number of passengers who sinked + the number of black boxes that expired till this state

    private State state;



    private Grid grid;

//    String operator;
//    //children??
    public SearchTreeNode(SearchTreeNode parent, ArrayList<String> actionsSequence, int pathCost, State state)
    {
        this.parent=parent;
        this.actionsSequence= (ArrayList<String>) actionsSequence.clone();
        this.pathCost=pathCost;
        this.state=state;
        this.grid = parent.grid;
    }
    public SearchTreeNode(SearchTreeNode parent, ArrayList<String> actionsSequence, int pathCost, State state, Grid grid)
    {
        this.parent=parent;
        this.actionsSequence= (ArrayList<String>) actionsSequence.clone();
        this.pathCost=pathCost;
        this.state=state;
        this.grid = grid;
    }
    public Grid getGrid() {
        return grid;
    }

    public void setGrid(Grid grid) {
        this.grid = grid;
    }

    public int getDepth()
    {
        return actionsSequence.size();
    }

    public SearchTreeNode getParent() {
        return parent;
    }

    public void setParent(SearchTreeNode parent) {
        this.parent = parent;
    }

    public void addAction(String action)
    {
        actionsSequence.add(action);
    }

    public int getPathCost() {
        return pathCost;
    }

    public void setPathCost(int pathCost) {
        this.pathCost = pathCost;
    }

    public State getState() {
        return state;
    }

    public void setState(State state) {
        this.state = state;
    }

    public int heuristic1()
    {
        //heuristic is the minimum of two situations:
        //Situation 1: the ship with the maximum number of passengers will sink, all passengers will sink
        //So time points (depth to the goal) will be number of passengers + 1 (lower bound to retrieve black box of the black box)
        //Situation 2: The ship with the max number of passengers will be rescued and the black box will be retrieved
        //So time points will be the actions needed to rescue all of them and retrieve black box
        //that is, number of moves to rescue and move them to station and retrieve black box
        //manhattan distance to ship + manhattan distance to station + 2 time points for pickup and retrieval of black box
        HashMap<Coordinates,Integer> passengersInCoordinates = getState().getPassengersInCoordinates();

        if(passengersInCoordinates.size()==0)//no more ships and no more black boxes
            return 0;//a goal node will have h=0

        //get the coordinate that has the maximum number of passengers
        Map.Entry <Coordinates, Integer> maxEntry = null;
        for (Map.Entry<Coordinates, Integer> entry : passengersInCoordinates.entrySet())
        {
            if (maxEntry == null || entry.getValue().compareTo(maxEntry.getValue()) > 0)
            {
                maxEntry = entry;
            }
        }


        Coordinates maxPassengersLocation = maxEntry.getKey();
        int maxPassengers = maxEntry.getValue();
        Coordinates cgLocation = getState().getCoastGuardLocation();//get the cg coordinate

        //get Manhattan Distance between location of passengers and cgLocation
        int distanceToCoordinates = maxPassengersLocation.manhattanDistance(cgLocation);

        //get minimum distance from coordinates to a station
        double minDistanceToStation = Double.POSITIVE_INFINITY;
        for(Coordinates c : getGrid().getStationsCoordinatesList())
        {
            if(c.manhattanDistance(maxPassengersLocation)<minDistanceToStation)
                minDistanceToStation = c.manhattanDistance(maxPassengersLocation);
        }

        return Math.min(maxPassengers+1, distanceToCoordinates+(int)minDistanceToStation+2);

    }

    public int heuristic2()
    {
        return 0;
    }

    public int AStarWithH1()
    {
        return  heuristic1() + getDepth();
    }
    public int AStarWithH2()
    {
        return  heuristic2() + getDepth();
    }

    @Override
    public String toString() {
        return getState().toString() +" depth: " + getDepth();
    }



}
