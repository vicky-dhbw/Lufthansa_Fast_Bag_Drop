package livingComponents;

import automatComponents.Display;
import automatComponents.PassportScanner;
import automatComponents.TakeDecision;
import identityRelevants.BoardingPass;
import identityRelevants.Passport;
import passengerRelevants.Baggage;
import services.CheckIn;

import java.util.ArrayList;
import java.util.List;

public class Passenger extends Human{

    private final Passport passport;
    private final List<Baggage> baggageList;
    private BoardingPass boardingPass;

    public Passenger(){
        passport=new Passport();
        baggageList=new ArrayList<>();
        boardingPass=new BoardingPass();
    }

    public void setName(String name){
        this.name=name;
    }

    public void executeCheckIn(PassportScanner passportScanner, Passport passport){

    }

    public void placePassportAtScanner(){

    }

    public void takeCheckInDecision(TakeDecision decision){

    }

    public void enterNumberOfBaggage(Display display, int numberOfBaggage){

    }

    public BoardingPass takeBoardingPass(){
        return new BoardingPass();
    }

    public List<Baggage> getBaggage() {
        return baggageList;
    }

    public BoardingPass getBoardingPass() {
        return boardingPass;
    }

    public void setBoardingPass(BoardingPass boardingPass){
        this.boardingPass=boardingPass;
    }


    public Passport getPassport() {
        return passport;
    }

    public List<Baggage> getBaggageList() {
        return baggageList;
    }
}
