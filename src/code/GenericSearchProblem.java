package code;


import com.sun.source.tree.TreeVisitor;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.Queue;

public abstract class GenericSearchProblem {

    String[] operators;
    State initialState;
    SearchTreeNode root;
    String[] actions;

    public abstract  ArrayList<SearchTreeNode> expandNode(SearchTreeNode parent, Grid grid);

    public String solveBreadthFirstSearch(Grid grid, Boolean visualize, SearchTreeNode root) {
        int expandedNodes = 0;

        NodesQueue<SearchTreeNode> nodes = new NodesQueue();
        nodes.add(root);
        while(true)
        {
            if(nodes.isEmpty())
                return "failure";

            SearchTreeNode n = (SearchTreeNode) nodes.remove();//dequeue
            expandedNodes++;

            //check if n passes goal test
            if(goalTest(n.getState()))
                return "" + n.getActionsSequence() + ";" + n.getState().getDeaths() + ";" + n.getState().getRetrieved() + ";" + expandedNodes;
            ArrayList children = expandNode(n,grid);
            nodes.addMultiple(children);

        }
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
    public boolean goalTest(State s){
        return true;
    }

    public abstract int pathCost(SearchTreeNode n);




}
