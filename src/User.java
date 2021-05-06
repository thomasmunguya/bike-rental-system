import java.util.*;
public class User extends Thread {
    //the number of trips the user has made
    private int tripsMade;

    //the bike stands to tour
    private List<BikeStand> bikeStands;

    //the number of the stand to start the trip from
    private int startStandNumber;

    //the number of the stand to stop the trip at
    private int arrivalStandNumber;


    //the minimum number of trips to take
    private final int MIN_TRIPS = 3;

    // the maximum number of trips to make
    private final int MAX_TRIPS = 8;

    public User() {
        this.tripsMade = 0;
    }

    public int getTripsMade() {
        return this.tripsMade;
    }

    public void setTripsMade(int tripsMade) {
        this.tripsMade = tripsMade;
    }



    public User(List<BikeStand> bikeStands, int startStandNumber, int arrivalStandNumber) {
        this.bikeStands = bikeStands;
        this.startStandNumber = startStandNumber;
        this.arrivalStandNumber = arrivalStandNumber;
        this.tripsMade = 0;
    }

    public void makeTrip() {
        Thread.currentThread().setPriority(5);
        for(int i = MIN_TRIPS; i <= MAX_TRIPS; i++) {
            if(i == arrivalStandNumber) {
                return;
            }
            try {
                Thread.currentThread().sleep(50);
            }catch(InterruptedException e) {
                e.printStackTrace();
            }
            //whenever a user makes a trip, increment the number of trips made by this user
            setTripsMade(getTripsMade() + 1);
        }
    }

    @Override
    public void run() {
        makeTrip();
    }

}
