package code;

import java.util.ArrayList;

public class CoastGuard extends GenericSearchProblem {

    //should I create the location of the coast guard here?

    public static String genGrid(){
        String grid = "";
        return grid;
    }

    public static String solve(String grid, String strategy, Boolean visualize) {

        //create the grid
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
        int cgX = Integer.parseInt(cgCoordinatesString[0]);
        int cgY = Integer.parseInt(cgCoordinatesString[1]);
        Coordinates cgCoordinates = new Coordinates(cgX,cgY);
        //fourth semicolon has the coordinates of the stations
        String[] stationsCoordinatesString = convertedGridArray[3].split(",");
        ArrayList<Coordinates> stationCoordinatesList = new ArrayList<>();//arraylist that will have all station coordinates
        for(int i=0; i<stationsCoordinatesString.length; i+=2){
            int x = Integer.parseInt(stationsCoordinatesString[i]);
            int y = Integer.parseInt(stationsCoordinatesString[i+1]);
            stationCoordinatesList.add(new Coordinates(x,y));
        }
        //fifth semicolon has the coordinates of the ships
        String[] shipsCoordinatesAndPassengersString = convertedGridArray[4].split(",");
        ArrayList<Coordinates> shipsCoordinatesList = new ArrayList<>();//arraylist that will have all ships coordinates
        ArrayList<Coordinates> shipsNumberOfPassengersList = new ArrayList<>();
        for(int i=0; i<shipsCoordinatesAndPassengersString.length; i+=3){
            int x = Integer.parseInt(shipsCoordinatesAndPassengersString[i]);
            int y = Integer.parseInt(shipsCoordinatesAndPassengersString[i+1]);
            int numberOfPassengers = Integer.parseInt(shipsCoordinatesAndPassengersString[i+2]);
            shipsCoordinatesList.add(new Coordinates(x,y));
//            shipsNumberOfPassengersList.add()

        }






        Grid grid1 = null;
        String solution;
        switch(strategy)
        {
            case "BF":
                solution = solveBreadthFirstSearch(grid1, visualize);
                break;
            case "DF":
                solution = solveDepthFirstSearch(grid1, visualize);
                break;
            case "ID":
                solution = solveIterativeDeepeningSearch(grid1, visualize);
                break;
            case "GR":
                solution = solveGreedySearch(grid1, visualize);
                break;
            case "AS":
                solution = solveAStarSearch(grid1, visualize);
                break;
            default:
                solution = "";
        }
        return solution;
    }


    public void pickUp(){

    }

    public void drop(){

    }

    public void retrieve(){

    }

    public void move(char direction){
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
    }
}


