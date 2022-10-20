package services;

import flightRelevants.Flight;
import flightRelevants.IATAAirportCodes;
import identityRelevants.BoardingPass;
import identityRelevants.BookingClass;
import identityRelevants.LeftBoardingPassPart;
import identityRelevants.RightBoardingPassPart;
import livingComponents.Passenger;

import java.io.BufferedReader;
import java.io.FileReader;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.Queue;

public class Import {

    public void executeImport(Flight forFlight){
        try {
            BufferedReader bufferedReader = new BufferedReader(new FileReader("src/main/java/Data/assignment.csv")); //change path to main/java/records.csv
            String line;

            while ((line = bufferedReader.readLine()) != null) {
                Passenger passenger=new Passenger();
                passenger=createPassengers(line);
                passenger.setBoardingPass(createBoardingPass(line,forFlight));

            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
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

    public Passenger createPassengers(String line){
        String[] entries = line.split(";");
        Passenger passenger=new Passenger();
        passenger.setName(entries[3]);
        passenger.createBaggage(Integer.parseInt(entries[2]));
        passenger.getPassport().setId(entries[4]);

        return passenger;
    }

    public RightBoardingPassPart createRightPartOfBoardingPass(LeftBoardingPassPart leftBoardingPassPart,Flight forFlight){
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

    public void formQueue(Queue queue){

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

