import java.util.*;

public class BikeRental {

    private static int[] startStands = {1,1,1,1,2,2,2,3,3,3,4,4,5,5,6,6,6,7,7,8,9,10,10,10,10};
    private static int[] arrivalStands = {1,2,2,3,3,3,4,4,4,5,5,5,6,6,6,6,7,7,8,8,8,8,9,9,10,10};
    private List<BikeStand> bikeStands;

    public BikeRental(List<BikeStand> bikeStands) {
        this.bikeStands = bikeStands;
    }

    public List<BikeStand> getBikeStands() {
        return bikeStands;
    }

    public void setBikeStands(List<BikeStand> bikeStands) {
        this.bikeStands = bikeStands;
    }

    private int getRandStand (int[] source) {
        return source[(int)(Math.random() * source.length)];
    }

    @Override
    public String toString() {
        StringBuilder systemState = new StringBuilder();
        systemState.append("Total number of bike stands: " + bikeStands.size() + "\n");
        for(int i = 0; i < bikeStands.size(); i++) {
            systemState.append("Bike stand " + i + " has " + bikeStands.get(i).getBikes().size() + " bikes.\n");
        }
        return systemState.toString();
    }

    public static void main(String[] args) {
        List<BikeStand> currentBikeStands = new ArrayList<>();
        //add 10 bikes to each stand
        for(int i = 0; i < 10; i++) {
            currentBikeStands.forEach(bikeStand ->  {
                bikeStand.addBike(new Bike());
            });
        }

        BikeRental bikeRental = new BikeRental(currentBikeStands);

        List<User> users = new ArrayList<>();

        for(int i = 0; i < 10; i++) {
            users.add(new User(currentBikeStands,
                    bikeRental.getRandStand(BikeRental.startStands),
                    bikeRental.getRandStand(BikeRental.arrivalStands)));
        }

        users.forEach(user -> user.run());

        Truck truck = new Truck(currentBikeStands);
        //set the truck to have the highest priority
        truck.setPriority(Thread.MAX_PRIORITY);

        truck.run();

        System.out.println("Bikes on Stand Number 1: " + currentBikeStands.get(0).getBikes());

        for(int i = 0; i < users.size(); i++) {
            System.out.println("Trips made by user " + (i + 1)  + ": " + users.get(i).getTripsMade());
        }

    }

}
