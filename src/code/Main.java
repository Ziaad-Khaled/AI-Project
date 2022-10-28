package code;

import java.util.Arrays;
import java.util.Random;

public class Main {
    public static void main(String[] args) {
        Random rand = new Random(); //instance of random class
        String s = CoastGuard.GenGrid();
        System.out.println(s);
        Grid g = CoastGuard.createGridFromString(s);
        System.out.println(g.getCoastGuardLocation());



    }
}
