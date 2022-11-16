package code;


import com.sun.source.tree.TreeVisitor;

import java.util.*;

public abstract class GenericSearchProblem {

    String[] operators;
    State initialState;
    SearchTreeNode root;
    String[] actions;

    public abstract  ArrayList<SearchTreeNode> expandNode(SearchTreeNode parent, Grid grid);

    public String solveBreadthFirstSearch(Grid grid, Boolean visualize, SearchTreeNode root) {
        int expandedNodes = 0;

        PriorityQueue<SearchTreeNode> nodes = new PriorityQueue<>(Comparator.comparing(SearchTreeNode::getDepth));
        //the less the depth of the node, the higher the priority

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
            nodes.addAll(children);

        }
    }

    public String solveDepthFirstSearch(Grid grid, Boolean visualize, SearchTreeNode root) {
        int expandedNodes = 0;

        PriorityQueue<SearchTreeNode> nodes = new PriorityQueue<>(Comparator.comparing(SearchTreeNode::getDepth).reversed());
        //the higher the depth of the node, the higher the priority

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
            nodes.addAll(children);

        }
    }

    public String solveIterativeDeepeningSearch(Grid grid, Boolean visualize, SearchTreeNode root) {
        int expandedNodes = 0;

        PriorityQueue<SearchTreeNode> nodes = new PriorityQueue<>(Comparator.comparing(SearchTreeNode::getDepth).reversed());
        //the higher the depth of the node, the higher the priority

        nodes.add(root);

        int maxLevel=0;//iterative deepening search starts with maxLevel=0 assuming that root has level=0
        while(true)
        {

            if(nodes.isEmpty())//Then we reached the max level
            {
                maxLevel++; //try higher level
                nodes.add(root);//start with queue having only the root again
            }

            SearchTreeNode n = (SearchTreeNode) nodes.remove();//dequeue

            expandedNodes++;

            //check if n passes goal test
            if(goalTest(n.getState()))
                return "" + n.getActionsSequence() + ";" + n.getState().getDeaths() + ";" + n.getState().getRetrieved() + ";" + expandedNodes;
            if(n.getDepth()<maxLevel)
            {
                ArrayList children = expandNode(n,grid);
                nodes.addAll(children);
            }

        }
    }

    public String solveGreedySearch(Grid grid, Boolean visualize, SearchTreeNode root, int i) {
        int expandedNodes = 0;

        PriorityQueue<SearchTreeNode> nodes = null;
        if(i==1)
            nodes = new PriorityQueue<>(Comparator.comparing(SearchTreeNode::heuristic1));
        else if (i==2) {
            nodes = new PriorityQueue<>(Comparator.comparing(SearchTreeNode::heuristic2));
        }
        else return "i must be 1 or 2";
        //the less the depth of the node, the higher the priority

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
            nodes.addAll(children);

        }
    }

    public String solveAStarSearch(Grid grid, Boolean visualize, SearchTreeNode root, int i) {
        int expandedNodes = 0;

        PriorityQueue<SearchTreeNode> nodes = null;
        if(i==1)
            nodes = new PriorityQueue<>(Comparator.comparing(SearchTreeNode::AStarWithH1));
        else if (i==2) {
            nodes = new PriorityQueue<>(Comparator.comparing(SearchTreeNode::AStarWithH1));
        }
        else return "i must be 1 or 2";
        //the less the depth of the node, the higher the priority

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
            nodes.addAll(children);
        }
    }
    public abstract boolean goalTest(State s);

    public abstract int pathCost(SearchTreeNode n);




}
