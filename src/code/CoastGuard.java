package code;

import java.util.*;

public class CoastGuard extends GenericSearchProblem {


    public static String GenGrid()
    {
        Random rand = new Random(); //instance of random class
        int width = rand.nextInt(16 - 5) + 5;
        int height= rand.nextInt(16 - 5) + 5;
        int passengersMax = rand.nextInt(100 -30) +30;

        // random number of stations to be created
        int stationsNumber=rand.nextInt(width*height-1)+1; //making sure we have at least one station
        Set<Coordinates> stationsCoordinatesSet=new HashSet<>();
        for(int i=0;i<stationsNumber;i++)
        {
            stationsCoordinatesSet.add(new Coordinates(rand.nextInt(height),rand.nextInt(width)));
        }


        Coordinates cgCoordinates=new Coordinates(rand.nextInt(height),rand.nextInt(width));
        //generate number of ships which cannot exceed my grid size and has upper limit of grid size minus stations
        int shipsNumber=rand.nextInt(width*height-stationsCoordinatesSet.size()-1)+1;//making sure we have at least one ship
        int shipPassengers;
        int x;
        int y;
        HashMap<Coordinates,Integer> passengersInCoordinates = new HashMap<>();
        for(int i=0;i<shipsNumber;i++)
        {   shipPassengers=rand.nextInt(100)+1;//1<=number of passengers<=100
            x=rand.nextInt(height);
            y=rand.nextInt(width);
            //making sure the coordinates do not have a ship
            while (stationsCoordinatesSet.contains(new Coordinates(x,y)))
            {
                x=rand.nextInt(height);
                y=rand.nextInt(width);
            }
            passengersInCoordinates.put(new Coordinates(x,y),shipPassengers);
        }

        //converting set to arraylist to ease iteration
        ArrayList<Coordinates> stationsCoordinatesList = new ArrayList<>(stationsCoordinatesSet);
        //parsing grid to a string
        String gridString = width + "," + height + ";" + passengersMax + ";" + cgCoordinates.getX() + "," + cgCoordinates.getY() + ";";

        //add the stationsLoactions to the grid string
        for(int i=0; i<stationsCoordinatesList.size();i++)
        {
            gridString+= stationsCoordinatesList.get(i).getX() + "," + stationsCoordinatesList.get(i).getY();
            if (i != stationsCoordinatesList.size() - 1)//add commas except at last element
                gridString+=",";
        }
        gridString+=";";

        //get coordinates of the ships
        ArrayList<Coordinates> shipCoordinates = new ArrayList<>(passengersInCoordinates.keySet());
        //add the ships locations and each ship's number of passengers to the grid strings
        for(int i=0; i<shipCoordinates.size(); i++) {
            gridString += shipCoordinates.get(i).getX() + "," + shipCoordinates.get(i).getY() + ",";
            gridString+= passengersInCoordinates.get(shipCoordinates.get(i));
            if (i != shipCoordinates.size() - 1)
                gridString+=",";
        }
        gridString+=";";


        return gridString;
    }

    public static String solve(String grid, String strategy, Boolean visualize) {

        //create the grid object from the string
        Grid gridObject = createGridFromString(grid);
        //create the initial state object
        State initialState = new State(gridObject.getCoastGuardLocation(),gridObject.getPassengersInCoordinates(),
                gridObject.getBlackBoxCounterInCoordinates(),0, 0, 0);
        //the root of the tree has the initial state
        SearchTreeNode root = new SearchTreeNode(null,new ArrayList<>(),new Pair(0,0),initialState, gridObject);
        String solution;
        CoastGuard p = new CoastGuard();
        switch(strategy)
        {
            case "BF":
                solution = p.solveBreadthFirstSearch(gridObject, visualize, root);
                break;
            case "DF":
                solution = p.solveDepthFirstSearch(gridObject, visualize, root);
                break;
            case "UC":
                solution = p.solveUniformCostSearch(gridObject, visualize, root);
                break;
            case "ID":
                solution = p.solveIterativeDeepeningSearch(gridObject, visualize, root);
                break;
            case "GR1", "GR2":
                solution = p.solveGreedySearch(gridObject, visualize, root, Integer.parseInt("" + strategy.charAt(strategy.length()-1)));
                break;
            case "AS1", "AS2":
                solution = p.solveAStarSearch(gridObject, visualize, root, Integer.parseInt("" + strategy.charAt(strategy.length()-1)));
                break;
            default:
                solution = "";
        }
        return solution;
    }
    
