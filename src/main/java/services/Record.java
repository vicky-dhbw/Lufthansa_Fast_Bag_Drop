package services;

import identityRelevants.BoardingPass;
import livingComponents.Passenger;
import passengerRelevants.Baggage;

import java.util.ArrayList;
import java.util.List;

public class Record {

    private final String name;
    private final int numberOfBaggage;
    private final String bookingClass;
    private final String seatId;
    private double baggageWeight;
    private final String surname;

    public Record(Passenger passenger){
        this.name=passenger.getName();
        this.numberOfBaggage=passenger.getBaggageList().size();  //checked in baggage are already in baggage list of passenger
        this.bookingClass=passenger.getPassengerBookingClass().toString();
        this.seatId=passenger.getBoardingPass().getLeftBoardingPassPart().getSeatId();
        for(Baggage baggage:passenger.getBaggageList()){
            baggageWeight+=baggage.getWeight();
        }
        List<String> nameByParts= List.of(name.split(" "));
        this.surname=nameByParts.get(1);

    }

    public String getName(){
        return this.name;
    }

    public int getNumberOfBaggage() {
        return numberOfBaggage;
    }

    public String getBookingClass() {
        return bookingClass;
    }

    public String getSeatId() {
        return seatId;
    }

    public String getSurname() {
        return surname;
    }

    public double getBaggageWeight() {
        return baggageWeight;
    }
}
