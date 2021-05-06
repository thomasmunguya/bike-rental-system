import java.util.*;
public class Truck extends Thread {
    //the bikes that the truck has loaded
    private List<Bike> bikesLoaded;

    //the truck load capacity
    private final int TRUCK_CAPACITY = 10;

    public int getTripsMade() {
        return tripsMade;
    }

    public void setTripsMade(int tripsMade) {
        this.tripsMade = tripsMade;
    }

    //the number of trips made by the truck
    private int tripsMade;

    //the bike stands to balance
    private List<BikeStand> bikeStands;

    public Truck() {
        this.tripsMade = 0;
        bikesLoaded = new ArrayList<>();
    }

    public Truck(List<BikeStand> bikeStands) {
        this.tripsMade = 0;
        bikesLoaded = new ArrayList<>();
        this.bikeStands = bikeStands;
    }

    public List<BikeStand> getBikeStands() {
        return bikeStands;
    }

    public void setBikeStands(List<BikeStand> bikeStands) {
        this.bikeStands = bikeStands;
    }

    /**
     * Balances a bike stand
     * @param bikeStand the bike stand to balance
     * @return the balanced bike stand
     */
    public BikeStand balance(BikeStand bikeStand) {
        //keep adding bikes to the stand until the stand is half full
        while(bikeStand.getBikes().size() < (0.5 * bikeStand.getStandCapacity())) {
            if(!bikesLoaded.isEmpty())
                bikeStand.getBikes().add(bikesLoaded.remove(0));
        }

        //keep removing bikes from the stand until the bike stand is half empty
        while(bikeStand.getBikes().size() > (0.5 * bikeStand.getStandCapacity())) {
            if(bikesLoaded.size() < TRUCK_CAPACITY) {
                bikesLoaded.add(bikeStand.getBikes().remove(0));
            }

        }
        try {
            wait(25);
        }catch (InterruptedException e) {
            e.printStackTrace();
        }
        return bikeStand;
    }

    public void run() {
        while(true) {
            bikeStands.forEach(bikeStand -> {
                balance(bikeStand);
            });
            //whenever a truck makes a trip, increment the number of trips made
            setTripsMade(getTripsMade() + 1);
        }
    }

}
