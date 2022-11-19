package code;

import java.util.*;

public class Main {
    public static void main(String[] args) {
        String grid0 = "2,4;50;0,0;0,0;1,1,90;";

        PriorityQueue<SearchTreeNode> nodes = new PriorityQueue<>(Comparator.comparing(SearchTreeNode::getDepth).reversed());
        //the higher the depth of the node, the higher the priority
        Grid gridObject = CoastGuard.createGridFromString(grid0);
        State initialState = new State(gridObject.getCoastGuardLocation(),gridObject.getPassengersInCoordinates(),
                gridObject.getBlackBoxCounterInCoordinates(),0, 0, 0);
        SearchTreeNode root = new SearchTreeNode(null,new ArrayList<>(),0,initialState, gridObject);
        CoastGuard p = new CoastGuard();
        int expandedNodes = 0;
        nodes.add(root);
        while(true)
        {
            if(nodes.isEmpty())
                System.out.println( "failure");

            SearchTreeNode n = (SearchTreeNode) nodes.remove();//dequeue
            expandedNodes++;

            //check if n passes goal test
            if(p.goalTest(n.getState()))
            {
                System.out.println("" +  Arrays.toString(n.getActionsSequence().toArray()).replace("[", "").replace("]", "").replace(" ", "") + ";" + n.getState().getDeaths() + ";" + n.getState().getRetrieved() + ";" + expandedNodes);
                System.out.println(n.getActionsSequence().size());
                return;
            }
            ArrayList children = p.expandNode(n,gridObject);
            nodes.addAll(children);

        }

        //BFS

//        Grid gridObject = CoastGuard.createGridFromString(grid0);
//        State initialState = new State(gridObject.getCoastGuardLocation(),gridObject.getPassengersInCoordinates(),
//                gridObject.getBlackBoxCounterInCoordinates(),0, 0, 0);
//        SearchTreeNode root = new SearchTreeNode(null,new ArrayList<>(),0,initialState, gridObject);
//        CoastGuard p = new CoastGuard();
//        int expandedNodes = 0;
//
//        PriorityQueue<SearchTreeNode> nodes = new PriorityQueue<>(Comparator.comparing(SearchTreeNode::getDepth));
//        //the less the depth of the node, the higher the priority
//
//        nodes.add(root);
//        while(true)
//        {
//            if(nodes.isEmpty())
//                System.out.println("failure");
//
//            SearchTreeNode n = (SearchTreeNode) nodes.remove();//dequeue
//            expandedNodes++;
//
//            //check if n passes goal test
//            if(p.goalTest(n.getState()))
//            {
//                System.out.println(n);
//                System.out.println("" + /*n.getActionsSequence()+*/ ";" + n.getState().getDeaths() + ";" + n.getState().getRetrieved() + ";" + expandedNodes);
//                System.out.println(n.getActionsSequence());
//                return;
//            }
//
//            ArrayList children = p.expandNode(n,gridObject);
//            nodes.addAll(children);
//
//        }


        //A*
//        int expandedNodes = 0;
//        PriorityQueue<SearchTreeNode> nodes = null;
//        int i = 1;
//        if(i==1)
//            nodes = new PriorityQueue<>(Comparator.comparing(SearchTreeNode::AStarWithH1));
//        else if (i==2) {
//            nodes = new PriorityQueue<>(Comparator.comparing(SearchTreeNode::AStarWithH1));
//        }
//
//        //the less the depth of the node, the higher the priority
//        Grid gridObject = CoastGuard.createGridFromString(grid0);
//        State initialState = new State(gridObject.getCoastGuardLocation(),gridObject.getPassengersInCoordinates(),
//                gridObject.getBlackBoxCounterInCoordinates(),0, 0, 0);
//        SearchTreeNode root = new SearchTreeNode(null,new ArrayList<>(),0,initialState, gridObject);
//        String solution;
//        CoastGuard p = new CoastGuard();
//        nodes.add(root);
//
//        while(true)
//        {
//            if(nodes.isEmpty())
//                System.out.println("failure");
//            SearchTreeNode n = (SearchTreeNode) nodes.remove();//dequeue
//
//            expandedNodes++;
//
//            //check if n passes goal test
//            if(p.goalTest(n.getState()))
//            {
//                System.out.println(/*n.getActionsSequence() +*/ ";" + n.getState().getDeaths() + ";" + n.getState().getRetrieved() + ";" + expandedNodes);
//                System.out.println(n.getActionsSequence());
//                return;
//            }
//            ArrayList children = p.expandNode(n,gridObject);
//            nodes.addAll(children);
//            System.out.println(n);
//            System.out.println(n.getActionsSequence());

        }



    public static class Ship {

        Coordinates location;
        int numberOfPassengers;
        int blackboxCount = 0;


        public Ship(Coordinates location, int numberOfPassengers) {
            // TODO Auto-generated constructor stub
            this.location = location;
            this.numberOfPassengers = numberOfPassengers;
        }

    }
}
