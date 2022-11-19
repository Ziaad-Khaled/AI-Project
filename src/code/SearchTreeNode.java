package code;

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
        HashMap<Coordinates, Integer> passengersInCoordinates = getState().getPassengersInCoordinates();
        if(getState().getPassengersInCoordinates().size()==0 && getState().getNumberOfPassengersOnCG()==0)
            //no more ships and no more black boxes, no more passengers to drop
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
        int capacity=grid.getPassengersMax() - this.getState().getNumberOfPassengersOnCG();
        int heuristicValue;
        Coordinates cgCoordinates = this.getState().getCoastGuardLocation();
        if(capacity>0){
            if(getState().getPassengersInCoordinates().size()==0 && getState().getNumberOfPassengersOnCG()==0)
                //no more ships and no more black boxes, no more passengers to drop
                return 0;//a goal node will have h=0


            HashMap<Coordinates, Integer> passengersInCoordinates = this.getState().getPassengersInCoordinates();
//        HashMap<Coordinates, Integer> ManhattanToCoordinates = (HashMap<Coordinates, Integer>) passengersInCoordinates.clone();
//        ManhattanToCoordinates.replaceAll( (k,v)->v=k.manhattanDistance(cgCoordinates));
            HashMap<Coordinates, Integer> distancesToRescueShips =(HashMap<Coordinates, Integer>) passengersInCoordinates.clone();
            distancesToRescueShips.replaceAll((shipCoordinate, v)->v=cgCoordinates.manhattanDistance(shipCoordinate) +
                    shipCoordinate.manhattanDistance(neareastStation(shipCoordinate)));
            HashMap<Coordinates, Integer> deathsToRescueShip = computeDeathsToRescueShip(distancesToRescueShips);
            HashMap<Coordinates, Integer> evaluateRescuingShips = (HashMap<Coordinates, Integer>) passengersInCoordinates.clone();
            // Free capacity on coastguard

            // evaluating each ship= number of passengers that are can be rescued on the ship - deaths so far to rescue it
            evaluateRescuingShips.replaceAll((shipCoordinate, v)->v=deathsToRescueShip.get(shipCoordinate)-Math.min(v,capacity));
            //finding the ship with the best evaluation
            Coordinates shipWithBestEvaluation=new Coordinates(0.0,0.0);
            double minValue = Double.POSITIVE_INFINITY;
            for(Coordinates shipCoordinates: evaluateRescuingShips.keySet()) {
                if (evaluateRescuingShips.get(shipCoordinates) < minValue) {
                    minValue = evaluateRescuingShips.get(shipCoordinates);
                    shipWithBestEvaluation = shipCoordinates;
                }
            }
             heuristicValue = shipWithBestEvaluation.manhattanDistance(cgCoordinates);

        }
        else{
            HashMap<Coordinates, Integer> distancesToStations =(HashMap<Coordinates, Integer>) this.grid.getStationsCoordinatesList().clone();
            distancesToStations.replaceAll((shipCoordinate, v)->v=cgCoordinates.manhattanDistance(neareastStation(cgCoordinates)));

            HashMap<Coordinates, Integer> deathsToStations = computeDeathsToRescueShip(distancesToStations);
            heuristicValue=Collections.min(deathsToStations.values());
        }

        return heuristicValue;
        //min ship
        //no:       4  17  19
        //distance:    1 1
        //compute deaths/0  compute deaths/3  compute deaths/5
        //[min-min,,,,,,,max]
        //[]
        //1-4=-3
        //  4
        //  1
        //  1
    }
    public Coordinates neareastStation(Coordinates shipCoordinate)
    {
        HashSet<Coordinates> stationCoordinates = this.grid.getStationsCoordinatesList();
        double minDistance = Double.POSITIVE_INFINITY;
        Coordinates stationWithMinDistance = null;
        for(Coordinates ship: stationCoordinates)
            if(ship.manhattanDistance(shipCoordinate)<minDistance)
            {
                minDistance=ship.manhattanDistance(shipCoordinate);
                stationWithMinDistance=ship;
            }
        return stationWithMinDistance;

    }
    public HashMap<Coordinates,Integer> computeDeathsToRescueShip(HashMap<Coordinates, Integer> distancesToRescueShips)
    {
        HashMap <Coordinates, Integer> deathsToRescueShips = new HashMap<>();
        for(Coordinates shipCoordinates: distancesToRescueShips.keySet())//ship coordinates is the one I will rescue
        {
            int shipDeaths = 0;
            for(Coordinates otherShipCoordinate: distancesToRescueShips.keySet())//how many deaths will happen in otherShipCoordinate
            {
                if(shipCoordinates.equals(otherShipCoordinate))//if this other ship is equal to the ship I will rescue
                {
                    shipDeaths += shipCoordinates.manhattanDistance(this.getState().getCoastGuardLocation());
                    continue;
                }
                shipDeaths+= Math.min(this.getState().getPassengersInCoordinates().get(otherShipCoordinate),
                        distancesToRescueShips.get(shipCoordinates));
            }
            deathsToRescueShips.put(shipCoordinates, shipDeaths);
        }
        return  deathsToRescueShips;
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
