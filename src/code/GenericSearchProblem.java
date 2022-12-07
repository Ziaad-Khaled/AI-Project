package code;


import com.sun.management.OperatingSystemMXBean;
import com.sun.source.tree.TreeVisitor;

import java.lang.management.ManagementFactory;
import java.util.*;

public abstract class GenericSearchProblem {

    String[] operators;
    State initialState;
    SearchTreeNode root;
    String[] actions;

    public abstract  ArrayList<SearchTreeNode> expandNode(SearchTreeNode parent, Grid grid, HashSet<State> uniqueStates);

    public static void compute(){
        OperatingSystemMXBean osBean = ManagementFactory.getPlatformMXBean(OperatingSystemMXBean.class);
        Runtime runtime = Runtime.getRuntime();
        // Run the garbage collector
        runtime.gc();
        System.out.println("Cpu Usage Time in milliseconds is " + osBean.getProcessCpuTime()/1000000);
        long memory = runtime.totalMemory() - runtime.freeMemory();
        System.out.println("Used memory in bytes: " + memory);
    }
    public String  solveBreadthFirstSearch(Grid grid, Boolean visualize, SearchTreeNode root) {

       // double maxprocesscpuload=osBean.getProcessCpuLoad();

        int expandedNodes = 0;

        PriorityQueue<SearchTreeNode> nodes = new PriorityQueue<>(Comparator.comparing(SearchTreeNode::getDepth));
        //the less the depth of the node, the higher the priority
        nodes.add(root);
        HashSet<State> uniqueStates = new HashSet<>();
        uniqueStates.add(root.getState());
        while(true)
        {


            //System.out.println("Cpu Usage for the whole system in percent is " + osBean.getProcessCpuTime()/100000);
            /*if(osBean.getProcessCpuLoad()>maxprocesscpuload)
            {
                maxprocesscpuload=osBean.getProcessCpuLoad();
            }*/

            if(nodes.isEmpty())
                return "failure";

            SearchTreeNode n = (SearchTreeNode) nodes.remove();//dequeue

            expandedNodes++;

            //check if n passes goal test
            if(goalTest(n.getState()))
            {
                //System.out.println("Cpu Usage is " + osBean.getCpuLoad());
                compute();
                System.out.println("Number of expanded Nodes = " + expandedNodes);

                if(visualize)
                {
                    Visualizer.visualizePathToGoalTest(n);
                }
                System.out.println("BFS Path cost" + n.getPathCost());
                return "" + Arrays.toString(n.getActionsSequence().toArray()).replace("[", "").replace("]", "").replace(" ", "") + ";" + n.getState().getDeaths() + ";" + n.getState().getRetrieved() + ";" + expandedNodes;
            }
            ArrayList children = expandNode(n,grid, uniqueStates);
            nodes.addAll(children);

        }
    }

    public String solveDepthFirstSearch(Grid grid, Boolean visualize, SearchTreeNode root) {


        int expandedNodes = 0;
        PriorityQueue<SearchTreeNode> nodes = new PriorityQueue<>(Comparator.comparing(SearchTreeNode::getDepth).reversed());
        //the higher the depth of the node, the higher the priority
        nodes.add(root);
        HashSet<State> uniqueStates = new HashSet<>();
        uniqueStates.add(root.getState());
        while(true)
        {


            if(nodes.isEmpty())
                return "failure";

            SearchTreeNode n = (SearchTreeNode) nodes.remove();//dequeue
            expandedNodes++;

            //check if n passes goal test
            if(goalTest(n.getState()))
            {
                compute();
                System.out.println("Number of expanded Nodes = " + expandedNodes);
                if(visualize)
                {
                    Visualizer.visualizePathToGoalTest(n);
                }
                System.out.println("DFS Path cost" + n.getPathCost());
                return "" +  Arrays.toString(n.getActionsSequence().toArray()).replace("[", "").replace("]", "").replace(" ", "") + ";" + n.getState().getDeaths() + ";" + n.getState().getRetrieved() + ";" + expandedNodes;
            }
            ArrayList children = expandNode(n,grid, uniqueStates);
            nodes.addAll(children);

        }
    }
    public String  solveUniformCostSearch(Grid grid, Boolean visualize, SearchTreeNode root) {

        // double maxprocesscpuload=osBean.getProcessCpuLoad();

        int expandedNodes = 0;

        PriorityQueue<SearchTreeNode> nodes = new PriorityQueue<>(Comparator.comparing(SearchTreeNode::getPathCost));
        //the less the depth of the node, the higher the priority
        nodes.add(root);
        HashSet<State> uniqueStates = new HashSet<>();
        uniqueStates.add(root.getState());
        while(true)
        {


            //System.out.println("Cpu Usage for the whole system in percent is " + osBean.getProcessCpuTime()/100000);
            /*if(osBean.getProcessCpuLoad()>maxprocesscpuload)
            {
                maxprocesscpuload=osBean.getProcessCpuLoad();
            }*/

            if(nodes.isEmpty())
                return "failure";

            SearchTreeNode n = (SearchTreeNode) nodes.remove();//dequeue

            expandedNodes++;

            //check if n passes goal test
            if(goalTest(n.getState()))
            {
                //System.out.println("Cpu Usage is " + osBean.getCpuLoad());
                compute();
                System.out.println("Number of expanded Nodes = " + expandedNodes);

                if(visualize)
                {
                    Visualizer.visualizePathToGoalTest(n);
                }
                System.out.println("UC Path cost" + n.getPathCost());
                return "" + Arrays.toString(n.getActionsSequence().toArray()).replace("[", "").replace("]", "").replace(" ", "") + ";" + n.getState().getDeaths() + ";" + n.getState().getRetrieved() + ";" + expandedNodes;
            }
            ArrayList children = expandNode(n,grid, uniqueStates);
            nodes.addAll(children);

        }
    }