    public static Grid createGridFromString(String grid)
    {
    	//splitting the grid string by semicolons
        String[] convertedGridArray = grid.split(";");
        //first semicolon has the grid dimensions
        String[] gridDimensions = convertedGridArray[0].split(",");
        int m = Integer.parseInt(gridDimensions[0]);
        int n = Integer.parseInt(gridDimensions[1]);
        //second semicolon has the max number of passengers the coast guard boat can carry
        int C = Integer.parseInt(convertedGridArray[1]);
        //third semicolon has the initial coordinates of the coast guard boat
        String[] cgCoordinatesString = convertedGridArray[2].split(",");
        double cgX = Double.parseDouble(cgCoordinatesString[0]);
        double cgY = Double.parseDouble(cgCoordinatesString[1]);
        Coordinates cgCoordinates = new Coordinates(cgX,cgY);
        //fourth semicolon has the coordinates of the stations
        String[] stationsCoordinatesString = convertedGridArray[3].split(",");
        HashSet<Coordinates> stationCoordinatesList = new HashSet<>();//arraylist that will have all station coordinates
        for(int i=0; i<stationsCoordinatesString.length; i+=2){
            double x = Double.parseDouble(stationsCoordinatesString[i]);
            double y = Double.parseDouble(stationsCoordinatesString[i+1]);
            stationCoordinatesList.add(new Coordinates(x,y));
        }
        //fifth semicolon has the coordinates of the ships
        String[] shipsCoordinatesAndPassengersString = convertedGridArray[4].split(",");
        HashMap<Coordinates,Integer> passengersInCoordinates = new HashMap<>();//arraylist that will have all ships
//        ArrayList<Coordinates> shipsNumberOfPassengersList = new ArrayList<>();
        HashMap<Coordinates, Integer> blackBoxCounterInCoordinates = new HashMap<>();
        for(int i=0; i<shipsCoordinatesAndPassengersString.length; i+=3){
            double x = Double.parseDouble(shipsCoordinatesAndPassengersString[i]);
            double y = Double.parseDouble(shipsCoordinatesAndPassengersString[i+1]);
            Coordinates shipCoordinates = new Coordinates(x, y);
            int numberOfPassengers = Integer.parseInt(shipsCoordinatesAndPassengersString[i+2]);
            passengersInCoordinates.put(shipCoordinates,numberOfPassengers);
            blackBoxCounterInCoordinates.put(shipCoordinates,0);
        }

        return new Grid(m,n,C,cgCoordinates,stationCoordinatesList, passengersInCoordinates,blackBoxCounterInCoordinates);
    }

    public ArrayList<SearchTreeNode> expandNode(SearchTreeNode parent, Grid grid, HashSet<State> uniqueStates){

        ArrayList<SearchTreeNode> children = new ArrayList<>();

        //Unique states hashset is passed whenever we do an action to check that the new states are not repeated
        if(canPickUp(parent, grid))
        {
            SearchTreeNode n = pickUp(parent,grid, uniqueStates);
            if(n!=null)
                children.add(n);
        }

        if(canDrop(parent, grid))
        {
            SearchTreeNode n = drop(parent, uniqueStates);
            if(n!=null)
                children.add(n);
        }

        if(canRetrieve(parent))
        {
            SearchTreeNode n = retrieve(parent, uniqueStates);
            if(n!=null)
                children.add(n);
        }

        if(canMoveRight(parent, grid))
        {
            SearchTreeNode n = move('R', parent, uniqueStates);
            if(n!=null)
                children.add(n);
        }

        if(canMoveLeft(parent))
        {
            SearchTreeNode n = move('L', parent, uniqueStates);
            if(n!=null)
                children.add(n);
        }

        if(canMoveUp(parent))
        {
            SearchTreeNode n = move('U', parent, uniqueStates);
            if(n!=null)
                children.add(n);
        }

        if(canMoveDown(parent, grid))
        {
            SearchTreeNode n = move('D', parent, uniqueStates);
            if(n!=null)
                children.add(n);
        }

        return children;
    }

