package code;

import java.util.HashMap;
import java.util.Iterator;

public class Main {
    public static void main(String[] args) {
        HashMap<Integer,Integer> hm = new HashMap<>();
        hm.put(1,1);
        hm.put(2,2);
        hm.put(3,3);
        for(HashMap.Entry<Integer, Integer> set : hm.entrySet())
        {
            if(set.getKey()==2)
                hm.put(2,200);
            System.out.println(set);
        }
        hm.values().removeIf(value -> value==200);
        System.out.println(hm);
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
