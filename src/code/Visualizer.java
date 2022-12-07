package code;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.LinkedList;

public class Visualizer {

    private static void visualizeGrid(SearchTreeNode node) {
        ArrayList<Integer> passengersInCoordinates = new ArrayList<>();
        ArrayList<Integer> blackBoxCounterInCoordinates = new ArrayList<>();
        HashSet<Coordinates> stations = node.getGrid().getStationsCoordinatesList();

        visualizeFirstRow(node.getGrid().getWidth());


        for(int i=0;i<node.getGrid().getHeight();i++)
        {
            System.out.print((i) + ": ");
            for(int j=0;j< node.getGrid().getWidth();j++)
            {
                Coordinates cellCoordinate = new Coordinates(i,j);
                Integer passengerCount = node.getState().getPassengersInCoordinates().get(cellCoordinate);
                Integer blackBoxCount = node.getState().getblackBoxCountInCoordinates().get(cellCoordinate);
                boolean coastGuardLocation = false;
                if(node.getState().getCoastGuardLocation().equals(cellCoordinate))
                {
                    coastGuardLocation = true;
                }

                if(passengerCount != null)
                {
                    if(passengerCount < 1)
                    {
                        blackBoxCounterInCoordinates.add(blackBoxCount);
                        if(coastGuardLocation)
                        {
                            System.out.print("cg,bb" + blackBoxCounterInCoordinates.size() +  "| ");
                            coastGuardLocation = false;
                        }
                        else
                            System.out.print("bb" + blackBoxCounterInCoordinates.size() +  "   | ");
                    }
                    else
                    {
                        passengersInCoordinates.add(passengerCount);
                        if(coastGuardLocation)
                        {
                            System.out.print("cg,sh" + passengersInCoordinates.size() +  "| ");
                            coastGuardLocation = false;
                        }
                        else
                            System.out.print(" sh" + passengersInCoordinates.size() +  "  | ");
                    }
                }
                else
                {
                    if(stations.contains(cellCoordinate))
                    {
                        if(coastGuardLocation)
                        {
                            System.out.print("cg,s| ");
                            coastGuardLocation = false;
                        }
                        else
                            System.out.print("  s   | ");
                    }
                    else
                    {
                        if(coastGuardLocation)
                            System.out.print(" cg   | ");
                        else
                            System.out.print("      | ");
                    }

                }
            }
            System.out.println();
        }

        for(int i=0;i<passengersInCoordinates.size();i++)
        {
            System.out.println("Number of passengers at ship " + (i) + " is: " + passengersInCoordinates.get(i));
        }

        for(int i=0;i<blackBoxCounterInCoordinates.size();i++)
        {
            System.out.println("The counter at blackbox " + (i) + " is: " + blackBoxCounterInCoordinates.get(i));
        }
        System.out.println("Maximum number on coast guard: " + node.getGrid().getPassengersMax());
    }

    private static void visualizeFirstRow(int nodeWidth) {
        System.out.print("   ");
        for(int i=0;i<nodeWidth;i++)
        {
            System.out.print("  "+ i+ "   | ");
        }
        System.out.println();
    }

    private static void visualizeState(State state) {
        System.out.println("Number of passengers on coast guard is: " + state.getNumberOfPassengersOnCG());
        System.out.println("Number of deaths is: " + state.getDeaths());
        System.out.println("Number of retrievals is: " + state.getRetrieved());
    }

    public static void visualizeNode(SearchTreeNode node)
    {
        visualizeGrid(node);
        visualizeState(node.getState());
    }

    public static void visualizePathToGoalTest(SearchTreeNode node) {
        LinkedList<SearchTreeNode> path = new LinkedList<>();
        while(node != null)
        {
            path.addFirst(node);
            node = node.getParent();
        }
        int counter = 1;
        while(!path.isEmpty())
        {
            System.out.println("Node Number: " + counter);
            visualizeNode(path.getFirst());
            path.removeFirst();
            System.out.println("********************************************************");
            counter++;
        }
    }
}
