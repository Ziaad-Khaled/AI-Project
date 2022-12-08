package code;

import java.util.*;


public class SearchTreeNode {

    private SearchTreeNode parent;//map every action to a new node

    public ArrayList<String> getActionsSequence() {
        return actionsSequence;
    }

    private ArrayList<String> actionsSequence;//sequence of actions from the root till this node
    private Pair pathCost;//the number of passengers who sinked , the number of black boxes that expired till this state

    private State state; //state of the node



    private Grid grid;

//    String operator;
//    //children??
    public SearchTreeNode(SearchTreeNode parent, ArrayList<String> actionsSequence, Pair pathCost, State state)
    {
        this.parent=parent;
        this.actionsSequence= (ArrayList<String>) actionsSequence.clone();
        this.pathCost=pathCost;
        this.state=state;
        this.grid = parent.grid;
    }
    public SearchTreeNode(SearchTreeNode parent, ArrayList<String> actionsSequence, Pair pathCost, State state, Grid grid)
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

    public Pair getPathCost() {
        return pathCost;
    }

    public void setPathCost(Pair pathCost) {
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
        int heuristicValue;
        int capacity=grid.getPassengersMax() - this.getState().getNumberOfPassengersOnCG();
        Coordinates cgCoordinates = this.getState().getCoastGuardLocation();
        HashMap<Coordinates, Integer> passengersInCoordinates = getState().getPassengersInCoordinates();
        if(passengersInCoordinates.isEmpty())//if no passengers present, then no one will die
            return 0;
        if(capacity>0) {//if cg still has capacity, search for the nearest ship that has passengers,
            //compute deaths to reach it
            Coordinates nearestShip = null;
            double minDistanceToShip = Double.POSITIVE_INFINITY;
            for(Coordinates ship: passengersInCoordinates.keySet())
            {
                if(cgCoordinates.manhattanDistance(ship)<minDistanceToShip && passengersInCoordinates.get(ship)>0)
                {
                    minDistanceToShip = cgCoordinates.manhattanDistance(ship);
                    nearestShip = ship;
                }
            }
            if(nearestShip==null)//if no ship was found to have passengers
                return 0;
            heuristicValue = computeDeathsFromDistance(cgCoordinates.manhattanDistance(nearestShip));
        }
        else{//if cg has no capacity, search for nearest station, calculate deaths to reach it
            Coordinates nearestStation = null;
            double minDistanceToStation = Double.POSITIVE_INFINITY;
            for(Coordinates station: getGrid().getStationsCoordinatesList())
            {
                if(cgCoordinates.manhattanDistance(station)<minDistanceToStation)
                {
                    minDistanceToStation = cgCoordinates.manhattanDistance(station);
                    nearestStation = station;
                }
            }
            heuristicValue = computeDeathsFromDistance(cgCoordinates.manhattanDistance(nearestStation));
        }
        return heuristicValue;
    }
    public int computeDeathsFromDistance(int distance)
    {
        int deaths = 0;
        HashMap<Coordinates,Integer> passengersInCoordinates = getState().getPassengersInCoordinates();
        for(Coordinates ship: passengersInCoordinates.keySet())
        {
            deaths += Math.min(distance, passengersInCoordinates.get(ship));
        }
        return deaths;
    }
    public int heuristic3()//temp heuristic
    {
        //heuristic is the minimum of two situations:
        //Situation 1: the ship with the maximum number of passengers will sink, all passengers will sink
        //So cost will be number of passengers
        //Situation 2: The ship with the max number of passengers will be rescued
        //So till we reach the ship, number of passengers who will die is the manhattan distance


        HashMap<Coordinates,Integer> passengersInCoordinates = getState().getPassengersInCoordinates();

        if(passengersInCoordinates.isEmpty())
            return 0;

//        boolean noMorePassengers = true;
//        for(Coordinates ship : passengersInCoordinates.keySet())
//            if(passengersInCoordinates.get(ship)>0)
//            {
//                noMorePassengers = false;break;
//            }
//
//        if(noMorePassengers)
//        {
//          int nonRetreivableBlackBoxes = 0;
//          for(Coordinates ship: blackBoxCountInCoordinates.keySet())
//              if(getState().getCoastGuardLocation().manhattanDistance(ship)>20-blackBoxCountInCoordinates.get(ship))
//                  nonRetreivableBlackBoxes++;
//          return nonRetreivableBlackBoxes;
//        }
//        else {
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


            return Math.min(maxPassengers, distanceToCoordinates);
//        }
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

    public Pair heuristic2()
    {
        //
        //
        int capacity = grid.getPassengersMax() - this.getState().getNumberOfPassengersOnCG();
        int heuristicValue;
        Coordinates cgCoordinates = this.getState().getCoastGuardLocation();



        int blackboxheuristic=0;
        //System.out.println("only boxes left");
        HashMap<Coordinates, Integer> BoxesEvaluation =(HashMap<Coordinates, Integer>) this.getState().getblackBoxCountInCoordinates().clone();
        //Evaluating remaining ticks for each blackbox if we reached it, If the value is negative this means black box cannot be retrieved
        // 20-(Counter+distance)
        BoxesEvaluation.replaceAll((boxCoordinate, v)->v=20-(v + cgCoordinates.manhattanDistance(boxCoordinate)));

        for(Coordinates boxCoordinates: BoxesEvaluation.keySet()) {
            if (BoxesEvaluation.get(boxCoordinates)<=0) {
                blackboxheuristic++;
            }
        }



        if(getState().getPassengersInCoordinates().size()==0)
        { return new Pair(0,0); }//a goal node will have h=0
         else{ //will enter this case if there are no more ships but there exist black boxes
            int maxValueInMap = (Collections.max(this.getState().getPassengersInCoordinates().values()));
            //if maximum number of people on a ship equals zero, This means no more people are there and only black boxes left
            if(maxValueInMap==0)
            {
                return new Pair(0,blackboxheuristic);//a goal node will have h=0
            }

        }
        if(capacity>0){


          HashMap<Coordinates, Integer> passengersInCoordinates = this.getState().getPassengersInCoordinates();
//        HashMap<Coordinates, Integer> ManhattanToCoordinates = (HashMap<Coordinates, Integer>) passengersInCoordinates.clone();
//        ManhattanToCoordinates.replaceAll( (k,v)->v=k.manhattanDistance(cgCoordinates));
            HashMap<Coordinates, Integer> distancesToRescueShips =(HashMap<Coordinates, Integer>) passengersInCoordinates.clone();
            distancesToRescueShips.replaceAll((shipCoordinate, v)->v=cgCoordinates.manhattanDistance(shipCoordinate));
//                    + shipCoordinate.manhattanDistance(neareastStation(shipCoordinate)));
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


        return new Pair(heuristicValue , blackboxheuristic);

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
//        System.out.println(getState().getCoastGuardLocation());
//        System.out.println(distancesToRescueShips.toString());
//        System.out.println(deathsToRescueShips.toString());
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


    public Pair AStarWithH1()
    {
        return new Pair(getPathCost().deaths + heuristic1(), getPathCost().expiredBlackBoxes);
    }
    public Pair AStarWithH2()
    {
        return new Pair(getPathCost().deaths + heuristic2().deaths, getPathCost().expiredBlackBoxes+heuristic2().expiredBlackBoxes);
    }
    public Pair AStarWithH3()
    {
        return new Pair(getPathCost().deaths + heuristic3(), getPathCost().expiredBlackBoxes+heuristic2().expiredBlackBoxes);
    }

    @Override
    public String toString() {
        return getState().toString() +" depth: " + getDepth();
    }



}

//Class pair that is used to define the path cost
//Path cost is a pair <deaths, expiredBlackBoxes>
class Pair implements Comparable {
    int deaths;
    int expiredBlackBoxes;
    public Pair(int deaths, int expiredBlackBoxes)
    {
        this.deaths=deaths;
        this.expiredBlackBoxes=expiredBlackBoxes;
    }

    //first compare with deaths, if equal, compare with expiredBlackBoxes
    @Override
    public int compareTo(Object o) {
        if(this.deaths==((Pair)o).deaths)
            return Integer.compare(this.expiredBlackBoxes, ((Pair) o).expiredBlackBoxes);
        return Integer.compare(this.deaths, ((Pair) o).deaths);

    }

    @Override
    public String toString() {
        return " Deaths: " + deaths + " Expired Black Boxes: " + expiredBlackBoxes ;
    }
}
