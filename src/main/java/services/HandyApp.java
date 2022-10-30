package services;

import identityRelevants.BoardingPass;
import livingComponents.Passenger;
import passengerRelevants.Baggage;
import passengerRelevants.BaggageTag;

import java.util.List;

public class HandyApp {
    private final HandyAppDatabase handyAppDatabase=new HandyAppDatabase();
    public BoardingPass getBoardingSpace(Passenger passenger){
        return handyAppDatabase.appDatabase1.get(passenger.getPassport().getId());
    }

    public HandyAppDatabase getHandyAppDatabase(){
        return handyAppDatabase;
    }

    public List<BaggageTag> baggageTags(Passenger passenger){
        return handyAppDatabase.getAppDatabase2().get(passenger.getPassport().getId());
    }
}
