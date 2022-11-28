package code;

import java.util.*;
import com.sun.management.OperatingSystemMXBean;
import java.lang.management.ManagementFactory;
import java.lang.Runtime;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
public class Main {


    public static void main(String[] args) {



        String grid5 = "5,5;69;3,3;0,0,0,1,1,0;0,3,78,1,2,2,1,3,14,4,4,9;";
        String solution = CoastGuard.solve(grid5, "BF", false);


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