    public String solveIterativeDeepeningSearch(Grid grid, Boolean visualize, SearchTreeNode root) {



        int expandedNodes = 0;

        PriorityQueue<SearchTreeNode> nodes = new PriorityQueue<>(Comparator.comparing(SearchTreeNode::getDepth).reversed());
        //the higher the depth of the node, the higher the priority

        nodes.add(root);
        HashSet<State> uniqueStates = new HashSet<>();
        uniqueStates.add(root.getState());
        int maxLevel=0;//iterative deepening search starts with maxLevel=0 assuming that root has level=0
        while(true)
        {

            if(nodes.isEmpty())//Then we reached the max level
            {
                maxLevel++; //try higher level
                nodes.add(root);//start with queue having only the root again
                uniqueStates = new HashSet<>();
                uniqueStates.add(root.getState());
            }

            SearchTreeNode n = (SearchTreeNode) nodes.remove();//dequeue


            //check if n passes goal test
            if(goalTest(n.getState()))
            {
                compute();
                System.out.println("Number of expanded Nodes = " + expandedNodes);

                if(visualize)
                {
                    Visualizer.visualizePathToGoalTest(n);
                }
                System.out.println("IDS Path cost" + n.getPathCost());
                return "" + Arrays.toString(n.getActionsSequence().toArray()).replace("[", "").replace("]", "").replace(" ", "") + ";" + n.getState().getDeaths() + ";" + n.getState().getRetrieved() + ";" + expandedNodes;
            }
            if(n.getDepth()<maxLevel)
            {
                ArrayList children = expandNode(n,grid, uniqueStates);
                expandedNodes++;
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
        HashSet<State> uniqueStates = new HashSet<>();
        uniqueStates.add(root.getState());
        while(true)
        {

            if(nodes.isEmpty())
                return "failure";

            SearchTreeNode n = (SearchTreeNode) nodes.remove();//dequeue

            //check if n passes goal test
            if(goalTest(n.getState()))
            {
                compute();
                System.out.println("Number of expanded Nodes = " + expandedNodes);

                if(visualize)
                {
                    Visualizer.visualizePathToGoalTest(n);
                }
                if(i==1)
                    System.out.println("GR1 Path cost" + n.getPathCost());
                if(i==2)
                    System.out.println("GR2 Path cost" + n.getPathCost());

                return "" + Arrays.toString(n.getActionsSequence().toArray()).replace("[", "").replace("]", "").replace(" ", "") + ";" + n.getState().getDeaths() + ";" + n.getState().getRetrieved() + ";" + expandedNodes;
            }
            ArrayList children = expandNode(n,grid, uniqueStates);
            expandedNodes++;
            nodes.addAll(children);

        }
    }

    public String solveAStarSearch(Grid grid, Boolean visualize, SearchTreeNode root, int i) {



        int expandedNodes = 0;

        PriorityQueue<SearchTreeNode> nodes = null;
        if(i==1)
            nodes = new PriorityQueue<>(Comparator.comparing(SearchTreeNode::AStarWithH1));
        else if (i==2) {
            nodes = new PriorityQueue<>(Comparator.comparing(SearchTreeNode::AStarWithH2));
        }
        else return "i must be 1 or 2";
        //the less the depth of the node, the higher the priority

        nodes.add(root);
        HashSet<State> uniqueStates = new HashSet<>();
        uniqueStates.add(root.getState());
        while(true)
        {


            if(nodes.isEmpty())
                return "failure";

            SearchTreeNode n = (SearchTreeNode) nodes.remove();//dequeue

            //check if n passes goal test
            if(goalTest(n.getState()))
            {
                compute();
                System.out.println("Number of expanded Nodes = " + expandedNodes);

                if(visualize)
                {
                    Visualizer.visualizePathToGoalTest(n);
                }
                if(i==1)
                    System.out.println("AS1 Path cost" + n.getPathCost());
                if(i==2)
                    System.out.println("AS2 Path cost" + n.getPathCost());

                return "" + Arrays.toString(n.getActionsSequence().toArray()).replace("[", "").replace("]", "").replace(" ", "") + ";" + n.getState().getDeaths() + ";" + n.getState().getRetrieved() + ";" + expandedNodes;
            }

            ArrayList children = expandNode(n,grid, uniqueStates);
            expandedNodes++;
            nodes.addAll(children);
        }
    }
    public abstract boolean goalTest(State s);

    public abstract Pair pathCost(SearchTreeNode n);


}
