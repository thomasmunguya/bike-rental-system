import java.util.*;

public class BikeStand {

    List<Bike> bikes;
    private final int STAND_CAPACITY = 10;

    public List<Bike> getBikes() {
        return bikes;
    }

    public int getStandCapacity() {
        return STAND_CAPACITY;
    }

    public synchronized void addBike(Bike bike) {
        while(bikes.size() == STAND_CAPACITY) {
            try {
                wait();
            }catch (InterruptedException e) {

            }
        }
        bikes.add(bike);
        notify();
    }

    public synchronized Bike removeBike() {
       while(bikes.isEmpty()) {
           try {
               wait();
           }catch (InterruptedException e) {

           }

       }
        Bike bike = bikes.remove(0);
        //whenever a bike is removed from a stand, increment the number of times it is used
        bike.setTimesUsed(bike.getTimesUsed() + 1);
        notify();
        return bike;

    }

}
