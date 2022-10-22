package flightRelevants;

import automatComponents.EconomyQueue;
import livingComponents.Passenger;

public class Flight {

    private FlightID flightID;
    private String boardingTime;
    private final BusinessClass businessClass=new BusinessClass();
    private final PremiumEconomyClass premiumEconomyClass=new PremiumEconomyClass();
    private final EconomyClass economyClass=new EconomyClass();

    private IATAAirportCodes source;
    private IATAAirportCodes destination;

    private Gate flightGate;

    public Flight(FlightID flightID,String boardingTime,IATAAirportCodes source,IATAAirportCodes destination,Gate gate){
        this.flightID=flightID;
        this.boardingTime=boardingTime;
        this.source=source;
        this.destination=destination;
        this.flightGate=gate;
    }
    public Flight(){

    }

    public BusinessClass getBusinessClass() {
        return businessClass;
    }

    public PremiumEconomyClass getPremiumEconomyClass() {
        return premiumEconomyClass;
    }

    public EconomyClass getEconomyClass() {
        return economyClass;
    }

    public FlightID getFlightID() {
        return flightID;
    }

    public String getBoardingTime() {
        return boardingTime;
    }

    public void setBoardingTime(String boardingTime) {
        this.boardingTime = boardingTime;
    }

    public IATAAirportCodes getSource() {
        return source;
    }

    public IATAAirportCodes getDestination() {
        return destination;
    }

    public Gate getFlightGate() {
        return flightGate;
    }

    public void printFlightSeats(){
       businessClass.printSeatIds();
       premiumEconomyClass.printSeatIds();
       economyClass.printSeatIds();
    }


}
