package code;

public class Main {
    public static void main(String[] args) {
        Grid grid = CoastGuard.createGridFromString("3,4;97;1,2;0,1;3,2,65;");
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
