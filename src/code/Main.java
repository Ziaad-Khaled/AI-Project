package code;

import java.util.*;
import com.sun.management.OperatingSystemMXBean;
import java.lang.management.ManagementFactory;
import java.lang.Runtime;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
public class Main {


    public static void main(String[] args) {


        HashMap<Integer, Integer> map
                = new HashMap<Integer, Integer>();

        // Inserting elements in the Map
        // using put() method

        // Custom input addition
        map.put(1, 0);
        map.put(2, 0);
        map.put(3, 0);
        map.put(4, 0);

        // Using Collections.max() method returning max
        // value in HashMap and storing in a integer
        // variable
        int maxValueInMap = (Collections.max(map.values()));
        System.out.print(maxValueInMap);
        //String grid5 = "5,5;69;3,3;0,0,0,1,1,0;0,3,78,1,2,2,1,3,14,4,4,9;";
        //String solution = CoastGuard.solve(grid5, "AS2", false);



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
