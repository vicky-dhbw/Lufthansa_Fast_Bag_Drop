package services;

import automatComponents.EconomyQueue;
import automatComponents.FastBagDrop;
import automatComponents.FastBagDropSection;
import flightRelevants.Flight;
import identityRelevants.BoardingPass;
import identityRelevants.BookingClass;
import identityRelevants.LeftBoardingPassPart;
import identityRelevants.RightBoardingPassPart;
import livingComponents.Passenger;

import java.text.SimpleDateFormat;
import java.util.Date;

public class CheckIn {

    public void executeCheckIn(FastBagDrop fastBagDrop,Flight flight){
        int v=0;
        while (!fastBagDrop.getLeftSection().getQueue().getPassengerQueue().isEmpty()){
            Passenger passenger=fastBagDrop.getLeftSection().getQueue().removePassenger();
            simulateCheckIn(fastBagDrop,passenger);
            v++;

        }
        System.err.println(v);
    }

    public void simulateCheckIn(FastBagDrop fastBagDrop,Passenger passenger){
        boolean ifFound=fastBagDrop.getLeftSection().getPassportScanner().scanPassport(fastBagDrop.getDatabase(), passenger.getPassport());
        fastBagDrop.getLeftSection().getDisplay().showMessage(ifFound,passenger);
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
