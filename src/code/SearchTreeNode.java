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
        //Situation 1: the ship with the maximum number of passengers will sink, all passengers will sink and we will not retrieve black box
        //So cost will be number of passengers + 1 (blackbox not retrieved)
        //Situation 2: The ship with the max number of passengers will be rescued and the black box will be retrieved
        //So till we reach the ship, number of passengers who will die is the manhattan distance


        HashMap<Coordinates,Integer> passengersInCoordinates = getState().getPassengersInCoordinates();
        HashMap<Coordinates,Integer> blackBoxCountInCoordinates = getState().getblackBoxCountInCoordinates();

        if(passengersInCoordinates.isEmpty())
            return 0;

        boolean noMorePassengers = true;
        for(Coordinates ship : passengersInCoordinates.keySet())
            if(passengersInCoordinates.get(ship)>0)
            {
                noMorePassengers = false;break;
            }

        if(noMorePassengers)
        {
          int nonRetreivableBlackBoxes = 0;
          for(Coordinates ship: blackBoxCountInCoordinates.keySet())
              if(getState().getCoastGuardLocation().manhattanDistance(ship)>blackBoxCountInCoordinates.get(ship))
                  nonRetreivableBlackBoxes++;
          return nonRetreivableBlackBoxes;
        }
        else {
            //get the coordinate that has the maximum number of passengers
            Map.Entry<Coordinates, Integer> maxEntry = null;
            for (Map.Entry<Coordinates, Integer> entry : passengersInCoordinates.entrySet()) {
                if (maxEntry == null || entry.getValue().compareTo(maxEntry.getValue()) > 0) {
                    maxEntry = entry;
                }
            }
            Coordinates maxPassengersLocation = maxEntry.getKey();
            int maxPassengers = maxEntry.getValue();
            Coordinates cgLocation = getState().getCoastGuardLocation();//get the cg coordinate

            //get Manhattan Distance between location of passengers and cgLocation
            int distanceToCoordinates = maxPassengersLocation.manhattanDistance(cgLocation);



            return Math.min(maxPassengers + 1, distanceToCoordinates);
        }
    }

    public int costOfRescuingMaxShip(Coordinates maxPassengersLocation)
    {
        int capacity=grid.getPassengersMax() - this.getState().getNumberOfPassengersOnCG();
        int heuristicValue;
        Coordinates cgCoordinates = this.getState().getCoastGuardLocation();
        if(capacity>0) {
            Coordinates cgLocation = getState().getCoastGuardLocation();//get the cg coordinate

            //get Manhattan Distance between location of passengers and cgLocation
            int distanceToCoordinates = maxPassengersLocation.manhattanDistance(cgLocation);

            //get minimum distance from coordinates to a station
            double minDistanceToStation = Double.POSITIVE_INFINITY;
            for (Coordinates c : getGrid().getStationsCoordinatesList()) {
                if (c.manhattanDistance(maxPassengersLocation) < minDistanceToStation)
                    minDistanceToStation = c.manhattanDistance(maxPassengersLocation);
            }

            //total needed distance to rescue this max ship is distance to reach ship + distance to reach nearest station
            int totalDistanceToRescueMaxShip = (int) (distanceToCoordinates + minDistanceToStation) + 2;
            int shipDeaths = 0;
            for (Coordinates otherShipCoordinate : getState().getPassengersInCoordinates().keySet())//how many deaths will happen in otherShipCoordinate
            {
                if (maxPassengersLocation.equals(otherShipCoordinate))//if this other ship is equal to the ship I will rescue
                {
                    shipDeaths += maxPassengersLocation.manhattanDistance(this.getState().getCoastGuardLocation());
                    continue;
                }
                shipDeaths += Math.min(this.getState().getPassengersInCoordinates().get(otherShipCoordinate),
                        totalDistanceToRescueMaxShip);
            }
            return shipDeaths;
        }
        else{
            // cg-
            //HashMap<Coordinates, Integer> distancesToStations =(HashMap<Coordinates, Integer>) this.grid.getStationsCoordinatesList().clone();
            HashSet<Coordinates> stationCoordinates= (HashSet<Coordinates>) this.getGrid().getStationsCoordinatesList().clone();
            HashMap<Coordinates, Integer> distancesToStations=new HashMap<>();
            for(Coordinates station:stationCoordinates){
                distancesToStations.put(station,cgCoordinates.manhattanDistance(station));
            }

            //distancesToStations.replaceAll((stationCoordinate, v)->v=cgCoordinates.manhattanDistance(neareastStation(cgCoordinates)));

            HashMap<Coordinates, Integer> deathsToStations = computeDeathsToReachStation(distancesToStations);

            return Collections.min(deathsToStations.values());
        }




    }
    //20
    //total num of people + people on CG
    //15  + 5
    //15
    //         [   []  9 6 3[15]     [] [] []      ]

    public int heuristic2()
    {
        int capacity=grid.getPassengersMax() - this.getState().getNumberOfPassengersOnCG();
        int heuristicValue;
        Coordinates cgCoordinates = this.getState().getCoastGuardLocation();
        if(capacity>0){
            if(getState().getPassengersInCoordinates().size()==0)
                //no more ships and no more black boxes, no more passengers to drop
                return 0;//a goal node will have h=0


            HashMap<Coordinates, Integer> passengersInCoordinates = this.getState().getPassengersInCoordinates();
//        HashMap<Coordinates, Integer> ManhattanToCoordinates = (HashMap<Coordinates, Integer>) passengersInCoordinates.clone();
//        ManhattanToCoordinates.replaceAll( (k,v)->v=k.manhattanDistance(cgCoordinates));
            HashMap<Coordinates, Integer> distancesToRescueShips =(HashMap<Coordinates, Integer>) passengersInCoordinates.clone();
            distancesToRescueShips.replaceAll((shipCoordinate, v)->v=cgCoordinates.manhattanDistance(shipCoordinate) +
                    shipCoordinate.manhattanDistance(neareastStation(shipCoordinate)));
            HashMap<Coordinates, Integer> deathsToRescueShip = computeDeathsToRescueEachShip(distancesToRescueShips);


            HashMap<Coordinates, Integer> evaluateRescuingShips = (HashMap<Coordinates, Integer>) passengersInCoordinates.clone();
            // Free capacity on coastguard

            // evaluating each ship= number of passengers that can be rescued on the ship - deaths so far to rescue it
            evaluateRescuingShips.replaceAll((shipCoordinate, v)->v=deathsToRescueShip.get(shipCoordinate)-Math.min(v-cgCoordinates.manhattanDistance(shipCoordinate),capacity));
            //finding the ship with the best evaluation

            Coordinates shipWithBestEvaluation=new Coordinates(0,0);
            double minValue = Double.POSITIVE_INFINITY;
            for(Coordinates shipCoordinates: evaluateRescuingShips.keySet()) {
                if (evaluateRescuingShips.get(shipCoordinates) < minValue) {
                    minValue = evaluateRescuingShips.get(shipCoordinates);
                    shipWithBestEvaluation = shipCoordinates;
                }
            }
            //heuristic is no. of deaths to rescue ship with best evaluation
             heuristicValue = deathsToRescueShip.get(shipWithBestEvaluation);

        }
        else{
            // cg-
            //HashMap<Coordinates, Integer> distancesToStations =(HashMap<Coordinates, Integer>) this.grid.getStationsCoordinatesList().clone();
            HashSet<Coordinates> stationCoordinates= (HashSet<Coordinates>) this.getGrid().getStationsCoordinatesList().clone();
            HashMap<Coordinates, Integer> distancesToStations=new HashMap<>();
            for(Coordinates station:stationCoordinates){
                distancesToStations.put(station,cgCoordinates.manhattanDistance(station));
            }

            //distancesToStations.replaceAll((stationCoordinate, v)->v=cgCoordinates.manhattanDistance(neareastStation(cgCoordinates)));

            HashMap<Coordinates, Integer> deathsToStations = computeDeathsToReachStation(distancesToStations);

            heuristicValue=Collections.min(deathsToStations.values());
        }

        return heuristicValue;

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
    public HashMap<Coordinates,Integer> computeDeathsToRescueEachShip(HashMap<Coordinates, Integer> distancesToRescueShips)
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

    public HashMap<Coordinates,Integer> computeDeathsToReachStation(HashMap<Coordinates, Integer> distancesToStations)
    {
        HashMap <Coordinates, Integer> deathsToStations = new HashMap<>();
        for(Coordinates stationCoordinate: distancesToStations.keySet())//station we will reach
        {
            int shipDeaths = 0;
            for(Coordinates ShipCoordinate: this.getState().getPassengersInCoordinates().keySet())//how many deaths will happen in otherShipCoordinate
            {
                shipDeaths+= Math.min(this.getState().getPassengersInCoordinates().get(ShipCoordinate),
                        distancesToStations.get(stationCoordinate));
            }
            deathsToStations.put(stationCoordinate, shipDeaths);
        }
        return  deathsToStations;
    }


    public int AStarWithH1()
    {
        return  heuristic1() + getPathCost();
    }
    public int AStarWithH2()
    {
        return  heuristic2() + getPathCost();
    }

    @Override
    public String toString() {
        return getState().toString() +" depth: " + getDepth();
    }



}
