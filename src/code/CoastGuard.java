package code;

public class CoastGuard extends GenericSearchProblem {

    //should I create the location of the coast guard here?

    public static String genGrid(){
        String grid = "";
        return grid;
    }

    public static String solve(Grid grid, String strategy, Boolean visualize) {

        //create the grid

        String solution;
        switch(strategy)
        {
            case "BF":
                solution = solveBreadthFirstSearch(grid, visualize);
                break;
            case "DF":
                solution = solveDepthFirstSearch(grid, visualize);
                break;
            case "ID":
                solution = solveIterativeDeepeningSearch(grid, visualize);
                break;
            case "GR":
                solution = solveGreedySearch(grid, visualize);
                break;
            case "AS":
                solution = solveAStarSearch(grid, visualize);
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
