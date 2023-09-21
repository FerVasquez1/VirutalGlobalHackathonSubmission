package UberProject;

public class main {
    public static void main(String[] args) {

        City Uberland = new City(7); //Initialize class City

        Uberland.add(5, 17, 0);          //
        Uberland.add(1, 2, 0);           //
        Uberland.add(2, 2, 1);           //
        Uberland.add(3, 4, 2);           // Each add creates a new Road which includes the distances
        Uberland.add(4, 8, 3);           // from road to road
        Uberland.add(5, 7, 3);           //
        Uberland.add(6, 5, 5);           //

        System.out.println(Uberland.findOptimalPickupLocation());          // Runs unique algorithm to determine the most efficient pickup spot 
                                                                           // location

    }
}