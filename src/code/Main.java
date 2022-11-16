package code;

import java.util.*;

public class Main {
    public static void main(String[] args) {
        PriorityQueue<SearchTreeNode> q = new PriorityQueue<>(Comparator.comparing(SearchTreeNode::getPathCost).reversed());
        SearchTreeNode n1 = new SearchTreeNode(null, new ArrayList<>(), 2, null);
        SearchTreeNode n2 = new SearchTreeNode(null, new ArrayList<>(), 4, null);
        SearchTreeNode n3 = new SearchTreeNode(null, new ArrayList<>(), 1, null);;
        q.add(n1);
        q.add(n2);
        q.add(n3);
        System.out.println(q.peek().getPathCost());
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