    public static boolean canPickUp(SearchTreeNode parent, Grid grid)
    {
        Coordinates cgCoordinates = parent.getState().getCoastGuardLocation();
        HashMap<Coordinates, Integer> passengersInCoordinates = parent.getState().getNumberOfPassngersInCoordinates();
        int numberOfPassengersOnCG = parent.getState().getNumberOfPassengersOnCG();
        int maxPassengersOnCG = grid.getPassengersMax();

        //check that the cg location has a ship and the number of passengers on the ship is not zero
        return passengersInCoordinates.containsKey(cgCoordinates) && passengersInCoordinates.get(cgCoordinates)-1>0
                && numberOfPassengersOnCG<maxPassengersOnCG;
    }

    public static boolean canDrop(SearchTreeNode parent, Grid grid)
    {
        //First: check that coast guard has passengers
        Coordinates cgCoordinates = parent.getState().getCoastGuardLocation();
        int numberOfPassengersOnCG = parent.getState().getNumberOfPassengersOnCG();

        if(numberOfPassengersOnCG == 0)
            return false;

        //Second: check that the coast guard is at a station
        HashSet<Coordinates> station = grid.getStationsCoordinatesList();
        return station.contains(cgCoordinates);
    }

    public static boolean canRetrieve(SearchTreeNode parent)
    {
        Coordinates cgCoordinates = parent.getState().getCoastGuardLocation();
        HashMap<Coordinates, Integer> passengersInCoordinates = parent.getState().getNumberOfPassngersInCoordinates();
        HashMap<Coordinates, Integer> blackBoxCounterInCoordinates = parent.getState().getblackBoxCountInCoordinates();


        //check that the cg is in the same location as a ship, it has no passengers and the black box counter in previous state is less than 19
        if(passengersInCoordinates.containsKey(cgCoordinates) && passengersInCoordinates.get(cgCoordinates) == 0
            && blackBoxCounterInCoordinates.get(cgCoordinates)<19)
            return true;
        return false;
    }

    public static boolean canMoveUp(SearchTreeNode parent)
    {
        Coordinates cgCoordinates = parent.getState().getCoastGuardLocation();
        return cgCoordinates.getX() != 0;
    }

    public static boolean canMoveDown(SearchTreeNode parent, Grid grid)
    {
        int height = grid.getHeight();
        Coordinates cgCoordinates = parent.getState().getCoastGuardLocation();
        return !(cgCoordinates.getX() >= height-1);
    }

    public static boolean canMoveRight(SearchTreeNode parent, Grid grid)
    {
        int width = grid.getWidth();
        Coordinates cgCoordinates = parent.getState().getCoastGuardLocation();
        return !(cgCoordinates.getY() >= width-1);
    }

    public static boolean canMoveLeft(SearchTreeNode parent)
    {
        Coordinates cgCoordinates = parent.getState().getCoastGuardLocation();
        return cgCoordinates.getY() != 0;
    }

