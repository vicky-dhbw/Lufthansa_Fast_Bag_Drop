package livingComponents;

import automatComponents.Display;
import automatComponents.PassportScanner;
import automatComponents.TakeDecision;
import identityRelevants.BoardingPass;
import identityRelevants.Passport;
import passengerRelevants.Baggage;

import java.util.ArrayList;
import java.util.List;

public class Passenger extends Human{

    private final Passport passport;
    private final List<Baggage> baggage;
    private BoardingPass boardingPass;

    public Passenger(){
        passport=new Passport();
        baggage=new ArrayList<>();
        boardingPass=new BoardingPass();
    }

    public void setName(String name){
        this.name=name;
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

    public BoardingPass getBoardingPass() {
        return boardingPass;
    }

    public void setBoardingPass(BoardingPass boardingPass){
        this.boardingPass=boardingPass;
    }

    public void createBaggage(int numberOfBaggage){
        for(int i=0;i<numberOfBaggage;i++){
            Baggage baggage_=new Baggage();
            baggage.add(baggage_);
        }
    }

    public Passport getPassport() {
        return passport;
    }
}
