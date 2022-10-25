package code;


import com.sun.source.tree.TreeVisitor;

public abstract class GenericSearchProblem {

    String[] operators;
    State intialState;
    Tree<State> stateSpace;

    public static String solveBreadthFirstSearch(Grid grid, Boolean visualize) {
        String solution = "";
        return solution;
    }

    public static String solveDepthFirstSearch(Grid grid, Boolean visualize) {
        String solution = "";
        return solution;
    }

    public static String solveIterativeDeepeningSearch(Grid grid, Boolean visualize) {
        String solution = "";
        return solution;
    }

    public static String solveGreedySearch(Grid grid, Boolean visualize) {
        String solution = "";
        return solution;
    }

    public static String solveAStarSearch(Grid grid, Boolean visualize) {
        String solution = "";
        return solution;
    }

    public boolean goalTest(){
        return false;
    }

    public int pathCost(){
        return 0;
    }
}