    public static SearchTreeNode pickUp(SearchTreeNode parent, Grid grid, HashSet<State> uniqueStates){
        //extract last state data
        Coordinates cgLocation = (Coordinates) parent.getState().getCoastGuardLocation().clone();//clone to avoid altering data of parent
        HashMap<Coordinates, Integer> passengers = (HashMap<Coordinates, Integer>) parent.getState().getNumberOfPassngersInCoordinates().clone();
        HashMap<Coordinates, Integer> blackBox = (HashMap<Coordinates, Integer>) parent.getState().getblackBoxCountInCoordinates().clone();
        int numberOfPassengersOnCG = parent.getState().getNumberOfPassengersOnCG();
        //the capacity of the cg now is its maximum capacity - the number of passengers that it now has
        int cgCapacity = grid.getPassengersMax() - numberOfPassengersOnCG;

        //the coordinates of the CG should now be decremented by the minimum of (cgCapacity, ship passengers) passengers
        int passengersRescued = Math.min(cgCapacity,passengers.get(cgLocation));
        passengers.put(cgLocation, passengers.get(cgLocation)-passengersRescued);

        State newState = new State(cgLocation, passengers, blackBox, numberOfPassengersOnCG + passengersRescued, parent.getState().getDeaths(), parent.getState().getRetrieved());

        //decrement all the passengers who died and increment all the black boxes count if no passengers exist
        Pair deathsAndExpBlackBoxes = newState.preformATimeStep();
        //cost represents the number of passengers who died + number of black boxes which became non-retrievable in this time step

        SearchTreeNode childNode = new SearchTreeNode(parent, parent.getActionsSequence(),
                new Pair(parent.getPathCost().deaths+deathsAndExpBlackBoxes.deaths, parent.getPathCost().expiredBlackBoxes+deathsAndExpBlackBoxes.expiredBlackBoxes )
                , newState);
        //add this action to the sequence of actions from the root till this node
        childNode.addAction("pickup");
        //check if the new state has been reached before, if so, do not return it
        if(uniqueStates.contains(newState))
            return null;
        //else add it to the hashset and return it
        uniqueStates.add(newState);
        return childNode;
    }

    public static SearchTreeNode drop(SearchTreeNode parent, HashSet<State> uniqueStates){
        //extract last state data
        Coordinates cgLocation = (Coordinates) parent.getState().getCoastGuardLocation().clone();//clone to avoid altering data of parent
        HashMap<Coordinates, Integer> passengers = (HashMap<Coordinates, Integer>) parent.getState().getNumberOfPassngersInCoordinates().clone();
        HashMap<Coordinates, Integer> blackBox = (HashMap<Coordinates, Integer>) parent.getState().getblackBoxCountInCoordinates().clone();

        //same state but with number of passengers on CG now equal to zero
        State newState = new State(cgLocation, passengers, blackBox, 0, parent.getState().getDeaths(), parent.getState().getRetrieved());

        //decrement all the passengers who died and increment all the black boxes count if no passengers exist in their ships
        Pair deathsAndExpBlackBoxes = newState.preformATimeStep();
        //cost represents the number of passengers who died + number of black boxes which became non-retrievable in this time step

        SearchTreeNode childNode = new SearchTreeNode(parent, parent.getActionsSequence(),
                new Pair(parent.getPathCost().deaths+deathsAndExpBlackBoxes.deaths, parent.getPathCost().expiredBlackBoxes+deathsAndExpBlackBoxes.expiredBlackBoxes )
                , newState);        //add this action to the sequence of actions from the root till this node
        childNode.addAction("drop");
        //check if the new state has been reached before, if so, do not return it
        if(uniqueStates.contains(newState))
            return null;
        //else add it to the hashset and return it
        uniqueStates.add(newState);
        return childNode;
    }

