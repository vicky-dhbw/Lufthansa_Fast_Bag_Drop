package automatComponents;

import flightRelevants.*;
import identityRelevants.BookingClass;
import identityRelevants.LeftBoardingPassPart;
import identityRelevants.RightBoardingPassPart;
import livingComponents.Passenger;
import passengerRelevants.Baggage;

import java.io.BufferedReader;
import java.io.FileReader;
import java.text.SimpleDateFormat;
import java.util.*;

public class CheckInSimulator {

    static Queue<String> contents;
    static Queue<String> weights;
    public CheckInSimulator(){
        contents=new LinkedList<>();
        weights=new LinkedList<>();
        getContentsToList();
        getBaggageWeightToQueue();
    }
    public void simulateCheckIn(FastBagDrop fastBagDrop, Passenger passenger, Flight flight, Position position){
        //the scanner uses the machine's database to find ticket id of the passenger-> 1 yes found, 0 not found
        boolean ifFound=fastBagDrop.getFastBagDropSection(position).getPassportScanner().scanPassport(fastBagDrop.getDatabase(), passenger.getPassport());

        //the display shows message whether ticket is found or not
        fastBagDrop.getFastBagDropSection(position).getDisplay().showMessage(ifFound,passenger);
        if(ifFound){
            //the database uses the static FreeSeatSearcher method to search a non-reserved seat
            Seat seat=fastBagDrop.getDatabase().searchForFreeSeat(flight,passenger);
            fastBagDrop.getFastBagDropSection(position).getDisplay().showTicketRelevantInformation(fastBagDrop.getDatabase(), passenger);
            boolean checkIn=fastBagDrop.getFastBagDropSection(position).getDisplay().offerChoiceAndAcceptCheckInDecision(flight,passenger);
            if(checkIn){
                fastBagDrop.getFastBagDropSection(position).getDisplay().showCheckInMessage();
                // the passenger returns the number of baggage he has, through enterNumberOfBaggage method in Passenger Class
                // the above method returns the int to the method getNumberOfBaggage in Display class
                // the display returns the int here
                int numberOfBaggage=fastBagDrop.getFastBagDropSection(position).getDisplay().getNumberOfBaggage(passenger);
                assignBaggageToPassenger(passenger,numberOfBaggage);
                assignBaggageWeight(passenger);
                Queue<Baggage> baggageQueue=fastBagDrop.getFastBagDropSection(position).getConveyorBelt().acceptBaggage(passenger.getBaggageList(),fastBagDrop.getFastBagDropSection(position).getSensor(),fastBagDrop.getFastBagDropSection(position).getDisplay());
                // the search of explosives occur over the scan baggage service where the baggage scanner scans baggage
                fastBagDrop.getServices().getScanBaggage().scanBaggage(baggageQueue,fastBagDrop.getFastBagDropSection(position).getBaggageScanner());
                //fastBagDrop.getFastBagDropSection(position).getConveyorBelt().removeBaggage(); //removes baggage from the conveyor belt
                FlightSeatStatusUpdater.reserveSeat(seat,flight);
                generateBoardingPass(fastBagDrop.getDatabase(), passenger,flight);
            }
            else{
                fastBagDrop.getFastBagDropSection(position).getDisplay().showCancellationMessage();
            }

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

    public void getBaggageWeightToQueue(){
        try{
            BufferedReader bufferedReader=new BufferedReader(new FileReader("src/main/java/Data/baggage_weight.txt"));
            String line;
            String[] entries = new String[375];
            while ((line = bufferedReader.readLine()) != null){
                entries=line.split(";");
            }
            for(String weight:entries){
                weights.offer(weight);
            }
        }catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
    public void generateBoardingPass(Database database, Passenger passenger, Flight forFlight){
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
    public void assignBaggageToPassenger(Passenger passenger, int numberOfBaggage){
        for(int i=0;i<numberOfBaggage;i++){
            Baggage baggage=new Baggage();
            passenger.getBaggageList().add(baggage);
            baggage.setContent(contents.poll());
        }
    }
    public void assignBaggageWeight(Passenger passenger){
        for(Baggage baggage:passenger.getBaggageList()){
            if(!weights.isEmpty()){
                baggage.setWeight(Double.parseDouble(weights.poll().replace(",",".")));
            }
        }
    }
}
