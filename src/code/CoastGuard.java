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

        //create the grid

        Grid gridObject = createGridFromString(grid);
        State initialState = new State(gridObject.getCoastGuardLocation(),gridObject.getPassengersInCoordinates(),
                gridObject.getBlackBoxCounterInCoordinates());
        SearchTreeNode root = new SearchTreeNode(null,0,0,initialState);
        String solution;
        switch(strategy)
        {
            case "BF":
                solution = solveBreadthFirstSearch(gridObject, visualize, root);
                break;
            case "DF":
                solution = solveDepthFirstSearch(gridObject, visualize, root);
                break;
            case "ID":
                solution = solveIterativeDeepeningSearch(gridObject, visualize, root);
                break;
            case "GR":
                solution = solveGreedySearch(gridObject, visualize, root);
                break;
            case "AS":
                solution = solveAStarSearch(gridObject, visualize, root);
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
        ArrayList<Coordinates> stationCoordinatesList = new ArrayList<>();//arraylist that will have all station coordinates
        for(int i=0; i<stationsCoordinatesString.length; i+=2){
            double x = Double.parseDouble(stationsCoordinatesString[i]);
            double y = Double.parseDouble(stationsCoordinatesString[i+1]);
            stationCoordinatesList.add(new Coordinates(x,y));
        }
        //fifth semicolon has the coordinates of the ships
        String[] shipsCoordinatesAndPassengersString = convertedGridArray[4].split(",");
        HashMap<Coordinates,Integer> passengersInCoordinates = new HashMap<>();//arraylist that will have all ships
//        ArrayList<Coordinates> shipsNumberOfPassengersList = new ArrayList<>();
        for(int i=0; i<shipsCoordinatesAndPassengersString.length; i+=3){
            double x = Double.parseDouble(shipsCoordinatesAndPassengersString[i]);
            double y = Double.parseDouble(shipsCoordinatesAndPassengersString[i+1]);
            Coordinates shipCoordinates = new Coordinates(x, y);
            int numberOfPassengers = Integer.parseInt(shipsCoordinatesAndPassengersString[i+2]);
            passengersInCoordinates.put(shipCoordinates,numberOfPassengers);
        }

        HashMap<Coordinates, Integer> blackBoxCounterInCoordinates = new HashMap<>();
        return new Grid(m,n,C,cgCoordinates,stationCoordinatesList, passengersInCoordinates,blackBoxCounterInCoordinates);
    }

    public ArrayList<SearchTreeNode> expandNode(SearchTreeNode parent, Grid grid){

        ArrayList<SearchTreeNode> children = new ArrayList<>();

        children.add(pickUp(parent));
        children.add(drop(parent) );
        children.add(retrieve(parent));
        children.add(move('R', parent));
        children.add(move('L', parent));
        children.add(move('U', parent));
        children.add(move('D', parent));

        return null;
    }

    public static boolean canPickUp(SearchTreeNode parent)
    {
        Coordinates cgCoordinates = parent.getState().getCoastGuardLocation();
        HashMap<Coordinates, Integer> passengersInCoordinates = parent.getState().getNumberOfPassngersInCoordinates();
        if(passengersInCoordinates.containsKey(cgCoordinates))
            return true;
        return false;
    }
    public static boolean canDrop(SearchTreeNode parent, Grid grid)
    {
        //first: check that coast guard has passengers
        Coordinates cgCoordinates = parent.getState().getCoastGuardLocation();
        int numberOfPassengersOnCG = parent.getState().getNumberOfPassengersOnCG();
        boolean cgHasPassengers = false;
        if(numberOfPassengersOnCG>0)
            cgHasPassengers = true;



        return false;

    }
    public static boolean canRetrieve(SearchTreeNode parent)
    {
        return false;

    }
    public static boolean canMoveUp(SearchTreeNode parent)
    {
        return false;

    }
    public static boolean canMoveDown(SearchTreeNode parent)
    {
        return false;

    }
    public static boolean canMoveRight(SearchTreeNode parent)
    {
        return false;

    }
    public static boolean canMoveLeft(SearchTreeNode parent)
    {
        return false;

    }

    public static SearchTreeNode pickUp(SearchTreeNode parent){
        return null;
    }

    public static SearchTreeNode drop(SearchTreeNode parent){
        return null;

    }

    public static SearchTreeNode retrieve(SearchTreeNode parent){
        return null;

    }

    public static SearchTreeNode move(char direction, SearchTreeNode parent){
        switch (direction){
            case 'R':
                //move coast guard
                break;
            case 'L':
                //move coast guard
                break;
            case 'U':
                //move coast guard
                break;
            case 'D':
                //move coast guard
                break;
            default:
                break;
        }
        return null;

    }

    @Override
    public boolean goalTest(State s) {
        //check that all the number of passengers in all locations = 0
        //check that all blackboxes counter reached 1
        return false;
    }

    @Override
    public int pathCost(SearchTreeNode n) {
        //check the number of killed passengers and the number of expired black boxes since the root node
        return n.getPathCost();
    }
}


