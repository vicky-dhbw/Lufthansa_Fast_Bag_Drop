package services;

import automatComponents.Database;
import automatComponents.EconomyQueue;
import automatComponents.FastBagDrop;
import automatComponents.FastBagDropSection;
import flightRelevants.Flight;
import flightRelevants.FlightID;
import flightRelevants.IATAAirportCodes;
import identityRelevants.BoardingPass;
import identityRelevants.BookingClass;
import identityRelevants.LeftBoardingPassPart;
import identityRelevants.RightBoardingPassPart;
import livingComponents.Passenger;

import javax.xml.crypto.Data;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

public class CheckIn {

    public void executeCheckIn(FastBagDrop fastBagDrop,Flight flight){
        while (!(fastBagDrop.getLeftSection().getBusinessQueue().getBusinessQueue().isEmpty())){
            Passenger passenger=fastBagDrop.getLeftSection().getBusinessQueue().removePassenger();
            simulateCheckInForBusinessQueue(fastBagDrop,passenger);
        }
        while (!(fastBagDrop.getRightSection().getEconomyQueue().getEconomyQueue().isEmpty())){
            Passenger passenger_=fastBagDrop.getRightSection().getEconomyQueue().removePassenger();
            simulateCheckInForEconomyQueue(fastBagDrop,passenger_);
        }
    }

    public void simulateCheckInForBusinessQueue(FastBagDrop fastBagDrop,Passenger passenger){
        boolean ifFound=fastBagDrop.getLeftSection().getPassportScanner().scanPassport(fastBagDrop.getDatabase(), passenger.getPassport());
        fastBagDrop.getLeftSection().getDisplay().showMessage(ifFound,passenger);
    }

    public void simulateCheckInForEconomyQueue(FastBagDrop fastBagDrop,Passenger passenger){
        boolean ifFound=fastBagDrop.getRightSection().getPassportScanner().scanPassport(fastBagDrop.getDatabase(), passenger.getPassport());
        fastBagDrop.getRightSection().getDisplay().showMessage(ifFound,passenger);
    }

    public BoardingPass createBoardingPass(String line, Flight forFlight){
        String[] entries = line.split(";");
        BoardingPass boardingPass=new BoardingPass();
        boardingPass.getLeftBoardingPassPart().setName(entries[3]);
        boardingPass.getLeftBoardingPassPart().setBookingClass(createBookingClass(entries[1]));
        boardingPass.getLeftBoardingPassPart().setDestination(forFlight.getDestination());
        boardingPass.getLeftBoardingPassPart().setSource(forFlight.getSource());
        boardingPass.getLeftBoardingPassPart().setId(entries[5]);
        boardingPass.getLeftBoardingPassPart().setFlightID(forFlight.getFlightID());
        Date date = new Date();
        SimpleDateFormat formatter = new SimpleDateFormat("dd.MMM");
        boardingPass.getLeftBoardingPassPart().setDate(formatter.format(date));

        boardingPass.setRightBoardingPassPart(createRightPartOfBoardingPass(boardingPass.getLeftBoardingPassPart(),forFlight));


        return boardingPass;
    }

    public void generateBoardingPass(Database database,Passenger passenger,Flight forFlight){
        //creates BoardingPass for Passenger from the Database created in Import
        List<Object> passengerFlightDetails=database.getListForKey(passenger.getPassport().getId());
        passenger.getBoardingPass().getLeftBoardingPassPart().setFlightID((FlightID) passengerFlightDetails.get(0));
        passenger.getBoardingPass().getLeftBoardingPassPart().setSource((IATAAirportCodes) passengerFlightDetails.get(1));
        passenger.getBoardingPass().getLeftBoardingPassPart().setDestination((IATAAirportCodes) passengerFlightDetails.get(2));
        passenger.getBoardingPass().getLeftBoardingPassPart().setId((String) passengerFlightDetails.get(5));
        passenger.getBoardingPass().getLeftBoardingPassPart().setBookingClass((BookingClass) passengerFlightDetails.get(6));
        passenger.getBoardingPass().getLeftBoardingPassPart().setName((String) passengerFlightDetails.get(7));
        Date date = new Date();
        SimpleDateFormat formatter = new SimpleDateFormat("dd.MMM");
        passenger.getBoardingPass().getLeftBoardingPassPart().setDate(formatter.format(date));
        passenger.getBoardingPass().setRightBoardingPassPart(createRightPartOfBoardingPass(passenger.getBoardingPass().getLeftBoardingPassPart(), forFlight));

    }
    public RightBoardingPassPart createRightPartOfBoardingPass(LeftBoardingPassPart leftBoardingPassPart, Flight forFlight){
        RightBoardingPassPart rightBoardingPassPart=new RightBoardingPassPart();
        rightBoardingPassPart.setName(leftBoardingPassPart.getName());
        rightBoardingPassPart.setBookingClass(leftBoardingPassPart.getBookingClass());
        rightBoardingPassPart.setDestination(leftBoardingPassPart.getDestination());
        rightBoardingPassPart.setSource(leftBoardingPassPart.getSource());
        rightBoardingPassPart.setId(leftBoardingPassPart.getId());
        rightBoardingPassPart.setFlightID(leftBoardingPassPart.getFlightID());
        rightBoardingPassPart.setDate(leftBoardingPassPart.getDate());
        rightBoardingPassPart.setGate(forFlight.getFlightGate());
        rightBoardingPassPart.setBoardingTime(forFlight.getBoardingTime());

        return rightBoardingPassPart;
    }

    public BookingClass createBookingClass(String bookingClass){
        if(bookingClass.equals("Business")){
            return BookingClass.B;
        }
        if(bookingClass.equals("Premium Economy")){
            return BookingClass.P;
        }
        if(bookingClass.equals("Economy")){
            return BookingClass.E;
        }

        return null;
    }
}
