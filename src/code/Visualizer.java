package code;

import java.util.ArrayList;

public class Visualizer {

    private static void visualizeGrid(SearchTreeNode node) {
        ArrayList<Integer> passengersInCoordinates = new ArrayList<>();
        ArrayList<Integer> blackBoxCounterInCoordinates = new ArrayList<>();
        for(int i=0;i<node.getGrid().getHeight();i++)
        {
            System.out.print((i+1) + ": ");
            for(int j=0;j< node.getGrid().getWidth();j++)
            {
                Coordinates cellCoordinate = new Coordinates(i,j);
                Integer passengerCount = node.getState().getPassengersInCoordinates().get(cellCoordinate);
                Integer blackBoxCount = node.getState().getblackBoxCountInCoordinates().get(cellCoordinate);
                if(node.getState().getCoastGuardLocation().equals(cellCoordinate))
                {
                    System.out.print("cg  | ");
                }
                else if(passengerCount != null)
                {
                    if(passengerCount < 1)
                    {
                        blackBoxCounterInCoordinates.add(blackBoxCount);
                        System.out.print("bb" + blackBoxCounterInCoordinates.size() +  " | ");
                    }
                    else
                    {
                        passengersInCoordinates.add(passengerCount);
                        System.out.print("sh" + passengersInCoordinates.size()+   " | ");
                    }
                }
                else
                {
                    System.out.print("    | ");
                }
            }
            System.out.println();
        }

        for(int i=0;i<passengersInCoordinates.size();i++)
        {
            System.out.println("Number of passengers at ship " + (i+1) + " is: " + passengersInCoordinates.get(i));
        }

        for(int i=0;i<blackBoxCounterInCoordinates.size();i++)
        {
            System.out.println("The counter at blackbox " + (i+1) + " is: " + blackBoxCounterInCoordinates.get(i));
        }
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
}
