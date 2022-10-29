package code;


import com.sun.source.tree.TreeVisitor;

import java.util.ArrayList;

public abstract class GenericSearchProblem {

    String[] operators;
    State initialState;
    SearchTreeNode root;
    String[] actions;

    public abstract ArrayList<SearchTreeNode> expandNode(SearchTreeNode parent, Grid grid);

    public static String solveBreadthFirstSearch(Grid grid, Boolean visualize, SearchTreeNode root) {
        String solution = new String();
        return solution;
    }

    public static String solveDepthFirstSearch(Grid grid, Boolean visualize, SearchTreeNode root) {
        String solution = "";
        return solution;
    }

    public static String solveIterativeDeepeningSearch(Grid grid, Boolean visualize, SearchTreeNode root) {
        String solution = "";
        return solution;
    }

    public static String solveGreedySearch(Grid grid, Boolean visualize, SearchTreeNode root) {
        String solution = "";
        return solution;
    }

    public static String solveAStarSearch(Grid grid, Boolean visualize, SearchTreeNode root) {
        String solution = "";
        return solution;
    }
    public abstract boolean goalTest(State s);

    public abstract int pathCost(SearchTreeNode n);




}
