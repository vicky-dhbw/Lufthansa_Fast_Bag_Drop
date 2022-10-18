package livingComponents;

import automatComponents.Display;
import automatComponents.PassportScanner;
import automatComponents.TakeDecision;
import identityRelevants.Passport;
import passengerRelevants.Baggage;

import java.util.ArrayList;
import java.util.List;

public class Passenger extends Human{

    Passport passport;
    private List<Baggage> baggage;

    public Passenger(){
        passport=new Passport();
        baggage=new ArrayList<>();
    }

    public void executeCheckIn(PassportScanner passportScanner, Passport passport){

    }

    public void takeCheckInDecision(TakeDecision decision){

    }

    public void enterNumberOfBaggage(Display display, int numberOfBaggage){

    }

    public List<Baggage> getBaggage() {
        return baggage;
    }

}
