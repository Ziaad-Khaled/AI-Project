package code;

import java.util.Arrays;

public class Main {
    public static void main(String[] args) {
        Grid grid = CoastGuard.createGridFromString("3,4;97;1,2;0,1;3,2,65;");
        System.out.println(grid.getStationsCoordinatesList().get(0).x);
        System.out.println(grid.getStationsCoordinatesList().get(0).y);


    }
}
