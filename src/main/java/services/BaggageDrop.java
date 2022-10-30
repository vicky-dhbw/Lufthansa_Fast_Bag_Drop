package services;

import automatComponents.Database;
import automatComponents.FastBagDrop;
import automatComponents.Position;
import com.google.zxing.WriterException;
import configuration.Configuration;
import flightRelevants.*;
import identityRelevants.BookingClass;
import identityRelevants.LeftBoardingPassPart;
import identityRelevants.RightBoardingPassPart;
import livingComponents.Passenger;
import passengerRelevants.Baggage;
import services.Record;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.*;

public class BaggageDrop {

    Queue<String> contents;
    Queue<String> weights;

    static int sequenceNumber=1;
    public BaggageDrop() throws IOException, WriterException {
        contents=new LinkedList<>();
        weights=new LinkedList<>();
        getContentsToList();
        getBaggageWeightToQueue();
    }
    public void simulateCheckIn(FastBagDrop fastBagDrop, Passenger passenger, Flight flight, Position position) throws IOException {
        //the scanner uses the machine's database to find ticket id of the passenger-> 1 yes found, 0 not found
        // check in possible only if the passengers passport is found in the passengerDatabase in FastBagDrop/Database
        boolean ifFound=fastBagDrop.getFastBagDropSection(position).getPassportScanner().scanPassport(fastBagDrop.getDatabase(), passenger.getPassport());

        //the display shows message whether ticket is found or not
        // if ifFound is true the display gives a positive message, else a negative message
        fastBagDrop.getFastBagDropSection(position).getDisplay().showMessage(ifFound,passenger);

        // if a ticket is found for the passenger a flight seat should be searched according to specification
        if(ifFound){
            //the database uses the static FreeSeatSearcher method to search a non-reserved seat --> see automateComponents/Database -> method searchForFreeSeat
            // find FreeSeatSearcher in AutomateComponents
            Seat seat=fastBagDrop.getDatabase().searchForFreeSeat(flight,passenger);

            // the display must show the information related to passenger through the fast bag drop database

            fastBagDrop.getFastBagDropSection(position).getDisplay().showTicketRelevantInformation(fastBagDrop.getDatabase(), passenger);

            // the display should provide passenger choices to either check in or deny check in
            // by default passenger's decision is set to true
            boolean checkIn=fastBagDrop.getFastBagDropSection(position).getDisplay().offerChoiceAndAcceptCheckInDecision(flight,passenger);

            if(checkIn){
                fastBagDrop.getFastBagDropSection(position).getDisplay().showCheckInMessage();
                // the passenger returns the number of baggage he has, through enterNumberOfBaggage method in Passenger Class
                // the above method returns the int to the method getNumberOfBaggage in Display class
                // the display returns the int here
                int numberOfBaggage=fastBagDrop.getFastBagDropSection(position).getDisplay().getNumberOfBaggage(passenger);

                // here the passengers are assigned baggage and baggage weights
                assignBaggageToPassenger(passenger,numberOfBaggage);
                assignBaggageWeight(passenger);

                // the baggage are sent through the conveyor belt so that the sensors can detect them
                // weight of baggage will be displayed here
                Queue<Baggage> baggageQueue=fastBagDrop.getFastBagDropSection(position).getConveyorBelt().acceptBaggage(passenger.getBaggageList(),fastBagDrop.getFastBagDropSection(position).getSensor(),fastBagDrop.getFastBagDropSection(position).getDisplay());

                // the search of explosives occur over the scan baggage service where the baggage scanner scans baggage
                // go to ScanBaggage class in services package to see what happens when baggage are scanned

                // a copy of the baggage are sent for scanning
                fastBagDrop.getServices().getScanBaggage().scanBaggage(baggageQueue,fastBagDrop,position,passenger.getBoardingPass(),passenger, fastBagDrop.getDatabase());

                // passenger who is a criminal will not get a seat or be issued a boarding pass

                if(!passenger.isCriminal()){ // <----- passengers who are not criminals shall be provided with a seat and be given valid boarding pass
                    String seatId=FlightSeatStatusUpdater.reserveSeat(seat,flight);  // <---- the searched seat is reserved for the passenger by updating the status of the flight seats
                    BoardingPassGenerator.generateBoardingPass(fastBagDrop.getDatabase(), passenger,flight,seatId);

                    passenger.getHandyApp().getHandyAppDatabase().getAppDatabase1().put(passenger.getPassport().getId(),passenger.getBoardingPass());
                    //after successful scan baggage a record of all information to a passenger is gathered as Record Object and saved in the fast bag drop database...nice!!!
                    Record record=new Record(passenger);
                    fastBagDrop.getDatabase().getRecordList().add(record);

                    fastBagDrop.getFastBagDropSection(position).getDocumentPrinter().printBoardingPass(passenger);  //<----- the display displays the boarding pass of the passenger
                    fastBagDrop.getFastBagDropSection(position).getDocumentPrinter().printVoucher(passenger);
                }

            }
            else{
                fastBagDrop.getFastBagDropSection(position).getDisplay().showCancellationMessage();
            }

        }

    }
    public void getContentsToList(){
        try{
            BufferedReader bufferedReader=new BufferedReader(new FileReader(Configuration.INSTANCE.baggageContents));
            String line;

            while ((line = bufferedReader.readLine()) != null){
                contents.add(line);
            }
        }catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    public void getBaggageWeightToQueue(){   //this method get weights from the baggage weight file , then saves it to a queue/  the baggage are then assigned to the passengers
        try{
            BufferedReader bufferedReader=new BufferedReader(new FileReader(Configuration.INSTANCE.baggageWeights));
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
    public void assignBaggageToPassenger(Passenger passenger, int numberOfBaggage){   // this method gets baggage contents from the baggage content file, saves it in a queue and then are set to baggage
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
