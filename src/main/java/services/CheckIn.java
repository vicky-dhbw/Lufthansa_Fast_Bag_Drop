package services;

import automatComponents.Database;
import automatComponents.EconomyQueue;
import automatComponents.FastBagDrop;
import automatComponents.FastBagDropSection;
import flightRelevants.*;
import identityRelevants.BoardingPass;
import identityRelevants.BookingClass;
import identityRelevants.LeftBoardingPassPart;
import identityRelevants.RightBoardingPassPart;
import livingComponents.Passenger;
import passengerRelevants.Baggage;

import javax.xml.crypto.Data;
import java.io.BufferedReader;
import java.io.FileReader;
import java.text.SimpleDateFormat;
import java.util.*;

public class CheckIn {
    Queue<String> contents;
    public CheckIn(){
        contents=new LinkedList<>();
        getContentsToList();
    }

    public void executeCheckIn(FastBagDrop fastBagDrop,Flight flight){
        while (!(fastBagDrop.getLeftSection().getBusinessQueue().getBusinessQueue().isEmpty())){
            Passenger passenger=fastBagDrop.getLeftSection().getBusinessQueue().removePassenger();
            simulateCheckInForBusinessQueue(fastBagDrop,passenger,flight);
            //generateBoardingPass(fastBagDrop.getDatabase(), passenger,flight);
            //fastBagDrop.getLeftSection().getDisplay().displayBoardingPass(passenger);
        }
        while (!(fastBagDrop.getRightSection().getEconomyQueue().getEconomyQueue().isEmpty())){
            Passenger passenger_=fastBagDrop.getRightSection().getEconomyQueue().removePassenger();
            simulateCheckInForEconomyQueue(fastBagDrop,passenger_,flight);
            //generateBoardingPass(fastBagDrop.getDatabase(), passenger_,flight);
            //fastBagDrop.getRightSection().getDisplay().displayBoardingPass(passenger_);
        }
    }

    public void simulateCheckInForBusinessQueue(FastBagDrop fastBagDrop,Passenger passenger,Flight flight){
        //the scanner uses the machine's database to find ticket id of the passenger-> 1 yes found, 0 not found
        boolean ifFound=fastBagDrop.getLeftSection().getPassportScanner().scanPassport(fastBagDrop.getDatabase(), passenger.getPassport());
        //the display shows message whether ticket is found or not
        fastBagDrop.getLeftSection().getDisplay().showMessage(ifFound,passenger);
        if(ifFound){
            //the database uses the static FreeSeatSearcher method to search a non-reserved seat
            Seat seat=fastBagDrop.getDatabase().searchForFreeSeat(flight,passenger);
            fastBagDrop.getLeftSection().getDisplay().showTicketRelevantInformation(fastBagDrop.getDatabase(), passenger);
            boolean checkIn=fastBagDrop.getLeftSection().getDisplay().offerChoiceAndAcceptCheckInDecision(flight,passenger);
            if(checkIn){
                fastBagDrop.getLeftSection().getDisplay().showCheckInMessage();
                int numberOfBaggage=fastBagDrop.getLeftSection().getDisplay().getNumberOfBaggage(passenger);
                assignBaggageToPassenger(passenger,numberOfBaggage);
                FlightSeatStatusUpdater.reserveSeat(seat,flight);
                generateBoardingPass(fastBagDrop.getDatabase(), passenger,flight);
            }
            else{
                fastBagDrop.getLeftSection().getDisplay().showCancellationMessage();
            }

        }

    }

    public void simulateCheckInForEconomyQueue(FastBagDrop fastBagDrop,Passenger passenger,Flight flight){
        boolean ifFound=fastBagDrop.getRightSection().getPassportScanner().scanPassport(fastBagDrop.getDatabase(), passenger.getPassport());
        fastBagDrop.getRightSection().getDisplay().showMessage(ifFound,passenger);
        if(ifFound){
            //the database uses the static FreeSeatSearcher method to search a non-reserved seat
            Seat seat=fastBagDrop.getDatabase().searchForFreeSeat(flight,passenger);
            fastBagDrop.getRightSection().getDisplay().showTicketRelevantInformation(fastBagDrop.getDatabase(), passenger);
            boolean checkIn=fastBagDrop.getRightSection().getDisplay().offerChoiceAndAcceptCheckInDecision(flight,passenger);
            if(checkIn){
                fastBagDrop.getRightSection().getDisplay().showCheckInMessage();
                int numberOfBaggage=fastBagDrop.getLeftSection().getDisplay().getNumberOfBaggage(passenger);
                assignBaggageToPassenger(passenger,numberOfBaggage);
                FlightSeatStatusUpdater.reserveSeat(seat,flight);
                generateBoardingPass(fastBagDrop.getDatabase(), passenger,flight);
            }
            else{
                fastBagDrop.getRightSection().getDisplay().showCancellationMessage();
            }
        }
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
        //passenger.getBoardingPass().getLeftBoardingPassPart().setSequence((Integer) passengerFlightDetails.get(8));
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
        //rightBoardingPassPart.setSequence(leftBoardingPassPart.getSequence());

        return rightBoardingPassPart;
    }
    public void assignBaggageToPassenger(Passenger passenger,int numberOfBaggage){
        for(int i=0;i<numberOfBaggage;i++){
            Baggage baggage=new Baggage();
            passenger.getBaggageList().add(baggage);
            baggage.setContent(contents.poll());
        }
    }

    public void getContentsToList(){
        try{
            BufferedReader bufferedReader=new BufferedReader(new FileReader("src/main/java/Data/baggage_content.txt"));
            String line;

            while ((line = bufferedReader.readLine()) != null){
                contents.add(line);
            }
        }catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

}
