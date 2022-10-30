package services;

import passengerRelevants.Baggage;

public class DetermineWeight {
    public boolean checkWeight(Baggage baggage){
        return baggage.getWeight()<=23;
    }
}