    public static SearchTreeNode retrieve(SearchTreeNode parent, HashSet<State> uniqueStates){
        //extract last state data
        Coordinates cgLocation = (Coordinates) parent.getState().getCoastGuardLocation().clone();//clone to avoid altering data of parent
        HashMap<Coordinates, Integer> passengers = (HashMap<Coordinates, Integer>) parent.getState().getNumberOfPassngersInCoordinates().clone();
        HashMap<Coordinates, Integer> blackBox = (HashMap<Coordinates, Integer>) parent.getState().getblackBoxCountInCoordinates().clone();
        int numberOfPassengersOnCG = parent.getState().getNumberOfPassengersOnCG();

        //remove the ship from the new state
        passengers.remove(cgLocation);
        blackBox.remove(cgLocation);

        //same state but with the ship information removed from the state
        State newState = new State(cgLocation, passengers, blackBox, numberOfPassengersOnCG, parent.getState().getDeaths(), parent.getState().getRetrieved());
        //decrement all the passengers who died and increment all the black boxes count if no passengers exist in their ships
        Pair deathsAndExpBlackBoxes = newState.preformATimeStep();
        //cost represents the number of passengers who died + number of black boxes which became non-retrievable in this time step

        //increase the number of retrieved boxes till this state
        newState.setRetrieved(newState.getRetrieved()+1);

        SearchTreeNode childNode = new SearchTreeNode(parent, parent.getActionsSequence(),
                new Pair(parent.getPathCost().deaths+deathsAndExpBlackBoxes.deaths, parent.getPathCost().expiredBlackBoxes+deathsAndExpBlackBoxes.expiredBlackBoxes )
                , newState);        //add this action to the sequence of actions from the root till this node        //add this action to the sequence of actions from the root till this node
        childNode.addAction("retrieve");
        //check if the new state has been reached before, if so, do not return it
        if(uniqueStates.contains(newState))
            return null;
        //else add it to the hashset and return it
        uniqueStates.add(newState);
        return childNode;
    }

    public static SearchTreeNode move(char direction, SearchTreeNode parent, HashSet<State> uniqueStates)
    {
        //extract last state data
        Coordinates cg = parent.getState().getCoastGuardLocation();
        HashMap<Coordinates, Integer> passengers = (HashMap<Coordinates, Integer>) parent.getState().getNumberOfPassngersInCoordinates().clone();
        HashMap<Coordinates, Integer> blackBox = (HashMap<Coordinates, Integer>) parent.getState().getblackBoxCountInCoordinates().clone();
        int numberOfPassengersOnCG = parent.getState().getNumberOfPassengersOnCG();


        State newState = new State(cg, passengers, blackBox, numberOfPassengersOnCG, parent.getState().getDeaths(), parent.getState().getRetrieved());
        Pair deathsAndExpBlackBoxes = newState.preformATimeStep();

        String action = "";
        switch (direction){
            case 'R':
                newState.setCoastGuardLocation(new Coordinates(cg.getX(), cg.getY()+1));
                action+="right";
                break;
            case 'L':
                newState.setCoastGuardLocation(new Coordinates(cg.getX(), cg.getY()-1));
                action+="left";
                break;
            case 'U':
                newState.setCoastGuardLocation(new Coordinates(cg.getX()-1, cg.getY()));
                action+="up";
                break;
            case 'D':
                newState.setCoastGuardLocation(new Coordinates(cg.getX()+1, cg.getY()));
                action+="down";
                break;
            default:
                break;
        }

        SearchTreeNode childNode = new SearchTreeNode(parent, parent.getActionsSequence(),
                new Pair(parent.getPathCost().deaths+deathsAndExpBlackBoxes.deaths, parent.getPathCost().expiredBlackBoxes+deathsAndExpBlackBoxes.expiredBlackBoxes )
                , newState);        //add this action to the sequence of actions from the root till this node        //add this action to the sequence of actions from the root till this node
        //add this action to the sequence of actions from the root till this node
        childNode.addAction(action);
        //check if the new state has been reached before, if so, do not return it
        if(uniqueStates.contains(newState))
            return null;
        //else add it to the hashset and return it
        uniqueStates.add(newState);
        return childNode;
    }

    @Override
    public boolean goalTest(State s) {
        //check that there are no ships that have any passengers
        //check that all blackboxes counter reached 1
        //check that there are no more passengers on the CG
        if(s.getPassengersInCoordinates().isEmpty() && s.getblackBoxCountInCoordinates().isEmpty() && s.getNumberOfPassengersOnCG()==0)
            return true;
        return false;
    }

    @Override
    public Pair pathCost(SearchTreeNode n) {
        //check the number of killed passengers and the number of expired black b oxes since the root node
        return n.getPathCost();
    }
}


